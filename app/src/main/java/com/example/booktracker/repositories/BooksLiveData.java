package com.example.booktracker.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.booktracker.models.api.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class BooksLiveData extends LiveData<List<Book>> {
    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            List<Book> books = new ArrayList<>();
            for(DataSnapshot dataSnapshot : snapshot.getChildren())
            {
                Book book = dataSnapshot.getValue(Book.class);
                books.add(book);
            }
            setValue(books);
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    DatabaseReference databaseReference;

    public BooksLiveData(DatabaseReference ref) {
        databaseReference = ref;
    }

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(listener);
    }
}
