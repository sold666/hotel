package com.sold.hotel.controllers.main_controllers;

import com.sold.hotel.utils.DBConnection;
import com.sold.hotel.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Level;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class StaffWindowController implements Initializable {
    private final Connection connection;
    private Scene scene;
    private Stage stage;

    public StaffWindowController() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void showRooms(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/tables/view_rooms.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("List rooms");
        stage.show();
    }

    public void showAdditionalServices(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/tables/view_additional_services.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("List additional services");
        stage.show();
    }

    public void showAllBookings(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/tables/view_all_booking.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("List all booking");
        stage.show();
    }

    public void showPersonalData(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/additional/profile_staff.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Profile");
        stage.show();
    }

    public void editAdditionalService(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/edit/edit_additional_service.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Edit additional service");
        stage.show();
    }

    public void editRoom(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/edit/edit_room.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Edit room");
        stage.show();
    }

    public void editData(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/edit/edit_staff.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Edit your data");
        stage.show();
    }

    public void changePasswordAction(ActionEvent event) {
        try {
            if (connection != null) {
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("/main/edit/change_password_admin.fxml")));
                stage.setTitle("Update data");
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception exception) {
            Utils.logger.log(Level.ERROR, null, exception);
        }
    }

    public void logoutAction(ActionEvent event) {
        try {
            if (connection != null) {
                Scene scene;
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("/main/sign_in.fxml")));
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception exception) {
            Utils.logger.log(Level.ERROR, exception.getMessage());
        }
    }
}
