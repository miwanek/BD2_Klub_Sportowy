package bd2.app.sport.flatEntities;

import bd2.app.sport.entities.PlayerTeam;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class FlatPlayerTeam {

    private String playerId;
    private String teamId;

    public FlatPlayerTeam(PlayerTeam playerTeam) {
        playerId = playerTeam.getPlayer().getId();
        teamId = playerTeam.getTeam().getId();
    }
}
