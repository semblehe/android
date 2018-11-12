package com.example.linuxlite.myapplication.model;

import com.example.linuxlite.myapplication.model.KotaModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yumna on 11/11/18.
 */

public class ListKota {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<KotaModel> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<KotaModel> getData() {
        return data;
    }

    public void setData(List<KotaModel> data) {
        this.data = data;
    }
}
