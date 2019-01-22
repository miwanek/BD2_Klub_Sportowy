CREATE INDEX sport_facility_address ON sport_facility (address_id);
CREATE INDEX hall_facility_id ON hall (facility_id);
CREATE INDEX game_hall_id ON game (hall_id);
CREATE INDEX game_tournament_id ON game (tournament_id);
CREATE INDEX game_discipline_id ON game (discipline_id);
CREATE INDEX game_unit_id ON game (unit_id);
CREATE INDEX tournament_participation_representation_id ON tournament_participation (representation_id);
CREATE INDEX tournament_participation_tournament_id ON tournament_participation (tournament_id);
CREATE INDEX game_participation_game_id ON game_participation (game_id);
CREATE INDEX game_participation_representation_id ON game_participation (representation_id);
CREATE INDEX representation_trainer_representation_id ON representation_trainer (representation_id);
CREATE INDEX representation_trainer_trainer_id ON representation_trainer (trainer_id);
CREATE INDEX tournament_discipline_discipline_id ON tournament_discipline (discipline_id);
CREATE INDEX tournament_discipline_tournament_id ON tournament_discipline (tournament_id);
CREATE INDEX trainer_discipline_discipline_id ON trainer_discipline (discipline_id);
CREATE INDEX trainer_discipline_trainer_id ON trainer_discipline (trainer_id);
CREATE INDEX player_group_section_id ON player_group (section_id);
CREATE INDEX player_group_tier_id ON player_group (tier_id);
CREATE INDEX player_group_id ON player (group_id);
CREATE INDEX player_discipline_discipline_id ON player_discipline (discipline_id);
CREATE INDEX player_discipline_player_representation_id ON player_discipline (player_representation_id);
CREATE INDEX player_team_team_representation_id ON player_team (team_representation_id);
CREATE INDEX player_team_player_representation_id ON player_team (player_representation_id);
CREATE INDEX team_representation_id ON team (representation_id);
CREATE INDEX team_section_id ON team (section_id);
CREATE INDEX team_tier_id ON team (tier_id);
CREATE INDEX section_discipline_id ON section (discipline_id);