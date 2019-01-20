package bd2.app.sport.services.entity;

import bd2.app.sport.ConditionNotSatisfiedException;
import bd2.app.sport.entities.Representation;
import bd2.app.sport.entities.Section;
import bd2.app.sport.entities.Team;
import bd2.app.sport.entities.Tier;
import bd2.app.sport.flatEntities.FlatTeam;
import bd2.app.sport.repositories.RepresentationRepository;
import bd2.app.sport.repositories.SectionRepository;
import bd2.app.sport.repositories.TeamRepository;
import bd2.app.sport.repositories.TierRepository;
import bd2.app.sport.services.AlertFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final RepresentationRepository representationRepository;
    private final SectionRepository sectionRepository;
    private final TierRepository tierRepository;

    public List<Team> getTeams(String selectedColumn, String columnValue) {
        if (selectedColumn == null || columnValue == null) {
            return teamRepository.findAll();
        }
        return null;
    }

    public void deleteTeam(Long id) throws DataIntegrityViolationException {
        teamRepository.deleteById(id);
    }

    @Transactional
    public void addTeam(List<String> columnValuesList) {
        Representation representation = new Representation();
        representationRepository.save(representation);

        Team.TeamBuilder teamBuilder;
        try {
            teamBuilder = validateTeamData(columnValuesList, representation);
        } catch (StringIndexOutOfBoundsException | ConditionNotSatisfiedException exception) {
            AlertFactory.createAlert("Name and sex must not be null");
            return;
        }
        teamRepository.save(teamBuilder.build());
    }

    @Transactional
    public void editTeam(List<String> columnValuesList, Object entity) {
        FlatTeam flatTeam = (FlatTeam) entity;
        Team team = teamRepository.findById(flatTeam.getId()).orElse(null);

        Team.TeamBuilder teamBuilder;
        try {
            teamBuilder = validateTeamData(columnValuesList, team.getRepresentation());
        } catch (StringIndexOutOfBoundsException | ConditionNotSatisfiedException exception) {
            AlertFactory.createAlert("Name, sex must not be null");
            return;
        }

        Team temporaryTeam = teamBuilder.build();

        team.setName(temporaryTeam.getName());
        team.setSection(temporaryTeam.getSection());
        team.setSex(temporaryTeam.getSex());
        team.setTier(temporaryTeam.getTier());
    }

    private Team.TeamBuilder validateTeamData(List<String> columnValuesList, Representation representation) throws ConditionNotSatisfiedException, StringIndexOutOfBoundsException {
        Optional<Section> section = Optional.empty();
        Optional<Tier> tier = Optional.empty();
        String name = columnValuesList.get(0);
        Character sex = columnValuesList.get(1).charAt(0);

        if (!columnValuesList.get(2).isEmpty()) {
            Long sectionId = Long.parseLong(columnValuesList.get(2));
            section = sectionRepository.findById(sectionId);

            if (!section.isPresent()) {
                AlertFactory.createAlert("No section with given id exists");
                throw new ConditionNotSatisfiedException();
            }
        }

        if (!columnValuesList.get(3).isEmpty()) {
            Long tierId = Long.parseLong(columnValuesList.get(3));
            tier = tierRepository.findById(tierId);

            if (!section.isPresent()) {
                AlertFactory.createAlert("No tier with given id exists");
                throw new ConditionNotSatisfiedException();
            }
        }

        if (!sex.equals('M') && !sex.equals('K') && !sex.equals('O')) {
            AlertFactory.createAlert("Sex can only be male(M) or female(K) or both (O)");
            throw new ConditionNotSatisfiedException();
        }

        if (name.isEmpty()) {
            AlertFactory.createAlert("Team name cannot be null");
            throw new ConditionNotSatisfiedException();
        }

        Team.TeamBuilder teamBuilder = Team.builder()
                .representation(representation)
                .name(name)
                .sex(sex);
        section.ifPresent(teamBuilder::section);
        tier.ifPresent(teamBuilder::tier);

        return teamBuilder;
    }
}
