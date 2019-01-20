package bd2.app.sport.services.entity;

import bd2.app.sport.ConditionNotSatisfiedException;
import bd2.app.sport.entities.Representation;
import bd2.app.sport.entities.RepresentationTrainer;
import bd2.app.sport.entities.Trainer;
import bd2.app.sport.entities.TrainerDiscipline;
import bd2.app.sport.flatEntities.FlatRepresentationTrainer;
import bd2.app.sport.repositories.RepresentationRepository;
import bd2.app.sport.repositories.RepresentationTrainerRepository;
import bd2.app.sport.repositories.TrainerDisciplineRepository;
import bd2.app.sport.repositories.TrainerRepository;
import bd2.app.sport.services.AlertFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final TrainerDisciplineRepository trainerDisciplineRepository;
    private final RepresentationTrainerRepository representationTrainerRepository;
    private final RepresentationRepository representationRepository;

    public List<Trainer> getTrainers(String selectedColumn, String columnValue) {
        if (selectedColumn == null || columnValue == null) {
            return trainerRepository.findAll();
        }
        return null;
    }

    public List<TrainerDiscipline> getTrainerDisciplines(String selectedColumn, String columnValue) {
        if (selectedColumn == null || columnValue == null) {
            return trainerDisciplineRepository.findAll();
        }
        return null;
    }

    public List<RepresentationTrainer> getRepresentationsTrainers(String selectedColumn, String columnValue) {
        if (selectedColumn == null || columnValue == null) {
            return representationTrainerRepository.findAll();
        }
        return null;
    }

    public void deleteRepresentationTrainer(Long id) throws DataIntegrityViolationException {
        representationTrainerRepository.deleteById(id);
    }


    @Transactional
    public void addRepresentationTrainer(List<String> columnValuesList) {
        RepresentationTrainer.RepresentationTrainerBuilder builder;
        try {
            builder = validateRepresentationTrainerData(columnValuesList);
        } catch (ConditionNotSatisfiedException exception) {
            return;
        } catch (StringIndexOutOfBoundsException exception) {
            AlertFactory.createAlert("Start date cannot be null");
            return;
        }
        representationTrainerRepository.save(builder.build());
    }

    @Transactional
    public void editRepresentationTrainer(List<String> columnValuesList, Object entity)  {
        FlatRepresentationTrainer flatRepresentationTrainer = (FlatRepresentationTrainer) entity;
        RepresentationTrainer representationTrainer = representationTrainerRepository.findById(flatRepresentationTrainer.getContractNumber()).orElse(null);

        RepresentationTrainer temporaryRepresentationTrainer;
        try {
            temporaryRepresentationTrainer = validateRepresentationTrainerData(columnValuesList).build();
        } catch (ConditionNotSatisfiedException exception) {
            return;
        } catch (StringIndexOutOfBoundsException exception) {
            AlertFactory.createAlert("Start date cannot be null");
            return;
        }

        representationTrainer.setRepresentation(temporaryRepresentationTrainer.getRepresentation());
        representationTrainer.setTrainer(temporaryRepresentationTrainer.getTrainer());
        representationTrainer.setStartDate(temporaryRepresentationTrainer.getStartDate());
        representationTrainer.setEndDate(temporaryRepresentationTrainer.getEndDate());
    }

    private RepresentationTrainer.RepresentationTrainerBuilder validateRepresentationTrainerData(List<String> columnValuesList) throws ConditionNotSatisfiedException, StringIndexOutOfBoundsException {
        Optional<Trainer> trainer = Optional.empty();
        Optional<Representation> representation = Optional.empty();


        if (!columnValuesList.get(0).isEmpty()) {
            Long trainerId = Long.parseLong(columnValuesList.get(0));
            trainer = trainerRepository.findById(trainerId);

            if (!trainer.isPresent()) {
                AlertFactory.createAlert("No trainer with given id exists");
                throw new ConditionNotSatisfiedException();
            }
        }

        if (!columnValuesList.get(1).isEmpty()) {
            Long representationId = Long.parseLong(columnValuesList.get(1));
            representation = representationRepository.findById(representationId);

            if (!representation.isPresent()) {
                AlertFactory.createAlert("No representation with given id exists");
                throw new ConditionNotSatisfiedException();
            }
        }

        LocalDate startDate = null;
        LocalDate endDate = null;

        if (!columnValuesList.get(2).isEmpty()) {
            try {
                startDate = LocalDate.parse(columnValuesList.get(2), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException exception) {
                AlertFactory.createAlert("Provide proper start date with correct format: \"yyyy-MM-dd\"");
                throw new ConditionNotSatisfiedException();
            }
        } else {
            AlertFactory.createAlert("Start date must not be null");
            throw new ConditionNotSatisfiedException();
        }

        if (!columnValuesList.get(3).isEmpty()) {
            try {
                endDate = LocalDate.parse(columnValuesList.get(3), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException exception) {
                AlertFactory.createAlert("Provide proper end date with correct format: \"yyyy-MM-dd\"");
                throw new ConditionNotSatisfiedException();
            }
        }

        if (endDate != null) {
            if (startDate.isAfter(endDate)) {
                AlertFactory.createAlert("Start date must be before end date");
                throw new ConditionNotSatisfiedException();
            }
        }

        List<RepresentationTrainer> conflictList = representationTrainerRepository.findByRepresentationAndTrainer(
                representation.get(),trainer.get());


        ///TODO test this throughly
        for(RepresentationTrainer element : conflictList) {
            LocalDate otherEndDate = element.getEndDate();
            if(otherEndDate != null) {
                if(otherEndDate.isAfter(startDate)){
                    if(endDate == null || endDate.isBefore(otherEndDate)) {
                        AlertFactory.createAlert("There is already contract for this period between Trainer and Representation");
                        throw new ConditionNotSatisfiedException();
                    }
                }
            }
            else if(element.getStartDate().isBefore(startDate)) {
                AlertFactory.createAlert("There is already contract for this period between Trainer and Representation");
                throw new ConditionNotSatisfiedException();
            }
        }

        RepresentationTrainer.RepresentationTrainerBuilder representationTrainerBuilder = RepresentationTrainer.builder()
                .startDate(startDate)
                .endDate(endDate);
        trainer.ifPresent(representationTrainerBuilder::trainer);
        representation.ifPresent(representationTrainerBuilder::representation);

        return representationTrainerBuilder;
    }

}
