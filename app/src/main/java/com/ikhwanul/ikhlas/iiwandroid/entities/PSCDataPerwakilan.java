package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class PSCDataPerwakilan implements Serializable {
    private String id_perwakilan;
    private String status;
    private String kode;
    private String nama_lengkap;
    private String kode_per;
    private String tgl_berakhir;

    public String getId_perwakilan() {
        return id_perwakilan;
    }

    public void setId_perwakilan(String id_perwakilan) {
        this.id_perwakilan = id_perwakilan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getKode_per() {
        return kode_per;
    }

    public void setKode_per(String kode_per) {
        this.kode_per = kode_per;
    }

    public String getTgl_berakhir() {
        return tgl_berakhir;
    }

    public void setTgl_berakhir(String tgl_berakhir) {
        this.tgl_berakhir = tgl_berakhir;
    }
}
