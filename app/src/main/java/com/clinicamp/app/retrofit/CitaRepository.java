package com.clinicamp.app.retrofit;

import android.util.Log;

import com.clinicamp.app.models.Citas;
import com.clinicamp.app.models.Medico;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitaRepository {

    private final ClienteRetrofit clienteRetrofit=new ClienteRetrofit();

    public void clienteXespec(Integer idEspecialidad,ObjectResponse objectResponse){
        Call<List<Medico>> result = clienteRetrofit.service.medicoXespecialidad(idEspecialidad);

        result.enqueue(new Callback<List<Medico>>() {
            @Override
            public void onResponse(Call<List<Medico>> call, Response<List<Medico>> response) {
                if(response.isSuccessful()){
                    objectResponse.response(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Medico>> call, Throwable t) {
                Log.e("especialidad",t.getMessage().toString());
            }
        });

    }

    public void reservarCita(Citas citas,ObjectResponse objectResponse){
        Call<Citas> result = clienteRetrofit.service.reservarCita(citas);

        result.enqueue(new Callback<Citas>() {
            @Override
            public void onResponse(Call<Citas> call, Response<Citas> response) {
                if(response.isSuccessful()){
                    objectResponse.response(response.body());
                }
            }

            @Override
            public void onFailure(Call<Citas> call, Throwable t) {
                Log.e("citas",t.getMessage().toString());
            }
        });

    }

    public void citasXusuario(Integer idUser,ObjectResponse objectResponse){
        Call<List<Citas>> result = clienteRetrofit.service.citasXusuario(idUser);
        result.enqueue(new Callback<List<Citas>>() {
            @Override
            public void onResponse(Call<List<Citas>> call, Response<List<Citas>> response) {
                if(response.isSuccessful()){
                    objectResponse.response(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Citas>> call, Throwable t) {
                Log.e("citas",t.getMessage().toString());
            }
        });

    }

    public void actualizarEstado(Citas citas,ObjectResponse objectResponse){
        Call<Citas> result = clienteRetrofit.service.actualizarEstado(citas);
        result.enqueue(new Callback<Citas>() {
            @Override
            public void onResponse(Call<Citas> call, Response<Citas> response) {
                if(response.isSuccessful()){
                    objectResponse.response(response.body());
                }
            }

            @Override
            public void onFailure(Call<Citas> call, Throwable t) {
                Log.e("citas",t.getMessage().toString());
            }
        });
    }

}
