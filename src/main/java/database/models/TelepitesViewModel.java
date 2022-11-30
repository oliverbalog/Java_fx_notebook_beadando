package database.models;

import java.util.Date;

public class TelepitesViewModel {
    private Integer Id;
    private String Verzio;
    private Date Datum;
    private Integer GepId;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public TelepitesViewModel() {
    }

    public TelepitesViewModel(Integer id, String verzio, Date datum, Integer gepId, String gepHely, String gepTipus, String gepIpcim, Integer szoftverId, String szoftverNev, String szoftverKategoria) {
        Id = id;
        Verzio = verzio;
        Datum = datum;
        GepId = gepId;
        GepHely = gepHely;
        GepTipus = gepTipus;
        GepIpcim = gepIpcim;
        SzoftverId = szoftverId;
        SzoftverNev = szoftverNev;
        SzoftverKategoria = szoftverKategoria;
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

    public Integer getGepId() {
        return GepId;
    }

    public void setGepId(Integer gepId) {
        GepId = gepId;
    }

    public String getGepHely() {
        return GepHely;
    }

    public void setGepHely(String gepHely) {
        GepHely = gepHely;
    }

    public String getGepTipus() {
        return GepTipus;
    }

    public void setGepTipus(String gepTipus) {
        GepTipus = gepTipus;
    }

    public String getGepIpcim() {
        return GepIpcim;
    }

    public void setGepIpcim(String gepIpcim) {
        GepIpcim = gepIpcim;
    }

    public Integer getSzoftverId() {
        return SzoftverId;
    }

    public void setSzoftverId(Integer szoftverId) {
        SzoftverId = szoftverId;
    }

    public String getSzoftverNev() {
        return SzoftverNev;
    }

    public void setSzoftverNev(String szoftverNev) {
        SzoftverNev = szoftverNev;
    }

    public String getSzoftverKategoria() {
        return SzoftverKategoria;
    }

    public void setSzoftverKategoria(String szoftverKategoria) {
        SzoftverKategoria = szoftverKategoria;
    }

    private String GepHely;
    private String GepTipus;
    private String GepIpcim;
    private Integer SzoftverId;
    private String SzoftverNev;
    private String SzoftverKategoria;
}
