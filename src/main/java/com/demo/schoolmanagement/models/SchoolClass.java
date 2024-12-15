package com.demo.schoolmanagement.models;

import java.util.ArrayList;
import java.util.List;

public class SchoolClass {

    private int schoolClassId;
    private String schoolClassName;
    private int schoolClassTeacher;
    private List<Integer> students;
    private List<Integer> lectures;

    public SchoolClass(int schoolClassId, String schoolClassName, int schoolClassTeacher) {
        this.schoolClassId = schoolClassId;
        this.schoolClassName = schoolClassName;
        this.schoolClassTeacher = schoolClassTeacher;
        this.students = new ArrayList<>();
        this.lectures = new ArrayList<>();
    }

    //region Zarządzanie Id klasy
    public int getSchoolClassId() {
        return schoolClassId;
    }
    public void setSchoolClassId(int schoolClassId) {
        this.schoolClassId = schoolClassId;
    }
    //endregion

    //region Zarządzanie nazwą klasy
    public String getSchoolClassName() {
        return schoolClassName;
    }
    public void setSchoolClassName(String schoolClassId) {
        this.schoolClassName = schoolClassId;
    }
    //endregion

    //region Zarządzanie wychowawcą klasy
    public int getSchoolClassTeacher() {
        return schoolClassTeacher;
    }
    public void setSchoolClassTeacher(int schoolClassTeacher) {
        this.schoolClassTeacher = schoolClassTeacher;
    }
    //endregion

    //region Zarządzanie uczniami
    public void addStudent(int studentId) {
        students.add(studentId);
    }
    public List<Integer> getStudents() {
        return students;
    }
    public void setStudents(List<Integer> students) {
        this.students = students;
    }
    //endregion

    //region Zarządzaie lekcjami
    public void addLecture(int lectureId) {
        lectures.add(lectureId);
    }
    public List<Integer> getLectures() {
        return lectures;
    }
    public void setLectures(List<Integer> lectures) {
        this.lectures = lectures;
    }
    //endregion
}
