package com.example.booktracker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {
    @Insert
    void insert(Book book);

    @Delete
    void delete(Book book);

    @Query("DELETE FROM books")
    void deleteAllBooks();

    @Query("SELECT * FROM books ORDER BY id DESC")
    LiveData<List<Book>> getAllBooks();
}
