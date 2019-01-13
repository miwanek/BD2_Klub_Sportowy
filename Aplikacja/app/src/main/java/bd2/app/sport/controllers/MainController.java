package bd2.app.sport.controllers;


import bd2.app.cell.ActionButtonTableCell;
import bd2.app.sport.services.FlatEntityService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import static javafx.scene.control.TableView.UNCONSTRAINED_RESIZE_POLICY;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final DataController dataController;

    private final DeleteController deleteController;

    @FXML
    private TextField elementTextField;

    @FXML
    private ComboBox<?> tableFieldList;

    @FXML
    private Button searchButton;

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

    @FXML
    void searchButtonPressed() {
        cleanTableViewAndResizeColumns();
        String selectedTable = tableList.getValue() != null ? tableList.getValue().toString() : null;
        String selectedColumn = tableFieldList.getValue() != null ? tableFieldList.getValue().toString() : null;
        String columnValue = elementTextField.getText();

        List<? extends Object> data = dataController.getDataFromSelectedTable(selectedTable, selectedColumn, columnValue);

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

    private void displayTableWithValues(String selectedTable)
            throws ClassNotFoundException, DataIntegrityViolationException {
        Class selectedClass = Class.forName("bd2.app.sport.entities." + selectedTable);
        selectedClass = FlatEntityService.getClass(selectedClass, selectedTable);
        Field[] fields = selectedClass.getDeclaredFields();

        System.out.println(fields.length);

        for (int i = 0; i < fields.length; i++) {
            TableColumn tableColumn = new TableColumn(fields[i].getName());
            System.out.println(fields[i].getName());

            tableColumn.setCellValueFactory(new PropertyValueFactory<>(fields[i].getName()));
            mainTable.getColumns().add(tableColumn);
        }

        TableColumn deleteColumn = new TableColumn("delete");
        TableColumn editColumn = new TableColumn("edit");

        mainTable.getColumns().addAll(deleteColumn, editColumn);

        deleteColumn.setCellFactory(ActionButtonTableCell.forTableColumn("delete", (Object p) -> {
            deleteController.deleteRowFromTable(selectedTable, p);
            mainTable.getItems().remove(p);
            return p;
        }));

        editColumn.setCellFactory(ActionButtonTableCell.forTableColumn("edit", (Object p) -> {
            deleteController.deleteRowFromTable(selectedTable, p);
            mainTable.getItems().remove(p);
            return p;
        }));
    }

    private boolean tryDisplayTableWithValues(String selectedTable) {
        try {
            displayTableWithValues(selectedTable);
        } catch (ClassNotFoundException exception) {
            return false;
        }
        return true;
    }
}
