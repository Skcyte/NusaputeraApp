package com.example.nickolas.nusaputeraapp;

/**
 * Created by Nickolas on 21-May-17.
 */

public class Nilai {
    private String komponen, nilai;

    public String getKomponen() {
        return komponen;
    }

    public void setKomponen(String komponen) {
        this.komponen = komponen;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public Nilai(String komponen, String nilai) {

        this.komponen = komponen;
        this.nilai = nilai;
    }
}
