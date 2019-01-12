package bd2.app.sport;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SportFacility {

    @Id
    private Integer facilityId ;

    @Column(length = 30, nullable = false)
    private String name ;

    @Column(length = 30)
    private String type ;

    @OneToOne(optional = false)
    @JoinColumn(name = "address_id")
    private Address address ;
}
