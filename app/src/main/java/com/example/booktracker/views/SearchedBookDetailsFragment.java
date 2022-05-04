package com.example.booktracker.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.booktracker.R;
import com.example.booktracker.adapters.ItemAPIModelAdapter;
import com.example.booktracker.models.api.ItemAPIModel;
import com.example.booktracker.viewmodels.BooksViewModel;

public class SearchedBookDetailsFragment extends Fragment {

    TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_searched_book_details, container, false);
        text = view.findViewById(R.id.bookDetailsName);
        BooksViewModel viewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        ItemAPIModel book = viewModel.getSelectedBook();
        text.setText(book.getVolumeInfo().getTitle());
        return view;
    }

}