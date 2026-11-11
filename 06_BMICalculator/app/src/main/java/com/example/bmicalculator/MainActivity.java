package com.example.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;  // FIXED: was android.support.v7

/**
 * Experiment 6: BMI Calculator
 * Formula: BMI = weight(kg) / (height(m) * height(m))
 * Categories: Underweight (<18.5), Normal (18.5-24.9), Overweight (25-29.9), Obese (>=30)
 */
public class MainActivity extends AppCompatActivity {

    private EditText editTextWeight;
    private EditText editTextHeight;
    private TextView textViewResult;
    private TextView textViewCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        textViewResult = findViewById(R.id.textViewResult);
        textViewCategory = findViewById(R.id.textViewCategory);

        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = editTextWeight.getText().toString().trim();
        String heightStr = editTextHeight.getText().toString().trim();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            textViewResult.setText("Please enter both weight and height.");
            textViewCategory.setText("");
            return;
        }

        try {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr);

            // FIXED: guard against height = 0
            if (height <= 0) {
                textViewResult.setText("Height must be greater than 0.");
                textViewCategory.setText("");
                return;
            }
            if (weight <= 0) {
                textViewResult.setText("Weight must be greater than 0.");
                textViewCategory.setText("");
                return;
            }

            float bmi = weight / (height * height);
            String result = String.format("Your BMI: %.2f", bmi);
            textViewResult.setText(result);

            // Determine BMI category (added per requirements)
            String category;
            int categoryColor;
            if (bmi < 18.5f) {
                category = "Underweight";
                categoryColor = 0xFF2196F3; // blue
            } else if (bmi < 25.0f) {
                category = "Normal weight";
                categoryColor = 0xFF4CAF50; // green
            } else if (bmi < 30.0f) {
                category = "Overweight";
                categoryColor = 0xFFFF9800; // orange
            } else {
                category = "Obese";
                categoryColor = 0xFFF44336; // red
            }
            textViewCategory.setText("Category: " + category);
            textViewCategory.setTextColor(categoryColor);

        } catch (NumberFormatException e) {
            textViewResult.setText("Invalid input. Please enter valid numbers.");
            textViewCategory.setText("");
        }
    }
}
