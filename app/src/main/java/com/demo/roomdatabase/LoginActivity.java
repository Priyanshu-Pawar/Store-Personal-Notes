package com.demo.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.roomdatabase.database.UserDao;
import com.demo.roomdatabase.database.UserDatabase;
import com.demo.roomdatabase.database.UserTable;
import com.demo.roomdatabase.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {
    Button loginBtn;
    TextView signUp;
    EditText username, password;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.LoginBtn);
        signUp = findViewById(R.id.loginSignUpBtn);
        username = findViewById(R.id.userName);
        password = findViewById(R.id.userPassword);

        userDao = UserDatabase.getDatabase(this).userDao();

        loginBtn.setOnClickListener(v -> {

            SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putBoolean("flag", true);
            editor.apply();

            String userName = username.getText().toString();
            String Password = password.getText().toString();
            if (userDao.login(userName, Password)) {
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Login Successfully!!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid Username and password!!!", Toast.LENGTH_SHORT).show();
            }
        });

        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

    }
}