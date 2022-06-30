package ru.yandex.praktikum.util;

public class NonExistentUser {
    @Override
    public String toString() {
        return "NonExistentUser{" +
                "login='" + login + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    private String login;
    private String user;
    public NonExistentUser(String login, String user) {
        this.login = login;
        this.user = user;
    }
}
