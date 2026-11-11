package com.example.expensemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Experiment 7: Expense Manager
 * Tracks daily expenses and income by category.
 * Shows this week's summary per category, persisted in SQLite.
 */
public class MainActivity extends AppCompatActivity {

    private EditText editTextExpense, editTextIncome;
    private TextView textViewWeeklyExpense, textViewWeeklyIncome, textViewCategorySummary;
    private Spinner categorySpinner;
    private ExpenseDbHelper dbHelper;

    private static final String[] CATEGORIES = {"Food", "Travel", "Rent", "Shopping", "Salary", "Other"};
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextExpense = findViewById(R.id.editTextExpense);
        editTextIncome = findViewById(R.id.editTextIncome);
        textViewWeeklyExpense = findViewById(R.id.textViewWeeklyExpense);
        textViewWeeklyIncome = findViewById(R.id.textViewWeeklyIncome);
        textViewCategorySummary = findViewById(R.id.textViewCategorySummary);
        categorySpinner = findViewById(R.id.categorySpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, CATEGORIES);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        dbHelper = new ExpenseDbHelper(this);
        updateSummary();
    }

    private String today() {
        return sdf.format(Calendar.getInstance().getTime());
    }

    public void addExpense(View view) {
        String amountStr = editTextExpense.getText().toString().trim();
        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Please enter an expense amount", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            double amount = Double.parseDouble(amountStr);
            if (amount <= 0) {
                Toast.makeText(this, "Amount must be positive", Toast.LENGTH_SHORT).show();
                return;
            }
            String category = categorySpinner.getSelectedItem().toString();
            dbHelper.insertTransaction(amount, "expense", category, today());
            editTextExpense.setText("");
            Toast.makeText(this, "Expense added: ₹" + String.format("%.2f", amount), Toast.LENGTH_SHORT).show();
            updateSummary();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid amount. Enter a valid number.", Toast.LENGTH_SHORT).show();
        }
    }

    public void addIncome(View view) {
        String amountStr = editTextIncome.getText().toString().trim();
        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Please enter an income amount", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            double amount = Double.parseDouble(amountStr);
            if (amount <= 0) {
                Toast.makeText(this, "Amount must be positive", Toast.LENGTH_SHORT).show();
                return;
            }
            String category = categorySpinner.getSelectedItem().toString();
            dbHelper.insertTransaction(amount, "income", category, today());
            editTextIncome.setText("");
            Toast.makeText(this, "Income added: ₹" + String.format("%.2f", amount), Toast.LENGTH_SHORT).show();
            updateSummary();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid amount. Enter a valid number.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateSummary() {
        List<Transaction> transactions = dbHelper.getThisWeekTransactions();
        double weeklyExpense = 0, weeklyIncome = 0;
        Map<String, double[]> catMap = new HashMap<>(); // [0]=expense, [1]=income

        for (Transaction t : transactions) {
            if ("expense".equals(t.getType())) {
                weeklyExpense += t.getAmount();
            } else {
                weeklyIncome += t.getAmount();
            }
            double[] arr = catMap.getOrDefault(t.getCategory(), new double[]{0, 0});
            if ("expense".equals(t.getType())) {
                arr[0] += t.getAmount();
            } else {
                arr[1] += t.getAmount();
            }
            catMap.put(t.getCategory(), arr);
        }

        textViewWeeklyExpense.setText("Weekly Expense: ₹" + String.format("%.2f", weeklyExpense));
        textViewWeeklyIncome.setText("Weekly Income: ₹" + String.format("%.2f", weeklyIncome));

        StringBuilder sb = new StringBuilder("Category Breakdown:\n");
        for (Map.Entry<String, double[]> entry : catMap.entrySet()) {
            sb.append(entry.getKey()).append(": ")
              .append("Exp ₹").append(String.format("%.2f", entry.getValue()[0]))
              .append(" | Inc ₹").append(String.format("%.2f", entry.getValue()[1]))
              .append('\n');
        }
        textViewCategorySummary.setText(sb.toString());
    }
}
