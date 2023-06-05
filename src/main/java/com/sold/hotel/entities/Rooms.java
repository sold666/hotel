package com.sold.hotel.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Rooms {

    private final SimpleStringProperty cost;
    private final SimpleIntegerProperty numberOfSeats;

    public Rooms(String cost, int numberOfSeats) {
        this.cost = new SimpleStringProperty(cost);
        this.numberOfSeats = new SimpleIntegerProperty(numberOfSeats);
    }

    public String getCost() {
        return cost.get();
    }

    public void setCost(String cost) {
        this.cost.set(cost);
    }

    public int getNumberOfSeats() {
        return numberOfSeats.get();
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats.set(numberOfSeats);
    }
}
