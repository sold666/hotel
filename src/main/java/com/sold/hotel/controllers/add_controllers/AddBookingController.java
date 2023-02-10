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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddBookingController implements Initializable {
    @FXML
    TextField loginClientTF;
    @FXML
    DatePicker inDP;
    @FXML
    DatePicker outDP;
    @FXML
    TextField idRoomTF;

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
                String loginClient = loginClientTF.getText();
                List<Date> inDateList = new ArrayList<>();
                if (inDP.getValue() == null) {
                    Utils.alertBox("Date incorrectly", null, "Error");
                    return;
                }
                Date in = Date.valueOf(inDP.getValue());
                if (Validate.isCorrectDate(in)) {
                    Utils.alertBox("Date incorrectly", null, "Error");
                    return;
                }
                List<Date> outDateList = new ArrayList<>();
                if (outDP.getValue() == null) {
                    Utils.alertBox("Date incorrectly", null, "Error");
                    return;
                }
                Date out = Date.valueOf(outDP.getValue());
                if (Validate.isDateMoreDate(out, in)) {
                    Utils.alertBox("Date incorrectly", null, "Error");
                    return;
                }
                String idRoom = idRoomTF.getText();
                if (!Validate.isId(idRoom)) {
                    Utils.alertBox("Number of room entered incorrectly", null, "Error");
                    return;
                }
                int IDClient;
                preparedStatement = connection.prepareStatement("SELECT id_user FROM users WHERE users.login LIKE ?");
                preparedStatement.setString(1, loginClient);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    preparedStatement = connection.prepareStatement("SELECT id_customer FROM customers WHERE customers.id_user LIKE ?");
                    preparedStatement.setInt(1, resultSet.getInt("id_user"));
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        IDClient = resultSet.getInt("id_customer");
                    } else {
                        Utils.alertBox("Enter the login of the client and not the employee", null, "Error");
                        return;
                    }
                } else {
                    Utils.alertBox("There is no user with this login", null, "Error");
                    return;
                }
                preparedStatement = connection.prepareStatement("SELECT id_room FROM rooms WHERE id_room LIKE ?");
                preparedStatement.setString(1, idRoom);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    Utils.alertBox("Room with this number not found.\n Try another one", null, "Error");
                    return;
                }
                preparedStatement = connection.prepareStatement("SELECT check_in, check_out FROM booking WHERE id_room LIKE ?");
                preparedStatement.setString(1, idRoom);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    inDateList.add(resultSet.getDate("check_in"));
                    outDateList.add(resultSet.getDate("check_out"));
                }
                boolean flag = true;
                for (Date value : inDateList) {
                    flag = in.before(value);
                }
                if (!flag) {
                    for (Date date : outDateList) {
                        flag = in.after(date);
                    }
                }
                if (!flag) {
                    Utils.alertBox("This room is already booked for this date", null, "Error");
                    return;
                }
                String request = "exec AddBooking ?, ?, ?, ?";
                CallableStatement statement = connection.prepareCall(request);
                statement.setInt(1, IDClient);
                statement.setDate(2, in);
                statement.setDate(3, out);
                statement.setString(4, idRoom);
                statement.execute();
                Utils.alertBox("Booking record add successfully!", null, "Information");
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
