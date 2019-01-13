package bd2.app.sport.services;

import bd2.app.sport.entities.Hall;
import bd2.app.sport.entities.Player;
import bd2.app.sport.entities.PlayerGroup;
import bd2.app.sport.entities.SportFacility;
import bd2.app.sport.flatEntities.FlatHall;
import bd2.app.sport.flatEntities.FlatPlayer;
import bd2.app.sport.flatEntities.FlatPlayerGroup;
import bd2.app.sport.flatEntities.FlatSportFacility;

public class FlatEntityService {

    public static Object getFlatEntity(String selectedTable, Object entity) {


        switch (selectedTable) {
            case "SportFacility":
                return new FlatSportFacility((SportFacility) entity);

            case "Player":
                return new FlatPlayer((Player) entity);

            case "Hall":
                return new FlatHall((Hall) entity);

            case "PlayerGroup":
                return new FlatPlayerGroup((PlayerGroup) entity);

            default:
                return entity;
        }
    }

    public static Class getClass(Class currentClass, String selectedTable) {
        switch (selectedTable) {
            case "SportFacility":
                return FlatSportFacility.class;

            case "Player":
                return FlatPlayer.class;

            case "Hall":
                return FlatHall.class;

            case "PlayerGroup":
                return FlatPlayerGroup.class;

            default:
                return currentClass;
        }
    }
}
