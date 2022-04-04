package com.example.booktracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.hasFixedSize();
        BooksViewModel viewModel = new ViewModelProvider(this).get(BooksViewModel.class);
/*
        List<Book> books = new ArrayList<>();

        books.add(new Book("Something1", "Me", 200, "Reading", 1));
        books.add(new Book("Something1", "Me", 200, "Reading", 1));
        books.add(new Book("Something1", "Me", 200, "Reading", 1));
        books.add(new Book("Something1", "Me", 200, "Reading", 1));


 */
        BookAdapter adapter = new BookAdapter();

        // TODO: include get books to display them in the recycler view
        if (viewModel.getAllBooks() != null)
            viewModel.getAllBooks().observe(getViewLifecycleOwner(), adapter::updateBookList);

        recyclerView.setAdapter(adapter);


        return view;
    }
}