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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.booktracker.models.api.ItemAPIModel;
import com.example.booktracker.viewmodels.BooksViewModel;
import com.example.booktracker.R;
import com.example.booktracker.adapters.ItemAPIModelAdapter;

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
        adapter.setOnClickListener(this::bookDetails);
        return view;

    }

    public void searchForBook(BooksViewModel viewModel) {
        String bookName = editText.getText().toString();
        if (!bookName.equals(" "))
            viewModel.searchForBook(bookName);
    }

    public void bookDetails(ItemAPIModel book) {
        NavController navController = NavHostFragment.findNavController(this);

        /*SearchFragmentDirections.ActionSearchFragmentToSearchedBookDetailsFragment action =
                SearchFragmentDirections.actionSearchFragmentToSearchedBookDetailsFragment();
        action.setBook(book);
        navController.navigate(
                action,
                new NavOptions.Builder()
                        .setEnterAnim(android.R.animator.fade_in)
                        .setExitAnim(android.R.animator.fade_out)
                        .build()
        );


         */
    }
}