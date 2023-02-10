package com.sold.hotel.controllers.main_controllers;

import com.sold.hotel.utils.DBConnection;
import com.sold.hotel.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.apache.log4j.Level;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class OrderAdditionalServiceController implements Initializable {
    @FXML
    ComboBox<String> comboBox;
    @FXML
    Label priceLB;
    @FXML
    Label descriptionLB;

    private Connection connection;
    private ObservableList<String> services;
    private ObservableList<String> costs;
    private ObservableList<String> descriptions;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DBConnection.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            if (connection != null) {
                services = FXCollections.observableArrayList();
                costs = FXCollections.observableArrayList();
                descriptions = FXCollections.observableArrayList();
                preparedStatement = connection.prepareStatement("SELECT service_name, cost, description FROM services");
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    services.add(resultSet.getString("service_name"));
                    costs.add(String.valueOf(resultSet.getBigDecimal("cost")));
                    descriptions.add(resultSet.getString("description"));
                }
                comboBox.setItems(services);
            }
        } catch (Exception exception) {
            Utils.logger.log(Level.ERROR, exception.getMessage());
        }
    }

    public void acceptAction() {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            if (connection != null) {
                if (comboBox.getValue() == null) {
                    Utils.alertBox("You have to choose a service", null, "Information");
                } else {
                    preparedStatement = connection.prepareStatement("SELECT id_customer FROM customers WHERE customers.id_user LIKE ?");
                    preparedStatement.setInt(1, SignInController.idUserCustomer);
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();

                    preparedStatement = connection.prepareStatement("SELECT id_booking FROM booking WHERE id_customer LIKE ?");
                    preparedStatement.setInt(1, resultSet.getInt("id_customer"));
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int IDbooking = resultSet.getInt("id_booking");
                        preparedStatement = connection.prepareStatement("SELECT id_service FROM services WHERE services.service_name LIKE ?");
                        preparedStatement.setString(1, comboBox.getValue());
                        resultSet = preparedStatement.executeQuery();
                        resultSet.next();
                        int IDservice = resultSet.getInt("id_service");

                        String request = "exec BuyService ?, ?";
                        CallableStatement statement = connection.prepareCall(request);
                        statement.setInt(1, IDbooking);
                        statement.setInt(2, IDservice);
                        statement.execute();
                        Utils.alertBox("You have successfully ordered an additional service!", null, "Information");
                    } else {
                        Utils.alertBox("First you need to book a room", null, "Information");
                    }
                }
            }
        } catch (Exception exception) {
            Utils.logger.log(Level.ERROR, exception.getMessage());
        }
    }

    public void chooseService() {
        for (int i = 0; i < services.size(); i++) {
            if (comboBox.getValue().equals(services.get(i))) {
                priceLB.setText(costs.get(i));
                descriptionLB.setText(descriptions.get(i));
            }
        }
    }

    public void backAction(ActionEvent event) {
        try {
            Utils.back(event);
        } catch (IOException exception) {
            Utils.logger.log(Level.ERROR, exception.getMessage());
        }
    }
}
