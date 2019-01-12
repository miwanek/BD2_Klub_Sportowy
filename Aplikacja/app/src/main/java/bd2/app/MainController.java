package bd2.app;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @FXML
    private TextField elementTextFirld;

    @FXML
    private ComboBox<?> tableFieldList;

    @FXML
    private Button searchButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox<?> tableList;

}
