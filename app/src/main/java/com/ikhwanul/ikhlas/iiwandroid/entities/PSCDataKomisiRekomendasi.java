package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class PSCDataKomisiRekomendasi implements Serializable {
    private String tipe;
    private String status;
    private String jumlah;
    private String keterangan;
    private String bln_tahun;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBln_tahun() {
        return bln_tahun;
    }

    public void setBln_tahun(String bln_tahun) {
        this.bln_tahun = bln_tahun;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
