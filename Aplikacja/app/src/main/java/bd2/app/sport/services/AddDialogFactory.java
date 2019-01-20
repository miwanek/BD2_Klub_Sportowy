package bd2.app.sport.services;

import bd2.app.sport.flags.CommonFlags;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AddDialogFactory {

    public static Dialog createDialog(Class selectedClass, String selectedTable, String title) {

        Field[] fields = selectedClass.getDeclaredFields();

        Dialog<List<String>> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText("Insert all necessary data for given entity");

        List<TextField> textFieldList = new ArrayList<>();

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        int startRange = 1;

        if (CommonFlags.COMPOSED_ENTITIES_TO_ADD.contains(selectedTable)) {
            startRange = 0;
        }

        IntStream.range(startRange, fields.length).forEachOrdered(i -> {
            TextField newTextField = new TextField();
            String fieldName = fields[i].getName();
            newTextField.setPromptText(fieldName);
            grid.add(new Label(fieldName), 0, i);
            grid.add(newTextField, 1, i);
            textFieldList.add(newTextField);
        });

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return textFieldList.stream().map(TextInputControl::getText).collect(Collectors.toList());
            }
            return null;
        });

        return dialog;
    }
}
