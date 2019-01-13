package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.PlayerGroup;
import bd2.app.sport.entities.Section;
import bd2.app.sport.entities.Tier;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@EqualsAndHashCode
public class FlatPlayerGroup {

    private Long groupId;
    private Character sex;
    private String name;
    private Long sectionId;
    private String tierId;

    public FlatPlayerGroup(PlayerGroup playerGroup) {
        groupId = playerGroup.getGroupId();
        sectionId = playerGroup.getSection().getSectionId();
        sex = playerGroup.getSex();
        name = playerGroup.getName();
        tierId = playerGroup.getTier().getTierId();
    }
}
