package com.demo.schoolmanagement.models;

import java.util.List;
import java.util.Map;
import java.time.LocalTime;

public class Lecture {
    private int lectureId;
    private String lectureName;
    private List<Integer> teachers;
    // Lecture Time
    private Map<Integer, List<LocalTime>> lectureTime;  // Przechowuje dane w postaci: Dzień tygodnia (int), godziny zajęć tego dnia (localtime)

    public Lecture(int lectureId, String lectureName, List<Integer> teachers, Map<Integer, List<LocalTime>> lectureTime) {
        this.lectureId = lectureId;
        this.lectureName = lectureName;
        this.teachers = teachers;
        this.lectureTime = lectureTime;
    }

    public void setLectureId(int lectureId) {this.lectureId = lectureId;}

    public int getLectureId() {return lectureId;}

    public void setLectureName(String newName) {
        lectureName = newName;
    }

    public String getLectureName() {
        return lectureName;
    }

    public Integer getId() {
        return lectureId;
    }
}
