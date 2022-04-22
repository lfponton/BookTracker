package com.example.booktracker;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

    private String googleAPIKey;
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
        Call<GoogleBookResponse> call = googleBookAPI.searchBooks(googleAPIKey, bookName);
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
