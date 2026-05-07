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

public class CheckoutController {

    // Reservations table
    @FXML private TableView<Reservation> reservationsTable;
    @FXML private TableColumn<Reservation, Integer> roomCol;
    @FXML private TableColumn<Reservation, LocalDate> checkinCol;
    @FXML private TableColumn<Reservation, LocalDate> checkoutCol;
    @FXML private TableColumn<Reservation, String> statusCol;

    // Payment method
    @FXML private ChoiceBox<String> paymentMethodBox;
    @FXML private RadioButton cashRadio;
    @FXML private RadioButton cardRadio;
    @FXML private RadioButton onlineRadio;

    // Invoice labels
    @FXML private Label invoiceGuestName;
    @FXML private Label invoiceRoom;
    @FXML private Label invoiceRoomType;
    @FXML private Label invoiceCheckin;
    @FXML private Label invoiceCheckout;
    @FXML private Label invoiceNights;
    @FXML private Label invoicePricePerNight;
    @FXML private Label invoiceTotal;
    @FXML private Label invoiceBalanceAfter;

    private ToggleGroup paymentToggleGroup;

    // -------------------------------------------------------
    // INITIALIZE
    // -------------------------------------------------------
    @FXML
    public void initialize() {
        setupPaymentOptions();
        setupTableColumns();
        loadActiveReservations();
        setupTableSelectionListener();
    }

    // -------------------------------------------------------
    // SETUP PAYMENT OPTIONS
    // -------------------------------------------------------
    private void setupPaymentOptions() {
        paymentToggleGroup = new ToggleGroup();
        cashRadio.setToggleGroup(paymentToggleGroup);
        cardRadio.setToggleGroup(paymentToggleGroup);
        onlineRadio.setToggleGroup(paymentToggleGroup);
        cashRadio.setSelected(true);

        paymentMethodBox.getItems().addAll("CASH", "CREDIT_CARD", "ONLINE");
        paymentMethodBox.setValue("CASH");

        // Sync radio buttons with choice box
        paymentToggleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == cashRadio) paymentMethodBox.setValue("CASH");
            else if (newVal == cardRadio) paymentMethodBox.setValue("CREDIT_CARD");
            else if (newVal == onlineRadio) paymentMethodBox.setValue("ONLINE");
        });
    }

    // -------------------------------------------------------
    // SETUP TABLE COLUMNS
    // -------------------------------------------------------
    private void setupTableColumns() {
        roomCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getRoom().getRoomNumber()).asObject());

        checkinCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().getCheckInDate()));

        checkoutCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().getCheckOutDate()));

        statusCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getStatus().toString()));
    }

    // -------------------------------------------------------
    // LOAD ACTIVE RESERVATIONS (CONFIRMED ONLY)
    // -------------------------------------------------------
    private void loadActiveReservations() {
        Guest guest = SessionManager.getCurrentGuest();
        if (guest == null) return;

        ArrayList<Reservation> active = guest.viewReservations().stream()
                .filter(r -> r.getStatus() == Reservation.ReservationStatus.CONFIRMED)
                .collect(Collectors.toCollection(ArrayList::new));

        ObservableList<Reservation> list = FXCollections.observableArrayList(active);
        reservationsTable.setItems(list);
    }

    // -------------------------------------------------------
    // LISTEN FOR TABLE SELECTION — update invoice preview
    // -------------------------------------------------------
    private void setupTableSelectionListener() {
        reservationsTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null) updateInvoicePreview(newVal);
                });
    }

    private void updateInvoicePreview(Reservation reservation) {
        Guest guest = SessionManager.getCurrentGuest();
        if (guest == null) return;

        Room room = reservation.getRoom();
        LocalDate checkIn = reservation.getCheckInDate();
        LocalDate checkOut = reservation.getCheckOutDate();
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        double total = nights * room.getPricePerNight();
        double balanceAfter = guest.getBalance() - total;

        invoiceGuestName.setText(guest.getUsername());
        invoiceRoom.setText(String.valueOf(room.getRoomNumber()));
        invoiceRoomType.setText(room.getType());
        invoiceCheckin.setText(checkIn.toString());
        invoiceCheckout.setText(checkOut.toString());
        invoiceNights.setText(nights + " night(s)");
        invoicePricePerNight.setText("$" + String.format("%.2f", room.getPricePerNight()));
        invoiceTotal.setText("$" + String.format("%.2f", total));
        invoiceBalanceAfter.setText("$" + String.format("%.2f", balanceAfter));
    }

    // -------------------------------------------------------
    // CONFIRM CHECKOUT
    // -------------------------------------------------------
    @FXML
    private void handleConfirmCheckout() {
        Reservation selected = reservationsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Reservation Selected",
                    "Please select a reservation to check out.");
            return;
        }

        String paymentValue = paymentMethodBox.getValue();
        if (paymentValue == null) {
            showAlert(Alert.AlertType.WARNING, "No Payment Method",
                    "Please select a payment method.");
            return;
        }

        Invoice.PaymentMethod method;
        try {
            method = Invoice.PaymentMethod.valueOf(paymentValue);
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Payment", "Invalid payment method selected.");
            return;
        }

        Guest guest = SessionManager.getCurrentGuest();
        if (guest == null) return;

        // Confirm dialog
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Checkout");
        confirm.setHeaderText(null);
        confirm.setContentText("Confirm checkout for Room "
                + selected.getRoom().getRoomNumber() + "?\n"
                + "Payment: " + paymentValue + "\n"
                + "Total: " + invoiceTotal.getText());

        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    Invoice invoice = guest.checkout(
                            selected.getRoom().getRoomNumber(), method);

                    showAlert(Alert.AlertType.INFORMATION, "Checkout Successful",
                            "✓ Checkout complete!\n"
                                    + "Total Charged: $" + String.format("%.2f", invoice.getTotalAmount()) + "\n"
                                    + "Payment Method: " + paymentValue + "\n"
                                    + "Remaining Balance: $" + String.format("%.2f", guest.getBalance()));

                    // Refresh the table and clear invoice
                    loadActiveReservations();
                    clearInvoice();

                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Checkout Failed", e.getMessage());
                }
            }
        });
    }

    private void clearInvoice() {
        invoiceGuestName.setText("—");
        invoiceRoom.setText("—");
        invoiceRoomType.setText("—");
        invoiceCheckin.setText("—");
        invoiceCheckout.setText("—");
        invoiceNights.setText("—");
        invoicePricePerNight.setText("—");
        invoiceTotal.setText("$0.00");
        invoiceBalanceAfter.setText("$0.00");
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
    private void navigateToReservations() {
        navigateTo("/fxml/reservationManagement.fxml", "Azure Grand — My Reservations");
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
