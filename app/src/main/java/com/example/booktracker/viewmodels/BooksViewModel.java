package com.example.booktracker.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.booktracker.models.api.ItemAPIModel;
import com.example.booktracker.repositories.BookRepository;
import com.example.booktracker.repositories.BooksLiveData;
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

    public void setSelectedBook(ItemAPIModel selectedBook) {
        bookRepository.setSelectedBook(selectedBook);
    }

    public ItemAPIModel getSelectedBook() {
        return bookRepository.getSelectedBook();
    }

    public void init() {
        String userId = userRepository.getCurrentUser().getValue().getUid();
        bookRepository.init(userId);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    public void saveBook(ItemAPIModel book) {
        bookRepository.saveBook(book);
    }


    public void signOut() {
        userRepository.signOut();
    }

    public BooksLiveData getAllBooks() {
        return bookRepository.getAllBooks();
    }

    public LiveData<List<ItemAPIModel>> getSearchedBook() {
        return bookRepository.getSearchedBooks();
    }

    public void searchForBook(String s) {
        bookRepository.searchForBook(s);
    }

}
