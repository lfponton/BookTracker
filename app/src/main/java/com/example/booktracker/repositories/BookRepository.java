package com.example.booktracker.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.booktracker.BuildConfig;
import com.example.booktracker.api.GoogleBookAPI;
import com.example.booktracker.api.ServiceGenerator;
import com.example.booktracker.models.GoogleBookResponse;
import com.example.booktracker.models.Book;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class BookRepository {
    private static BookRepository instance;
    private BooksLiveData allBooks;
    private final MutableLiveData<List<Book>> searchedBooks;
    private DatabaseReference myRef;
    private DatabaseReference booksRef;
    private DatabaseReference bookRef;
    private Book selectedBook;
    private BookLiveData book;


    private static final String API_KEY = BuildConfig.API_KEY;

    private BookRepository() {
        searchedBooks = new MutableLiveData<>();
    }

    public static synchronized BookRepository getInstance() {
        if (instance == null)
            instance = new BookRepository();
        return instance;
    }

    // Firebase
    public void init(String userId) {
        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
        booksRef = myRef.child("books");
        book = new BookLiveData(booksRef);
        allBooks = new BooksLiveData(booksRef);
    }

    public void saveBook(Book book) {
        DatabaseReference newChildRef = booksRef.push();
        String key = newChildRef.getKey();
        booksRef.child(key).setValue(book);
    }

    // End of Firebase

    public BooksLiveData getAllBooks() {
        return allBooks;
    }

    // Google Books API
    public void searchForBook(String bookName) {
        GoogleBookAPI googleBookAPI = ServiceGenerator.getGoogleBookAPI();
        Call<GoogleBookResponse> call = googleBookAPI.searchBooks(API_KEY, bookName);
        call.enqueue(new Callback<GoogleBookResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<GoogleBookResponse> call, Response<GoogleBookResponse> response) {
                if (response.isSuccessful()) {
                    searchedBooks.setValue(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<GoogleBookResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public LiveData<List<Book>> getSearchedBooks() {
        return searchedBooks;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }

    public Book getSelectedBook() {
        return selectedBook;
    }
}
