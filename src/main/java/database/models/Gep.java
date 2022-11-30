package database.models;

import javax.persistence.*;

@Entity
@Table(name = "gep")
public class Gep {
    @Id @GeneratedValue
    @Column(name = "id")
    private Integer Id;
    @Column(name = "hely")
    private String Hely;

    public Gep() {
    }

    public Gep(Integer id, String hely, String tipus, String ipcim) {
        Id = id;
        Hely = hely;
        Tipus = tipus;
        Ipcim = ipcim;
    }

    @Column(name = "tipus")
    private String Tipus;
    @Column(name = "ipcim")
    private String Ipcim;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getHely() {
        return Hely;
    }

    public void setHely(String hely) {
        Hely = hely;
    }

    public String getTipus() {
        return Tipus;
    }

    public void setTipus(String tipus) {
        Tipus = tipus;
    }

    public String getIpcim() {
        return Ipcim;
    }

    public void setIpcim(String ipcim) {
        Ipcim = ipcim;
    }
}
