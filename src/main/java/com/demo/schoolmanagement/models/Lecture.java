package com.demo.schoolmanagement.models;

import java.util.List;
import java.util.Map;
import java.time.LocalTime;

public class Lecture {
    private String lectureName;
    private List<Teacher> teachers;
    // Lecture Time
    private Map<Integer, List<LocalTime>> lectureTime;  // Przechowuje dane w postaci: Dzień tygodnia (int), godziny zajęć tego dnia (localtime)

    public Lecture(String lectureName) {
        this.lectureName = lectureName;
    }

    public void setLectureName(String newName) {
        lectureName = newName;
    }

    public String getLectureName() {
        return lectureName;
    }

}
