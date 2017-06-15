package com.example.nickolas.nusaputeraapp;

/**
 * Created by Nickolas on 15-Jun-17.
 */

public class Raport {
    private String namamapel;
    private String kode;

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

    public Raport(String namamapel, String kode, String nilai) {

        this.namamapel = namamapel;
        this.kode = kode;
        this.nilai = nilai;
    }

    private String nilai;


}
