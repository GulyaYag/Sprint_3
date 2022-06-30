package ru.yandex.praktikum.util;

public class SameCourierLogin {
    @Override
    public String toString() {
        return "SameCourierLogin{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }
    private String login;
    private String password;
    public SameCourierLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
