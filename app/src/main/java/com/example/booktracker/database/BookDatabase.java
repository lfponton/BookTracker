package com.example.booktracker.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.booktracker.models.Book;

@Database(entities = {com.example.booktracker.models.Book.class}, version = 1, exportSchema = false)
public abstract class BookDatabase extends RoomDatabase {

    private static BookDatabase instance;

    public abstract BookDao bookDao();

    public static synchronized BookDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    BookDatabase.class, "books_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
