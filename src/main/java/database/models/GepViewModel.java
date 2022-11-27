package database.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class GepViewModel {

    private Integer Id;
    private String Gyarto;
    private String Tipus;
    private String Kijelzo;
    private Integer Memoria;
    private Integer Merevlemez;
    private String Videovezerlo;
    private Integer Ar;
    private Integer Db;
    private String Processzor;
    private String Oprendszer;

    public GepViewModel() {
    }

    public GepViewModel(Integer id, String gyarto, String tipus, String kijelzo, Integer memoria, Integer merevlemez, String videovezerlo, Integer ar, Integer db, String processzor, String oprendszer) {
        Id = id;
        Gyarto = gyarto;
        Tipus = tipus;
        Kijelzo = kijelzo;
        Memoria = memoria;
        Merevlemez = merevlemez;
        Videovezerlo = videovezerlo;
        Ar = ar;
        Db = db;
        Processzor = processzor;
        Oprendszer = oprendszer;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getGyarto() {
        return Gyarto;
    }

    public void setGyarto(String gyarto) {
        Gyarto = gyarto;
    }

    public String getTipus() {
        return Tipus;
    }

    public void setTipus(String tipus) {
        Tipus = tipus;
    }

    public String getKijelzo() {
        return Kijelzo;
    }

    public void setKijelzo(String kijelzo) {
        Kijelzo = kijelzo;
    }

    public Integer getMemoria() {
        return Memoria;
    }

    public void setMemoria(Integer memoria) {
        Memoria = memoria;
    }

    public Integer getMerevlemez() {
        return Merevlemez;
    }

    public void setMerevlemez(Integer merevlemez) {
        Merevlemez = merevlemez;
    }

    public String getVideovezerlo() {
        return Videovezerlo;
    }

    public void setVideovezerlo(String videovezerlo) {
        Videovezerlo = videovezerlo;
    }

    public Integer getAr() {
        return Ar;
    }

    public void setAr(Integer ar) {
        Ar = ar;
    }

    public Integer getDb() {
        return Db;
    }

    public void setDb(Integer db) {
        Db = db;
    }

    public String getProcesszor() {
        return Processzor;
    }

    public void setProcesszor(String processzor) {
        Processzor = processzor;
    }

    public String getOprendszer() {
        return Oprendszer;
    }

    public void setOprendszer(String oprendszer) {
        Oprendszer = oprendszer;
    }
}
