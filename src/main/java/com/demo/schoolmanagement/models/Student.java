package com.demo.schoolmanagement.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends Person {
    private int studentId;
    private int schoolClassId;
    private ArrayList<Integer> courses = new ArrayList<>();
    private HashMap<Integer, ArrayList<Integer>> grades = new HashMap<>();  // Integer represents id of the course, List contains grades related to this course

    // Constructor
    public Student(String name, String surname, int id) {
        super(name, surname);
        studentId = id;
    }

    //region Id management
    public void changeId(int id) {
        studentId = id;
    }
    public int getId() {
        return studentId;
    }
    //endregion

    //region SchoolClass management
    public void changeSchoolClassId(int id) {
        schoolClassId = id;
    }
    public int getSchoolClassId() {
        return schoolClassId;
    }
    //endregion

    //region Courses management
    public void addCourse(int courseId) {
        courses.add(courseId);
    }
    public void removeCourse(int courseId) {
        courses.remove(courseId);
    }
    public ArrayList<Integer> getCourses() {
        return courses;
    }
    //endregion

    //region Grades Management
    public void addGrade(int courseId, int grade) {
        if (!grades.containsKey(courseId)) {
            grades.put(courseId, new ArrayList<>());
            grades.get(courseId).add(grade);
        } else {
            grades.get(courseId).add(grade);
        }
    }

    public void removeGrade(int courseId, int grade) {
        if (grades.containsKey(courseId)) {
            grades.get(courseId).remove(grade);
        } else {
            System.out.println("Course " + courseId + " not found");
        }
    }

    public HashMap<Integer, ArrayList<Integer>> getGrades() {
        return grades;
    }

    // For later loading data to Student
//    public void setGrades(HashMap<Integer, ArrayList<Integer>> grades) {
//        this.grades = grades;
//    }
    //endregion
}
