package com.sold.hotel.controllers.tables_controllers;

import com.sold.hotel.controllers.main_controllers.SignInController;
import com.sold.hotel.entities.Booking;
import com.sold.hotel.utils.DBConnection;
import com.sold.hotel.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.log4j.Level;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewAllCustomerBookingController implements Initializable {
    @FXML
    private TableView<Booking> table;
    @FXML
    private TableColumn<Booking, String> in;
    @FXML
    private TableColumn<Booking, String> out;
    @FXML
    private TableColumn<Booking, String> total;
    private final Connection connection;
    private final List<Booking> bookingList = new ArrayList<>();

    public ViewAllCustomerBookingController() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        in.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        out.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement("SELECT id_customer FROM customers WHERE customers.id_user LIKE ?");
                preparedStatement.setInt(1, SignInController.idUserCustomer);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                String request = "exec ShowClientBooking ?";
                preparedStatement = connection.prepareStatement(request);
                preparedStatement.setInt(1, resultSet.getInt("id_customer"));
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String in = resultSet.getString("IN");
                    String out = resultSet.getString("OUT");
                    String total = resultSet.getString("Total");

                    bookingList.add(new Booking(in, out, total));
                }
                table.getItems().setAll(bookingList);
            }
        } catch (SQLException exception) {
            Utils.logger.log(Level.ERROR, null, exception);
        } catch (NumberFormatException exception) {
            Utils.logger.log(Level.INFO, "User closed the window without entering a value", null);
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
