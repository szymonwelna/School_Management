package com.demo.schoolmanagement.models;

import java.util.List;
import java.util.Map;

public class ListsHolder {

    private List<User> users;
    private Map<Integer, Student> students;
    private Map<Integer, Teacher> teachers;
    int lecturesNum;
    private Map<Integer, Lecture> lectures;

    public void addStudentToList(Student student) {
        students.put(student.getId(), student);
    }
    public void addTeacherToList(Teacher teacher) {
        teachers.put(teacher.getId(), teacher);
    }
    public void addLectureToList(Lecture lecture) {
        lectures.put(lecturesNum++, lecture);
    }

    // Loading functions to be added
}
