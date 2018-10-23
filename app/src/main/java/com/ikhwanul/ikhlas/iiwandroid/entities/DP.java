package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class DP implements Serializable{
    private String id_dp;
    private String jenis_dp;

    public String getId_dp() {
        return id_dp;
    }

    public void setId_dp(String id_dp) {
        this.id_dp = id_dp;
    }

    public String getJenis_dp() {
        return jenis_dp;
    }

    public void setJenis_dp(String jenis_dp) {
        this.jenis_dp = jenis_dp;
    }
}
