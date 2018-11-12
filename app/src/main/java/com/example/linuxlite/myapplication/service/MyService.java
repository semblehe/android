package com.example.linuxlite.myapplication.service;


import com.example.linuxlite.myapplication.model.ListKota;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyService {

    @GET("radio")
    Call<ListKota> getKota();

}
