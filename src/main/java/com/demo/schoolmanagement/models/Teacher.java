package com.demo.schoolmanagement.models;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person {
    private int teacherId;
    private int teacherSchoolClassId;
    private List<Integer> teacherLecturesId = new ArrayList<>();

    public Teacher(String name, String surname, int teacherId) {
        super(name, surname);
        this.teacherId = teacherId;
    }

    public int getId() {
        return teacherId;
    }
    public void setTeacherId(int Id) {
        this.teacherId = Id;
    }

    //region Zarządzanie klasą nauczyciela
    public int getTeacherSchoolClassId() {
        return teacherSchoolClassId;
    }
    public void setTeacherSchoolClassId(int schoolClassId) {
        this.teacherSchoolClassId = schoolClassId;
    }
    //endregion

    //region Zarządzanie lekcjami nauczyciela
    public List<Integer> getTeacherLectures() {
        return teacherLecturesId;
    }
    public void addTeacherLecture(int lectureId) {
        teacherLecturesId.add(lectureId);
    }
    public void setTeacherLectures(List<Integer> lecturesIds) {
        this.teacherLecturesId = lecturesIds;
    }
    //endregion
}
