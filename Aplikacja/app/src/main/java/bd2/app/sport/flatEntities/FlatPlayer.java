package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.Player;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
        groupId = player.getPlayerGroup().getGroupId();
    }
}
