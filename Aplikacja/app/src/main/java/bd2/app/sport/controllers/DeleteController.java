package bd2.app.sport.controllers;

import bd2.app.sport.entities.Address;
import bd2.app.sport.entities.Tournament;
import bd2.app.sport.flatEntities.FlatGame;
import bd2.app.sport.flatEntities.FlatGameParticipation;
import bd2.app.sport.flatEntities.FlatPlayer;
import bd2.app.sport.flatEntities.FlatPlayerDiscipline;
import bd2.app.sport.flatEntities.FlatPlayerGroup;
import bd2.app.sport.flatEntities.FlatPlayerTeam;
import bd2.app.sport.flatEntities.FlatSection;
import bd2.app.sport.flatEntities.FlatSportFacility;
import bd2.app.sport.flatEntities.FlatTeam;
import bd2.app.sport.services.entity.GameService;
import bd2.app.sport.services.entity.PlayerService;
import bd2.app.sport.services.entity.SectionService;
import bd2.app.sport.services.entity.SportFacilityService;
import bd2.app.sport.services.entity.TeamService;
import bd2.app.sport.services.entity.TournamentService;
import javafx.scene.control.Alert;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class DeleteController {

    private final PlayerService playerService;
    private final GameService gameService;
    private final SportFacilityService sportFacilityService;
    private final TeamService teamService;
    private final SectionService sectionService;
    private final TournamentService tournamentService;

    public void deleteRowFromTable(String selectedTable, Object toDelete)  {

        Long id;
        String stringId;

        try {
            switch (selectedTable) {
                case "Address":
                    id = ((Address) toDelete).getAddressId();
                    sportFacilityService.deleteAddress(id);
                    break;

                case "SportFacility":
                    id = ((FlatSportFacility) toDelete).getFacilityId();
                    sportFacilityService.deleteSportFacility(id);
                    break;

                case "Game":
                    id = ((FlatGame) toDelete).getGameId();
                    gameService.deleteGame(id);
                    break;

                case "GameParticipation":
                    FlatGameParticipation flatGameParticipation = (FlatGameParticipation) toDelete;
                    Long gameId = flatGameParticipation.getGameId();
                    String representationId = flatGameParticipation.getRepresentationId();
                    gameService.deleteGameParticipation(gameId, representationId);
                    break;

                case "Player":
                    stringId = ((FlatPlayer) toDelete).getId();
                    playerService.deletePlayer(stringId);
                    break;

                case "PlayerGroup":
                    id = ((FlatPlayerGroup) toDelete).getGroupId();
                    playerService.deletePlayerGroup(id);
                    break;

                case "PlayerDiscipline":
                    playerService.deletePlayerDiscipline((FlatPlayerDiscipline) toDelete);
                    break;

                case "PlayerTeam":
                    playerService.deletePlayerTeam(((FlatPlayerTeam) toDelete));
                    break;

                case "Section":
                    id = ((FlatSection) toDelete).getSectionId();
                    sectionService.deleteSection(id);
                    break;

                case "Team":
                    stringId = ((FlatTeam) toDelete).getId();
                    teamService.deleteTeam(stringId);
                    break;

                case "Tournament":
                    id = ((Tournament) toDelete).getTournamentId();
                    tournamentService.deleteTournament(id);
                    break;
            }
        }
        catch (DataIntegrityViolationException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database error");
            alert.setContentText("Data integrity violation. \nForeign key in another table may block operation");
            alert.showAndWait();
        }
    }
}
