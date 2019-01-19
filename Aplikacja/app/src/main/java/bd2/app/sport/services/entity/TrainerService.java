package bd2.app.sport.services.entity;

import bd2.app.sport.entities.RepresentationTrainer;
import bd2.app.sport.entities.Trainer;
import bd2.app.sport.entities.TrainerDiscipline;
import bd2.app.sport.repositories.RepresentationTrainerRepository;
import bd2.app.sport.repositories.TrainerDisciplineRepository;
import bd2.app.sport.repositories.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final TrainerDisciplineRepository trainerDisciplineRepository;
    private final RepresentationTrainerRepository representationTrainerRepository;

    public List<Trainer> getTrainers(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return trainerRepository.findAll();
        }
        return null;
    }

    public List<TrainerDiscipline> getTrainerDisciplines(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return trainerDisciplineRepository.findAll();
        }
        return null;
    }

    public List<RepresentationTrainer> getRepresentationsTrainers(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return representationTrainerRepository.findAll();
        }
        return null;
    }

    public void deleteRepresentationTrainer(Long id) throws DataIntegrityViolationException {
        representationTrainerRepository.deleteById(id);
    }


    public void addRepresentationTrainer(List<String> columnValuesList) {
    }
}
