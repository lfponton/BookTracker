package com.example.booktracker.views;

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
import android.widget.TextView;

import com.example.booktracker.adapters.ItemAPIModelAdapter;
import com.example.booktracker.models.Book;
import com.example.booktracker.viewmodels.BooksViewModel;
import com.example.booktracker.R;

public class FinishedFragment extends Fragment {

    RecyclerView recyclerView;
    TextView textView;
    private BooksViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_finished, container, false);
        recyclerView = view.findViewById(R.id.rv);
        textView = view.findViewById(R.id.finishedBooks);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.hasFixedSize();

        viewModel = new ViewModelProvider(this).get(BooksViewModel.class);

        ItemAPIModelAdapter adapter = new ItemAPIModelAdapter();

        viewModel.getFinishedBooks().observe(getViewLifecycleOwner(), books -> {
            adapter.updateBookList(books);
            if (adapter.getItemCount() == 0)
                recyclerView.setVisibility(View.GONE);
            else
                textView.setVisibility(View.GONE);
        });
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(this::bookDetails);
        return view;
    }

    public void bookDetails(Book book) {
        NavController navController = NavHostFragment.findNavController(this);

        viewModel.setSelectedBook(book);
        navController.navigate(
                R.id.action_tabFragment_to_finishedBookFragment,
                null,
                new NavOptions.Builder()
                        .setEnterAnim(android.R.animator.fade_in)
                        .setExitAnim(android.R.animator.fade_out)
                        .build()
        );

    }


}