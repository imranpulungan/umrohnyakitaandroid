package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class Jamaah implements Serializable {
    private String id_pendaftaraan;
    private String kode_grup;
    private String kode_paket;
    private String kode_dp;
    private String harga;
    private String tgl_berangkat;
    private String no_kwitansi;
    private String harga_rupiah;
    private String dibayar;
    private String nama_jamaah;
    private String nama_ayah;
    private String tempat_lahir;
    private String tgl_lahir;
    private String no_paspor;
    private String tempat_keluar_paspor;
    private String no_identitas;
    private String alamat;
    private String id_kabupaten;
    private String no_hp;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getPendidikan_terakhir() {
        return pendidikan_terakhir;
    }

    public void setPendidikan_terakhir(String pendidikan_terakhir) {
        this.pendidikan_terakhir = pendidikan_terakhir;
    }

    public String getSouvenir() {
        return souvenir;
    }

    public void setSouvenir(String souvenir) {
        this.souvenir = souvenir;
    }

    public String getSize_souvenir() {
        return size_souvenir;
    }

    public void setSize_souvenir(String size_souvenir) {
        this.size_souvenir = size_souvenir;
    }

    public String getAhli_waris() {
        return ahli_waris;
    }

    public void setAhli_waris(String ahli_waris) {
        this.ahli_waris = ahli_waris;
    }

    public String getId_perwakilan() {
        return id_perwakilan;
    }

    public void setId_perwakilan(String id_perwakilan) {
        this.id_perwakilan = id_perwakilan;
    }

    public String getNo_rekening() {
        return no_rekening;
    }

    public void setNo_rekening(String no_rekening) {
        this.no_rekening = no_rekening;
    }

    public String getAn() {
        return an;
    }

    public void setAn(String an) {
        this.an = an;
    }

    public String getNama_kerabat() {
        return nama_kerabat;
    }

    public void setNama_kerabat(String nama_kerabat) {
        this.nama_kerabat = nama_kerabat;
    }

    public String getNo_identitas_kerabat() {
        return no_identitas_kerabat;
    }

    public void setNo_identitas_kerabat(String no_identitas_kerabat) {
        this.no_identitas_kerabat = no_identitas_kerabat;
    }

    public String getNo_hp_kerabat() {
        return no_hp_kerabat;
    }

    public void setNo_hp_kerabat(String no_hp_kerabat) {
        this.no_hp_kerabat = no_hp_kerabat;
    }

    public String getHubungan_kerabat() {
        return hubungan_kerabat;
    }

    public void setHubungan_kerabat(String hubungan_kerabat) {
        this.hubungan_kerabat = hubungan_kerabat;
    }

    public String getAlamat_kerabat() {
        return alamat_kerabat;
    }

    public void setAlamat_kerabat(String alamat_kerabat) {
        this.alamat_kerabat = alamat_kerabat;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCetak() {
        return cetak;
    }

    public void setCetak(String cetak) {
        this.cetak = cetak;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTgl_dibuat() {
        return tgl_dibuat;
    }

    public void setTgl_dibuat(String tgl_dibuat) {
        this.tgl_dibuat = tgl_dibuat;
    }

    private String pekerjaan;
    private String pendidikan_terakhir;
    private String souvenir;
    private String size_souvenir;
    private String ahli_waris;
    private String id_perwakilan;
    private String no_rekening;
    private String an;
    private String nama_kerabat;
    private String no_identitas_kerabat;
    private String no_hp_kerabat;
    private String hubungan_kerabat;
    private String alamat_kerabat;
    private String keterangan;
    private String status;
    private String cetak;
    private String ktp;
    private String foto;
    private String tgl_dibuat;

    public String getId_pendaftaraan() {
        return id_pendaftaraan;
    }

    public void setId_pendaftaraan(String id_pendaftaraan) {
        this.id_pendaftaraan = id_pendaftaraan;
    }

    public String getKode_grup() {
        return kode_grup;
    }

    public void setKode_grup(String kode_grup) {
        this.kode_grup = kode_grup;
    }

    public String getKode_paket() {
        return kode_paket;
    }

    public void setKode_paket(String kode_paket) {
        this.kode_paket = kode_paket;
    }

    public String getKode_dp() {
        return kode_dp;
    }

    public void setKode_dp(String kode_dp) {
        this.kode_dp = kode_dp;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getTgl_berangkat() {
        return tgl_berangkat;
    }

    public void setTgl_berangkat(String tgl_berangkat) {
        this.tgl_berangkat = tgl_berangkat;
    }

    public String getNo_kwitansi() {
        return no_kwitansi;
    }

    public void setNo_kwitansi(String no_kwitansi) {
        this.no_kwitansi = no_kwitansi;
    }

    public String getHarga_rupiah() {
        return harga_rupiah;
    }

    public void setHarga_rupiah(String harga_rupiah) {
        this.harga_rupiah = harga_rupiah;
    }

    public String getDibayar() {
        return dibayar;
    }

    public void setDibayar(String dibayar) {
        this.dibayar = dibayar;
    }

    public String getNama_jamaah() {
        return nama_jamaah;
    }

    public void setNama_jamaah(String nama_jamaah) {
        this.nama_jamaah = nama_jamaah;
    }

    public String getNama_ayah() {
        return nama_ayah;
    }

    public void setNama_ayah(String nama_ayah) {
        this.nama_ayah = nama_ayah;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getNo_paspor() {
        return no_paspor;
    }

    public void setNo_paspor(String no_paspor) {
        this.no_paspor = no_paspor;
    }

    public String getTempat_keluar_paspor() {
        return tempat_keluar_paspor;
    }

    public void setTempat_keluar_paspor(String tempat_keluar_paspor) {
        this.tempat_keluar_paspor = tempat_keluar_paspor;
    }

    public String getNo_identitas() {
        return no_identitas;
    }

    public void setNo_identitas(String no_identitas) {
        this.no_identitas = no_identitas;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getId_kabupaten() {
        return id_kabupaten;
    }

    public void setId_kabupaten(String id_kabupaten) {
        this.id_kabupaten = id_kabupaten;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }
}
