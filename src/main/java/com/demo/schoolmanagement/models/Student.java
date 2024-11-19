package com.demo.schoolmanagement.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Student extends Person {
    private Map<Lecture, List<Integer>> grades = new HashMap<>(); // Inicjalizacja pustej mapy
    private Class studentsClass;

    public Student(int id, String name, String surname) {
        super(id, name, surname);
    }

    public void setGroup(Class studentsClass) {
        this.studentsClass = studentsClass;
    }

    // Metody do zarządzania ocenami
    public void addGrade(Lecture lecture, int grade) {
        grades.computeIfAbsent(lecture, k -> new ArrayList<>()).add(grade);
    }

    public List<Integer> getGrades(Lecture lecture) {
        return grades.getOrDefault(lecture, new ArrayList<>());
    }
}