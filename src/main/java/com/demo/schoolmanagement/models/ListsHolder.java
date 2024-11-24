package com.demo.schoolmanagement.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ListsHolder {

    // Statyczna instancja singletona
    private static final ListsHolder instance = new ListsHolder();

    // Prywatne pola
    private int lastId = 0;
    private List<User> users = new ArrayList<>();
    private Map<Integer, Student> students = new ConcurrentHashMap<>();
    private Map<Integer, Teacher> teachers = new ConcurrentHashMap<>();
    private int lecturesNum = 0;
    private Map<Integer, Lecture> lectures = new ConcurrentHashMap<>();

    // Prywatny konstruktor, by zapobiec tworzeniu innych instancji
    private ListsHolder() {}

    // Publiczny dostęp do singletona
    public static ListsHolder getInstance() {
        return instance;
    }

    // Getter do pobierania ostatniego ID
    public int getLastId() {
        return lastId;
    }


    // Metody zarządzania użytkownikami
    public void addNewUser(User user) {
        users.add(user);
    }

    public List<User> getUsers(List<User> users) {
        return users;
    }


    // Metody zarządzania uczniami
    public void addStudentToList(Student student) {
        students.put(student.getId(), student);
        lastId++;
    }

    public Student getStudentById(int id) {
        return students.get(id);
    }

    public Map<Integer, Student> getStudents() {
        return students;
    }


    // Metoda dodawania nauczyciela
    public void addTeacherToList(Teacher teacher) {
        teachers.put(teacher.getId(), teacher);
        lastId++;
    }


    // Metoda dodawania wykładu
    public void addLectureToList(Lecture lecture) {
        lectures.put(lecturesNum++, lecture);
        lastId++;
    }




    // Pozostałe metody (np. do ładowania danych, uzyskiwania list) można dodać tutaj
}
