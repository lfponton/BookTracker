package com.example.booktracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.booktracker.R;
import com.example.booktracker.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    List<Book> books;

    public BookAdapter() {
        books = new ArrayList<>();
    }

    public void updateBookList(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.book_item, parent, false);
        return new BookAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
        holder.book.setText(books.get(position).getVolumeInfo().getTitle());
        if (books.get(position).getVolumeInfo().getAuthors() != null) {
                holder.author.setText(books.get(position).getVolumeInfo().getAuthors().get(0));
            }
        else {
            holder.author.setText(R.string.unknown_author);
        }
        String url = books.get(position).getVolumeInfo().getImageLinks().getThumbnail();
        Glide.with(holder.itemView)
                .asBitmap()
                .load(url)
                .placeholder(R.drawable.ic_baseline_book_24)
                .centerCrop()
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public interface OnClickListener {
        void onClick(Book book);
    }

    private OnClickListener listener;

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView book;
        TextView author;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            book = itemView.findViewById(R.id.book);
            author = itemView.findViewById(R.id.author);
            icon = itemView.findViewById(R.id.iv_icon);
            itemView.setOnClickListener(v -> {
                listener.onClick(books.get(this.getAdapterPosition()));
            });
        }
    }
}
