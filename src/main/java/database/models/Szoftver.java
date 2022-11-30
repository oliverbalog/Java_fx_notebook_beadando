package database.models;

import javax.persistence.*;

@Entity
@Table(name = "szoftver")
public class Szoftver {
    @Id @GeneratedValue
    @Column(name = "id")
    private Integer Id;
    @Column(name = "nev")
    private String Nev;
    @Column(name = "kategoria")
    private String Kategoria;

    public Szoftver() {
    }

    public Szoftver(Integer id, String nev, String kategoria) {
        Id = id;
        Nev = nev;
        Kategoria = kategoria;
    }

    public Integer getId() {
        return Id;
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

    public String getKategoria() {
        return Kategoria;
    }

    public void setKategoria(String kategoria) {
        Kategoria = kategoria;
    }
}
