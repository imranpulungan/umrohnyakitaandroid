package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class PSCDataJualKwitansi implements Serializable {
    private String data_saya_kode;
    private String data_saya_nama;
    private String jumlah;
    private String keterangan;
    private String tgl_dibuat;
    private String no_kwitansi;

    public String getNo_kwitansi() {
        return no_kwitansi;
    }

    public void setNo_kwitansi(String no_kwitansi) {
        this.no_kwitansi = no_kwitansi;
    }

    public String getData_saya_kode() {
        return data_saya_kode;
    }

    public void setData_saya_kode(String data_saya_kode) {
        this.data_saya_kode = data_saya_kode;
    }

    public String getData_saya_nama() {
        return data_saya_nama;
    }

    public void setData_saya_nama(String data_saya_nama) {
        this.data_saya_nama = data_saya_nama;
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
}
