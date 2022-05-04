package com.example.booktracker.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.booktracker.R;
import com.example.booktracker.adapters.ItemAPIModelAdapter;
import com.example.booktracker.models.api.ItemAPIModel;
import com.example.booktracker.viewmodels.BooksViewModel;

public class SearchedBookDetailsFragment extends Fragment {

    TextView bookTitle;
    TextView bookAuthor;
    ImageView cover;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_searched_book_details, container, false);
        bookTitle = view.findViewById(R.id.bookDetailsName);
        bookAuthor = view.findViewById(R.id.bookDetailsAuthor);
        cover = view.findViewById(R.id.bookDetailsCover);
        BooksViewModel viewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        ItemAPIModel book = viewModel.getSelectedBook();
        bookTitle.setText(book.getVolumeInfo().getTitle());
        bookAuthor.setText(book.getVolumeInfo().getAuthors());

        String url = book.getVolumeInfo().getImageLinks().getThumbnail();
        Glide.with(this)
                .asBitmap()
                .load(url)
                .placeholder(R.drawable.ic_baseline_book_24)
                .centerCrop()
                .into(cover);
        return view;
    }

}