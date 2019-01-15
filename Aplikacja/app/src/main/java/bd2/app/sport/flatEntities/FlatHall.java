package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.Hall;
import lombok.Getter;

@Getter
public class FlatHall {

    private Long hallId;
    private String type;
    private Character active;
    private Long maxPlayers;
    private Long maxFans;
    private Long rentalCost;
    private Long sportFacilityId;

    public FlatHall(Hall hall) {

        hallId = hall.getHallId();
        type = hall.getType();
        active = hall.getActive();
        maxFans = hall.getMaxFans();
        maxPlayers = hall.getMaxPlayers();
        rentalCost = hall.getRentalCost();
        sportFacilityId = hall.getSportFacility().getFacilityId();
    }
}
