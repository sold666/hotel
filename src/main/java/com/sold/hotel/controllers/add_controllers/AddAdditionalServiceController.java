package com.sold.hotel.controllers.add_controllers;

import com.sold.hotel.utils.DBConnection;
import com.sold.hotel.utils.Utils;
import com.sold.hotel.utils.Validate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.log4j.Level;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ResourceBundle;

public class AddAdditionalServiceController implements Initializable {
    @FXML
    TextField nameTF;
    @FXML
    TextField costTF;
    @FXML
    TextArea descriptionTA;

    private Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DBConnection.getConnection();
    }

    public void acceptAction() {
        try {
            if (connection != null) {
                String name = nameTF.getText();
                if (!Validate.isName(name)) {
                    Utils.alertBox("Name entered incorrectly\nEnter only letters", null, "Error");
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
                String description = descriptionTA.getText();
                String request = "exec AddServices ?, ?, ?";
                CallableStatement statement = connection.prepareCall(request);
                statement.setString(1, name);
                statement.setString(2, cost);
                statement.setString(3, description);
                statement.execute();
                Utils.alertBox("Additional service add successfully!", null, "Information");
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
