package com.sold.hotel;

import com.sold.hotel.utils.DBConnection;
import com.sold.hotel.utils.Utils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.IOException;

public class HotelApplication extends Application {

    @Override
    public void start(Stage stage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(HotelApplication.class.getResource("/main/sign_in.fxml"));
        } catch (IOException exception) {
            Utils.logger.log(Level.ERROR, exception.getMessage());
        }
        Scene scene = new Scene(root, 820, 540);
        stage.getIcons().add(new Image(new File("src/main/resources/assets/icon.png").toURI().toString()));
        stage.setTitle("Hotel");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        PropertyConfigurator.configure(classLoader.getResource("log4j.properties"));
        Utils.logger.log(Level.INFO, "Application has started");
        DBConnection.connection();
        launch();
        Utils.logger.log(Level.INFO, "Application has finished");
    }
}