package bd2.app.sport.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class SportFacility {

    @Id
    private Long facilityId ;

    @Column(length = 30, nullable = false)
    private String name ;

    @Column(length = 30)
    private String type ;

    @OneToOne(optional = false)
    @JoinColumn(name = "address_id")
    private Address address ;
}
