package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class Dollar implements Serializable{
    private String id_kurs;
    private String mata_uang;
    private String rupiah;
    private String tanggal;
    private String diinput;

    public String getId_kurs() {
        return id_kurs;
    }

    public void setId_kurs(String id_kurs) {
        this.id_kurs = id_kurs;
    }

    public String getMata_uang() {
        return mata_uang;
    }

    public void setMata_uang(String mata_uang) {
        this.mata_uang = mata_uang;
    }

    public String getRupiah() {
        return rupiah;
    }

    public void setRupiah(String rupiah) {
        this.rupiah = rupiah;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getDiinput() {
        return diinput;
    }

    public void setDiinput(String diinput) {
        this.diinput = diinput;
    }
}
