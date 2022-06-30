package ru.yandex.praktikum.util;

public class MissingPassword {
        public String getLogin() {
            return login;
        }
        public void setLogin(String login) {
            this.login = login;
        }

    @Override
    public String toString() {
        return "MissingPassword{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private String login;
        private String password;
        public MissingPassword(String login, String password) {
            this.login = login;
            this.password = password;
        }
    }

