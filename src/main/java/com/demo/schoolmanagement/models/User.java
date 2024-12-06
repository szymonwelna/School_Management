package com.demo.schoolmanagement.models;

public class User {
    private int uprawnienia;    // 1 - Użytkownik standardowy 2 - Administrator
    private String login;
    private String password;

    public User(int uprawnienia, String login, String password) {
        this.uprawnienia = uprawnienia;
        this.login = login;
        this.password = password;
    }
    public int getUprawnienia() {
        return uprawnienia;
    }
    public void setUprawnienia(int uprawnienia) {
        this.uprawnienia = uprawnienia;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
