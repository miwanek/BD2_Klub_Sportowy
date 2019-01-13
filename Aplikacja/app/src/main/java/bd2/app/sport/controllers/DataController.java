package bd2.app.sport.controllers;


import bd2.app.sport.services.entity.PlayerService;
import bd2.app.sport.services.entity.SportFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DataController {

    private final SportFacilityService sportFacilityService;
    private final PlayerService playerService;

    public List<? extends Object> getDataFromSelectedTable(String selectedTable, String selectedColumn, String columnValue) {

        if(selectedTable == null) return null;

        switch(selectedTable)
        {
            case "Address" :
                return sportFacilityService.getAddresses(selectedColumn, columnValue);

            case "Hall" :
                return sportFacilityService.getHalls(selectedColumn,columnValue);

            case "SportFacility" :
                return sportFacilityService.getSportFacilities(selectedColumn, columnValue);

            case "Player" :
                return playerService.getPlayers(selectedColumn, columnValue);

            case "PlayerGroup" :
                return playerService.getPlayersGroups(selectedColumn, columnValue);
        }

        return null;
    }
}
