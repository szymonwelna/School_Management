package com.demo.schoolmanagement.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Student extends Person {
    private Map<Integer, List<Integer>> grades = new HashMap<>(); // Inicjalizacja pustej mapy gdzie integer reprezentuje id wykładu/przedmiotu szkolnego
    private String classId;
    public Student(int id, String name, String surname) {
        super(id, name, surname);
    }

    public void setClass(Class studentsClass) {
        this.classId = studentsClass.getClassId();
    }
    public String getClassId() {return classId;}

    public void setGrades(Map<Integer, List<Integer>> grades) {
        this.grades = grades;
    }

    public void addGrade(int lectureId, int grade) {
        if (grades.containsKey(lectureId)) {
            grades.get(lectureId).add(grade);
        } else {
            List<Integer> gradeList = new ArrayList<>();
            gradeList.add(grade);
            grades.put(lectureId, gradeList);
        }
    }


    public Map<Integer, List<Integer>> getGrades() {return grades;}
}