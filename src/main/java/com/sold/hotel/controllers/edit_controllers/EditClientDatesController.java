package com.sold.hotel.controllers.edit_controllers;

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

public class EditClientDatesController implements Initializable {
    @FXML
    TextField idBookingTF;
    @FXML
    DatePicker inDP;
    @FXML
    DatePicker outDP;

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
                String idBooking = idBookingTF.getText();
                if (!Validate.isId(idBooking)) {
                    Utils.alertBox("Number of booking entered incorrectly", null, "Error");
                    return;
                }
                if (inDP.getValue() == null) {
                    Utils.alertBox("Date incorrectly", null, "Error");
                    return;
                }
                Date in = Date.valueOf(inDP.getValue());
                if (Validate.isCorrectDate(in.toLocalDate())) {
                    Utils.alertBox("Date incorrectly", null, "Error");
                    return;
                }
                if (outDP.getValue() == null) {
                    Utils.alertBox("Date incorrectly", null, "Error");
                    return;
                }
                Date out = Date.valueOf(outDP.getValue());
                if (Validate.isDateMoreDate(out.toLocalDate(), in.toLocalDate())) {
                    Utils.alertBox("Date incorrectly", null, "Error");
                    return;
                }
                preparedStatement = connection.prepareStatement("SELECT id_booking FROM booking WHERE id_booking LIKE ?");
                preparedStatement.setString(1, idBooking);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    Utils.alertBox("Booking record with this number not found.\n Try another one", null, "Error");
                    return;
                }
                String request = "exec ChangeDatesInBooking ?, ?, ?";
                CallableStatement statement = connection.prepareCall(request);
                statement.setString(1, idBooking);
                statement.setDate(2, in);
                statement.setDate(3, out);
                statement.execute();
                Utils.alertBox("Dates has been successfully changed!", null, "Information");
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
