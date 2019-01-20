package bd2.app.sport.repositories;

import bd2.app.sport.classId.TournamentDisciplinesId;
import bd2.app.sport.entities.Tournament;
import bd2.app.sport.entities.TournamentDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentDisciplineRepository extends JpaRepository<TournamentDiscipline, TournamentDisciplinesId> {

    void deleteById_Discipline_DisciplineIdAndId_Tournament_TournamentId(Long disciplineId, Long tournamentId);

    List<TournamentDiscipline> findById_Tournament(Tournament tournament);
}
