package bd2.app.sport;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Access(AccessType.PROPERTY)
@Entity
@NoArgsConstructor
@Table(name = "adres")
public class Address {

    private IntegerProperty id_adresu = new SimpleIntegerProperty();

    private StringProperty miasto = new SimpleStringProperty();

    private StringProperty ulica = new SimpleStringProperty();

    private StringProperty nr_budynku = new SimpleStringProperty();

    @Id
    public int getId_adresu() {
        return id_adresu.get();
    }

    @Column(nullable = false, length = 30)
    public String getMiasto() {
        return miasto.get();
    }

    @Column(length = 30)
    public String getUlica() {
        return ulica.get();
    }

    @Column(length = 30)
    public String getNr_budynku() {
        return nr_budynku.get();
    }

    public void setId_adresu(int id_adresu) {
        this.id_adresu.set(id_adresu);
    }

    public void setMiasto(String miasto) {
        this.miasto.set(miasto);
    }

    public void setUlica(String ulica) {
        this.ulica.set(ulica);
    }

    public void setNr_budynku(String nr_budynku) {
        this.nr_budynku.set(nr_budynku);
    }
}
