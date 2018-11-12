package com.example.linuxlite.myapplication.service;


import com.example.linuxlite.myapplication.model.KotaModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyService {

    @GET("kota")
    Call<KotaModel> getKota();

}
