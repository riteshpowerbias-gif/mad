package com.example.expensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/** SQLite helper for expense/income transactions. */
public class ExpenseDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "expenses.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE = "transactions";

    public ExpenseDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "amount REAL NOT NULL, " +
                "type TEXT NOT NULL, " +
                "category TEXT NOT NULL, " +
                "date TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void insertTransaction(double amount, String type, String category, String date) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("amount", amount);
        cv.put("type", type);
        cv.put("category", category);
        cv.put("date", date);
        db.insert(TABLE, null, cv);
        db.close();
    }

    public List<Transaction> getThisWeekTransactions() {
        List<Transaction> list = new ArrayList<>();
        // Get Monday of current week
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String weekStart = sdf.format(cal.getTime());

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE, null, "date >= ?", new String[]{weekStart},
                null, null, "date DESC");
        while (cursor.moveToNext()) {
            double amount = cursor.getDouble(cursor.getColumnIndexOrThrow("amount"));
            String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
            String category = cursor.getString(cursor.getColumnIndexOrThrow("category"));
            String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
            list.add(new Transaction(amount, type, category, date));
        }
        cursor.close();
        db.close();
        return list;
    }
}
