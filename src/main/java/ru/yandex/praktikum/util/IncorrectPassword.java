package ru.yandex.praktikum.util;

public class IncorrectPassword {
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "IncorrectPassword{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private String login;
    private String password;

    public IncorrectPassword(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
