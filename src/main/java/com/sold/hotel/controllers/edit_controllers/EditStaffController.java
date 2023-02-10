package com.sold.hotel.controllers.edit_controllers;

import com.sold.hotel.controllers.main_controllers.SignInController;
import com.sold.hotel.utils.DBConnection;
import com.sold.hotel.utils.Utils;
import com.sold.hotel.utils.Validate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.apache.log4j.Level;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EditStaffController implements Initializable {
    @FXML
    TextField nameTF;
    @FXML
    TextField surnameTF;
    @FXML
    TextField patronymicTF;
    @FXML
    TextField phoneTF;
    @FXML
    DatePicker birthdate;

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
                String surname = surnameTF.getText();
                if (!Validate.isName(surname)) {
                    Utils.alertBox("Surname entered incorrectly", null, "Error");
                    return;
                }
                String patronymic = patronymicTF.getText();
                if (!Validate.isName(patronymic)) {
                    Utils.alertBox("Patronymic entered incorrectly", null, "Error");
                    return;
                }
                if (birthdate.getValue() == null) {
                    Utils.alertBox("Date incorrectly", null, "Error");
                    return;
                }
                Date date = Date.valueOf(birthdate.getValue());
                if (!Validate.isCorrectDate(date)) {
                    Utils.alertBox("Date incorrectly", null, "Error");
                    return;
                }
                String phone = phoneTF.getText();
                if (!Validate.isPhone(phone)) {
                    Utils.alertBox("Phone entered incorrectly", null, "Error");
                    return;
                }
                preparedStatement = connection.prepareStatement("SELECT id_employee FROM staff WHERE staff.id_user LIKE ?");
                preparedStatement.setInt(1, SignInController.idUserStaff);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();

                String request = "exec ChangeDataStaff ?, ?, ?, ?, ?, ?";
                CallableStatement statement = connection.prepareCall(request);
                statement.setInt(1, resultSet.getInt("id_employee"));
                statement.setString(2, name);
                statement.setString(3, surname);
                statement.setString(4, patronymic);
                statement.setDate(5, date);
                statement.setString(6, phone);
                statement.execute();
                Utils.alertBox("Your data has been changed successfully!", null, "Information");
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
