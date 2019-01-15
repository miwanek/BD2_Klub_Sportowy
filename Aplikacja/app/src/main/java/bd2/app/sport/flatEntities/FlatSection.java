package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.Section;
import lombok.Getter;

@Getter
public class FlatSection {

    private Long sectionId;
    private String name;
    private Long disciplineId;

    public FlatSection(Section section) {
        sectionId = section.getSectionId();
        name = section.getName();
        disciplineId = section.getDiscipline().getDisciplineId();
    }
}
