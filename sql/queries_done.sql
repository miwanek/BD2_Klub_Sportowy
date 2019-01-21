--średni wiek playerów w klubie
SELECT AVG(TIMESTAMPDIFF(YEAR, birth_date, CURDATE()))
FROM player;


--ilość rozegranych rozgrywek w poszczególnych obiektach sportowych (może utrzymywanie którejś jest nieopłacalne)
SELECT sf.name, COUNT(g.game_id)
FROM sport_facility sf
	JOIN hall h ON (h.facility_id = sf.facility_id)
	JOIN game g ON (g.hall_id = h.hall_id)
GROUP BY sf.name;


--lista trainerów z wypisywaniem zdobytych punktów na tournamentach wybranej dyscypliny(szukamy najlepszego trainera)
SELECT d.name AS discipline, t.trainer_id, t.surname, t.name, SUM(tp.score) AS score
FROM discipline d
	JOIN trainer_discipline td ON (d.discipline_id = td.discipline_id)
	JOIN trainer t ON (td.trainer_id = t.trainer_id)
	JOIN representation_trainer rt ON (t.trainer_id = rt.trainer_id)
	JOIN representation r ON (rt.representation_id = r.representation_id)
	JOIN tournament_participation tp ON (r.representation_id = tp.representation_id)
GROUP BY d.name, t.trainer_id
ORDER BY d.name, score DESC;


--ilość playerów w wybranej dyscyplinie za określony rok (chcemy zobaczyć jakie dyscypliny są bardziej popularne)
-- 1 wersja
SELECT YEAR(t.start_date) year, d.name, COUNT(DISTINCT p.representation_id) number_of_players
FROM tournament t
	JOIN tournament_participation tp ON (t.tournament_id = tp.tournament_id)
	JOIN representation r ON (tp.representation_id = r.representation_id)
	JOIN player p ON (r.representation_id = p.representation_id)
	JOIN player_discipline pd ON (p.representation_id = pd.player_representation_id)
	JOIN discipline d ON (pd.discipline_id = d.discipline_id)
GROUP BY YEAR(t.start_date), d.name;

-- 2 wersja
SELECT year, name, COUNT(representation_id) number_of_players
FROM
  (SELECT YEAR(t.start_date) year, d.name, p.representation_id
   FROM tournament t JOIN tournament_participation tp ON (t.tournament_id = tp.tournament_id)
		JOIN representation r ON (tp.representation_id = r.representation_id)
		JOIN player p ON (r.representation_id = p.representation_id)
		JOIN player_discipline pd ON (p.representation_id = pd.player_representation_id)
		JOIN discipline d ON (pd.discipline_id = d.discipline_id)
   GROUP BY YEAR(t.start_date), d.name, p.representation_id) mytable
GROUP BY year, name;


--lista rozegranych rozgrywek wraz z wynikami w ramach Mistrzostw Świata w Piłkę Nożną w 2005 roku
SELECT g.game_id, g.start_date, g.end_date, g.referee, r.representation_id, team.name, gp.place, gp.score
FROM tournament t
	JOIN game g ON (t.tournament_id = g.tournament_id)
	JOIN game_participation gp ON (gp.game_id = g.game_id)
	JOIN representation r ON (r.representation_id = gp.representation_id)
	JOIN team ON (team.representation_id = r.representation_id)
WHERE t.name = 'Mistrzostwa Świata Piłkę Nożną' AND YEAR(t.start_date) = 2005;


-- lista playerów drużyny tenisa chłopców z poziomu początkującego
SELECT p.surname, p.name, p.birth_date
FROM player p
	JOIN player_team pt ON (p.representation_id = pt.player_representation_id)
	JOIN team ON (team.representation_id = pt.team_representation_id)
	JOIN tier ON (team.tier_id = tier.tier_id)
	JOIN section s ON (team.section_id = s.section_id)
	JOIN discipline d ON (s.discipline_id = d.discipline_id)
WHERE p.sex = 'M' AND tier.name = 'początkujący' AND d.name = 'Tenis ziemny';


--średni wynik dziewczynek z poziomu zaawansowanego w podnoszeniu ciężarów w roku 2017
SELECT AVG(gp.score)
FROM discipline d
       JOIN player_discipline pd ON (d.discipline_id = pd.discipline_id)
       JOIN player p ON (p.representation_id = pd.player_representation_id)
       JOIN player_group pg ON (p.group_id = pg.group_id)
       JOIN tier ON (pg.tier_id = tier.tier_id)
       JOIN representation r ON (r.representation_id = p.representation_id)
       JOIN game_participation gp ON (gp.representation_id = r.representation_id)
       JOIN game g ON (g.game_id = gp.game_id)
WHERE pg.sex = 'K' AND tier.name = 'zaawansowany' AND d.name = 'Podnoszenie ciężarów'