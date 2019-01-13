package bd2.app.sport.services.entity;


import bd2.app.sport.entities.SportFacility;
import bd2.app.sport.repositories.SportFacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SportFacilityService {

    private final SportFacilityRepository sportFacilityRepository;

    public List<SportFacility> getSportFacilities(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return sportFacilityRepository.findAll();
        }

        return null;
    }

    public void deleteSportFacility(Long id) throws DataIntegrityViolationException {
        sportFacilityRepository.deleteById(id);
    }
}
