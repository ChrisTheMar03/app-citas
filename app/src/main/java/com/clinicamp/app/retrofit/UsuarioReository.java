package com.clinicamp.app.retrofit;

import android.util.Log;

import com.clinicamp.app.models.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioReository {

    private final ClienteRetrofit clienteRetrofit=new ClienteRetrofit();

    public void iniciarSesion(String dni,String password,ObjectResponse objectResponse){
        Call<Usuario> service = clienteRetrofit.service.iniciarSesion(dni,password);

        service.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Object res = null;
                if(response.isSuccessful()){
                    res=response.body();
                    objectResponse.response(res);
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("usuarios",t.getMessage().toString());
            }
        });

    }

    public void registrarUsuario(Usuario usuario, ObjectResponse objectResponse){

        Call<Usuario> resultado = clienteRetrofit.service.registrarUsuario(usuario);
        resultado.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    objectResponse.response(response.body());
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("usuarios",t.getMessage().toString());
            }
        });

    }

    public void obtenerUsuario(Integer id,ObjectResponse objectResponse){
        Call<Usuario> call = clienteRetrofit.service.obtenerUsuario(id);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    objectResponse.response(response.body());
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("usuarios",t.getMessage().toString());
            }
        });
    }

}

