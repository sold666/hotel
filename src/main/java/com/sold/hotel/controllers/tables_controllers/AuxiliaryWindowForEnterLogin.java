package com.sold.hotel.controllers.tables_controllers;

import com.sold.hotel.utils.DBConnection;
import com.sold.hotel.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.log4j.Level;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AuxiliaryWindowForEnterLogin implements Initializable {
    private final Connection connection;
    @FXML
    TextField loginClientTF;

    public AuxiliaryWindowForEnterLogin() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void acceptAction(ActionEvent event) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            if (connection != null) {
                String loginClient = loginClientTF.getText();
                preparedStatement = connection.prepareStatement("SELECT id_user FROM users WHERE users.login LIKE ?");
                preparedStatement.setString(1, loginClient);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    preparedStatement = connection.prepareStatement("SELECT id_customer FROM customers WHERE customers.id_user LIKE ?");
                    preparedStatement.setInt(1, resultSet.getInt("id_user"));
                    resultSet = preparedStatement.executeQuery();
                    if (!resultSet.next()) {
                        Utils.alertBox("You must enter the client's login!", null, "Information");
                    } else {
                        preparedStatement = connection.prepareStatement("SELECT id_booking FROM booking WHERE booking.id_customer LIKE ?");
                        preparedStatement.setInt(1, resultSet.getInt("id_customer"));
                        resultSet = preparedStatement.executeQuery();
                        if (!resultSet.next()) {
                            Utils.alertBox("This client does not have a reservation", null, "Information");
                            return;
                        }
                        int IDBooking = resultSet.getInt("id_booking");

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/tables/view_ordered_additional_services.fxml"));
                        Parent root = fxmlLoader.load();
                        ViewOrderedAdditionalServicesController controller = fxmlLoader.getController();
                        controller.display(IDBooking);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("List ordered additional services by booking number");
                        stage.show();
                    }
                } else {
                    Utils.alertBox("There is no user with this login", null, "Error");
                }
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
