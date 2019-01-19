package bd2.app.sport.controllers;

import bd2.app.sport.services.entity.GameService;
import bd2.app.sport.services.entity.PlayerService;
import bd2.app.sport.services.entity.TeamService;
import bd2.app.sport.services.entity.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EditController {


    private final PlayerService playerService;
    private final GameService gameService;
    private final TeamService teamService;
    private final TrainerService trainerService;

    public void editTableRow(String selectedTable, List<String> columnValuesList) {
        switch (selectedTable) {
            case "Game":
                gameService.editGame(columnValuesList);
                break;

            case "GameParticipation":
                gameService.editGameParticipation(columnValuesList);
                break;

            case "Player":
                playerService.editPlayer(columnValuesList);
                break;

            case "PlayerGroup":
                playerService.editPlayerGroup(columnValuesList);
                break;

            case "PlayerDiscipline":
                playerService.editPlayerDiscipline(columnValuesList);
                break;

            case "PlayerTeam":
                playerService.editPlayerTeam(columnValuesList);
                break;

            case "Team":
                teamService.editTeam(columnValuesList);
                break;

            case "RepresentationTrainer":
                trainerService.editRepresentationTrainer(columnValuesList);
                break;
        }
    }
}
