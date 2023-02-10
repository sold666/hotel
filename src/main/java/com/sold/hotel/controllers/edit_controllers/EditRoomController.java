package com.sold.hotel.controllers.edit_controllers;

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

public class EditRoomController implements Initializable {
    @FXML
    TextField idRoomTF;
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
                String idRoom = idRoomTF.getText();
                if (!Validate.isId(idRoom)) {
                    Utils.alertBox("Number of room entered incorrectly", null, "Error");
                    return;
                }
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
                preparedStatement = connection.prepareStatement("SELECT id_room FROM rooms WHERE id_room LIKE ?");
                preparedStatement.setString(1, idRoom);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    Utils.alertBox("Room with this number not found.\n Try another one", null, "Error");
                    return;
                }
                preparedStatement = connection.prepareStatement("SELECT id_classification FROM classification_rooms WHERE classification_name LIKE ?");
                preparedStatement.setString(1, chooseClassification());
                resultSet = preparedStatement.executeQuery();
                resultSet.next();

                String request = "exec ChangeRooms ?, ?, ?, ?";
                CallableStatement statement = connection.prepareCall(request);
                statement.setString(1, idRoom);
                statement.setInt(2, resultSet.getInt("id_classification"));
                statement.setString(3, cost);
                statement.setString(4, numOfSeats);
                statement.execute();
                Utils.alertBox("Room has been successfully changed!", null, "Information");
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
