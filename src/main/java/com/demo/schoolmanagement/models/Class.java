package com.demo.schoolmanagement.models;

import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

public class Class {
    private String classId;

    private int ClassSize;
    private int classTeacherId;
    private List<Integer> studentsIds;
    private List<Integer> lecturesIds;

    public Class(String classId, int classTeacher) {
        this.classId = classId;
        this.classTeacherId = classTeacher;
    }

    public String getClassId() {return classId;}

    //region Teachers management
    public void changeTeacher(Teacher newTeacher) {
        classTeacherId = newTeacher.getId();
    }
    public int getTeacher() {
        return classTeacherId;
    }
    public void changeTeacherById(int newTeacherId) {
        classTeacherId = newTeacherId;
    }
    //endregion

    //region Students management
    public void addStudent(Student newStudent) {
        studentsIds.add(newStudent.getId());
        ClassSize++;
    }
    public void addStudentById(int id) {
        studentsIds.add(id);
    }

    public void removeStudent(int id) {
        studentsIds.remove(id);
    }

    public List<Integer> getStudents() {
        return studentsIds;
    }
    //endregion

    //region Lectures management
    public void addLecture(Lecture lecture) {
        lecturesIds.add(lecture.getId());
    }

    public void addLectureById(int id) {lecturesIds.add(id);}

    public void removeLecture(int lectureId) {
        lecturesIds.remove(lectureId);
    }
    //endregion
}

