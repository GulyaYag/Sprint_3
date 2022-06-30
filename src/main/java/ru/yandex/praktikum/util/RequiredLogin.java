package ru.yandex.praktikum.util;

public class RequiredLogin {
    @Override
    public String toString() {
        return "RequiredLogin{" +
                "login='" + login + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    private String login;
    public RequiredLogin(String login) {
        this.login = login;
    }

}


