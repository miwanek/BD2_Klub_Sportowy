package bd2.app.sport.controllers;

import bd2.app.sport.services.entity.DictionariesService;
import bd2.app.sport.services.entity.GameService;
import bd2.app.sport.services.entity.PlayerService;
import bd2.app.sport.services.entity.SectionService;
import bd2.app.sport.services.entity.SportFacilityService;
import bd2.app.sport.services.entity.TeamService;
import bd2.app.sport.services.entity.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DataController {

    private final SportFacilityService sportFacilityService;
    private final PlayerService playerService;
    private final DictionariesService dictionariesService;
    private final GameService gameService;
    private final TeamService teamService;
    private final TournamentService tournamentService;
    private final SectionService sectionService;

    public List<? extends Object> getDataFromSelectedTable(String selectedTable, String selectedColumn, String columnValue) {

        if(selectedTable == null) return null;

        switch(selectedTable)
        {
            case "Address" :
                return sportFacilityService.getAddresses(selectedColumn, columnValue);

            case "Discipline" :
                return dictionariesService.getDisciplines();

            case "Team" :
                return teamService.getTeams(selectedColumn, columnValue);

            case "Hall" :
                return sportFacilityService.getHalls(selectedColumn,columnValue);

            case "Game" :
                return gameService.getGames(selectedColumn,columnValue);

            case "GameParticipation" :
                return gameService.getGameParticipation(selectedColumn,columnValue);

            case "SportFacility" :
                return sportFacilityService.getSportFacilities(selectedColumn, columnValue);

            case "Player" :
                return playerService.getPlayers(selectedColumn, columnValue);

            case "PlayerGroup" :
                return playerService.getPlayersGroups(selectedColumn, columnValue);

            case "PlayerDiscipline" :
                return playerService.getPlayerDisciplines(selectedColumn, columnValue);

            case "PlayerTeam" :
                return playerService.getPlayerTeams(selectedColumn, columnValue);

            case "Section" :
                return sectionService.getSections(selectedColumn, columnValue);

            case "Tier" :
                return dictionariesService.getTiers();

            case "Tournament" :
                return tournamentService.getTournaments(selectedColumn, columnValue);

            case "Unit" :
                return dictionariesService.getUnits();
        }

        return null;
    }
}
