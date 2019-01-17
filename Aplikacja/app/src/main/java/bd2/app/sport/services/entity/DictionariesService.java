package bd2.app.sport.services.entity;

import bd2.app.sport.entities.Discipline;
import bd2.app.sport.entities.Tier;
import bd2.app.sport.entities.Unit;
import bd2.app.sport.repositories.DisciplineRepository;
import bd2.app.sport.repositories.TierRepository;
import bd2.app.sport.repositories.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DictionariesService {

    private final TierRepository tierRepository;
    private final DisciplineRepository disciplineRepository;
    private final UnitRepository unitRepository;

    public List<Tier> getTiers() {
        return tierRepository.findAll();
    }

    public List<Discipline> getDisciplines() {
        return disciplineRepository.findAll();
    }

    public List<Unit> getUnits() {
        return unitRepository.findAll();
    }
}
