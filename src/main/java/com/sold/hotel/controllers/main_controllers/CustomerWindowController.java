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

public class CustomerWindowController implements Initializable {
    private final Connection connection;
    private Scene scene;
    private Stage stage;

    public CustomerWindowController() {
        connection = DBConnection.getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void showAllYourBookings(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/tables/view_customer_booking.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("List your booking");
        stage.show();
    }

    public void showPersonalData(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/additional/profile_customer.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Profile");
        stage.show();
    }

    public void orderingAdditionalService(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/additional/order_additional_service.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Ordering additional services");
        stage.show();
    }

    //todo другой интерфейс
    //todo неправильно total считается при добавлении доп услуг (Изменяется у всех записей?? Выдает ошибку если уже была выбрана услуга)
    //todo локализация
    public void addBooking(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/add/add_booking.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Add booking record");
        stage.show();
    }

    public void editData(ActionEvent event) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("/main/edit/edit_customer.fxml")));
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
