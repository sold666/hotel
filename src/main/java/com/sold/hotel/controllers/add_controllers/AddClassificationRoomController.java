package com.sold.hotel.controllers.add_controllers;

import com.sold.hotel.utils.DBConnection;
import com.sold.hotel.utils.Utils;
import com.sold.hotel.utils.Validate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.apache.log4j.Level;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ResourceBundle;

public class AddClassificationRoomController implements Initializable {
    @FXML
    TextField nameClassificationTF;

    private Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DBConnection.getConnection();
    }

    public void acceptAction() {
        try {
            if (connection != null) {
                String name = nameClassificationTF.getText();
                if (!Validate.isName(name)) {
                    Utils.alertBox("Name entered incorrectly\nEnter only letters", null, "Error");
                    return;
                }
                String request = "exec AddRoomClassification ?";
                CallableStatement statement = connection.prepareCall(request);
                statement.setString(1, name);
                statement.execute();
                Utils.alertBox("Room classification add successfully!", null, "Information");
            }
        } catch (Exception exception) {
            Utils.logger.log(Level.ERROR, exception.getMessage());
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
