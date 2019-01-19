package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.PlayerGroup;
import lombok.Getter;

@Getter
public class FlatPlayerGroup {

    private Long groupId;
    private Character sex;
    private String name;
    private Long sectionId;
    private Long tierId;

    public FlatPlayerGroup(PlayerGroup playerGroup) {
        groupId = playerGroup.getGroupId();
        sectionId = playerGroup.getSection().getSectionId();
        sex = playerGroup.getSex();
        name = playerGroup.getName();
        tierId = playerGroup.getTier().getTierId();
    }
}
