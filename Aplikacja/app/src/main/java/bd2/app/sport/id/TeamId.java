package bd2.app.sport.id;

import bd2.app.sport.entities.Representation;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class TeamId implements Serializable {
    private Representation representation;
}