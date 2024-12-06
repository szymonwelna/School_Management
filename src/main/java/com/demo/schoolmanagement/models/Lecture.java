package com.demo.schoolmanagement.models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Lecture {

    private int lectureId;
    private String lectureName;
    private List<Integer> lectureTeachers = new ArrayList<>();
    private List<LocalTime> lectureHours = new ArrayList<>();

    public Lecture(int lectureId, String lectureName) {
        this.lectureId = lectureId;
        this.lectureName = lectureName;
    }

    //region Zarządzanie id
    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }
    //endregion

    //region Zarządzanie nazwą lekcji/kursu
    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }
    //endregion

    //region Zarządzanie nauczycielami
    public List<Integer> getLectureTeachers() {
        return lectureTeachers;
    }

    public void addLectureTeacher(int teacherId) {
        this.lectureTeachers.add(teacherId);
    }

    public void setLectureTeachers(List<Integer> lectureTeachers) {
        this.lectureTeachers = lectureTeachers;
    }
    //endregion

    //region Zarządzanie godzinami zajęć
    public List<LocalTime> getLectureHours() {
        return lectureHours;
    }

    public void addLectureHour(int hour, int minute) {
        this.lectureHours.add(LocalTime.of(hour, minute));
    }

    public void setLectureHours(List<LocalTime> lectureHours) {
        this.lectureHours = lectureHours;
    }
    //endregion
}
