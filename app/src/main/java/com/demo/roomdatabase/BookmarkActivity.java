package com.demo.roomdatabase;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.roomdatabase.database.UserDatabase;
import com.demo.roomdatabase.database.UserNotes;

import java.util.ArrayList;

public class BookmarkActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        recyclerView = findViewById(R.id.bookmark_recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        UserDatabase.getDatabase(this).userNotesDao().getBookmarkedUserNotes().observe(this, userNotes -> {
            BookmarkAdapter bookmarkAdapter = new BookmarkAdapter(this, (ArrayList<UserNotes>) userNotes);
            recyclerView.setAdapter(bookmarkAdapter);
        });

        recyclerView.setHasFixedSize(true);

    }
}