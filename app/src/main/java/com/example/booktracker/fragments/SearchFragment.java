package com.example.booktracker.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.booktracker.models.Book;
import com.example.booktracker.viewmodels.BooksViewModel;
import com.example.booktracker.R;
import com.example.booktracker.adapters.BookAdapter;

public class SearchFragment extends Fragment {

    private BooksViewModel viewModel;
    private RecyclerView recyclerView;
    private EditText editText;
    private Button button;

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
        viewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        button.setOnClickListener(v -> searchForBook(viewModel));
        BookAdapter adapter = new BookAdapter();
        recyclerView.setAdapter(adapter);
        viewModel.getSearchedBook().observe(getViewLifecycleOwner(), adapter::updateBookList);
        adapter.setOnClickListener(this::bookDetails);
        return view;

    }

    public void searchForBook(BooksViewModel viewModel) {
        String bookName = editText.getText().toString();
        if (!bookName.equals(" "))
            viewModel.searchForBook(bookName);
    }

    public void bookDetails(Book book) {
        NavController navController = NavHostFragment.findNavController(this);

        viewModel.setSelectedBook(book);
        navController.navigate(
                R.id.action_searchFragment_to_searchedBookDetailsFragment,
                null,
                new NavOptions.Builder()
                        .setEnterAnim(android.R.animator.fade_in)
                        .setExitAnim(android.R.animator.fade_out)
                        .build()
        );

    }
}