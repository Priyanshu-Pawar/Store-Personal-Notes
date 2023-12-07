package com.demo.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.roomdatabase.database.UserDao;
import com.demo.roomdatabase.database.UserDatabase;
import com.demo.roomdatabase.database.UserTable;
import com.demo.roomdatabase.databinding.ActivityMainBinding;

public class SignUpActivity extends AppCompatActivity {

    UserDao userDao;
    public static boolean isAllowed = false;
    EditText emailET, passwordET;
    TextView loginText;
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailET = findViewById(R.id.userEmail);
        passwordET = findViewById(R.id.userPassword);
        loginText = findViewById(R.id.loginMainBtn);
        signUpBtn = findViewById(R.id.signUpBtn);


        userDao = UserDatabase.getDatabase(this).userDao();

        emailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String userName = s.toString();
                if (userDao.is_taken(userName)) {
                    isAllowed = false;
                    Toast.makeText(SignUpActivity.this, "Already Taken!!!", Toast.LENGTH_SHORT).show();
                } else {
                    isAllowed = true;
                }
            }
        });

        signUpBtn.setOnClickListener(v -> {

            if (isAllowed) {
                UserTable userTable = new UserTable(0, emailET.getText().toString(), passwordET.getText().toString());
                userDao.insertUser(userTable);

                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean("flag", true);
                editor.apply();

                Intent newIntent = new Intent(SignUpActivity.this, DashboardActivity.class);
                startActivity(newIntent);
                Toast.makeText(this, "SignUp Successfully!!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(SignUpActivity.this, "Username Already Taken!!!", Toast.LENGTH_SHORT).show();
            }
        });


        loginText.setOnClickListener(v -> {
            Intent newIntent2 = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(newIntent2);
        });

    }
}