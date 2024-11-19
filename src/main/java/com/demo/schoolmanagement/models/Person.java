package com.demo.schoolmanagement.models;

public class Person {
    private int id;
    private String name;
    private String surname;

    public Person(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
    public void updateId(int newId) {
        id = newId;
    }

    public void changeName(String newName) {
        name = newName;
    }

    public void changeSurname(String newSurname) {
        surname = newSurname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
