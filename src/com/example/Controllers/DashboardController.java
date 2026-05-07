package com.example.Controllers;

import com.example.Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DashboardController {

    // Sidebar labels
    @FXML private Label sidebarNameLabel;
    @FXML private Label sidebarBalanceLabel;

    // Header
    @FXML private Label dateLabel;

    // Welcome banner
    @FXML private Label welcomeLabel;

    // Stat cards
    @FXML private Label balanceStatLabel;
    @FXML private Label reservationCountLabel;
    @FXML private Label activeCountLabel;
    @FXML private Label availableRoomsLabel;

    // Profile card
    @FXML private Label profileUsername;
    @FXML private Label profileDob;
    @FXML private Label profileAddress;
    @FXML private Label profileGender;
    @FXML private Label profilePreferences;

    // Reservations table
    @FXML private TableView<Reservation> reservationsTable;
    @FXML private TableColumn<Reservation, Integer> roomCol;
    @FXML private TableColumn<Reservation, String> typeCol;
    @FXML private TableColumn<Reservation, LocalDate> checkinCol;
    @FXML private TableColumn<Reservation, LocalDate> checkoutCol;
    @FXML private TableColumn<Reservation, String> statusCol;

    // -------------------------------------------------------
    // INITIALIZE — runs when screen loads
    // -------------------------------------------------------
    @FXML
    public void initialize() {
        Guest guest = SessionManager.getCurrentGuest();
        if (guest == null) return;

        loadProfile(guest);
        loadStats(guest);
        loadReservationsTable(guest);
        loadDate();
    }

    // -------------------------------------------------------
    // LOAD PROFILE INFO
    // -------------------------------------------------------
    private void loadProfile(Guest guest) {
        String name = guest.getUsername();

        sidebarNameLabel.setText(name);
        sidebarBalanceLabel.setText("Balance: $" + String.format("%.2f", guest.getBalance()));
        welcomeLabel.setText("Welcome back, " + name + "!");

        profileUsername.setText(guest.getUsername());
        profileDob.setText(guest.getDateOfBirth().toString());
        profileAddress.setText(guest.getAddress());
        profileGender.setText(guest.getGender().toString());
        profilePreferences.setText(guest.getRoomPreferences());
    }

    // -------------------------------------------------------
    // LOAD STATS
    // -------------------------------------------------------
    private void loadStats(Guest guest) {
        balanceStatLabel.setText("$" + String.format("%.2f", guest.getBalance()));

        ArrayList<Reservation> myReservations = guest.viewReservations();
        reservationCountLabel.setText(String.valueOf(myReservations.size()));

        long activeCount = myReservations.stream()
                .filter(r -> r.getStatus() == Reservation.ReservationStatus.CONFIRMED
                        || r.getStatus() == Reservation.ReservationStatus.PENDING)
                .count();
        activeCountLabel.setText(String.valueOf(activeCount));

        ArrayList<Room> availableRooms = guest.viewAvailableRooms();
        availableRoomsLabel.setText(String.valueOf(availableRooms.size()));
    }

    // -------------------------------------------------------
    // LOAD RESERVATIONS TABLE
    // -------------------------------------------------------
    private void loadReservationsTable(Guest guest) {
        roomCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getRoom().getRoomNumber()).asObject());

        typeCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getRoom().getType()));

        checkinCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().getCheckInDate()));

        checkoutCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().getCheckOutDate()));

        statusCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getStatus().toString()));

        ArrayList<Reservation> myReservations = guest.viewReservations();
        ObservableList<Reservation> list = FXCollections.observableArrayList(myReservations);
        reservationsTable.setItems(list);
    }

    // -------------------------------------------------------
    // LOAD DATE
    // -------------------------------------------------------
    private void loadDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        dateLabel.setText(LocalDate.now().format(formatter));
    }

    // -------------------------------------------------------
    // NAVIGATION
    // -------------------------------------------------------
    @FXML
    private void showDashboard() {
        initialize(); // just refresh
    }

    @FXML
    private void navigateToRooms() {
        navigateTo("/fxml/roomBrowsing.fxml", sidebarNameLabel, "Azure Grand — Browse Rooms");
    }

    @FXML
    private void navigateToReservations() {
        navigateTo("/fxml/reservationManagement.fxml", sidebarNameLabel, "Azure Grand — My Reservations");
    }

    @FXML
    private void navigateToCheckout() {
        navigateTo("/fxml/checkout.fxml", sidebarNameLabel, "Azure Grand — Checkout");
    }

    @FXML
    private void handleLogout() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Sign Out");
        confirm.setHeaderText(null);
        confirm.setContentText("Are you sure you want to sign out?");
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                SessionManager.logout();
                navigateTo("/fxml/login.fxml", sidebarNameLabel, "Azure Grand — Login");
            }
        });
    }

    // -------------------------------------------------------
    // HELPER
    // -------------------------------------------------------
    private void navigateTo(String fxmlPath, javafx.scene.Node source, String title) {
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
