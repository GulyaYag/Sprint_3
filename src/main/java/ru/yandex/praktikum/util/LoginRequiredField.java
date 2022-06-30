package ru.yandex.praktikum.util;

public class LoginRequiredField {
    @Override
    public String toString() {
        return "LoginRequiredField{" +
                "login='" + login + '\'' +
                '}';
    }

    private String login;
    public LoginRequiredField(String login) {
        this.login = login;
    }


}