package database.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "telepites")
public class Telepites {
    @Id @GeneratedValue
    @Column(name = "id")
    private Integer Id;

    public Telepites(Integer id, String verzio, Date datum, database.models.Szoftver szoftver, database.models.Gep gep) {
        Id = id;
        Verzio = verzio;
        Datum = datum;
        Szoftver = szoftver;
        Gep = gep;
    }

    public Telepites() {
    }

    @Column(name = "verzio")
    private String Verzio;
    @Column(name = "datum")
    private Date Datum;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getVerzio() {
        return Verzio;
    }

    public void setVerzio(String verzio) {
        Verzio = verzio;
    }

    public Date getDatum() {
        return Datum;
    }

    public void setDatum(Date datum) {
        Datum = datum;
    }

    public database.models.Szoftver getSzoftver() {
        return Szoftver;
    }

    public void setSzoftver(database.models.Szoftver szoftver) {
        Szoftver = szoftver;
    }

    public database.models.Gep getGep() {
        return Gep;
    }

    public void setGep(database.models.Gep gep) {
        Gep = gep;
    }

    @JoinColumn(name = "szoftverid")
    @ManyToOne(cascade = CascadeType.ALL)
    private Szoftver Szoftver;
    @JoinColumn(name = "gepid")
    @ManyToOne(cascade = CascadeType.ALL)
    private Gep Gep;

}
