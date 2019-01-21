package bd2.app.sport.services;

import bd2.app.sport.report.GroupReport;
import bd2.app.sport.report.SectionReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final EntityManager em;

    public List<SectionReport> getSectionReportResults(String sectionName, LocalDate startDate, LocalDate endDate) {
        Query query = em.createNativeQuery("{call raport2_players(?,?,?)}")
                .setParameter(1, sectionName)
                .setParameter(2, startDate)
                .setParameter(3, endDate);

        List<Object[]> result = query.getResultList();

        List<SectionReport> resultList = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            Object[] row = result.get(i);

            SectionReport reportRow = new SectionReport();
            reportRow.setSurname((String) row[0]);
            reportRow.setName((String) row[1]);
            reportRow.setBirthDate((Timestamp) row[2]);
            reportRow.setTournamentName((String) row[3]);
            reportRow.setWon((BigDecimal) row[4]);
            reportRow.setFinalPlace((Integer) row[5]);
            reportRow.setNotWon((BigDecimal) row[6]);

            resultList.add(reportRow);
        }

        return resultList;
    }

    public List<GroupReport> getGroupReportResults(String groupName, LocalDate startDate, LocalDate endDate) {
        Query query = em.createNativeQuery("{call raport1(?,?,?)}")
                .setParameter(1, groupName)
                .setParameter(2, startDate)
                .setParameter(3, endDate);

        List<Object[]> result = query.getResultList();

        List<GroupReport> resultList = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            Object[] row = result.get(i);

            GroupReport reportRow = new GroupReport();
            reportRow.setSurname((String) row[0]);
            reportRow.setName((String) row[1]);
            reportRow.setBirthDate((Timestamp) row[2]);

            reportRow.setHighestPlace((Integer) row[3]);
            reportRow.setAveragePlace((BigDecimal) row[4]);
            reportRow.setWorstPlace((Integer) row[5]);

            reportRow.setWorstResult((BigDecimal) row[6]);
            reportRow.setAverageResult((BigDecimal) row[7]);
            reportRow.setBestResult((BigDecimal) row[8]);

            reportRow.setLowestScore((BigDecimal) row[9]);
            reportRow.setAverageScore((BigDecimal) row[10]);
            reportRow.setHighestScore((BigDecimal) row[11]);

            resultList.add(reportRow);
        }

        return resultList;
    }
}
