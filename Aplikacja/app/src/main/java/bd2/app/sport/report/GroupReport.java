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
public class GroupReport {
    private String surname;
    private String name;
    private Timestamp birthDate;
    private Integer highestPlace;
    private BigDecimal averagePlace;
    private Integer worstPlace;
    private BigDecimal worstResult;
    private BigDecimal averageResult;
    private BigDecimal bestResult;
    private BigDecimal lowestScore;
    private BigDecimal averageScore;
    private BigDecimal highestScore;
}
