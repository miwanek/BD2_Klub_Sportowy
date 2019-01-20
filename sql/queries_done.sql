--średni wiek playerów w klubie
SELECT AVG(TIMESTAMPDIFF(YEAR, birth_date, CURDATE()))
FROM player;

--ilość rozegranych rozgrywek w poszczególnych obiektach sportowych (może utrzymywanie którejś jest nieopłacalne)
SELECT sf.name, COUNT(g.game_id)
FROM sport_facility sf JOIN hall h ON (h.facility_id = sf.facility_id)
		JOIN game g ON (g.hall_id = h.hall_id)
GROUP BY sf.name;

