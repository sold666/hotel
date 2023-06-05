package com.sold.hotel.controllers.edit_controllers;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ChangeStatusStaffController implements Initializable {
    private final Connection connection;
    @FXML
    TextField loginStaffTF;
    @FXML
    TextField statusTF;

    public ChangeStatusStaffController() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void acceptAction() {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            if (connection != null) {
                String loginStaff = loginStaffTF.getText();
                String status = statusTF.getText();
                if (!Validate.isStatus(status)) {
                    Utils.alertBox("Status entered incorrectly\n" +
                            """
                                       Enter only 1 or 0:\s
                                       1 - active\s
                                       0 - non active
                                    """, null, "Error");
                    return;
                }
                preparedStatement = connection.prepareStatement("SELECT id_user FROM users WHERE users.login LIKE ?");
                preparedStatement.setString(1, loginStaff);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    preparedStatement = connection.prepareStatement("SELECT id_employee FROM staff WHERE staff.id_user LIKE ?");
                    preparedStatement.setInt(1, resultSet.getInt("id_user"));
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    int IDStaff = resultSet.getInt("id_employee");

                    String request = "exec ChangeStaffStatus ?, ?";
                    CallableStatement statement = connection.prepareCall(request);
                    statement.setInt(1, IDStaff);
                    statement.setString(2, status);
                    statement.execute();
                } else {
                    Utils.alertBox("There is no user with this login", null, "Error");
                    return;
                }
                Utils.alertBox("Status changed successfully!", null, "Information");
            }
        } catch (Exception exception) {
            Utils.logger.log(Level.ERROR, null, exception);
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
