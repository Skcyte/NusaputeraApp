package com.example.nickolas.nusaputeraapp;

/**
 * Created by Edwin Listyo on 5/30/2017.
 */

public class Pesan {
    private String pesan, judul, tgl;

    public Pesan(String judul, String pesan, String tgl) {
        this.pesan = pesan;
        this.judul = judul;
        this.tgl = tgl;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }
}
