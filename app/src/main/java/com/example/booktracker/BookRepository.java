package com.example.booktracker;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookRepository {
    private static BookRepository instance;
    private final BookDao bookDao;
    private final LiveData<List<Book>> allBooks;
    private final ExecutorService executorService;

    private BookRepository(Application application) {
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
}
