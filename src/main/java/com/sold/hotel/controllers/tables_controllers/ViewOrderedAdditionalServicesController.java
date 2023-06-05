package com.sold.hotel.controllers.tables_controllers;

import com.sold.hotel.entities.Services;
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


public class ViewOrderedAdditionalServicesController implements Initializable {
    @FXML
    private TableView<Services> table;
    @FXML
    private TableColumn<Services, String> additionalServicesName;
    @FXML
    private TableColumn<Services, String> cost;
    @FXML
    private TableColumn<Services, String> description;
    private final Connection connection;
    private final List<Services> servicesArrayList = new ArrayList<>();

    public ViewOrderedAdditionalServicesController() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        additionalServicesName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    public void display(int IDBooking) {
        try {
            if (connection != null) {
                String request = "exec ShowAdditionalServicesByNumberOfBooking ?";
                PreparedStatement statement = connection.prepareStatement(request);
                statement.setInt(1, IDBooking);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    String cost = resultSet.getString("Cost");
                    String description = resultSet.getString("Description");

                    servicesArrayList.add(new Services(name, cost, description));
                }
                table.getItems().setAll(servicesArrayList);
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
