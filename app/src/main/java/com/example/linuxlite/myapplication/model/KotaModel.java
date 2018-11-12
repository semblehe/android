package com.example.linuxlite.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yumna on 11/11/18.
 */

public class KotaModel {
    @SerializedName("idradio")
    @Expose
    private String idradio;
    @SerializedName("judul")
    @Expose
    private String judul;

    public String getIdkota() {
        return idradio;
    }

    public void setIdkota(String idkota) {
        this.idradio = idkota;
    }

    public String getKota() {
        return judul;
    }

    public void setKota(String kota) {
        this.judul = kota;
    }
}
