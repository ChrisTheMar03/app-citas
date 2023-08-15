package com.clinicamp.app.retrofit;

import android.util.Log;

import com.clinicamp.app.models.Especialidad;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EspecialidadRepository {

    private final ClienteRetrofit clienteRetrofit=new ClienteRetrofit();

    public void obtenerEspecialidades(ObjectResponse objectResponse){

        Call<List<Especialidad>> res = clienteRetrofit.service.listarEspecialidades();
        res.enqueue(new Callback<List<Especialidad>>() {
            @Override
            public void onResponse(Call<List<Especialidad>> call, Response<List<Especialidad>> response) {
                if(response.isSuccessful()){
                    objectResponse.response(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Especialidad>> call, Throwable t) {
                Log.e("especialidades",t.getMessage().toString());
            }
        });

    }

}
