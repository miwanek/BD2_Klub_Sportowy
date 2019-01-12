package bd2.app.sport;

import lombok.RequiredArgsConstructor;
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
}
