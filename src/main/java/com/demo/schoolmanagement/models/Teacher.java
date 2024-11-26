package com.demo.schoolmanagement.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Teacher extends Person {
    private int classroomNumber;
    private List<Integer> lecturesIds = new ArrayList<>();

    public Teacher(int id, String name, String surname) {
        super(id, name, surname);
    }

    public void addClassroomNumber(int number) {
        classroomNumber = number;
    }

    public int getClassroomNumber() {
        return classroomNumber;
    }

    public void addLecture(Lecture lecture) {
        lecturesIds.add(lecture.getLectureId());
    }

    public void removeLecture(String lectureName) {
        lecturesIds.remove(lectureName);
    }

    public int getTeacherLecturesNum() {
        return lecturesIds.size(); // Zwraca liczbę wykładów
    }
}
