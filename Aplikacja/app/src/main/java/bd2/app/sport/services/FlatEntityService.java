package bd2.app.sport.services;

import bd2.app.sport.entities.Player;
import bd2.app.sport.entities.SportFacility;
import bd2.app.sport.flatEntities.FlatPlayer;
import bd2.app.sport.flatEntities.FlatSportFacility;

public class FlatEntityService {

    public static Object getFlatEntity(String selectedTable, Object entity) {


        switch (selectedTable) {
            case "SportFacility":
                return new FlatSportFacility((SportFacility) entity);

            case "Player":
                return new FlatPlayer((Player) entity);

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

            default:
                return currentClass;
        }
    }
}
