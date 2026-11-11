package com.example.userregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Experiment 5: User Login
 * Authenticates users against the SQLite database.
 */
public class LoginActivity extends AppCompatActivity {

    EditText loginUsernameEditText, loginPasswordEditText;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginUsernameEditText = findViewById(R.id.loginUsernameEditText);
        loginPasswordEditText = findViewById(R.id.loginPasswordEditText);
        dbHelper = new DatabaseHelper(this);
    }

    /** Called by Login button via android:onClick="login" */
    public void login(View view) {
        String username = loginUsernameEditText.getText().toString().trim();
        String password = loginPasswordEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean valid = dbHelper.checkLogin(username, password);
        if (valid) {
            Toast.makeText(this, "Login successful! Welcome, " + username + "!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid username or password.", Toast.LENGTH_SHORT).show();
            loginPasswordEditText.setText("");
        }
    }

    /** Navigate to Registration screen */
    public void goToRegister(View view) {
        startActivity(new Intent(this, RegistrationActivity.class));
    }
}
