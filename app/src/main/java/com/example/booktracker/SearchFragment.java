package com.example.booktracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchFragment extends Fragment {

    RecyclerView recyclerView;
    EditText editText;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.rvSearch);
        editText = view.findViewById(R.id.bookName);
        button = view.findViewById(R.id.searchButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.hasFixedSize();
        BooksViewModel viewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        button.setOnClickListener(v -> searchForBook(viewModel));
        ItemAPIModelAdapter adapter = new ItemAPIModelAdapter();
        recyclerView.setAdapter(adapter);
        viewModel.getSearchedBook().observe(getViewLifecycleOwner(), adapter::updateBookList);

        return view;

    }

    public void searchForBook(BooksViewModel viewModel) {
        String bookName = editText.getText().toString();
        if (!bookName.equals(" "))
            viewModel.searchForBook(bookName);
    }
}