import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
        @Override
        public void start(Stage stage) {
            // Simple label in the center
            Label label = new Label("JavaFX is working!");

            // Layout
            StackPane root = new StackPane();
            root.getChildren().add(label);

            // Scene with width and height
            Scene scene = new Scene(root, 400, 300);

            // Stage (the window)
            stage.setTitle("Hotel Reservation System");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
    }
}