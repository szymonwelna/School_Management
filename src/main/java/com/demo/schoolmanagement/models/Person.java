package com.demo.schoolmanagement.models;

public class Person {
    private String name;
    private String surname;

    public Person (String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    public void changeName(String name) {
        this.name = name;
    }
    public void changeSurname(String surname) {
        this.surname = surname;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
}
