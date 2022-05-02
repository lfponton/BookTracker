package com.example.booktracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.booktracker.viewmodels.BooksViewModel;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {
    private BooksViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        viewModel.init();
        checkIfSignedIn();
        setContentView(R.layout.activity_main);
        MaterialToolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.log_out) {
            signOut();
        }
        return super.onOptionsItemSelected(item);
    }
    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user == null) {
                startLoginActivity();
            }
        });
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }

    public void signOut() {
        viewModel.signOut();
    }

}