package bd2.app.sport.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class SportFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facilityId ;

    @Size(min = 1, max = 30)
    private String name ;

    @Size(max = 30)
    private String type ;

    @OneToOne(optional = false)
    @JoinColumn(name = "address_id")
    private Address address ;
}
