package com.example.loginapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView infoLabel;

    private int attemptsLeft = 3;
    private static final String VALID_USER = "admin";
    private static final String VALID_PASS = "admin123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        infoLabel = findViewById(R.id.infoLabel);

        Button resetButton = findViewById(R.id.resetButton);
        Button submitButton = findViewById(R.id.submitButton);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameEditText.setText("");
                passwordEditText.setText("");
                infoLabel.setText("Please enter your credentials");
                attemptsLeft = 3;
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Check for empty fields
                if (username.isEmpty() || password.isEmpty()) {
                    infoLabel.setText("Please fill in all fields.");
                    return;
                }

                // Check if account is locked
                if (attemptsLeft <= 0) {
                    infoLabel.setText("Account locked. Too many failed attempts.");
                    return;
                }

                // Validate credentials
                if (username.equals(VALID_USER) && password.equals(VALID_PASS)) {
                    infoLabel.setText("Login successful! Welcome, admin!");
                    attemptsLeft = 3;
                } else {
                    attemptsLeft--;
                    passwordEditText.setText("");
                    if (attemptsLeft > 0) {
                        infoLabel.setText("Invalid credentials. " + attemptsLeft + " attempt(s) remaining.");
                    } else {
                        infoLabel.setText("Account locked. Too many failed attempts.");
                    }
                }
            }
        });
    }
}
