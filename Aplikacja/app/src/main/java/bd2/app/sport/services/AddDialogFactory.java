package bd2.app.sport.services;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddDialogFactory {

    public Dialog createDialog() {


        Dialog<List<String >> dialog = new Dialog<>();
        dialog.setTitle("Add new record");
        dialog.setHeaderText("Insert all necessary data for given entity");


        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

//// Create the username and password labels and fields.
//        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(20, 150, 10, 10));
//
        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
//
//        grid.add(new Label("Username:"), 0, 0);
//        grid.add(username, 1, 0);
//        grid.add(new Label("Password:"), 0, 1);
//        grid.add(password, 1, 1);
//
//// Enable/Disable login button depending on whether a username was entered.
//        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
//        loginButton.setDisable(true);
//
//// Do some validation (using the Java 8 lambda syntax).
//        username.textProperty().addListener((observable, oldValue, newValue) -> {
//            loginButton.setDisable(newValue.trim().isEmpty());
//        });
//
//        dialog.getDialogPane().setContent(grid);
//
//// Request focus on the username field by default.
//        Platform.runLater(() -> username.requestFocus());

// Convert the result to a username-password-pair when the login button is clicked.
//        dialog.setResultConverter(dialogButton -> {
//            if (dialogButton == loginButtonType) {
//                return new Pair<>(username.getText(), password.getText());
//            }
//            return null;
//        });

//        Optional<Pair<String, String>> result = dialog.showAndWait();
//
//        result.ifPresent(usernamePassword -> {
//            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
//        });
            return  dialog;
    }
}
