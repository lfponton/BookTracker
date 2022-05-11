package com.example.booktracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.booktracker.models.Book;
import com.example.booktracker.viewmodels.BooksViewModel;

public class FinishedBookFragment extends Fragment {

    TextView bookTitle;
    TextView bookAuthor;
    ImageView cover;
    BooksViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_finished_book, container, false);
        bookTitle = view.findViewById(R.id.finishedBookTitle);
        bookAuthor = view.findViewById(R.id.finishedBookAuthor);
        cover = view.findViewById(R.id.finishedBookDetailsCover);
        viewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        Book book = viewModel.getSelectedBook();
        bookTitle.setText(book.getVolumeInfo().getTitle());
        bookAuthor.setText(book.getVolumeInfo().getAuthors().get(0));
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