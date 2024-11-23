package com.demo.schoolmanagement.models;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ListsHolder {

    // Statyczna instancja singletona
    private static final ListsHolder instance = new ListsHolder();

    // Prywatne pola
    private int lastId = 0;
    private List<User> users;
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

    // Metoda dodawania studenta
    public void addStudentToList(Student student) {
        students.put(student.getId(), student);
        lastId++;
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

    // Getter do pobierania ostatniego ID
    public int getLastId() {
        return lastId;
    }

    public Map<Integer, Student> getStudents() {
        return students;
    }

    // Pozostałe metody (np. do ładowania danych, uzyskiwania list) można dodać tutaj
}
