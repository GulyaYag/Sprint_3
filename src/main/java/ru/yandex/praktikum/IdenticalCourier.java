package ru.yandex.praktikum;

public class IdenticalCourier {
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String login;
    public String password;

    public IdenticalCourier(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
