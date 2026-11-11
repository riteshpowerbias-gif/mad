package com.example.studentdatabase;

/**
 * Model class for a student record in Firebase Realtime Database.
 * No-argument constructor is REQUIRED by Firebase for deserialization.
 */
public class Student {
    private String id;
    private String name;
    private String rollNo;

    // Required no-arg constructor for Firebase
    public Student() {}

    public Student(String id, String name, String rollNo) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
}
