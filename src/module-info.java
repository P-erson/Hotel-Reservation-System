module Hotel.Reservation.System {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example to javafx.fxml;

    exports com.example;
    exports com.example.Exceptions;
    opens com.example.Exceptions to javafx.fxml;
    exports com.example.Classes;
    opens com.example.Classes to javafx.fxml;
    exports com.example.Controllers;
    opens com.example.Controllers to javafx.fxml;
}