package com.example.Controllers;

import com.example.Classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Button registerButton;

    @FXML
    private void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        FXMLLoader loader = null;

        try {
            User loggedIn = User.login(username, password);
            SessionManager.currentUser = loggedIn;
            System.out.println("Login successful: " + loggedIn.getUsername());

            String userType = String.valueOf(loggedIn.getUserType());

            loader = switch (userType) {
                case "GUEST" -> new FXMLLoader(getClass().getResource("/fxml/guestDashboard.fxml"));
                case "ADMIN" -> new FXMLLoader(getClass().getResource("/fxml/adminDashboard.fxml"));
                case "RECEPTIONIST" -> new FXMLLoader(getClass().getResource("/fxml/receptionistDashboard.fxml"));
                default -> throw new Exception();
            };

            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    private void handleRegister() {
        try {
            Stage stage = (Stage) registerButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("Navigation error: " + e.getMessage());
        }
    }
}