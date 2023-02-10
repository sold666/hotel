package com.sold.hotel.controllers.main_controllers;

import com.sold.hotel.utils.DBConnection;
import com.sold.hotel.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.log4j.Level;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    Button signInButton;

    public static String loginUser;
    public static String postUser = "";
    public static int idUserCustomer;
    public static int idUserStaff;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signInButton.setOnAction(this::loginAction);
    }

    public void loginAction(ActionEvent event) {
        String login = usernameTF.getText();
        String password = passwordTF.getText();

        Connection connection;
        PreparedStatement preparedStatement;
        PreparedStatement preparedStatementForCustomer;
        PreparedStatement preparedStatementForStaff;

        ResultSet resultSet;
        ResultSet resultSetCustomer;
        ResultSet resultSetStaff;

        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(Utils.AUTHORIZATION_SQL);
            preparedStatementForCustomer = connection.prepareStatement(Utils.AUTHORIZATION_SQL_CUSTOMER);
            preparedStatementForStaff = connection.prepareStatement(Utils.AUTHORIZATION_SQL_STAFF);

            resultSet = preparedStatement.executeQuery();
            resultSetCustomer = preparedStatementForCustomer.executeQuery();
            resultSetStaff = preparedStatementForStaff.executeQuery();

            while (resultSet.next()) {
                String logUser = resultSet.getString("login");
                if (login.equals(logUser)) {
                    String passwordUser = resultSet.getString("password");
                    if (Utils.passwordEncoder.matches(password, passwordUser)) { //password.equals(passwordUser), Utils.passwordEncoder.matches(password, passwordUser)
                        loginUser = logUser;
                        int id = resultSet.getInt("id_user");
                        boolean statusCustomer;
                        boolean statusStaff;
                        Scene scene = null;
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.close();
                        while (resultSetStaff.next()) {
                            idUserStaff = resultSetStaff.getInt("id_user");
                            statusStaff = resultSetStaff.getBoolean("status");
                            postUser = resultSetStaff.getString("post");
                            if (id == idUserStaff && !postUser.equals("Admin") && statusStaff) {
                                scene = new Scene(FXMLLoader.load(getClass().getResource("/main/staff_window.fxml")));
                                break;
                            } else if (id == idUserStaff && statusStaff) {
                                scene = new Scene(FXMLLoader.load(getClass().getResource("/main/admin_window.fxml")));
                                break;
                            } else if (id == idUserStaff) {
                                Utils.alertBox("This user is not active!", null, "Error");
                                System.exit(0);
                            }
                        }
                        while (resultSetCustomer.next()) {
                            idUserCustomer = resultSetCustomer.getInt("id_user");
                            statusCustomer = resultSetCustomer.getBoolean("status");
                            if (id == idUserCustomer && statusCustomer) {
                                postUser = "";
                                scene = new Scene(FXMLLoader.load(getClass().getResource("/main/client_window.fxml")));
                                break;
                            } else if (id == idUserCustomer) {
                                Utils.alertBox("This user is not active!", null, "Error");
                                System.exit(0);
                            }
                        }
                        stage.setTitle("Hotel");
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        Utils.alertBox("Invalid password or login entered", null, "Error");
                    }
                }
            }
        } catch (Exception exception) {
            Utils.logger.log(Level.ERROR, exception.getMessage());
        }
    }
}