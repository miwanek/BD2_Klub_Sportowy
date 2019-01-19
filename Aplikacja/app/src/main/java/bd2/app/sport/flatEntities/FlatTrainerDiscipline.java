package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.TrainerDiscipline;
import lombok.Getter;

@Getter
public class FlatTrainerDiscipline {
    private Long trainerId;
    private Long disciplineId;

    public FlatTrainerDiscipline(TrainerDiscipline trainerDiscipline) {
        trainerId = trainerDiscipline.getTrainer().getTrainerId();
        disciplineId = trainerDiscipline.getDiscipline().getDisciplineId();
    }
}
