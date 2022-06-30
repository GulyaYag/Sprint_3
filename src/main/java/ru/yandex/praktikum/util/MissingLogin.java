package ru.yandex.praktikum.util;

public class MissingLogin {
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "MissingLogin{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private String login;
    private String password;
    public MissingLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }
}

