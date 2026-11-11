package com.example.todomanagement;

/** Represents a single to-do task with text and done/undone state. */
public class TodoItem {
    private String text;
    private boolean done;

    public TodoItem(String text, boolean done) {
        this.text = text;
        this.done = done;
    }

    public String getText() { return text; }
    public boolean isDone() { return done; }
    public void setDone(boolean done) { this.done = done; }

    /** Serializes to "text|0" or "text|1" for SharedPreferences storage */
    public String serialize() { return text + "|" + (done ? "1" : "0"); }

    /** Deserializes from "text|0" or "text|1" */
    public static TodoItem deserialize(String s) {
        int lastPipe = s.lastIndexOf('|');
        if (lastPipe < 0) return new TodoItem(s, false);
        String txt = s.substring(0, lastPipe);
        boolean d = "1".equals(s.substring(lastPipe + 1));
        return new TodoItem(txt, d);
    }
}
