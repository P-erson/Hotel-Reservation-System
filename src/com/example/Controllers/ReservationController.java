package com.example.Controllers;

import com.example.Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ReservationController {

    @FXML private Label countLabel;
    @FXML private Button allTab;

    @FXML private TableView<Reservation> reservationsTable;
    @FXML private TableColumn<Reservation, Integer> resRoomCol;
    @FXML private TableColumn<Reservation, String> resTypeCol;
    @FXML private TableColumn<Reservation, LocalDate> resCheckinCol;
    @FXML private TableColumn<Reservation, LocalDate> resCheckoutCol;
    @FXML private TableColumn<Reservation, Double> resPriceCol;
    @FXML private TableColumn<Reservation, String> resStatusCol;

    private ArrayList<Reservation> allReservations;

    // -------------------------------------------------------
    // INITIALIZE
    // -------------------------------------------------------
    @FXML
    public void initialize() {
        setupTableColumns();
        loadReservations();
    }

    // -------------------------------------------------------
    // SETUP TABLE COLUMNS
    // -------------------------------------------------------
    private void setupTableColumns() {
        resRoomCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getRoom().getRoomNumber()).asObject());

        resTypeCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getRoom().getType()));

        resCheckinCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().getCheckInDate()));

        resCheckoutCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().getCheckOutDate()));

        resPriceCol.setCellValueFactory(data -> {
            Reservation res = data.getValue();
            long nights = ChronoUnit.DAYS.between(
                    res.getCheckInDate(), res.getCheckOutDate());
            double total = nights * res.getRoom().getPricePerNight();
            return new javafx.beans.property.SimpleDoubleProperty(total).asObject();
        });

        resStatusCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getStatus().toString()));
    }

    // -------------------------------------------------------
    // LOAD RESERVATIONS
    // -------------------------------------------------------
    private void loadReservations() {
        Guest guest = SessionManager.getCurrentGuest();
        if (guest == null) return;

        allReservations = guest.viewReservations();
        displayReservations(allReservations);
    }

    private void displayReservations(ArrayList<Reservation> reservations) {
        ObservableList<Reservation> list = FXCollections.observableArrayList(reservations);
        reservationsTable.setItems(list);
        countLabel.setText(reservations.size() + " reservation(s)");
    }

    // -------------------------------------------------------
    // FILTER BUTTONS
    // -------------------------------------------------------
    @FXML
    private void filterAll() {
        displayReservations(allReservations);
    }

    @FXML
    private void filterConfirmed() {
        displayReservations(filterByStatus(Reservation.ReservationStatus.CONFIRMED));
    }

    @FXML
    private void filterPending() {
        displayReservations(filterByStatus(Reservation.ReservationStatus.PENDING));
    }

    @FXML
    private void filterCompleted() {
        displayReservations(filterByStatus(Reservation.ReservationStatus.COMPLETED));
    }

    @FXML
    private void filterCancelled() {
        displayReservations(filterByStatus(Reservation.ReservationStatus.CANCELLED));
    }

    private ArrayList<Reservation> filterByStatus(Reservation.ReservationStatus status) {
        return allReservations.stream()
                .filter(r -> r.getStatus() == status)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // -------------------------------------------------------
    // CANCEL RESERVATION
    // -------------------------------------------------------
    @FXML
    private void handleCancel() {
        Reservation selected = reservationsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection",
                    "Please select a reservation to cancel.");
            return;
        }

        if (selected.getStatus() == Reservation.ReservationStatus.CANCELLED) {
            showAlert(Alert.AlertType.WARNING, "Already Cancelled",
                    "This reservation is already cancelled.");
            return;
        }

        if (selected.getStatus() == Reservation.ReservationStatus.COMPLETED) {
            showAlert(Alert.AlertType.WARNING, "Cannot Cancel",
                    "Completed reservations cannot be cancelled.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Cancel Reservation");
        confirm.setHeaderText(null);
        confirm.setContentText("Are you sure you want to cancel the reservation for Room "
                + selected.getRoom().getRoomNumber() + "?");

        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Guest guest = SessionManager.getCurrentGuest();
                if (guest != null) {
                    guest.cancelReservation(selected.getRoom().getRoomNumber());
                    showAlert(Alert.AlertType.INFORMATION, "Cancelled",
                            "Reservation cancelled successfully.");
                    loadReservations();
                }
            }
        });
    }

    // -------------------------------------------------------
    // PROCEED TO CHECKOUT
    // -------------------------------------------------------
    @FXML
    private void handleCheckout() {
        Reservation selected = reservationsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection",
                    "Please select a reservation to check out.");
            return;
        }

        if (selected.getStatus() != Reservation.ReservationStatus.CONFIRMED) {
            showAlert(Alert.AlertType.WARNING, "Cannot Checkout",
                    "Only confirmed reservations can be checked out.");
            return;
        }

        navigateTo("/fxml/checkout.fxml", "Azure Grand — Checkout");
    }

    // -------------------------------------------------------
    // NAVIGATION
    // -------------------------------------------------------
    @FXML
    private void navigateToDashboard() {
        navigateTo("/fxml/dashboard.fxml", "Azure Grand — Dashboard");
    }

    @FXML
    private void navigateToRooms() {
        navigateTo("/fxml/roomBrowsing.fxml", "Azure Grand — Browse Rooms");
    }

    @FXML
    private void navigateToCheckout() {
        navigateTo("/fxml/checkout.fxml", "Azure Grand — Checkout");
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
                navigateTo("/fxml/login.fxml", "Azure Grand — Login");
            }
        });
    }

    private void navigateTo(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) reservationsTable.getScene().getWindow();
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
