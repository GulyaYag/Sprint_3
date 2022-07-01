package ru.yandex.praktikum.util;

public class CourierCredentials {
    @Override
    public String toString() {
        return "CourierCredentials{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String login;
    public String password;

    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
