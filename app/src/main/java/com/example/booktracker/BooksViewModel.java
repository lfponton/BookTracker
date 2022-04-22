package com.example.booktracker;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BooksViewModel extends AndroidViewModel {

    private final BookRepository repository;

    public BooksViewModel(Application application) {
        super(application);
        repository = BookRepository.getInstance(application);
    }

    public LiveData<List<Book>> getAllBooks() {
        return repository.getAllBooks();
    }

    public void insert(final Book book) {
        repository.insert(book);
    }

    public void deleteAllBooks() {
        repository.deleteAllBooks();
    }

    public LiveData<List<ItemAPIModel>> getSearchedBook() {
        return repository.getSearchedBook();
    }

    public void searchForBook(String s) {
        repository.searchForBook(s);
    }

}
