package com.example.booktracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView book;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            book = itemView.findViewById(R.id.book);
        }
    }
}
