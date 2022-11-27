package database.models;

import javax.persistence.*;

@Entity
public class Oprendszer {
    public Integer getId() {
        return Id;
    }

    public Oprendszer(String nev) {
        Nev = nev;
    }
    public Oprendszer() {
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNev() {
        return Nev;
    }

    public void setNev(String nev) {
        Nev = nev;
    }

    @Id @GeneratedValue
    @Column(name = "id")
    private Integer Id;
    @Column(name = "nev")
    private String Nev;
}
