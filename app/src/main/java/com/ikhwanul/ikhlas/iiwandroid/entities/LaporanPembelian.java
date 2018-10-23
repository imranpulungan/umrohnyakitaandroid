package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class LaporanPembelian implements Serializable{
    private String id_pembelian;
    private String id_perwakilan;
    private String no_transaksi;
    private String jumlah_stock;
    private String no_kwintansi;
    private String keterangan;
    private String tgl_dibuat;
    private String status;

    public String getId_pembelian() {
        return id_pembelian;
    }

    public void setId_pembelian(String id_pembelian) {
        this.id_pembelian = id_pembelian;
    }

    public String getId_perwakilan() {
        return id_perwakilan;
    }

    public void setId_perwakilan(String id_perwakilan) {
        this.id_perwakilan = id_perwakilan;
    }

    public String getNo_transaksi() {
        return no_transaksi;
    }

    public void setNo_transaksi(String no_transaksi) {
        this.no_transaksi = no_transaksi;
    }

    public String getJumlah_stock() {
        return jumlah_stock;
    }

    public void setJumlah_stock(String jumlah_stock) {
        this.jumlah_stock = jumlah_stock;
    }

    public String getNo_kwintansi() {
        return no_kwintansi;
    }

    public void setNo_kwintansi(String no_kwintansi) {
        this.no_kwintansi = no_kwintansi;
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
