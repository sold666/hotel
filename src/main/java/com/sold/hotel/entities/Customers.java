package com.sold.hotel.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Customers {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty login;
    private final SimpleStringProperty password;
    private final SimpleStringProperty name;
    private final SimpleStringProperty surname;
    private final SimpleStringProperty pantronymic;
    private final SimpleStringProperty passport;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty isAvailable;

    public Customers(SimpleIntegerProperty id, SimpleStringProperty login, SimpleStringProperty password,
                     SimpleStringProperty name, SimpleStringProperty surname, SimpleStringProperty pantronymic,
                     SimpleStringProperty passport, SimpleStringProperty phone, SimpleStringProperty isAvailable) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.pantronymic = pantronymic;
        this.passport = passport;
        this.phone = phone;
        this.isAvailable = isAvailable;
    }

    public static int getAge(Date birthday) {
        return Period.between(convert(String.valueOf(birthday)), LocalDate.now()).getYears();
    }

    private static LocalDate convert(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
