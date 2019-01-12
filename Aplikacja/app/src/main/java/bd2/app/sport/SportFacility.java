package bd2.app.sport;

import javafx.beans.property.*;

import javax.persistence.*;

@Entity
@Table(name = "obiekt_sportowy")
public class SportFacility {

    private IntegerProperty id_obiektu = new SimpleIntegerProperty();

    private StringProperty nazwa_obiektu = new SimpleStringProperty();

    private StringProperty typ_obiektu = new SimpleStringProperty();

    private ObjectProperty<Address> address = new SimpleObjectProperty<>();

    @Id
    @Column(length = 9)
    public int getId_obiektu() {
        return id_obiektu.get();
    }


    public void setId_obiektu(int id_obiektu) {
        this.id_obiektu.set(id_obiektu);
    }

    @Column(length = 30)
    public String getTyp_obiektu() {
        return typ_obiektu.get();
    }

    public void setTyp_obiektu(String typ_obiektu) {
        this.typ_obiektu.set(typ_obiektu);
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "id_adresu")
    public Address getAddress() {
        return address.get();
    }

    public void setAddress(Address address) {
        this.address.set(address);
    }

    @Column(length = 30, nullable = false)
    public String getNazwa_obiektu() {
        return nazwa_obiektu.get();
    }

    public void setNazwa_obiektu(String nazwa_obiektu) {
        this.nazwa_obiektu.set(nazwa_obiektu);
    }
}
