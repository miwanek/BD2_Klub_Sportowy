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
        //mainTable.setEditable(true);
        String selectedTable = tableList.getValue() != null ? tableList.getValue().toString() : null;
        String selectedColumn = tableFieldList.getValue() != null ? tableFieldList.getValue().toString() : null;
        String columnValue = null;

        List<? extends Object> data =  dataController.chooseSearchTable(selectedTable, selectedColumn, columnValue);

//        System.out.println(data.toString());

        try {
             Class selectedClass = Class.forName("bd2.app.sport." + selectedTable);
             Field[] fields = selectedClass.getDeclaredFields();

             System.out.println(fields.length);

             for(int i = 0 ; i < fields.length; i++) {
                 TableColumn tableColumn = (TableColumn)mainTable.getColumns().get(i);
                 System.out.println(fields[i].getName());

                 tableColumn.setCellValueFactory(new PropertyValueFactory<>(fields[i].getName()));
                 tableColumn.setText(fields[i].getName());
             }

             TableColumn deleteColumn = (TableColumn)mainTable.getColumns().get(fields.length);
             TableColumn editColumn = (TableColumn)mainTable.getColumns().get(fields.length + 1);

             deleteColumn.setText("delete");
             editColumn.setText("edit");

            deleteColumn.setCellFactory(ActionButtonTableCell.forTableColumn("delete", (Object p) -> {
                mainTable.getItems().remove(p);
                return p;
            }));

            editColumn.setCellFactory(ActionButtonTableCell.forTableColumn("edit", (Object p) -> {
                mainTable.getItems().remove(p);
                return p;
            }));

        }
        catch (Exception ClassNotFoundException) {
            return;
        }

//        TableColumn column1 = (TableColumn)mainTable.getColumns().get(0);
//        column1.setCellValueFactory(new PropertyValueFactory<>("id_adresu"));
//
//        TableColumn column2 = (TableColumn)mainTable.getColumns().get(1);
//        column2.setCellValueFactory(new PropertyValueFactory<>("miasto"));
//
//        TableColumn column3 = (TableColumn)mainTable.getColumns().get(2);
//        column3.setCellValueFactory(new PropertyValueFactory<>("ulica"));
//
//        TableColumn column4 = (TableColumn)mainTable.getColumns().get(3);
//        column4.setCellValueFactory(new PropertyValueFactory<>("nr_budynku"));

        if(data != null) data.forEach(row -> mainTable.getItems().add(row));
    }

}
