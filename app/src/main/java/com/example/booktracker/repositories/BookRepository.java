package com.example.booktracker.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.booktracker.BuildConfig;
import com.example.booktracker.api.GoogleBookAPI;
import com.example.booktracker.api.ServiceGenerator;
import com.example.booktracker.models.GoogleBookResponse;
import com.example.booktracker.models.Book;
import com.example.booktracker.repositories.livedata.FinishedBooksLiveData;
import com.example.booktracker.repositories.livedata.ReadingBooksLiveData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class BookRepository {
    private static BookRepository instance;
    private ReadingBooksLiveData readingBooks;
    private FinishedBooksLiveData finishedBooks;
    private final MutableLiveData<List<Book>> searchedBooks;
    private DatabaseReference myRef;
    private DatabaseReference readingBooksRef;
    private DatabaseReference finishedBooksRef;
    private Book selectedBook;

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
        readingBooksRef = myRef.child("books").child("reading");
        finishedBooksRef = myRef.child("books").child("finished");
        readingBooks = new ReadingBooksLiveData(readingBooksRef);
        finishedBooks = new FinishedBooksLiveData(finishedBooksRef);
    }

    public void saveBook(Book book) {
        DatabaseReference newChildRef = readingBooksRef.push();
        String key = newChildRef.getKey();
        readingBooksRef.child(key).setValue(book);
    }

    public void markAsFinished(Book book) {
        Query booToRemove = readingBooksRef.orderByChild("id").equalTo(book.getId());
        booToRemove.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    dataSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference newChildRef = finishedBooksRef.push();
        String key = newChildRef.getKey();
        finishedBooksRef.child(key).setValue(book);
    }

    public ReadingBooksLiveData getReadingBooks() {
        return readingBooks;
    }

    public FinishedBooksLiveData getFinishedBooks() {
        return finishedBooks;
    }
    // End of Firebase

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
