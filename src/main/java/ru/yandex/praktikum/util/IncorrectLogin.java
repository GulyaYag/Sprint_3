package ru.yandex.praktikum.util;

public class IncorrectLogin {
    public IncorrectLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "IncorrectLogin{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private String login;
    private String password;
}
