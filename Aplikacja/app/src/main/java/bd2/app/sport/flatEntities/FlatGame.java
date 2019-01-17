package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.Game;
import bd2.app.sport.entities.Tournament;
import bd2.app.sport.entities.Unit;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FlatGame {

        private Long gameId;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String type;
        private String referee;
        private String sex;
        private Long hallId;
        private Long tournamentId;
        private Long disciplineId;
        private Long unitId;

        public FlatGame(Game game) {
            gameId = game.getGameId();
            startDate = game.getStartDate();
            endDate = game.getEndDate();
            type = game.getType();
            referee = game.getReferee();
            sex = game.getSex();
            hallId = game.getHall().getHallId();
            disciplineId = game.getDiscipline().getDisciplineId();

            Tournament tournament = game.getTournament();
            tournamentId = tournament != null ? tournament.getTournamentId() : null;

            Unit unit = game.getUnit();
            unitId = unit != null ? unit.getUnitId() : null;
    }
}
