package com.example.Controllers;

import com.example.Classes.Guest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

    // -------------------------------------------------------
    // INITIALIZE — populate gender choices
    // -------------------------------------------------------
    @FXML
    public void initialize() {
        genderChoiceBox.getItems().addAll("MALE", "FEMALE");
        genderChoiceBox.setValue("MALE");
    }

    // -------------------------------------------------------
    // HANDLE REGISTER
    // -------------------------------------------------------
    @FXML
    private void handleRegister() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String dobText = dobField.getText().trim();
        String address = addressField.getText().trim();
        String preferences = preferencesField.getText().trim();
        String balanceText = balanceField.getText().trim();
        String genderValue = genderChoiceBox.getValue();

        // Basic empty field checks
        if (username.isEmpty() || password.isEmpty() || dobText.isEmpty() || balanceText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Fields",
                    "Please fill in all required fields.");
            return;
        }

        if (genderValue == null) {
            showAlert(Alert.AlertType.WARNING, "Missing Gender", "Please select a gender.");
            return;
        }

        // Parse date
        LocalDate dob;
        try {
            dob = LocalDate.parse(dobText);
        } catch (DateTimeParseException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Date",
                    "Please enter date of birth in YYYY-MM-DD format.");
            return;
        }

        // Parse balance
        double balance;
        try {
            balance = Double.parseDouble(balanceText);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Balance",
                    "Please enter a valid number for balance.");
            return;
        }

        // Parse gender
        Guest.Gender gender;
        try {
            gender = Guest.Gender.valueOf(genderValue.toUpperCase());
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Gender", "Please select MALE or FEMALE.");
            return;
        }

        // Attempt to create guest — constructor handles validation and adds to DATABASE
        try {
            new Guest(username, password, dob, address, preferences, balance, gender);

            showAlert(Alert.AlertType.INFORMATION, "Registration Successful",
                    "Your Azure Grand account has been created! Please sign in.");

            handleBackToLogin();

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", e.getMessage());
        }
    }

    // -------------------------------------------------------
    // NAVIGATE BACK TO LOGIN
    // -------------------------------------------------------
    @FXML
    private void handleBackToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Azure Grand — Login");
            stage.show();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", e.getMessage());
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------
    // HELPER
    // -------------------------------------------------------
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
