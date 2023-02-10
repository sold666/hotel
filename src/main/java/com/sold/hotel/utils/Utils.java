package com.sold.hotel.utils;

import com.sold.hotel.controllers.main_controllers.SignInController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Utils {
    public static Logger logger = Logger.getLogger(Utils.class);
    public static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static final String AUTHORIZATION_SQL = "SELECT * FROM users";
    public static final String AUTHORIZATION_SQL_CUSTOMER = "SELECT * FROM customers";
    public static final String AUTHORIZATION_SQL_STAFF = "SELECT * FROM staff";

    public static void alertBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(new File("src/main/resources/assets/icon.png").toURI().toString()));
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public static void back(ActionEvent event) throws IOException {
        Scene scene;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        if (SignInController.postUser.equals("Admin")) {
            scene = new Scene(FXMLLoader.load(Objects.requireNonNull(Utils.class.getResource("/main/admin_window.fxml"))));
        } else if (SignInController.postUser.equals("")) {
            scene = new Scene(FXMLLoader.load(Objects.requireNonNull(Utils.class.getResource("/main/client_window.fxml"))));
        } else {
            scene = new Scene(FXMLLoader.load(Objects.requireNonNull(Utils.class.getResource("/main/staff_window.fxml"))));
        }
        stage.setScene(scene);
        stage.setTitle("Hotel");
        stage.show();
    }
}
