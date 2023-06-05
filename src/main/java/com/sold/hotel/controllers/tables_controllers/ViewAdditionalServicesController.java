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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewAdditionalServicesController implements Initializable {
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

    public ViewAdditionalServicesController() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        additionalServicesName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        try {
            if (connection != null) {
                String request = "exec ShowAdditionalServices";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(request);

                while (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    String cost = resultSet.getString("Cost");
                    String description = resultSet.getString("Description");

                    servicesArrayList.add(new Services(name, cost, description));
                }
                table.getItems().setAll(servicesArrayList);
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
