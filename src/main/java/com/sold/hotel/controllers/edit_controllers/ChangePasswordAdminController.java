package com.sold.hotel.controllers.edit_controllers;

import com.sold.hotel.controllers.main_controllers.SignInController;
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
import java.util.Objects;
import java.util.ResourceBundle;


public class ChangePasswordAdminController implements Initializable {
    private final Connection connection;
    @FXML
    TextField usernameTF;
    @FXML
    TextField passwordTF;

    public ChangePasswordAdminController() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void acceptAction() {
        try {
            if (connection != null) {
                String login = usernameTF.getText();
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
                if (Objects.equals(login, SignInController.loginUser)) {
                    String request = "exec ChangePassword ?, ?";
                    CallableStatement callableStatement = connection.prepareCall(request);
                    callableStatement.setString(1, login);
                    callableStatement.setString(2, Utils.passwordEncoder.encode(password));
                    callableStatement.execute();
                } else {
                    Utils.alertBox("There is no user with this username", null, "Error");
                    return;
                }
                Utils.alertBox("Password changed successfully!", null, "Information");
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
