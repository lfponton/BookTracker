package com.example.booktracker.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.booktracker.models.Book;
import com.example.booktracker.models.api.ItemAPIModel;
import com.example.booktracker.repositories.BookRepository;
import com.example.booktracker.repositories.UserRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class BooksViewModel extends AndroidViewModel {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public BooksViewModel(Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
        bookRepository = BookRepository.getInstance(application);
    }

    public void init() {
        String userId = userRepository.getCurrentUser().getValue().getUid();
        bookRepository.init(userId);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    public void saveBook(Book book) {
        bookRepository.saveBook(book);
    }

    public LiveData<Book> getBook() {
        return bookRepository.getBook();
    }

    public void signOut() {
        userRepository.signOut();
    }

    public LiveData<List<Book>> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public void insert(final Book book) {
        bookRepository.insert(book);
    }

    public void deleteAllBooks() {
        bookRepository.deleteAllBooks();
    }

    public LiveData<List<ItemAPIModel>> getSearchedBook() {
        return bookRepository.getSearchedBook();
    }

    public void searchForBook(String s) {
        bookRepository.searchForBook(s);
    }

}
