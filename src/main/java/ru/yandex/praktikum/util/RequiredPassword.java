package ru.yandex.praktikum.util;

public class RequiredPassword {
    @Override
    public String toString() {
        return "RequiredPassword{" +
                "password='" + password + '\'' +
                '}';
    }

    private String password;
    public RequiredPassword(String password) {
        this.password = password;
    }
}

