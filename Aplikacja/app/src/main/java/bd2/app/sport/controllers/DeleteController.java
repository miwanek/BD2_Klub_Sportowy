package bd2.app.sport.controllers;


import bd2.app.sport.entities.Address;
import bd2.app.sport.entities.SportFacility;
import bd2.app.sport.flatEntities.FlatSportFacility;
import bd2.app.sport.services.entity.AddressService;
import bd2.app.sport.services.entity.SportFacilityService;
import javafx.scene.control.Alert;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DeleteController {

    private final AddressService addressService;

    private final SportFacilityService sportFacilityService;

    public void deleteRowFromTable(String selectedTable, Object toDelete)  {

        try {
            Long id;
            switch (selectedTable) {
                case "Address":
                    id = ((Address) toDelete).getAddressId();
                    addressService.deleteAddress(id);
                    break;

                case "SportFacility":
                    id = ((FlatSportFacility) toDelete).getFacilityId();
                    sportFacilityService.deleteSportFacility(id);
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
