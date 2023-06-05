package com.sold.hotel.entities;

import javafx.beans.property.SimpleStringProperty;

public class ClassificationRooms {
    private final SimpleStringProperty classificationName;

    public ClassificationRooms(String classificationName) {
        this.classificationName = new SimpleStringProperty(classificationName);
    }

    public String getClassificationName() {
        return classificationName.get();
    }

    public void setClassificationName(String classificationName) {
        this.classificationName.set(classificationName);
    }
}
