package com.example.r2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "produse")
public class Produs implements Serializable {
    @PrimaryKey(autoGenerate = false)
    private long id;
    private String denumire;
    private float pret;

    public Produs(long id, String denumire, float pret) {
        this.id = id;
        this.denumire = denumire;
        this.pret = pret;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", pret=" + pret +
                '}';
    }
}
