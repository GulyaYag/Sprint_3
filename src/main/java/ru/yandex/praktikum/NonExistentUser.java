package ru.yandex.praktikum;

public class NonExistentUser {
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
