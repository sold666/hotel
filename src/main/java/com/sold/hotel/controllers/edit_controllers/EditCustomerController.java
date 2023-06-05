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

public class EditCustomerController implements Initializable {
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
                preparedStatement = connection.prepareStatement("SELECT id_customer FROM customers WHERE customers.id_user LIKE ?");
                preparedStatement.setInt(1, SignInController.idUserCustomer);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();

                String request = "exec ChangeDataСustomer ?, ?, ?, ?, ?, ?, ?";
                CallableStatement statement = connection.prepareCall(request);
                statement.setInt(1, resultSet.getInt("id_customer"));
                statement.setString(2, name);
                statement.setString(3, surname);
                statement.setString(4, patronymic);
                statement.setDate(5, date);
                statement.setString(6, phone);
                statement.setString(7, passport);
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
