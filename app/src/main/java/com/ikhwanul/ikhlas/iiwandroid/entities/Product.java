package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class Product implements Serializable{
    private String id_produk;
    private String produk;
    private String tahun;
    private String harga;
    private String hotelmekah;
    private String catering;
    private String hotelmadinah;
    private String medanjeddah;
    private String medanmadinah;
    private String info_singkat;
    private String info_lengkap;
    private String gambar;
    private String seo_link;
    private String seo_title;
    private String seo_desc;
    private String seo_key;

    public String getId_produk() {
        return id_produk;
    }

    public void setId_produk(String id_produk) {
        this.id_produk = id_produk;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getHotelmekah() {
        return hotelmekah;
    }

    public void setHotelmekah(String hotelmekah) {
        this.hotelmekah = hotelmekah;
    }

    public String getCatering() {
        return catering;
    }

    public void setCatering(String catering) {
        this.catering = catering;
    }

    public String getHotelmadinah() {
        return hotelmadinah;
    }

    public void setHotelmadinah(String hotelmadinah) {
        this.hotelmadinah = hotelmadinah;
    }

    public String getMedanjeddah() {
        return medanjeddah;
    }

    public void setMedanjeddah(String medanjeddah) {
        this.medanjeddah = medanjeddah;
    }

    public String getMedanmadinah() {
        return medanmadinah;
    }

    public void setMedanmadinah(String medanmadinah) {
        this.medanmadinah = medanmadinah;
    }

    public String getInfo_singkat() {
        return info_singkat;
    }

    public void setInfo_singkat(String info_singkat) {
        this.info_singkat = info_singkat;
    }

    public String getInfo_lengkap() {
        return info_lengkap;
    }

    public void setInfo_lengkap(String info_lengkap) {
        this.info_lengkap = info_lengkap;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getSeo_link() {
        return seo_link;
    }

    public void setSeo_link(String seo_link) {
        this.seo_link = seo_link;
    }

    public String getSeo_title() {
        return seo_title;
    }

    public void setSeo_title(String seo_title) {
        this.seo_title = seo_title;
    }

    public String getSeo_desc() {
        return seo_desc;
    }

    public void setSeo_desc(String seo_desc) {
        this.seo_desc = seo_desc;
    }

    public String getSeo_key() {
        return seo_key;
    }

    public void setSeo_key(String seo_key) {
        this.seo_key = seo_key;
    }
}
