package com.sold.hotel.controllers.tables_controllers;

import com.sold.hotel.entities.ClassificationRooms;
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


public class ViewRoomsClassificationsController implements Initializable {
    @FXML
    private TableView<ClassificationRooms> table;
    @FXML
    private TableColumn<ClassificationRooms, String> classificationName;
    private final Connection connection;
    private final List<ClassificationRooms> classificationRoomsList = new ArrayList<>();

    public ViewRoomsClassificationsController() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        classificationName.setCellValueFactory(new PropertyValueFactory<>("classificationName"));
        try {
            if (connection != null) {
                String request = "exec ShowClassifications";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(request);

                while (resultSet.next()) {
                    String name = resultSet.getString("Name");

                    classificationRoomsList.add(new ClassificationRooms(name));
                }
                table.getItems().setAll(classificationRoomsList);
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