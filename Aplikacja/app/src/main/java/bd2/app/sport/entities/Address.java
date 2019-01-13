package bd2.app.sport.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

//@Access(AccessType.PROPERTY)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Address {

    @Id
    private Long addressId;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(length = 30)
    private String street;

    @Column(length = 30)
    private String buildingNumber;
}
