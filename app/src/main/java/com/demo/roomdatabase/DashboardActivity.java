package com.demo.roomdatabase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.roomdatabase.database.UserDatabase;
import com.demo.roomdatabase.database.UserNotes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    ImageView logoutBtn, bookmarkBtn;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;

    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        RecyclerIsEmptyOrNot();


        logoutBtn = findViewById(R.id.logoutBtn);
        bookmarkBtn = findViewById(R.id.bookmarkBtn);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recycler_view);
        cardView = findViewById(R.id.cardView);

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, UserInputActivity.class);
            intent.putExtra("onPosition", 0);
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        UserDatabase.getDatabase(this).userNotesDao().readAllData().observe(this, userNotes -> {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, (ArrayList<UserNotes>) userNotes);
            recyclerView.setAdapter(recyclerAdapter);

        });


        logoutBtn.setOnClickListener(v -> {

            SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putBoolean("flag", false);
            editor.apply();


            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Logout Successfully!!", Toast.LENGTH_SHORT).show();
            finish();
        });

        bookmarkBtn.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, BookmarkActivity.class);
            startActivity(intent);
        });

    }

    private void RecyclerIsEmptyOrNot() {

        UserDatabase.getDatabase(this).userNotesDao().readAllData().observe(this, new Observer<List<UserNotes>>() {
            @Override
            public void onChanged(List<UserNotes> userNotes) {
                if (userNotes.isEmpty()) {
                    cardView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    cardView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}