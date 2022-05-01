package com.example.booktracker.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.booktracker.BuildConfig;
import com.example.booktracker.api.GoogleBookAPI;
import com.example.booktracker.api.ServiceGenerator;
import com.example.booktracker.database.BookDao;
import com.example.booktracker.database.BookDatabase;
import com.example.booktracker.models.Book;
import com.example.booktracker.models.api.GoogleBookResponse;
import com.example.booktracker.models.api.ItemAPIModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class BookRepository {
    private static BookRepository instance;
    private final BookDao bookDao;
    private final LiveData<List<Book>> allBooks;
    private final ExecutorService executorService;
    private final MutableLiveData<List<ItemAPIModel>> searchedBook;
    private DatabaseReference myRef;
    private BookLiveData book;

    //private BookRepository() {}

    private static final String API_KEY = BuildConfig.API_KEY;

    private BookRepository(Application application) {
        searchedBook = new MutableLiveData<>();
        BookDatabase database = BookDatabase.getInstance(application);
        bookDao = database.bookDao();
        allBooks = bookDao.getAllBooks();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static synchronized BookRepository getInstance(Application application) {
        if (instance == null)
            instance = new BookRepository(application);

        return instance;
    }
    // Firebase
    public void init(String userId) {
        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
        book = new BookLiveData(myRef);
    }

    public void saveBook(Book book) {
        myRef.setValue(book);
    }

    public BookLiveData getBook() {
        return book;
    }
    // End of Firebase

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    public void insert(Book book) {
        executorService.execute(() -> bookDao.insert(book));
    }

    public void deleteAllBooks() {
        executorService.execute(bookDao::deleteAllBooks);
    }

    public void searchForBook(String bookName) {
        GoogleBookAPI googleBookAPI = ServiceGenerator.getGoogleBookAPI();
        Call<GoogleBookResponse> call = googleBookAPI.searchBooks(API_KEY, bookName);
        call.enqueue(new Callback<GoogleBookResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<GoogleBookResponse> call, Response<GoogleBookResponse> response) {
                if (response.isSuccessful()) {
                    searchedBook.setValue(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<GoogleBookResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public LiveData<List<ItemAPIModel>> getSearchedBook() {
        return searchedBook;
    }
}
