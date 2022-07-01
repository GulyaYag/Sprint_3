package ru.yandex.praktikum.util;

public class IdenticalCourier {
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "IdenticalCourier{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String login;
    public String password;

    public IdenticalCourier(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
