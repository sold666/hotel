package com.sold.hotel.entities;

import javafx.beans.property.SimpleStringProperty;

public class Booking {
    private final SimpleStringProperty checkIn;
    private final SimpleStringProperty checkOut;
    private final SimpleStringProperty total;

    public Booking(String checkIn, String checkOut, String total) {
        this.checkIn = new SimpleStringProperty(checkIn);
        this.checkOut = new SimpleStringProperty(checkOut);
        this.total = new SimpleStringProperty(total);
    }

    public String getCheckIn() {
        return checkIn.get();
    }


    public void setCheckIn(String checkIn) {
        this.checkIn.set(checkIn);
    }

    public String getCheckOut() {
        return checkOut.get();
    }

    public void setCheckOut(String checkOut) {
        this.checkOut.set(checkOut);
    }

    public String getTotal() {
        return total.get();
    }

    public void setTotal(String total) {
        this.total.set(total);
    }
}
