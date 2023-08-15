package com.clinicamp.app.retrofit;

import com.clinicamp.app.models.Citas;
import com.clinicamp.app.models.Especialidad;
import com.clinicamp.app.models.Medico;
import com.clinicamp.app.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClinicaService {

    //Registrar usuario
    @POST("usuario/")
    public Call<Usuario> registrarUsuario(@Body Usuario usuario);

    @POST("usuario/sesion/{dni}/{password}")
    public Call<Usuario> iniciarSesion(@Path("dni") String dni,@Path("password") String password);

    @GET("usuario/{id}")
    public Call<Usuario> obtenerUsuario(@Path("id") Integer id);

    @GET("especialidad/")
    public Call<List<Especialidad>> listarEspecialidades();

    @GET("medico/filtrar/{id}")
    public Call<List<Medico>> medicoXespecialidad(@Path("id") Integer id);

    @POST("citas/")
    public Call<Citas> reservarCita(@Body Citas citas);

    @GET("citas/listar/{id}")
    public Call<List<Citas>> citasXusuario(@Path("id") Integer id);

    @PUT("citas/")
    public Call<Citas> actualizarEstado(@Body Citas citas);


}
