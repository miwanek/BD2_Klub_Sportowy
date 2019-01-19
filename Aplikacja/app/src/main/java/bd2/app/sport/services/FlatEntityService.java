package bd2.app.sport.services;

import bd2.app.sport.entities.Game;
import bd2.app.sport.entities.GameParticipation;
import bd2.app.sport.entities.Hall;
import bd2.app.sport.entities.Player;
import bd2.app.sport.entities.PlayerDiscipline;
import bd2.app.sport.entities.PlayerGroup;
import bd2.app.sport.entities.PlayerTeam;
import bd2.app.sport.entities.RepresentationTrainer;
import bd2.app.sport.entities.Section;
import bd2.app.sport.entities.SportFacility;
import bd2.app.sport.entities.Team;
import bd2.app.sport.entities.TrainerDiscipline;
import bd2.app.sport.flatEntities.FlatGame;
import bd2.app.sport.flatEntities.FlatGameParticipation;
import bd2.app.sport.flatEntities.FlatHall;
import bd2.app.sport.flatEntities.FlatPlayer;
import bd2.app.sport.flatEntities.FlatPlayerDiscipline;
import bd2.app.sport.flatEntities.FlatPlayerGroup;
import bd2.app.sport.flatEntities.FlatPlayerTeam;
import bd2.app.sport.flatEntities.FlatRepresentationTrainer;
import bd2.app.sport.flatEntities.FlatSection;
import bd2.app.sport.flatEntities.FlatSportFacility;
import bd2.app.sport.flatEntities.FlatTeam;
import bd2.app.sport.flatEntities.FlatTrainerDiscipline;

public class FlatEntityService {

    public static Object getFlatEntity(String selectedTable, Object entity) {

        switch (selectedTable) {
            case "SportFacility":
                return new FlatSportFacility((SportFacility) entity);

            case "Hall":
                return new FlatHall((Hall) entity);

            case "Player":
                return new FlatPlayer((Player) entity);

            case "Game":
                return new FlatGame((Game) entity);

            case "GameParticipation":
                return new FlatGameParticipation((GameParticipation) entity);

            case "PlayerGroup":
                return new FlatPlayerGroup((PlayerGroup) entity);

            case "PlayerTeam":
                return new FlatPlayerTeam((PlayerTeam) entity);

            case "PlayerDiscipline":
                return new FlatPlayerDiscipline((PlayerDiscipline) entity);

            case "Section":
                return new FlatSection((Section) entity);

            case "Team":
                return new FlatTeam((Team) entity);

            case "TrainerDiscipline":
                return new FlatTrainerDiscipline((TrainerDiscipline) entity);

            case "RepresentationTrainer":
                return new FlatRepresentationTrainer((RepresentationTrainer) entity);

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

            case "Game":
                return FlatGame.class;

            case "GameParticipation":
                return FlatGameParticipation.class;

            case "Player":
                return FlatPlayer.class;

            case "PlayerGroup":
                return FlatPlayerGroup.class;

            case "PlayerDiscipline":
                return FlatPlayerDiscipline.class;

            case "PlayerTeam":
                return FlatPlayerTeam.class;

            case "Section":
                return FlatSection.class;

            case "Team":
                return FlatTeam.class;

            case "TrainerDiscipline":
                return FlatTrainerDiscipline.class;

            case "RepresentationTrainer":
                return FlatRepresentationTrainer.class;

            default:
                return currentClass;
        }
    }
}
