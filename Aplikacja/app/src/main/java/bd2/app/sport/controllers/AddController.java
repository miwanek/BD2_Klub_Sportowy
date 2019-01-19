package bd2.app.sport.controllers;

import bd2.app.sport.entities.Address;
import bd2.app.sport.flatEntities.FlatGame;
import bd2.app.sport.flatEntities.FlatGameParticipation;
import bd2.app.sport.flatEntities.FlatPlayer;
import bd2.app.sport.flatEntities.FlatPlayerDiscipline;
import bd2.app.sport.flatEntities.FlatPlayerGroup;
import bd2.app.sport.flatEntities.FlatPlayerTeam;
import bd2.app.sport.flatEntities.FlatSection;
import bd2.app.sport.flatEntities.FlatSportFacility;
import bd2.app.sport.flatEntities.FlatTeam;
import bd2.app.sport.flatEntities.FlatTournamentDiscipline;
import bd2.app.sport.services.AlertFactory;
import bd2.app.sport.services.entity.GameService;
import bd2.app.sport.services.entity.PlayerService;
import bd2.app.sport.services.entity.SectionService;
import bd2.app.sport.services.entity.SportFacilityService;
import bd2.app.sport.services.entity.TeamService;
import bd2.app.sport.services.entity.TournamentService;
import bd2.app.sport.services.entity.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AddController {

    private final PlayerService playerService;
    private final GameService gameService;
    private final SportFacilityService sportFacilityService;
    private final TeamService teamService;
    private final SectionService sectionService;
    private final TournamentService tournamentService;
    private final TrainerService trainerService;

    public void addRowToTable(String selectedTable, List<String> columnValuesList) {

        switch (selectedTable) {
            case "Game":
                gameService.addGame(columnValuesList);
                break;

            case "GameParticipation":
                gameService.addGameParticipation(columnValuesList);
                break;

            case "Player":
                    playerService.addPlayer(columnValuesList);
                break;

            case "PlayerGroup":
                playerService.addPlayerGroup(columnValuesList);
                break;

            case "PlayerDiscipline":
                playerService.addPlayerDiscipline(columnValuesList);
                break;

            case "PlayerTeam":
                playerService.addPlayerTeam(columnValuesList);
                break;

            case "Team":
                teamService.addTeam(columnValuesList);
                break;

            case "RepresentationTrainer":
                trainerService.addRepresentationTrainer(columnValuesList);
                break;
        }
    }
}