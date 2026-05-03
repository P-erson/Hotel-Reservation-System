package com.example.Controllers;

import com.example.Classes.HotelDatabase;
import com.example.Classes.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminDashboard {
    @FXML
    private Label totalRoomsLabel, availableRoomsLabel, activeResLabel, staffCountLabel;



    public AdminDashboard() {
        totalRoomsLabel.setText(String.valueOf(HotelDatabase.instance.getRooms().size()));
        availableRoomsLabel.setText(String.valueOf(com.example.Classes.User.viewAvailableRooms().size()));
        activeResLabel.setText(String.valueOf(HotelDatabase.instance.getRooms().size()));
    }
}
