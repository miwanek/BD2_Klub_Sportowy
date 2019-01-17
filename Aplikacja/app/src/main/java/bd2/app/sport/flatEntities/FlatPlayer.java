package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.Player;
import bd2.app.sport.entities.PlayerGroup;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FlatPlayer {

    private String id;

    private String name;

    private String surname;

    private Character sex;

    private LocalDateTime birthDate;

    private Long groupId;

    public FlatPlayer(Player player) {
        id = player.getId();
        name = player.getName();
        surname = player.getSurname();
        sex = player.getSex();
        birthDate = player.getBirthDate();

        PlayerGroup group = player.getPlayerGroup();

        groupId = group != null ? group.getGroupId() : null;
    }
}
