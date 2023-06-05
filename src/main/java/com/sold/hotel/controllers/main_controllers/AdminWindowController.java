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

public class AdminWindowController implements Initializable {
    private final Connection connection;
    private Scene scene;
    private Stage stage;

    public AdminWindowController() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void registerNewCustomer(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/add/sign_up_client.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Registration form");
        stage.show();
    }

    public void registerNewEmployee(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/add/sign_up_staff.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Registration form");
        stage.show();
    }

    public void addRoom(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/add/add_room.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Add room");
        stage.show();
    }

    public void addBooking(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/add/add_booking.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Add booking record");
        stage.show();
    }

    public void addAdditionalService(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/add/add_additional_service.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Add additional service");
        stage.show();
    }

    public void addRoomClassification(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/add/add_classification_room.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Add room classification");
        stage.show();
    }

    public void editRoom(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/edit/edit_room.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Edit room");
        stage.show();
    }

    public void editAdditionalService(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/edit/edit_additional_service.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Edit additional service");
        stage.show();
    }

    public void editDatesOfArrivalAndDeparture(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/edit/edit_client_dates.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Edit the arrival and departure dates");
        stage.show();
    }

    public void showRooms(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/tables/view_rooms.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("List rooms");
        stage.show();
    }

    public void showClassifications(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/tables/view_classifications.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("List classifications of rooms");
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

    public void showOrderedAdditionalServices(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/additional/auxiliary_window_for_enter_login.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Enter the client's login");
        stage.show();
    }

    public void changeStatusStaff(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/edit/change_status_staff.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Change staff status");
        stage.show();
    }

    public void changeStatusCustomer(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/edit/change_status_client.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Change client status");
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
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("/main/sign_in.fxml")));
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception exception) {
            Utils.logger.log(Level.ERROR, null, exception);
        }
    }
}
