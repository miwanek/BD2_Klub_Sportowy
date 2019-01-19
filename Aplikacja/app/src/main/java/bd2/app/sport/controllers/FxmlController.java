package bd2.app.sport.controllers;


import bd2.app.cell.ActionButtonTableCell;
import bd2.app.sport.flags.CommonFlags;
import bd2.app.sport.services.AddDialogFactory;
import bd2.app.sport.services.FlatEntityService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static javafx.scene.control.TableView.UNCONSTRAINED_RESIZE_POLICY;

@Controller
@RequiredArgsConstructor
public class FxmlController implements Initializable {

    private final FetchController fetchController;
    private final DeleteController deleteController;
    private final EditController editController;
    private final AddController addController;

    @FXML
    private TextField elementTextField;

    @FXML
    private ComboBox<?> tableFieldList;

    @FXML
    private Button groupReportButton;

    @FXML
    private Button sectionReportButton;

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<?> tableList;

    @FXML
    private TableView mainTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.setVisible(false);
    }

    @FXML
    void tableChanged() {
        addButton.setVisible(true);

        if (tableList.getValue() == null || CommonFlags.READ_ONLY_ENTITIES
                .contains(tableList.getValue().toString())) {
            addButton.setVisible(false);
        }
    }

    @FXML
    void addEntityButtonPressed() {
        Class selectedClass;
        String selectedTable = tableList.getValue() != null ? tableList.getValue().toString() : null;

        try {
            selectedClass = Class.forName("bd2.app.sport.entities." + selectedTable);
        } catch (ClassNotFoundException exception) {
            return ;
        }

        selectedClass = FlatEntityService.getClass(selectedClass, selectedTable);

        Dialog  dialog = AddDialogFactory.createDialog(selectedClass, selectedTable);

        Optional<List<String>> result = dialog.showAndWait();

        result.ifPresent(list -> addController.addRowToTable(selectedTable, list));
    }

    @FXML
    void searchButtonPressed() {
        cleanTableViewAndResizeColumns();
        String selectedTable = tableList.getValue() != null ? tableList.getValue().toString() : null;
        String selectedColumn = tableFieldList.getValue() != null ? tableFieldList.getValue().toString() : null;
        String columnValue = elementTextField.getText();

        List<? extends Object> data = fetchController.getDataFromSelectedTable(selectedTable, selectedColumn, columnValue);

        if (data != null) {
            data = data.stream().map(x -> FlatEntityService.getFlatEntity(selectedTable, x)).collect(Collectors.toList());
        }

        if (!tryDisplayTableWithValues(selectedTable)) return;

        if (data != null) data.forEach(row -> mainTable.getItems().add(row));
    }

    private void cleanTableViewAndResizeColumns() {
        mainTable.getItems().clear();
        mainTable.getColumns().clear();
        mainTable.setColumnResizePolicy(UNCONSTRAINED_RESIZE_POLICY);
    }

    private boolean tryDisplayTableWithValues(String selectedTable) {
        try {
            displayTableWithValues(selectedTable);
        } catch (ClassNotFoundException exception) {
            return false;
        }
        return true;
    }

    private void displayTableWithValues(String selectedTable) throws ClassNotFoundException {

        Class selectedClass = Class.forName("bd2.app.sport.entities." + selectedTable);
        selectedClass = FlatEntityService.getClass(selectedClass, selectedTable);
        Field[] fields = selectedClass.getDeclaredFields();

        System.out.println(fields.length);

        for (int i = 0; i < fields.length; i++) {
            TableColumn tableColumn = new TableColumn(fields[i].getName());

            tableColumn.setCellValueFactory(new PropertyValueFactory<>(fields[i].getName()));
            mainTable.getColumns().add(tableColumn);
        }

        if (CommonFlags.ADD_ENTITIES.contains(selectedTable)) {
            addInsertButton(selectedTable);
        }

        if (CommonFlags.DELETE_ENTITIES.contains(selectedTable)) {
            addDeleteColumn(selectedTable);
        }
    }
    
    private void addDeleteColumn(String selectedTable) {
        TableColumn deleteColumn = new TableColumn("delete");
        mainTable.getColumns().add(deleteColumn);

        deleteColumn.setCellFactory(ActionButtonTableCell.forTableColumn("delete", (Object p) -> {
            deleteController.deleteRowFromTable(selectedTable, p);
            mainTable.getItems().remove(p);
            searchButtonPressed();
            return p;
        }));
    }

    private void addInsertButton(String selectedTable) {

        TableColumn editColumn = new TableColumn("edit");

        mainTable.getColumns().add(editColumn);

        editColumn.setCellFactory(ActionButtonTableCell.forTableColumn("edit", (Object p) -> {
            deleteController.deleteRowFromTable(selectedTable, p);
            mainTable.getItems().remove(p);
            searchButtonPressed();
            return p;
        }));
    }
}
