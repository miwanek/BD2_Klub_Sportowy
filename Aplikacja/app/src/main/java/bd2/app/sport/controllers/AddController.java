package bd2.app.sport.controllers;

import bd2.app.sport.services.entity.GameService;
import bd2.app.sport.services.entity.PlayerService;
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
    private final TeamService teamService;
    private final TrainerService trainerService;
    private final TournamentService tournamentService;

    public void addRowToTable(String selectedTable, List<String> columnValuesList) {

        switch (selectedTable) {
            case "Game":
                gameService.addGame(columnValuesList);
                break;

            case "GameParticipation":
                gameService.addGameParticipation(columnValuesList);
                break;

            case "TournamentParticipation":
                tournamentService.addTournamentParticipation(columnValuesList);
                break;

            case "TournamentDiscipline":
                tournamentService.addTournamentDiscipline(columnValuesList);
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