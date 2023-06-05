package com.sold.hotel.controllers.add_controllers;

import com.sold.hotel.utils.DBConnection;
import com.sold.hotel.utils.Utils;
import com.sold.hotel.utils.Validate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.apache.log4j.Level;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AddRoomController implements Initializable {
    @FXML
    ComboBox<String> comboBox;
    @FXML
    TextField costTF;
    @FXML
    TextField numberOfSeatsTF;

    private Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DBConnection.getConnection();
        ObservableList<String> classifications = FXCollections.observableArrayList("Luxury", "Economy", "For two", "Presidential");
        comboBox.setItems(classifications);
    }

    public void acceptAction() {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            if (connection != null) {
                String cost = costTF.getText();
                if (!Validate.isCost(cost)) {
                    Utils.alertBox("Cost entered incorrectly\n" +
                            """
                                    Example:\s
                                    100\s
                                    0.99\s
                                    1234567890
                                    """, null, "Error");
                    return;
                }
                String numOfSeats = numberOfSeatsTF.getText();
                if (!Validate.isNumberOfSeats(numOfSeats)) {
                    Utils.alertBox("Number of seats entered incorrectly", null, "Error");
                    return;
                }
                preparedStatement = connection.prepareStatement("SELECT id_classification FROM classification_rooms WHERE classification_name LIKE ?");
                preparedStatement.setString(1, chooseClassification());
                resultSet = preparedStatement.executeQuery();
                resultSet.next();

                String request = "exec AddRoom ?, ?, ?";
                CallableStatement statement = connection.prepareCall(request);
                statement.setInt(1, resultSet.getInt("id_classification"));
                statement.setString(2, cost);
                statement.setString(3, numOfSeats);
                statement.execute();
                Utils.alertBox("Room add successfully!", null, "Information");
            }
        } catch (Exception exception) {
            Utils.logger.log(Level.ERROR, exception.getMessage());
        }
    }

    public String chooseClassification() {
        return comboBox.getValue();
    }

    public void backAction(ActionEvent event) {
        try {
            Utils.back(event);
        } catch (IOException exception) {
            Utils.logger.log(Level.ERROR, exception.getMessage());
        }
    }
}
