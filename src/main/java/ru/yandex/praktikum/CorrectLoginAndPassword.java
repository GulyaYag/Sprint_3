package ru.yandex.praktikum;

public class CorrectLoginAndPassword {
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    private String login;
    private String password;


    public CorrectLoginAndPassword(String login, String password) {
        this.login = login;
        this.password = password;
    }


}
