package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class Kwitansi implements Serializable{
    private String no_transaksi;
    private String no_kwitansi;
    private String status_guna;
    private String jenis_kwitansi;

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

    public String getStatus_guna() {
        return status_guna;
    }

    public void setStatus_guna(String status_guna) {
        this.status_guna = status_guna;
    }

    public String getJenis_kwitansi() {
        return jenis_kwitansi;
    }

    public void setJenis_kwitansi(String jenis_kwitansi) {
        this.jenis_kwitansi = jenis_kwitansi;
    }
}
