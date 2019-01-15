package bd2.app.sport.services;

import bd2.app.sport.entities.Hall;
import bd2.app.sport.entities.Player;
import bd2.app.sport.entities.PlayerDiscipline;
import bd2.app.sport.entities.PlayerGroup;
import bd2.app.sport.entities.PlayerTeam;
import bd2.app.sport.entities.SportFacility;
import bd2.app.sport.entities.Team;
import bd2.app.sport.entities.Tournament;
import bd2.app.sport.flatEntities.FlatHall;
import bd2.app.sport.flatEntities.FlatPlayer;
import bd2.app.sport.flatEntities.FlatPlayerDiscipline;
import bd2.app.sport.flatEntities.FlatPlayerGroup;
import bd2.app.sport.flatEntities.FlatPlayerTeam;
import bd2.app.sport.flatEntities.FlatSportFacility;
import bd2.app.sport.flatEntities.FlatTeam;

public class FlatEntityService {

    public static Object getFlatEntity(String selectedTable, Object entity) {

        switch (selectedTable) {
            case "SportFacility":
                return new FlatSportFacility((SportFacility) entity);

            case "Hall":
                return new FlatHall((Hall) entity);

            case "Player":
                return new FlatPlayer((Player) entity);

            case "PlayerGroup":
                return new FlatPlayerGroup((PlayerGroup) entity);

            case "PlayerTeam":
                return new FlatPlayerTeam((PlayerTeam) entity);

            case "PlayerDiscipline":
                return new FlatPlayerDiscipline((PlayerDiscipline) entity);

            case "Team":
                return new FlatTeam((Team) entity);

            default:
                return entity;
        }
    }

    public static Class getClass(Class currentClass, String selectedTable) {
        switch (selectedTable) {
            case "SportFacility":
                return FlatSportFacility.class;

            case "Hall":
                return FlatHall.class;

            case "Player":
                return FlatPlayer.class;

            case "PlayerGroup":
                return FlatPlayerGroup.class;

            case "PlayerDiscipline":
                return FlatPlayerDiscipline.class;

            case "PlayerTeam":
                return FlatPlayerTeam.class;

            case "Team":
                return FlatTeam.class;

            case "Tournament":
                return Tournament.class;

            default:
                return currentClass;
        }
    }
}
