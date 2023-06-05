package com.sold.hotel.controllers.main_controllers;

import com.sold.hotel.entities.Staff;
import com.sold.hotel.utils.DBConnection;
import com.sold.hotel.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.apache.log4j.Level;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class StaffProfileController implements Initializable {
    @FXML
    Label name;
    @FXML
    Label surname;
    @FXML
    Label patronymic;
    @FXML
    Label age;
    @FXML
    Label phone;
    @FXML
    Label post;
    @FXML
    Label nameLB;
    @FXML
    Label surnameLB;
    @FXML
    Label patronymicLB;
    @FXML
    Label ageLB;
    @FXML
    Label phoneLB;
    @FXML
    Label postLB;

    private Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DBConnection.getConnection();
        takeStaff();
    }

    public void takeStaff() {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement("SELECT name, surname, patronymic, date_of_birth, phone, post " +
                    "FROM staff WHERE staff.id_user LIKE ?");
            preparedStatement.setInt(1, SignInController.idUserStaff);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                nameLB.setText(resultSet.getString("name"));
                surnameLB.setText(resultSet.getString("surname"));
                patronymicLB.setText(resultSet.getString("patronymic"));
                ageLB.setText(String.valueOf(Staff.getAge(resultSet.getDate("date_of_birth"))));
                phoneLB.setText(resultSet.getString("phone"));
                postLB.setText(resultSet.getString("post"));
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
