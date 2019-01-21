package bd2.app.sport.report;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SectionReport {
    String surname;
    String name;
    Timestamp birthDate;
    String tournamentName;
    BigDecimal won;
    Integer finalPlace;
    BigDecimal notWon;
}
