package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class PSCDataHistoryStok implements Serializable {
    private String no_transaksi;
    private String tgl_dibuat;
    private String jumlah_stock;

    public String getNo_transaksi() {
        return no_transaksi;
    }

    public void setNo_transaksi(String no_transaksi) {
        this.no_transaksi = no_transaksi;
    }

    public String getTgl_dibuat() {
        return tgl_dibuat;
    }

    public void setTgl_dibuat(String tgl_dibuat) {
        this.tgl_dibuat = tgl_dibuat;
    }

    public String getJumlah_stock() {
        return jumlah_stock;
    }

    public void setJumlah_stock(String jumlah_stock) {
        this.jumlah_stock = jumlah_stock;
    }
}
