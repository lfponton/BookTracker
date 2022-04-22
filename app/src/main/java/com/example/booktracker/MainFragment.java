package com.example.booktracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainFragment extends Fragment {

    RecyclerView recyclerView;
    TextView textView;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = view.findViewById(R.id.rv);
        textView = view.findViewById(R.id.textView);
        button = view.findViewById(R.id.button);
        button.setOnClickListener(v -> searchFragment());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.hasFixedSize();
        BooksViewModel viewModel = new ViewModelProvider(this).get(BooksViewModel.class);

        // Delete and insert here for development purposes
        //viewModel.deleteAllBooks();
        //viewModel.insert( new Book("Something1", "Me", 200, "Reading", 1));

        BookAdapter adapter = new BookAdapter();

        viewModel.getAllBooks().observe(getViewLifecycleOwner(), adapter::updateBookList);

        recyclerView.setAdapter(adapter);

        // TODO: This is not working because LiveData is async. I need to wait for the data to load
        /*
        if (adapter.getItemCount() == 0)
            recyclerView.setVisibility(View.GONE);
        else
            textView.setVisibility(View.GONE);
         */
        textView.setVisibility(View.GONE);
        return view;
    }

    public void searchFragment() {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(
                R.id.action_mainFragment_to_searchFragment,
                null,
                new NavOptions.Builder()
                        .setEnterAnim(android.R.animator.fade_in)
                        .setExitAnim(android.R.animator.fade_out)
                        .build()
        );

    }


}