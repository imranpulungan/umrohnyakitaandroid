package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class KomisiPelunasan implements Serializable {
    private String id_komisi;
    private String jumlah;
    private String id_perwakilan;
    private String id_perekomendasi;
    private String nama_jamaah;
    private String no_kwitansi;
    private String ket;
    private String tgl_dibuat;
    private String kategori;
    private String status;

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

    public String getNama_jamaah() {
        return nama_jamaah;
    }

    public void setNama_jamaah(String nama_jamaah) {
        this.nama_jamaah = nama_jamaah;
    }

    public String getNo_kwitansi() {
        return no_kwitansi;
    }

    public void setNo_kwitansi(String no_kwitansi) {
        this.no_kwitansi = no_kwitansi;
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
}
