package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class PSCKwitansiPerwakilan implements Serializable {
    private String no_transaksi;
    private String no_kwitansi;
    private String guna;
    private String status;
    private String untuk;
    private String kode;
    private String keterangan;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNo_transaksi() {
        return no_transaksi;
    }

    public void setNo_transaksi(String no_transaksi) {
        this.no_transaksi = no_transaksi;
    }

    public String getNo_kwitansi() {
        return no_kwitansi;
    }

    public void setNo_kwitansi(String no_kwitansi) {
        this.no_kwitansi = no_kwitansi;
    }

    public String getGuna() {
        return guna;
    }

    public void setGuna(String guna) {
        this.guna = guna;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUntuk() {
        return untuk;
    }

    public void setUntuk(String untuk) {
        this.untuk = untuk;
    }
}
