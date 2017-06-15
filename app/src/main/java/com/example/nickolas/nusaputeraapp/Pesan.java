package com.example.nickolas.nusaputeraapp;

/**
 * Created by Edwin Listyo on 5/30/2017.
 */

public class Pesan {
    private String no, pesan, judul, tgl, status;

    public Pesan(String no, String pesan, String judul, String tgl, String status) {
        this.no = no;
        this.pesan = pesan;
        this.judul = judul;
        this.tgl = tgl;
        this.status = status;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
