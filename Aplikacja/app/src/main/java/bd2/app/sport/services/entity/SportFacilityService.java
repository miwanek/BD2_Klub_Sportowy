package bd2.app.sport.services.entity;


import bd2.app.sport.entities.Address;
import bd2.app.sport.entities.Hall;
import bd2.app.sport.entities.SportFacility;
import bd2.app.sport.repositories.AddressRepository;
import bd2.app.sport.repositories.HallRepository;
import bd2.app.sport.repositories.SportFacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SportFacilityService {

    private final AddressRepository addressRepository;
    private final SportFacilityRepository sportFacilityRepository;
    private final HallRepository hallRepository;

    public List<SportFacility> getSportFacilities(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return sportFacilityRepository.findAll();
        }
        return null;
    }

    public List<Hall> getHalls(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return hallRepository.findAll();
        }
        return null;
    }

    public List<Address> getAddresses(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return addressRepository.findAll();
        }
        return null;
    }

    public void deleteSportFacility(Long id) throws DataIntegrityViolationException {
        sportFacilityRepository.deleteById(id);
    }

    public void deleteAddress(Long id) throws DataIntegrityViolationException {
        addressRepository.deleteById(id);
    }
}
