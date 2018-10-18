package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class KomisiPSC implements Serializable {
    private String id_komisi;
    private String id_perwakilan;
    private String tipe;
    private String jumlah;
    private String keterangan;
    private String tgl_dibuat;
    private String status;

    public String getId_komisi() {
        return id_komisi;
    }

    public void setId_komisi(String id_komisi) {
        this.id_komisi = id_komisi;
    }

    public String getId_perwakilan() {
        return id_perwakilan;
    }

    public void setId_perwakilan(String id_perwakilan) {
        this.id_perwakilan = id_perwakilan;
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

    public String getTgl_dibuat() {
        return tgl_dibuat;
    }

    public void setTgl_dibuat(String tgl_dibuat) {
        this.tgl_dibuat = tgl_dibuat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
