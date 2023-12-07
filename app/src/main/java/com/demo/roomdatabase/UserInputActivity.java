package com.demo.roomdatabase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.demo.roomdatabase.database.UserDatabase;
import com.demo.roomdatabase.database.UserNotes;

import java.util.List;

public class UserInputActivity extends AppCompatActivity {

    EditText userName, userNotes;
    Button submitBtn, updateBtn;

    int userId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);

        userName = findViewById(R.id.edit_text_firstName);
        userNotes = findViewById(R.id.edit_text_notes);
        submitBtn = findViewById(R.id.submit_Btn);
        updateBtn = findViewById(R.id.update_Btn);


        userId = getIntent().getIntExtra("userId", -1);
        if (userId != -1) {
            // Load user data for updating
            UserNotes user = UserDatabase.getDatabase(this).userNotesDao().getUserById(userId);
            // Populate the UI with existing user data
            userName.setText(user.getUserName());
            userNotes.setText(user.getUserNotes());
        }


        if (getIntent().hasExtra("userPosition")) {

            if (getIntent().getIntExtra("userPosition", -1) == -1) {
                submitBtn.setVisibility(View.VISIBLE);
                updateBtn.setVisibility(View.GONE);
            } else {
                submitBtn.setVisibility(View.GONE);
                updateBtn.setVisibility(View.VISIBLE);
            }
        }


        //important code
        if (getIntent().hasExtra("onPosition")) {

            if (getIntent().getIntExtra("onPosition", 0) == 0) {
                updateBtn.setVisibility(View.GONE);
                submitBtn.setVisibility(View.VISIBLE);
            } else {
                updateBtn.setVisibility(View.VISIBLE);
                submitBtn.setVisibility(View.GONE);
            }
        }

        submitBtn.setOnClickListener(v -> {
            saveOrUpdateUser();
        });

        updateBtn.setOnClickListener(v -> {
            saveOrUpdateUser();
        });
    }

    private void saveOrUpdateUser() {
        String username = userName.getText().toString().trim();
        String notes = userNotes.getText().toString().trim();

        if (!username.isEmpty() && !notes.isEmpty()) {
            if (userId == -1) {
                // Insert a new user if the userId is -1
                UserNotes user = new UserNotes(0, username, notes, false);
                UserDatabase.getDatabase(this).userNotesDao().insertUser(user);
                Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show();
            } else {
                // Update the existing user
                boolean isBookmark = UserDatabase.getDatabase(this).userNotesDao().getBookmark(userId);
                UserNotes user = new UserNotes(userId, username, notes, isBookmark);
                UserDatabase.getDatabase(this).userNotesDao().updateUser(user);
                Toast.makeText(this, "User updated successfully", Toast.LENGTH_SHORT).show();
            }
            finish();
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }

    }
}