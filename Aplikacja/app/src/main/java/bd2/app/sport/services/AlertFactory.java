package bd2.app.sport.services;

import javafx.scene.control.Alert;

public class AlertFactory {

    public static void createAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Database error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
