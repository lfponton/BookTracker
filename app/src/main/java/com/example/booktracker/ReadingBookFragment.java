package com.example.booktracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.booktracker.R;
import com.example.booktracker.models.Book;
import com.example.booktracker.viewmodels.BooksViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ReadingBookFragment extends Fragment {

    TextView bookTitle;
    TextView bookAuthor;
    ImageView cover;
    Button button;
    BooksViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reading_book, container, false);
        bookTitle = view.findViewById(R.id.readingBookTitle);
        bookAuthor = view.findViewById(R.id.readingBookAuthor);
        cover = view.findViewById(R.id.readingBookDetailsCover);
        viewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        Book book = viewModel.getSelectedBook();
        bookTitle.setText(book.getVolumeInfo().getTitle());
        bookAuthor.setText(book.getVolumeInfo().getAuthors().get(0));
        button = view.findViewById(R.id.markAsFinished);
        button.setOnClickListener(v -> markAsFinished(book));
        String url = book.getVolumeInfo().getImageLinks().getThumbnail();
        Glide.with(this)
                .asBitmap()
                .load(url)
                .placeholder(R.drawable.ic_baseline_book_24)
                .centerCrop()
                .into(cover);
        return view;

    }

    public void markAsFinished(Book book) {
        viewModel.markAsFinished(book);
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(
                R.id.action_readingBookFragment_to_tabFragment,
                null,
                new NavOptions.Builder()
                        .setEnterAnim(android.R.animator.fade_in)
                        .setExitAnim(android.R.animator.fade_out)
                        .build()
        );
    }
}