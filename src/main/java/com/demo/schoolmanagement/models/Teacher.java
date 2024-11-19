package com.demo.schoolmanagement.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Teacher extends Person {
    private int classroomNumber;
    private List<Lecture> lectures = new ArrayList<>(); // Inicjalizacja pustej listy

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
        lectures.add(lecture);
    }

    public void removeLecture(String lectureName) {
        Iterator<Lecture> iterator = lectures.iterator();

        while (iterator.hasNext()) {
            Lecture lec = iterator.next();
            if (lec.getLectureName().equals(lectureName)) { // Używamy equals do porównania nazw
                iterator.remove();
                System.out.println("Usunięto lekcję: " + lectureName);
                break;
            }
        }
    }

    public int getLecturesNum() {
        return lectures.size(); // Zwraca liczbę wykładów
    }
}
