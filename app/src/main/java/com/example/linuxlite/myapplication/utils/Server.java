package com.example.linuxlite.myapplication.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Server {

    public static final String URL = "http://sisterdummy.unej.ac.id/mobile/";

    // base url sister
//    public static final String URL = "https://mobile-sister.unej.ac.id/mobile/";

    public static final String LOGIN_URL = URL+"login/"; // login url
    public static final String JADWAL_URL = URL+"jadwal/"; // login url

    public static Retrofit RETROFIT = null;
    //public static OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
    public static Retrofit getClient(){
        if(RETROFIT==null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new LoggingInterceptor())
                    .build();
            RETROFIT = new Retrofit.Builder()
                    .baseUrl(URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }

        return RETROFIT;
    }
}
