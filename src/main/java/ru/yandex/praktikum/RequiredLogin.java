package ru.yandex.praktikum;

public class RequiredLogin {

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


