package com.sold.hotel.entities;

import javafx.beans.property.SimpleStringProperty;

public class Services {
    private final SimpleStringProperty serviceName;
    private final SimpleStringProperty cost;
    private final SimpleStringProperty description;

    public Services(String serviceName, String cost, String description) {
        this.serviceName = new SimpleStringProperty(serviceName);
        this.cost = new SimpleStringProperty(cost);
        this.description = new SimpleStringProperty(description);
    }

    public String getServiceName() {
        return serviceName.get();
    }

    public void setServiceName(String serviceName) {
        this.serviceName.set(serviceName);
    }

    public String getCost() {
        return cost.get();
    }

    public void setCost(String cost) {
        this.cost.set(cost);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
