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

    public static Dialog createReportDialog(String title, String type) {

        Dialog<List<String>> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText("Insert all necessary data for report");

        List<TextField> textFieldList = new ArrayList<>();

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField typeNameField = new TextField();
        TextField startDateField = new TextField();
        TextField endDateField = new TextField();

        typeNameField.setPromptText(type);

        String startFieldName = "Start date";
        startDateField.setPromptText(startFieldName);

        String endFieldName = "End date";
        endDateField.setPromptText(endFieldName);

        grid.add(new Label(type), 0, 0);
        grid.add(new Label(startFieldName), 0, 1);
        grid.add(new Label(endFieldName), 0, 2);

        grid.add(typeNameField, 1, 0);
        grid.add(startDateField, 1, 1);
        grid.add(endDateField, 1, 2);

        textFieldList.add(typeNameField);
        textFieldList.add(startDateField);
        textFieldList.add(endDateField);

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
