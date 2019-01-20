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

    public void editTableRow(String selectedTable, List<String> columnValuesList, Object entity) {
        switch (selectedTable) {
            case "Game":
                gameService.editGame(columnValuesList, entity);
                break;

            case "GameParticipation":
                gameService.editGameParticipation(columnValuesList, entity);
                break;

            case "Player":
                playerService.editPlayer(columnValuesList, entity);
                break;

            case "PlayerGroup":
                playerService.editPlayerGroup(columnValuesList, entity);
                break;

            case "Team":
                teamService.editTeam(columnValuesList, entity);
                break;

            case "RepresentationTrainer":
                trainerService.editRepresentationTrainer(columnValuesList, entity);
                break;
        }
    }
}
