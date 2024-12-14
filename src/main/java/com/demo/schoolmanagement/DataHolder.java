package com.demo.schoolmanagement;

import com.demo.schoolmanagement.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataHolder {

    private static final DataHolder instance = new DataHolder();

    public static DataHolder getInstance() {
        return instance;
    }

    //region Zarządzanie użytkownikami
    private HashMap<Integer, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public void removeUser(int userId) {
        users.remove(userId);
    }

    public HashMap<Integer, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<Integer, User> users) {
        this.users = users;
    }
    //endregion

    //region Zarządzanie uczniami
    private int lastStudentId = 0;
    private HashMap<Integer, Student> students =  new HashMap<>();


    public int getLastStudentId() {
        return lastStudentId;
    }

    public void setLastStudentId(int lastStudentId) {
        this.lastStudentId = lastStudentId;
    }

    public void addStudent(Student student) {
        students.put(student.getId(), student);
        lastStudentId++;
    }

    public void removeStudent(int studentId) {
        students.remove(studentId);
    }

    public HashMap<Integer, Student> getStudents() {
        return students;
    }

    public void setStudents(HashMap<Integer, Student> students) {
        this.students = students;
    }
    //endregion

    //region Zarządzanie klasami
    private List<SchoolClass> schoolClasses = new ArrayList<>();

    public void addSchoolClass(SchoolClass schoolClass) {
        schoolClasses.add(schoolClass);
    }

    public SchoolClass getSchoolClass(int schoolClassId) {
        return schoolClasses.get(schoolClassId);
    }

    public List<SchoolClass> getSchoolClasses() {
        return schoolClasses;
    }

    public void setSchoolClasses(List<SchoolClass> schoolClasses) {
        this.schoolClasses = schoolClasses;
    }
    //endregion

    //region Zarządzanie nauczycielami
    private int lastTeacherId = 0;
    private HashMap<Integer, Teacher> teachers =  new HashMap<>();

    public int getLastTeacherId() {
        return lastTeacherId;
    }

    public void setLastTeacherId(int lastTeacherId) {
        this.lastTeacherId = lastTeacherId;
    }

    public HashMap<Integer, Teacher> getTeachers() {
        return teachers;
    }
    public void addTeacher(Teacher teacher) {
        teachers.put(lastTeacherId, teacher);
        lastTeacherId++;
    }
    public void setTeachers(HashMap<Integer, Teacher> teachers) {
        this.teachers = teachers;
    }
    //endregion

    //region Zarządzanie lekcjami
    private HashMap<Integer, Lecture> lectures = new HashMap<>();
    private int lastLectureId = 0;

    public int getLastLectureId() {
        return lastLectureId;
    }
    public void setLastLectureId(int lastLectureId) {
        this.lastLectureId = lastLectureId;
    }
    public HashMap<Integer, Lecture> getLectures() {
        return lectures;
    }
    public void addLecture(Lecture lecture) {
        lectures.put(lastLectureId, lecture);
        lastLectureId++;
    }
    public void setLectures(HashMap<Integer, Lecture> lectures) {
        this.lectures = lectures;
    }
    //endregion
}
