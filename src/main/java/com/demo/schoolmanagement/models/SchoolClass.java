package com.demo.schoolmanagement.models;

import java.util.ArrayList;
import java.util.List;

public class SchoolClass {

    private String schoolClassId;
    private int schoolClassTeacher;
    private List<Integer> students;
    private List<Integer> lectures;

    public SchoolClass(String schoolClassId, int schoolClassTeacher) {
        this.schoolClassId = schoolClassId;
        this.schoolClassTeacher = schoolClassTeacher;
        this.students = new ArrayList<>();
        this.lectures = new ArrayList<>();
    }

    //region Zarządzanie Id klasy
    public String getSchoolClassId() {
        return schoolClassId;
    }
    public void setSchoolClassId(String schoolClassId) {
        this.schoolClassId = schoolClassId;
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
