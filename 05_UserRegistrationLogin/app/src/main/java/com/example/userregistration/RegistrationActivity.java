package com.example.userregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Experiment 5: User Registration
 * Allows new users to register with username and password stored in SQLite (password SHA-256 hashed).
 */
public class RegistrationActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        dbHelper = new DatabaseHelper(this);
    }

    /** Called by Register button via android:onClick="register" */
    public void register(View view) {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 4) {
            Toast.makeText(this, "Password must be at least 4 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean success = dbHelper.registerUser(username, password);
        if (success) {
            Toast.makeText(this, "Registration successful! Please login.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Username already exists. Please choose another.", Toast.LENGTH_SHORT).show();
        }
    }

    /** Navigate to Login screen */
    public void goToLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
