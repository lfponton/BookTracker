package com.example.booktracker.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.booktracker.models.Book;
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
        bookRepository = BookRepository.getInstance();
    }

    public void setSelectedBook(Book selectedBook) {
        bookRepository.setSelectedBook(selectedBook);
    }

    public Book getSelectedBook() {
        return bookRepository.getSelectedBook();
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


    public void signOut() {
        userRepository.signOut();
    }

    public LiveData<List<Book>> getReadingBooks() {
        return bookRepository.getReadingBooks();
    }

    public LiveData<List<Book>> getSearchedBook() {
        return bookRepository.getSearchedBooks();
    }

    public void searchForBook(String s) {
        bookRepository.searchForBook(s);
    }

    public LiveData<List<Book>> getFinishedBooks() {
        return bookRepository.getFinishedBooks();
    }

    public void markAsFinished(Book book) {
        bookRepository.markAsFinished(book);
    }
}
