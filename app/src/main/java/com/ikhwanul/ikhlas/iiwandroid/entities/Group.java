package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class Group implements Serializable{
    private String id_grup;
    private String jenis_grup;

    public String getId_grup() {
        return id_grup;
    }

    public void setId_grup(String id_grup) {
        this.id_grup = id_grup;
    }

    public String getJenis_grup() {
        return jenis_grup;
    }

    public void setJenis_grup(String jenis_grup) {
        this.jenis_grup = jenis_grup;
    }
}
