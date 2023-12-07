package com.demo.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.demo.roomdatabase.database.UserDao;
import com.demo.roomdatabase.database.UserDatabase;
import com.demo.roomdatabase.database.UserTable;
import com.demo.roomdatabase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(() -> {
            SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
            boolean check =  sharedPreferences.getBoolean("flag", false);

            Intent intent;

            if(check){
                intent = new Intent(MainActivity.this, DashboardActivity.class);
            }
            else {
                intent = new Intent(MainActivity.this, SignUpActivity.class);
            }

            startActivity(intent);
            finish();
        }, 3000);

    }
}