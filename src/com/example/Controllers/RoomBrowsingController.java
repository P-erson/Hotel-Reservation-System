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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import com.example.Classes.HotelDatabase;

public class RoomBrowsingController {

    @FXML private ChoiceBox<String> typeFilter;
    @FXML private TextField priceFilter;
    @FXML private Label roomCountLabel;

    @FXML private TableView<Room> roomsTable;
    @FXML private TableColumn<Room, Integer> roomNumberCol;
    @FXML private TableColumn<Room, String> roomTypeCol;
    @FXML private TableColumn<Room, Double> roomPriceCol;
    @FXML private TableColumn<Room, String> roomAmenitiesCol;
    @FXML private TableColumn<Room, String> roomStatusCol;

    @FXML private TextField checkInField;
    @FXML private TextField checkOutField;

    private ArrayList<Room> allAvailableRooms;

    // -------------------------------------------------------
    // INITIALIZE
    // -------------------------------------------------------
    @FXML
    public void initialize() {
        setupTableColumns();
        loadRoomTypes();
        loadAvailableRooms();
    }

    // -------------------------------------------------------
    // SETUP TABLE COLUMNS
    // -------------------------------------------------------
    private void setupTableColumns() {
        roomNumberCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().getRoomNumber()).asObject());

        roomTypeCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getType()));

        roomPriceCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleDoubleProperty(
                        data.getValue().getPricePerNight()).asObject());

        roomAmenitiesCol.setCellValueFactory(data -> {
            ArrayList<Amenity> amenities = data.getValue().getAmenities();
            String amenityNames = amenities.stream()
                    .map(Amenity::getName)
                    .collect(Collectors.joining(", "));
            return new javafx.beans.property.SimpleStringProperty(
                    amenityNames.isEmpty() ? "No amenities" : amenityNames);
        });

        roomStatusCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().isAvailable() ? "Available" : "Occupied"));
    }

    // -------------------------------------------------------
    // LOAD ROOM TYPES INTO FILTER
    // -------------------------------------------------------
    private void loadRoomTypes() {
        typeFilter.getItems().add("All Types");
        ArrayList<Room> rooms = HotelDatabase.instance.getRooms();
        rooms.stream()
                .map(Room::getType)
                .distinct()
                .forEach(type -> typeFilter.getItems().add(type));
        typeFilter.setValue("All Types");
    }

    // -------------------------------------------------------
    // LOAD AVAILABLE ROOMS
    // -------------------------------------------------------
    private void loadAvailableRooms() {
        Guest guest = SessionManager.getCurrentGuest();
        if (guest == null) return;

        allAvailableRooms = guest.viewAvailableRooms();
        displayRooms(allAvailableRooms);
    }

    private void displayRooms(ArrayList<Room> rooms) {
        ObservableList<Room> list = FXCollections.observableArrayList(rooms);
        roomsTable.setItems(list);
        roomCountLabel.setText(rooms.size() + " rooms available");
    }

    // -------------------------------------------------------
    // FILTER
    // -------------------------------------------------------
    @FXML
    private void handleFilter() {
        String selectedType = typeFilter.getValue();
        String priceText = priceFilter.getText().trim();

        ArrayList<Room> filtered = allAvailableRooms;

        // Filter by type
        if (selectedType != null && !selectedType.equals("All Types")) {
            filtered = filtered.stream()
                    .filter(r -> r.getType().equalsIgnoreCase(selectedType))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        // Filter by max price
        if (!priceText.isEmpty()) {
            try {
                double maxPrice = Double.parseDouble(priceText);
                filtered = filtered.stream()
                        .filter(r -> r.getPricePerNight() <= maxPrice)
                        .collect(Collectors.toCollection(ArrayList::new));
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.WARNING, "Invalid Price",
                        "Please enter a valid number for max price.");
                return;
            }
        }

        displayRooms(filtered);
    }

    @FXML
    private void handleReset() {
        typeFilter.setValue("All Types");
        priceFilter.clear();
        displayRooms(allAvailableRooms);
    }

    // -------------------------------------------------------
    // BOOK ROOM
    // -------------------------------------------------------
    @FXML
    private void handleBookRoom() {
        Room selectedRoom = roomsTable.getSelectionModel().getSelectedItem();
        if (selectedRoom == null) {
            showAlert(Alert.AlertType.WARNING, "No Room Selected",
                    "Please select a room from the table first.");
            return;
        }

        String checkInText = checkInField.getText().trim();
        String checkOutText = checkOutField.getText().trim();

        if (checkInText.isEmpty() || checkOutText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Dates",
                    "Please enter both check-in and check-out dates.");
            return;
        }

        LocalDate checkIn, checkOut;
        try {
            checkIn = LocalDate.parse(checkInText);
            checkOut = LocalDate.parse(checkOutText);
        } catch (DateTimeParseException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Date",
                    "Please enter dates in YYYY-MM-DD format.");
            return;
        }

        if (!checkOut.isAfter(checkIn)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Date Range",
                    "Check-out date must be after check-in date.");
            return;
        }

        Guest guest = SessionManager.getCurrentGuest();
        if (guest == null) return;

        try {
            guest.makeReservation(selectedRoom, checkIn, checkOut);
            showAlert(Alert.AlertType.INFORMATION, "Reservation Confirmed",
                    "Room " + selectedRoom.getRoomNumber() + " booked successfully!\n"
                            + "Check-in: " + checkIn + "\nCheck-out: " + checkOut);

            // Refresh the room list
            loadAvailableRooms();
            checkInField.clear();
            checkOutField.clear();

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Booking Failed", e.getMessage());
        }
    }

    // -------------------------------------------------------
    // NAVIGATION
    // -------------------------------------------------------
    @FXML
    private void navigateToDashboard() {
        navigateTo("/fxml/dashboard.fxml", "Azure Grand — Dashboard");
    }

    @FXML
    private void navigateToReservations() {
        navigateTo("/fxml/reservationManagement.fxml", "Azure Grand — My Reservations");
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
            Stage stage = (Stage) roomsTable.getScene().getWindow();
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
