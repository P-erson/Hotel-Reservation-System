package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;

public class RegisterController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField dobField;
    @FXML private TextField addressField;
    @FXML private TextField preferencesField;
    @FXML private TextField balanceField;
    @FXML private ChoiceBox<String> genderChoiceBox;
    @FXML private Button registerButton;
    @FXML private Button loginButton;

    @FXML
    public void initialize() {
        genderChoiceBox.getItems().addAll("MALE", "FEMALE");
    }

    @FXML
    private void handleRegister() {
        try {
            String username = usernameField.getText();
            String password = passwordField.getText();
            LocalDate dob = LocalDate.parse(dobField.getText());
            String address = addressField.getText();
            String preferences = preferencesField.getText();
            double balance = Double.parseDouble(balanceField.getText());
            Guest.Gender gender = Guest.Gender.valueOf(genderChoiceBox.getValue().toUpperCase());

            new Guest(username, password, dob, address, preferences, balance, gender);

            // Show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText(null);
            alert.setContentText("Account created successfully! Please login.");
            alert.showAndWait();

            // Navigate back to login
            handleBackToLogin();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration Failed");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleBackToLogin() {
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("Navigation error: " + e.getMessage());
        }
    }
}