package com.sold.hotel.controllers.edit_controllers;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class EditAdditionalServiceController implements Initializable {
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
        PreparedStatement preparedStatement;
        ResultSet resultSet;
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
                preparedStatement = connection.prepareStatement("SELECT id_service FROM services WHERE services.service_name LIKE ?");
                preparedStatement.setString(1, name);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    Utils.alertBox("Additional Service with this name not found.\n Try another one", null, "Error");
                    return;
                }
                String request = "exec ChangeServices ?, ?, ?, ?";
                CallableStatement statement = connection.prepareCall(request);
                statement.setInt(1, resultSet.getInt("id_service"));
                statement.setString(2, name);
                statement.setString(3, cost);
                statement.setString(4, description);
                statement.execute();
                Utils.alertBox("Additional service has been successfully changed!", null, "Information");
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
