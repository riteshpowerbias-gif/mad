package com.example.calculator2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Experiment 3: Calculator
 * Supports +, -, *, /, %(mod), n1^n2 operations.
 * Clicking on a number field clears it (clearTextNum1/clearTextNum2).
 */
public class MainActivity extends AppCompatActivity {

    // Input fields and result display
    public EditText e1, e2;
    TextView t1;
    // Parsed numeric values (using double to support decimals)
    double num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize views
        e1 = findViewById(R.id.num1);
        e2 = findViewById(R.id.num2);
        t1 = findViewById(R.id.result);
    }

    /**
     * Reads and validates both number fields.
     * Returns true if both are non-empty valid numbers.
     */
    public boolean getNumbers() {
        String s1 = e1.getText().toString().trim();
        String s2 = e2.getText().toString().trim();

        if (s1.isEmpty() && s2.isEmpty()) {
            e1.setError("Required");
            e2.setError("Required");
            return false;
        }
        if (s1.isEmpty()) {
            e1.setError("Required");
            return false;
        }
        if (s2.isEmpty()) {
            e2.setError("Required");
            return false;
        }
        try {
            num1 = Double.parseDouble(s1);
            num2 = Double.parseDouble(s2);
        } catch (NumberFormatException ex) {
            t1.setText("Invalid number format");
            return false;
        }
        return true;
    }

    /** Clears Number 1 field when tapped */
    public void clearTextNum1(View v) {
        e1.getText().clear();
    }

    /** Clears Number 2 field when tapped */
    public void clearTextNum2(View v) {
        e2.getText().clear();
    }

    public void doSum(View v) {
        if (getNumbers()) {
            t1.setText(String.valueOf(num1 + num2));
        } else {
            t1.setText("Error: Please enter required values");
        }
    }

    public void doSub(View v) {
        if (getNumbers()) {
            t1.setText(String.valueOf(num1 - num2));
        } else {
            t1.setText("Error: Please enter required values");
        }
    }

    public void doMul(View v) {
        if (getNumbers()) {
            t1.setText(String.valueOf(num1 * num2));
        } else {
            t1.setText("Error: Please enter required values");
        }
    }

    public void doDiv(View v) {
        if (getNumbers()) {
            if (num2 == 0) {
                t1.setText("Error: Division by zero");
            } else {
                t1.setText(String.valueOf(num1 / num2));
            }
        } else {
            t1.setText("Error: Please enter required values");
        }
    }

    public void doMod(View v) {
        if (getNumbers()) {
            if (num2 == 0) {
                t1.setText("Error: Mod by zero");
            } else {
                t1.setText(String.valueOf(num1 % num2));
            }
        } else {
            t1.setText("Error: Please enter required values");
        }
    }

    public void doPow(View v) {
        if (getNumbers()) {
            t1.setText(String.valueOf(Math.pow(num1, num2)));
        } else {
            t1.setText("Error: Please enter required values");
        }
    }
}
