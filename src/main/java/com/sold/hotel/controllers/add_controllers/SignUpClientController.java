package com.sold.hotel.controllers.add_controllers;

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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.util.ResourceBundle;

public class SignUpClientController implements Initializable {
    @FXML
    TextField loginTF;
    @FXML
    TextField passwordTF;
    @FXML
    TextField nameTF;
    @FXML
    TextField surnameTF;
    @FXML
    TextField patronymicTF;
    @FXML
    DatePicker birthdate;
    @FXML
    TextField phoneTF;
    @FXML
    TextField passportTF;
    @FXML
    TextField statusTF;

    private Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DBConnection.getConnection();
    }

    public void acceptAction() {
        try {
            if (connection != null) {
                String login = loginTF.getText();
                if (!Validate.isLogin(login)) {
                    Utils.alertBox("Login must be at least 3 characters " +
                            "(It can contain numbers as well symbols: ._-)", null, "Error");
                    return;
                }
                String password = passwordTF.getText();
                if (!Validate.isPassword(password)) {
                    Utils.alertBox("Password must be at least 5 characters " +
                            "(It must be contain numbers, symbols: @#$%^&+=, capital letters)", null, "Error");
                    return;
                }
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
                if (!Validate.isCorrectDate(date.toLocalDate())) {
                    Utils.alertBox("Date incorrectly", null, "Error");
                    return;
                }
                String phone = phoneTF.getText();
                if (!Validate.isPhone(phone)) {
                    Utils.alertBox("Phone entered incorrectly", null, "Error");
                    return;
                }
                String passport = passportTF.getText();
                if (!Validate.isPassport(passport)) {
                    Utils.alertBox("Passport entered incorrectly\n" +
                            """
                                       Enter only passport series and number (10 numbers)\s
                                       Example: 7417932322
                                    """, null, "Error");
                    return;
                }
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

                String request = "exec AddCustomer ?, ?, ?, ?, ?, ?, ?, ?, ?";
                CallableStatement statement = connection.prepareCall(request);
                statement.setString(1, login);
                statement.setString(2, Utils.passwordEncoder.encode(password));
                statement.setString(3, name);
                statement.setString(4, surname);
                statement.setString(5, patronymic);
                statement.setDate(6, date);
                statement.setString(7, passport);
                statement.setString(8, phone);
                statement.setString(9, status);
                statement.execute();
                Utils.alertBox("User add successfully!", null, "Information");
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
