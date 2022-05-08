package com.example.booktracker.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.booktracker.models.api.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ItemAPIModelsLiveData extends LiveData<Book> {
    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
           for(DataSnapshot dataSnapshot: snapshot.getChildren())
           {
               Book book = dataSnapshot.getValue(Book.class);
           }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    DatabaseReference databaseReference;

    public ItemAPIModelsLiveData(DatabaseReference ref) {
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
