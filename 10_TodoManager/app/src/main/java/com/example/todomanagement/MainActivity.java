package com.example.todomanagement;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Experiment 10: To-Do Manager
 * - Add tasks via AlertDialog (FIXED: PDF had onClick body outside the method)
 * - Tap a task to toggle done/strikethrough
 * - Long-press a task to delete it
 * - Tasks persisted in SharedPreferences (survive app restart)
 */
public class MainActivity extends AppCompatActivity {

    private ListView todoListView;
    private Button addTaskButton;
    private List<TodoItem> tasks;
    private TodoAdapter tasksAdapter;
    private SharedPreferences prefs;
    private static final String PREFS_NAME = "todo_prefs";
    private static final String KEY_TASKS = "tasks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoListView = findViewById(R.id.todoListView);
        addTaskButton = findViewById(R.id.addTaskButton);

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        tasks = loadTasks();
        tasksAdapter = new TodoAdapter(this, tasks);
        todoListView.setAdapter(tasksAdapter);

        // FIXED: onClick body moved inside the listener (PDF had it outside onCreate)
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddTaskDialog();
            }
        });

        // Tap to toggle done/undone
        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TodoItem item = tasks.get(position);
                item.setDone(!item.isDone());
                tasksAdapter.notifyDataSetChanged();
                saveTasks();
            }
        });

        // Long-press to delete
        todoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete Task")
                        .setMessage("Delete \"" + tasks.get(pos).getText() + "\"?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tasks.remove(pos);
                                tasksAdapter.notifyDataSetChanged();
                                saveTasks();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
                return true;
            }
        });
    }

    /** Shows an AlertDialog with an EditText to enter the new task. */
    private void showAddTaskDialog() {
        final EditText input = new EditText(this);
        input.setHint("Enter task");
        input.setSingleLine(true);
        new AlertDialog.Builder(this)
                .setTitle("New Task")
                .setView(input)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String taskText = input.getText().toString().trim();
                        if (!taskText.isEmpty()) {
                            tasks.add(new TodoItem(taskText, false));
                            tasksAdapter.notifyDataSetChanged();
                            saveTasks();
                        } else {
                            Toast.makeText(MainActivity.this, "Task cannot be empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    /** Saves tasks to SharedPreferences as a Set of serialized strings. */
    private void saveTasks() {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < tasks.size(); i++) {
            // Prefix with index to maintain order (SharedPreferences Set is unordered)
            set.add(i + ":" + tasks.get(i).serialize());
        }
        prefs.edit().putStringSet(KEY_TASKS, set).apply();
    }

    /** Loads tasks from SharedPreferences. */
    private List<TodoItem> loadTasks() {
        Set<String> set = prefs.getStringSet(KEY_TASKS, new HashSet<>());
        List<String> sorted = new ArrayList<>(set);
        // Sort by the index prefix to restore order
        java.util.Collections.sort(sorted, (a, b) -> {
            int ia = indexOf(a);
            int ib = indexOf(b);
            return Integer.compare(ia, ib);
        });
        List<TodoItem> result = new ArrayList<>();
        for (String s : sorted) {
            int colon = s.indexOf(':');
            if (colon >= 0 && colon + 1 < s.length()) {
                result.add(TodoItem.deserialize(s.substring(colon + 1)));
            }
        }
        return result;
    }

    private int indexOf(String s) {
        int colon = s.indexOf(':');
        if (colon < 0) return Integer.MAX_VALUE;
        try { return Integer.parseInt(s.substring(0, colon)); } catch (NumberFormatException e) { return Integer.MAX_VALUE; }
    }
}
