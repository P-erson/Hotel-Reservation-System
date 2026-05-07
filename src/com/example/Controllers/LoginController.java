package com.example.Controllers;

import com.example.Classes.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Button registerButton;

    // -------------------------------------------------------
    // HANDLE LOGIN
    // -------------------------------------------------------
    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Fields",
                    "Please enter both your username and password.");
            return;
        }

        try {
            User loggedIn = User.login(username, password);
            SessionManager.setCurrentUser(loggedIn);

            // Route to correct dashboard based on user type
            if (loggedIn instanceof Guest) {
                navigateTo("/fxml/dashboard.fxml", loginButton, "Azure Grand — Guest Dashboard");
            } else if (loggedIn instanceof Admin) {
                navigateTo("/fxml/AdminDashboard.fxml", loginButton, "Azure Grand — Admin Dashboard");
            } else if (loggedIn instanceof Receptionist) {
                navigateTo("/fxml/ReceptionistDashboard.fxml", loginButton, "Azure Grand — Receptionist Dashboard");
            }

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Login Failed",
                    "Incorrect username or password. Please try again.");
        }
    }

    // -------------------------------------------------------
    // SOCIAL LOGIN PLACEHOLDERS
    // -------------------------------------------------------
    @FXML
    private void handleGoogleLogin() {
        showAlert(Alert.AlertType.INFORMATION, "Coming Soon",
                "Google login will be available in a future update.");
    }

    @FXML
    private void handleEmailLogin() {
        usernameField.requestFocus();
    }

    // -------------------------------------------------------
    // NAVIGATE TO REGISTER
    // -------------------------------------------------------
    @FXML
    private void handleRegister() {
        navigateTo("/fxml/register.fxml", registerButton, "Azure Grand — Register");
    }

    // -------------------------------------------------------
    // HELPERS
    // -------------------------------------------------------
    private void navigateTo(String fxmlPath, Button source, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.setMaximized(true);
            stage.show();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
