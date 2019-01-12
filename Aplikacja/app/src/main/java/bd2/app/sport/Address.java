package bd2.app.sport;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Access(AccessType.PROPERTY)
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    private Integer addressId;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(length = 30)
    private String street;

    @Column(length = 30)
    private String buildingNumber;
}
