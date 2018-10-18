package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class Reward implements Serializable{
    private String id_reward;
    private String id_perwakilan;
    private String barang_reward;
    private String keterangan;
    private String tgl_dibuat;
    private String status;
    private String kategori;

    public String getId_reward() {
        return id_reward;
    }

    public void setId_reward(String id_reward) {
        this.id_reward = id_reward;
    }

    public String getId_perwakilan() {
        return id_perwakilan;
    }

    public void setId_perwakilan(String id_perwakilan) {
        this.id_perwakilan = id_perwakilan;
    }

    public String getBarang_reward() {
        return barang_reward;
    }

    public void setBarang_reward(String barang_reward) {
        this.barang_reward = barang_reward;
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

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
