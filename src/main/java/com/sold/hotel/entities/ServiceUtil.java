package com.sold.hotel.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ServiceUtil {
    private final SimpleIntegerProperty idBooking;
    private final SimpleIntegerProperty idService;
    private final SimpleStringProperty cost;

    public ServiceUtil(SimpleIntegerProperty idBooking, SimpleIntegerProperty idService, SimpleStringProperty cost) {
        this.idBooking = idBooking;
        this.idService = idService;
        this.cost = cost;
    }
}
