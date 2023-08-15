package com.clinicamp.app.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteRetrofit {

    private static final String URL = "http://192.168.18.29:9898/api/clinica/";

    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ClinicaService service = retrofit.create(ClinicaService.class);

}
