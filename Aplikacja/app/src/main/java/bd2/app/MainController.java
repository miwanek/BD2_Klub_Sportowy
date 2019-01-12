package bd2.app;


import bd2.app.cell.ActionButtonTableCell;
import bd2.app.sport.Address;
import bd2.app.sport.SportFacility;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Field;
import java.util.List;

import static javafx.scene.control.TableView.CONSTRAINED_RESIZE_POLICY;
import static javafx.scene.control.TableView.UNCONSTRAINED_RESIZE_POLICY;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final DataController dataController;

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
        mainTable.getItems().clear();
        mainTable.getColumns().clear();
        mainTable.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
        String selectedTable = tableList.getValue() != null ? tableList.getValue().toString() : null;
        String selectedColumn = tableFieldList.getValue() != null ? tableFieldList.getValue().toString() : null;
        String columnValue = null;

        List<? extends Object> data = dataController.chooseSearchTable(selectedTable, selectedColumn, columnValue);

        try {
            displayTableWithValues(selectedTable);
        } catch (Exception ClassNotFoundException) {
            return;
        }

        if (data != null) data.forEach(row -> mainTable.getItems().add(row));
    }

    private void displayTableWithValues(String selectedTable) throws ClassNotFoundException {
        Class selectedClass = Class.forName("bd2.app.sport." + selectedTable);
        Field[] fields = selectedClass.getDeclaredFields();

        System.out.println(fields.length);

        for (int i = 0; i < fields.length; i++) {
            TableColumn tableColumn = new TableColumn(fields[i].getName());
            System.out.println(fields[i].getName());

            tableColumn.setCellValueFactory(new PropertyValueFactory<>(fields[i].getName()));
            mainTable.getColumns().add(tableColumn);
            tableColumn.setMinWidth(100);

        }

        TableColumn deleteColumn = new TableColumn("delete");
        TableColumn editColumn = new TableColumn("edit");

//        deleteColumn.setMinWidth(100);
//        editColumn.setMinWidth(100);

        mainTable.getColumns().addAll(deleteColumn, editColumn);

        deleteColumn.setCellFactory(ActionButtonTableCell.forTableColumn("delete", (Object p) -> {

            mainTable.getItems().remove(p);
            return p;
        }));

        editColumn.setCellFactory(ActionButtonTableCell.forTableColumn("edit", (Object p) -> {
            mainTable.getItems().remove(p);
            return p;
        }));
    }
}
