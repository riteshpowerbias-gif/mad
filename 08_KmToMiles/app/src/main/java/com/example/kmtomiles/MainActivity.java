package com.example.kmtomiles;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Experiment 8: Unit Converter
 * Converts km to miles (factor 0.621371) and kg to pounds (factor 2.20462).
 * Uses a Spinner to select the conversion type.
 */
public class MainActivity extends AppCompatActivity {

    private EditText editTextValue;
    private Button buttonConvert;
    private TextView textViewResult;
    private Spinner conversionSpinner;

    // 0 = km->miles, 1 = kg->pounds
    private int conversionMode = 0;

    private static final String[] CONVERSIONS = {"Kilometers to Miles", "Kilograms to Pounds"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextValue = findViewById(R.id.editTextValue);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);
        conversionSpinner = findViewById(R.id.conversionSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, CONVERSIONS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conversionSpinner.setAdapter(adapter);

        conversionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                conversionMode = position;
                if (position == 0) {
                    editTextValue.setHint("Enter kilometers");
                    buttonConvert.setText("Convert to Miles");
                } else {
                    editTextValue.setHint("Enter kilograms");
                    buttonConvert.setText("Convert to Pounds");
                }
                textViewResult.setText("Result will be shown here");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performConversion();
            }
        });
    }

    /**
     * Performs the conversion based on selected mode.
     * km→miles factor 0.621371, kg→pounds factor 2.20462.
     * Output formatted to 4 decimal places.
     */
    private void performConversion() {
        String input = editTextValue.getText().toString().trim();
        if (input.isEmpty()) {
            textViewResult.setText("Please enter a value.");
            return;
        }
        try {
            double value = Double.parseDouble(input);
            if (value < 0) {
                textViewResult.setText("Please enter a non‑negative value.");
                return;
            }
            if (conversionMode == 0) {
                double miles = value * 0.621371;
                textViewResult.setText(String.format("%.4f km = %.4f miles", value, miles));
            } else {
                double pounds = value * 2.20462;
                textViewResult.setText(String.format("%.4f kg = %.4f pounds", value, pounds));
            }
        } catch (NumberFormatException e) {
            textViewResult.setText("Invalid input. Please enter a valid number.");
        }
    }
}
