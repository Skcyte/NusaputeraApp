package com.example.nickolas.nusaputeraapp;

/**
 * Created by Nickolas on 17-May-17.
 */

public class Mapel {
    String kdmapel;
    String namaMapel;


    public Mapel(String kdmapel , String namaMapel) {
        this.kdmapel = kdmapel;
        this.namaMapel = namaMapel;
    }

    public String getNamaMapel() {
        return namaMapel;
    }

    public void setNamaMapel(String namaMapel) {
        this.namaMapel = namaMapel;
    }

    public String getKdmapel() {
        return kdmapel;
    }

    public void setKdmapel(String kdmapel) {
        this.kdmapel = kdmapel;
    }
}
