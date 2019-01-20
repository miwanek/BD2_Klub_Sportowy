package bd2.app.sport.services.entity;

import bd2.app.sport.entities.Team;
import bd2.app.sport.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public List<Team> getTeams(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return teamRepository.findAll();
        }
        return null;
    }

    public void deleteTeam(Long id) throws DataIntegrityViolationException {
        teamRepository.deleteById(id);
    }

    public void addTeam(List<String> columnValuesList) {

    }

    public void editTeam(List<String> columnValuesList, Object entity) {
    }
}
