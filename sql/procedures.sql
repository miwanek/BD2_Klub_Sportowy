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
	WHERE pg.name = player_group_name AND g.start_date >= start_date AND g.start_date <= end_date
	GROUP BY p.surname, p.name, p.birth_date;

END;


-- TO JESZCZE NIE SPRAWDZONE
CREATE PROCEDURE
--Wyświetlanie liczby rozgrywek wygranych przez jednostki sportowe danej sekcji w poszczególnych turniejach, ostateczne miejsce oraz liczbę pozostałych rozgrywek w jakich dana jednostka brała udział. Wyniki wyświetlamy dla turniejów z podanego okresu czasu.
	raport2_players_number_of_victory(section_name varchar, start_date date, end_date date)
BEGIN
	SELECT p.surname, p.name, p.birth_date, t.name, COUNT(g.game_id)
		FROM ( ( ( ( ( section s JOIN player_group pg ON (s.section_id = pg.section_id) )
		  JOIN player p ON (pg.group_id = p.group_id) )
			JOIN representation r ON (r.representation_id = p.representation_id) )
				JOIN game_participation gp ON (gp.representation_id = r.representation_id) )
					JOIN game g ON (gp.game_id = g.game_id) )
						JOIN tournament t ON (t.tournament_id = g.tournament_id)
		WHERE s.name = 'Sekcja siatkowki' AND gp.place = 1
		GROUP BY p.surname, p.name, p.birth_date;
--sekcja siatkowki -> section_name
END;

