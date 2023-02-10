package com.sold.hotel.controllers.tables_controllers;

import com.sold.hotel.entities.Rooms;
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

public class ViewRoomsController implements Initializable {
    @FXML
    private TableView<Rooms> table;
    @FXML
    private TableColumn<Rooms, String> cost;
    @FXML
    private TableColumn<Rooms, Integer> numberOfSeats;
    private final Connection connection;

    private final List<Rooms> roomsList = new ArrayList<>();

    public ViewRoomsController() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        numberOfSeats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        try {
            if (connection != null) {
                String request = "exec ShowRooms";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(request);

                while (resultSet.next()) {
                    String cost = resultSet.getString("Cost");
                    int numberOfSeats = resultSet.getInt("Number_Of_Seats");

                    roomsList.add(new Rooms(cost, numberOfSeats));
                }
                table.getItems().setAll(roomsList);
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
