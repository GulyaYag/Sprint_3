package ru.yandex.praktikum.util;

public class PasswordRequiredField {
    @Override
    public String toString() {
        return "PasswordRequiredField{" +
                "password='" + password + '\'' +
                '}';
    }

    private String password;
    public PasswordRequiredField(String password) {
        this.password = password;
    }
}
