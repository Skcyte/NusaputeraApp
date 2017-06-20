package com.example.nickolas.nusaputeraapp;

/**
 * Created by Nickolas on 20-Jun-17.
 */

public class HeaderRaport {
    String nama, nilai, ketrampilan, sikap;

    public HeaderRaport(String nama, String nilai, String ketrampilan, String sikap) {
        this.nama = nama;
        this.nilai = nilai;
        this.ketrampilan = ketrampilan;
        this.sikap = sikap;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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
