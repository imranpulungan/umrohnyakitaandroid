package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class Komisi implements Serializable {
    private String id_komisi;
    private String jumlah;
    private String id_perwakilan;
    private String id_perekomendasi;
    private String ket;
    private String tgl_dibuat;
    private String kategori;
    private String status;
    private String foto;



    public String getId_komisi() {
        return id_komisi;
    }

    public void setId_komisi(String id_komisi) {
        this.id_komisi = id_komisi;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getId_perwakilan() {
        return id_perwakilan;
    }

    public void setId_perwakilan(String id_perwakilan) {
        this.id_perwakilan = id_perwakilan;
    }

    public String getId_perekomendasi() {
        return id_perekomendasi;
    }

    public void setId_perekomendasi(String id_perekomendasi) {
        this.id_perekomendasi = id_perekomendasi;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getTgl_dibuat() {
        return tgl_dibuat;
    }

    public void setTgl_dibuat(String tgl_dibuat) {
        this.tgl_dibuat = tgl_dibuat;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
