package com.ikhwanul.ikhlas.iiwandroid.entities;

import java.io.Serializable;

public class KDMBelanja implements Serializable {
    private String label;
    private String number;
    private String notes;
    private boolean  ismoney;

    public boolean isIsmoney() {
        return ismoney;
    }

    public void setIsmoney(boolean ismoney) {
        this.ismoney = ismoney;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
