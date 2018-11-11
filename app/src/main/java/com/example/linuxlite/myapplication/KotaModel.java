package com.example.linuxlite.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yumna on 11/11/18.
 */

public class KotaModel {
    @SerializedName("idkota")
    @Expose
    private String idkota;
    @SerializedName("kota")
    @Expose
    private String kota;

    public String getIdkota() {
        return idkota;
    }

    public void setIdkota(String idkota) {
        this.idkota = idkota;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }
}
