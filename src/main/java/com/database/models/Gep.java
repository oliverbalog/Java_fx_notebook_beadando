package com.database.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "gep")
public class Gep {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer Id;
    @Column(name = "gyarto")
    private String Gyarto;
    @Column(name = "tipus")
    private String Tipus;
    @Column(name = "kijelzo")
    private String Kijelzo;
    @Column(name = "memoria")
    private Integer Memoria;
    @Column(name = "merevlemez")
    private Integer Merevlemez;

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

    public Gep(String gyarto, String tipus, String kijelzo, Integer memoria, Integer merevlemez, String videovezerlo, BigDecimal ar, Integer db) {
        Gyarto = gyarto;
        Tipus = tipus;
        Kijelzo = kijelzo;
        Memoria = memoria;
        Merevlemez = merevlemez;
        Videovezerlo = videovezerlo;
        Ar = ar;
        Db = db;
    }

    public Gep(){

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

    public BigDecimal getAr() {
        return Ar;
    }

    public void setAr(BigDecimal ar) {
        Ar = ar;
    }

    public Integer getDb() {
        return Db;
    }

    public void setDb(Integer db) {
        Db = db;
    }

    @Column(name = "videovezerlo")
    private String Videovezerlo;
    @Column(name = "ar")
    private BigDecimal Ar;
    @Column(name = "db")
    private Integer Db;
}
