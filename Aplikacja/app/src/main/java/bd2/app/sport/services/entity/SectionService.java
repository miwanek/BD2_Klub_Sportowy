package bd2.app.sport.services.entity;

import bd2.app.sport.entities.Section;
import bd2.app.sport.repositories.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionService {
    private final SectionRepository sectionRepository;

    public List<Section> getSections(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return sectionRepository.findAll();
        }
        return null;
    }

    public void deleteSection(Long id) throws DataIntegrityViolationException {
        sectionRepository.deleteById(id);
    }

}
