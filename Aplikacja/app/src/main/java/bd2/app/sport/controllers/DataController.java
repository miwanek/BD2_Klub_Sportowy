package bd2.app.sport.controllers;


import bd2.app.sport.services.entity.AddressService;
import bd2.app.sport.services.entity.SportFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DataController {

    private final AddressService addressService;

    private final SportFacilityService sportFacilityService;

    public List<? extends Object> getDataFromSelectedTable(String selectedTable, String selectedColumn, String columnValue) {

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
