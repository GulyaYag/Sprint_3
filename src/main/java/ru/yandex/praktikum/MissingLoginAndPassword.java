package ru.yandex.praktikum;

public class MissingLoginAndPassword {
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    private String login;
    private String password;

    public MissingLoginAndPassword(String login, String password) {
        this.login = login;
        this.password = password;
    }


}
