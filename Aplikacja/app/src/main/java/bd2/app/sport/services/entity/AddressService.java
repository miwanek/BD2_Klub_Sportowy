package bd2.app.sport.services.entity;

import bd2.app.sport.entities.Address;
import bd2.app.sport.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Address> getAdresses(String selectedColumn, String columnValue) {
        if(selectedColumn == null || columnValue == null) {
            return addressRepository.findAll();
        }

        return null;
    }

    public void deleteAddress(Long id) throws DataIntegrityViolationException {
        addressRepository.deleteById(id);
    }
}
