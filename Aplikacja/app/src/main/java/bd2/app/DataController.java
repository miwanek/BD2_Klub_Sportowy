package bd2.app;


import bd2.app.sport.services.AddressService;
import bd2.app.sport.services.SportFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DataController {

    private final AddressService addressService;

    private final SportFacilityService sportFacilityService;

    public List<? extends Object> chooseSearchTable(String selectedTable, String selectedColumn, String columnValue) {

        if(selectedTable == null) return null;

        switch(selectedTable)
        {
            case "Address" :
                return addressService.getAdresses(selectedColumn, columnValue);

            case "SportFacility" :
                return sportFacilityService.getSportFacilities(selectedColumn, columnValue);

        }

        return null;
    }
}
