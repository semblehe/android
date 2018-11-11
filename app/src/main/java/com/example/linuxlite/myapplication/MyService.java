package com.example.linuxlite.myapplication;


import retrofit2.Call;
import retrofit2.http.GET;

public interface MyService {

    @GET("kota")
    Call<KotaModel> getKota();

}
