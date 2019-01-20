package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.RepresentationTrainer;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class FlatRepresentationTrainer {

    private Long contractNumber;
    private Long trainerId;
    private Long representationId;
    private LocalDate startDate;
    private LocalDate endDate;

    public FlatRepresentationTrainer(RepresentationTrainer representationTrainer) {
        contractNumber = representationTrainer.getContractNumber();
        trainerId = representationTrainer.getTrainer().getTrainerId();
        representationId = representationTrainer.getRepresentation().getRepresentationId();
        startDate = representationTrainer.getStartDate();
        endDate = representationTrainer.getEndDate();
    }
}
