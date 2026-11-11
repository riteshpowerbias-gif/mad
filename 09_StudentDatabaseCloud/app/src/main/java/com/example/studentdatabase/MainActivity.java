package com.example.studentdatabase;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Experiment 9: Student Database (Firebase Cloud)
 * Saves and retrieves student records (name, roll number) using Firebase Realtime Database.
 * If Firebase is not configured (no google-services.json), shows a Toast and works in offline mode.
 */
public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextRollNo;
    private Button buttonSave;
    private ListView listViewStudents;
    private DatabaseReference databaseReference;
    private ValueEventListener studentListener;
    private ArrayAdapter<String> adapter;
    private List<String> studentList;
    private boolean firebaseAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextRollNo = findViewById(R.id.editTextRollNo);
        buttonSave = findViewById(R.id.buttonSave);
        listViewStudents = findViewById(R.id.listViewStudents);

        studentList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        listViewStudents.setAdapter(adapter);

        // Try to initialize Firebase; handle case where google-services.json is absent
        try {
            databaseReference = FirebaseDatabase.getInstance().getReference("students");
            firebaseAvailable = true;
            loadStudents();
        } catch (Exception e) {
            Toast.makeText(this,
                "Firebase not configured. See app/FIREBASE_SETUP.md for setup instructions.",
                Toast.LENGTH_LONG).show();
        }

        buttonSave.setOnClickListener(view -> saveStudent());
    }

    private void saveStudent() {
        String name = editTextName.getText().toString().trim();
        String rollNo = editTextRollNo.getText().toString().trim();

        if (name.isEmpty() || rollNo.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!firebaseAvailable) {
            Toast.makeText(this, "Firebase not configured. Cannot save.", Toast.LENGTH_SHORT).show();
            return;
        }

        String studentId = databaseReference.push().getKey();
        if (studentId == null) {
            Toast.makeText(this, "Failed to generate student ID", Toast.LENGTH_SHORT).show();
            return;
        }
        Student student = new Student(studentId, name, rollNo);
        databaseReference.child(studentId).setValue(student)
            .addOnSuccessListener(aVoid -> {
                Toast.makeText(this, "Student saved successfully", Toast.LENGTH_SHORT).show();
                editTextName.setText("");
                editTextRollNo.setText("");
            })
            .addOnFailureListener(e ->
                Toast.makeText(this, "Failed to save: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void loadStudents() {
        // ADDED: PDF only saved, never retrieved
        studentListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentList.clear();
                for (DataSnapshot child : snapshot.getChildren()) {
                    Student s = child.getValue(Student.class);
                    if (s != null) {
                        studentList.add(s.getName() + " - " + s.getRollNo());
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to load: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        databaseReference.addValueEventListener(studentListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // ADDED: remove listener to prevent memory leak
        if (firebaseAvailable && databaseReference != null && studentListener != null) {
            databaseReference.removeEventListener(studentListener);
        }
    }
}
