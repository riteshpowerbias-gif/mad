package com.example.userregistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/** Welcome screen shown after successful login. */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        String username = getIntent().getStringExtra("USERNAME");
        TextView welcomeTextView = findViewById(R.id.welcomeTextView);
        if (username != null) {
            welcomeTextView.setText("Welcome, " + username + "!");
        }
    }

    public void logout(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
