package com.database.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Processzor {
    public Integer getId() {
        return Id;
    }

    public Processzor(String gyarto, String tipus) {
        Gyarto = gyarto;
        Tipus = tipus;
    }
    public Processzor(){}

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

    @Id @GeneratedValue
    @Column(name = "id")
    private Integer Id;
    @Column(name = "gyarto")
    private String Gyarto;
    @Column(name = "tipus")
    private String Tipus;
}
