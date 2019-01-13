package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.SportFacility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlatSportFacility {

    private Long facilityId;

    private String name;

    private String type;

    private Long addressId;

    public FlatSportFacility(SportFacility sportFacility) {
        facilityId = sportFacility.getFacilityId();
        name = sportFacility.getName();
        type = sportFacility.getType();
        addressId = sportFacility.getAddress().getAddressId();
    }
}
