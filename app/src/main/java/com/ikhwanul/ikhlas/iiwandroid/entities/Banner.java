package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class Banner implements Serializable {
    private String judul;
    private String gambar;

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
