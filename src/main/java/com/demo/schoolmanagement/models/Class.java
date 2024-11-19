package com.demo.schoolmanagement.models;

import java.util.List;
import java.util.Iterator;

public class Class {

    private int ClassNum;
    private char ClassChar;

    private int ClassSize;
    private Teacher ClassTeacher;
    private List<Student> students;
    private List<Lecture> lectures;

    public Class(int num, char ClassChar, Teacher ClassTeacher) {
        ClassNum = num;
        this.ClassChar = ClassChar;
        this.ClassTeacher = ClassTeacher;
    }


    public void changeTeacher(Teacher newTeacher) {
        ClassTeacher = newTeacher;
    }
    public Teacher getTeacher() {
        return ClassTeacher;
    }


    // Students management
    public void addStudent(Student newStudent) {
        students.add(newStudent);
        ClassSize++;
    }

    public void removeStudent(int id) {
        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {
            Student obj = iterator.next();
            if (obj.getId() == id) {
                iterator.remove();
                ClassSize--;
                System.out.println("Usunięto ucznia o ID: " + id);
                break;
            }
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    // Lectures management
    public void addLecture(Lecture lecture) {
        lectures.add(lecture);
    }

    public void removeLecture(String lectureName) {
        Iterator<Lecture> iterator = lectures.iterator();

        while (iterator.hasNext()) {
            Lecture obj = iterator.next();
            if (obj.getLectureName() == lectureName) {
                iterator.remove();
                System.out.println("Usunięto lekcję: " + lectureName);
                break;
            }
        }
    }

}

