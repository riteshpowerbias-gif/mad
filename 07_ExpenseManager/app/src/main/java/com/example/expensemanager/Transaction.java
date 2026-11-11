package com.example.expensemanager;

/** Model for an expense/income transaction. */
public class Transaction {
    private final double amount;
    private final String type;
    private final String category;
    private final String date;

    public Transaction(double amount, String type, String category, String date) {
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
    }

    public double getAmount() { return amount; }
    public String getType() { return type; }
    public String getCategory() { return category; }
    public String getDate() { return date; }
}
