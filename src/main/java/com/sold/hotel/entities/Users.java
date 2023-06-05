package com.sold.hotel.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Users {
    private final SimpleIntegerProperty idUser;
    private final SimpleStringProperty login;
    private final SimpleStringProperty password;

    public Users(SimpleIntegerProperty idUser, SimpleStringProperty login, SimpleStringProperty password) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
    }
}
