package bd2.app.sport.flags;

import com.google.common.collect.ImmutableList;

public class CommonFlags {
    public static final ImmutableList<String> READ_ONLY_ENTITIES =
            ImmutableList.of("Address", "Unit", "Tier", "Discipline", "Hall",
                    "SportFacility", "Section", "Trainer", "TrainerDiscipline",
                    "Tournament", "TournamentDiscipline");


    public static final ImmutableList<String> DELETE_ENTITIES =
            ImmutableList.of("Game", "GameParticipation", "Player", "PlayerGroup", "PlayerDiscipline",
                    "PlayerTeam", "Team");


    public static final ImmutableList<String> ADD_ENTITIES =
            ImmutableList.of("Game", "GameParticipation", "Player", "PlayerGroup", "PlayerDiscipline",
                    "PlayerTeam", "Team", "TournamentParticipation");

    public static final ImmutableList<String> COMPOSED_ENTITIES_TO_ADD =
            ImmutableList.of("GameParticipation", "PlayerTeam");
}
