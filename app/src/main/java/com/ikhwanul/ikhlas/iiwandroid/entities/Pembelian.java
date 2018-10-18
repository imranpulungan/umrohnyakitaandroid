package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class Pembelian implements Serializable{
    private String id_penjualan;
    private String id_perwakilan;
    private String id_perekomendasi;
    private String id_paket;
    private String no_transaksi;
    private String jumlah;
    private String total;
    private String keterangan;
    private String tgl_dibuat;
    private String status;

    public String getId_penjualan() {
        return id_penjualan;
    }

    public void setId_penjualan(String id_penjualan) {
        this.id_penjualan = id_penjualan;
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

    public String getId_paket() {
        return id_paket;
    }

    public void setId_paket(String id_paket) {
        this.id_paket = id_paket;
    }

    public String getNo_transaksi() {
        return no_transaksi;
    }

    public void setNo_transaksi(String no_transaksi) {
        this.no_transaksi = no_transaksi;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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
