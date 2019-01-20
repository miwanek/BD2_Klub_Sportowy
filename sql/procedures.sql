--Wyświetlanie maksymalnej/ średniej/ minimalnej liczby punktów, rezultatów  oraz miejsc zawodników z podanej grupy w rozgrywkach w danym okresie czasu.
CREATE PROCEDURE
	raport1(player_group_name varchar, start_date date, end_date date)
BEGIN
	SELECT p.surname, p.name, p.birth_date, MIN(gp.place), AVG(gp.place), MAX(gp.place),
	MIN(gp.result), AVG(gp.result), MAX(gp.result),
	MIN(gp.score), AVG(gp.score), MAX(gp.score)
	FROM ( ( ( player p JOIN player_group pg ON (p.group_id = pg.group_id) )
			JOIN representation r ON (r.representation_id = p.representation_id) )
				JOIN game_participation gp ON (r.representation_id = gp.representation_id) )
					JOIN game g ON (gp.game_id = g.game_id)
	WHERE pg.name = player_group_name AND g.start_date >= start_date AND g.end_date <= end_date
	GROUP BY p.surname, p.name, p.birth_date;

END;


--Wyświetlanie liczby rozgrywek wygranych przez jednostki sportowe danej sekcji w poszczególnych turniejach, ostateczne miejsce liczbę pozostałych rozgrywek w jakich dana jednostka brała udział. Wyniki wyświetlamy dla turniejów z podanego okresu czasu.

--players
CREATE PROCEDURE
	raport2_players(section_name varchar, start_date date, end_date date)
BEGIN
	SELECT p.surname, p.name, p.birth_date, t.name,
		SUM(CASE gp.place
			WHEN 1 THEN 1
			ELSE 0 END) AS won,
		tp.place AS final_place,
		SUM(CASE gp.place
			WHEN 1 THEN 0
			ELSE 1 END) AS not_won
	FROM ( ( ( ( ( ( section s JOIN player_group pg ON (s.section_id = pg.section_id) )
		JOIN player p ON (pg.group_id = p.group_id) )
			JOIN representation r ON (r.representation_id = p.representation_id) )
				JOIN game_participation gp ON (gp.representation_id = r.representation_id) )
					JOIN game g ON (gp.game_id = g.game_id) )
					   JOIN tournament t ON (t.tournament_id = g.tournament_id) )
						  JOIN tournament_participation tp ON (t.tournament_id = tp.tournament_id AND tp.representation_id = r.representation_id)
	WHERE s.name = section_name AND t.start_date >= start_date AND t.end_date <= end_date
	GROUP BY p.surname, p.name, p.birth_date, t.name;
END;

--teams
CREATE PROCEDURE
	raport2_teams(section_name varchar(30), start_date date, end_date date)
BEGIN
	SELECT team.name, t.name,
		SUM(CASE gp.place
			WHEN 1 THEN 1
			ELSE 0 END) AS won,
		tp.place AS final_place,
		SUM(CASE gp.place
			WHEN 1 THEN 0
			ELSE 1 END) AS not_won
	FROM ( ( ( ( section s JOIN team ON (s.section_id = team.section_id) )
			JOIN representation r ON (r.representation_id = p.representation_id) )
				JOIN game_participation gp ON (gp.representation_id = r.representation_id) )
					JOIN game g ON (gp.game_id = g.game_id) )
						JOIN tournament t ON (t.tournament_id = g.tournament_id)
	WHERE s.name = section_name AND t.start_date >= start_date AND t.end_date <= end_date
	GROUP BY team.name, t.name;
END;


--




