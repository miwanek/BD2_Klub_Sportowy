package bd2.app.sport.controllers;


import bd2.app.sport.entities.Address;
import bd2.app.sport.flatEntities.FlatPlayer;
import bd2.app.sport.flatEntities.FlatSportFacility;
import bd2.app.sport.services.entity.PlayerService;
import bd2.app.sport.services.entity.SportFacilityService;
import javafx.scene.control.Alert;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class DeleteController {

    private final PlayerService playerService;
    private final SportFacilityService sportFacilityService;

    public void deleteRowFromTable(String selectedTable, Object toDelete)  {

        Long id;
        String stringId;

        try {
            switch (selectedTable) {
                case "Address":
                    id = ((Address) toDelete).getAddressId();
                    sportFacilityService.deleteAddress(id);
                    break;

                case "SportFacility":
                    id = ((FlatSportFacility) toDelete).getFacilityId();
                    sportFacilityService.deleteSportFacility(id);
                    break;

                case "Player":
                    stringId = ((FlatPlayer) toDelete).getId();
                    playerService.deletePlayer(stringId);
                    break;
            }
        }
        catch (DataIntegrityViolationException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database error");
            alert.setContentText("Data integrity violation. \nForeign key in another table may block operation");
            alert.showAndWait();
        }
    }
}
