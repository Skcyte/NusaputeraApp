package com.example.nickolas.nusaputeraapp;

/**
 * Created by Nickolas on 15-Jun-17.
 */

public class Raport {
    private String namamapel, kode, nilai, ketrampilan, sikap;

    public Raport(String namamapel, String kode, String nilai, String ketrampilan, String sikap) {
        this.namamapel = namamapel;
        this.kode = kode;
        this.nilai = nilai;
        this.ketrampilan = ketrampilan;
        this.sikap = sikap;
    }

    public String getNamamapel() {
        return namamapel;
    }

    public void setNamamapel(String namamapel) {
        this.namamapel = namamapel;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getKetrampilan() {
        return ketrampilan;
    }

    public void setKetrampilan(String ketrampilan) {
        this.ketrampilan = ketrampilan;
    }

    public String getSikap() {
        return sikap;
    }

    public void setSikap(String sikap) {
        this.sikap = sikap;
    }
}
