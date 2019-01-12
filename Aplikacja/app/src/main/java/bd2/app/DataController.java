package bd2.app;


import bd2.app.obiekt_sportowy.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DataController {

    private final AddressService addressService;

    public List<? extends Object> chooseSearchTable(String selectedTable, String selectedColumn, String columnValue) {

        if(selectedTable == null) return null;

        switch(selectedTable)
        {
            case "Adres" :
                return addressService.getAdresses(selectedColumn, columnValue);

            case "zawodnik" :

        }

        return null;
    }
}
