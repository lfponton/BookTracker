package com.example.booktracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ItemAPIModelAdapter extends RecyclerView.Adapter<ItemAPIModelAdapter.ViewHolder> {
    List<ItemAPIModel> books;

    public ItemAPIModelAdapter() {
        books = new ArrayList<>();
    }

    public void updateBookList(List<ItemAPIModel> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemAPIModelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.book_item, parent, false);
        return new ItemAPIModelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAPIModelAdapter.ViewHolder holder, int position) {
        holder.book.setText(books.get(position).getVolumeInfo().getTitle());
        holder.author.setText(books.get(position).getVolumeInfo().getAuthors());
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView book;
        TextView author;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            book = itemView.findViewById(R.id.book);
            author = itemView.findViewById(R.id.author);
            icon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
