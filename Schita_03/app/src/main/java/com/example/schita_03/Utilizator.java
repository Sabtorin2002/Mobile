package com.example.schita_03;

import java.io.Serializable;

public class Utilizator implements Serializable {

    private String nume;
    private String parola;

    public Utilizator(){}

    public Utilizator(String nume, String parola) {
        this.nume = nume;
        this.parola = parola;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "nume='" + nume + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }
}
