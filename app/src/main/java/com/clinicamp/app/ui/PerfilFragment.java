package com.clinicamp.app.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.clinicamp.app.PrincipalActivity;
import com.clinicamp.app.R;
import com.clinicamp.app.models.Usuario;
import com.clinicamp.app.retrofit.UsuarioReository;
import com.clinicamp.app.utilitarios.Preferencias;
import com.google.android.material.button.MaterialButton;

public class PerfilFragment extends Fragment {

    private Preferencias preferencias;
    private TextView nombre,apellido,dni,edad,fono;
    private UsuarioReository repository;
    private ProgressBar progressBar;
    MaterialButton btn;
    private ImageView imageView;
    private static final Integer REQUEST_CAMERA_PERMISSION=002;
    private static final Integer REQUEST_IMAGE_CAPTURE=003;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferencias=new Preferencias(getContext());
        repository=new UsuarioReository();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_perfil, container, false);
        initViews(view);
        progressBar.setVisibility(View.VISIBLE);
        Integer iduser =Integer.parseInt(preferencias.getStringValue("iduser"));
        repository.obtenerUsuario(iduser, user ->{
            if(user!=null){
                progressBar.setVisibility(View.GONE);
                Usuario usuario = (Usuario) user;
                nombre.setText(usuario.getNombre());
                fono.setText(usuario.getTelefono());
                apellido.setText(usuario.getApellido());
                dni.setText(usuario.getDni());
                edad.setText(String.valueOf(usuario.getEdad())+" años");
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn.setOnClickListener(v->{
            preferencias.deleteStringValue("iduser");
            getActivity().finish();
        });

        imageView.setOnClickListener(v->{

            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            } else {
                openCamera();
            }

        });

    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Verifica si hay una camara disponible
        /*if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
        }*/
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(getContext(), "Habilite los Permisos en ajustes", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == PrincipalActivity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    private void initViews(View view){
        nombre=view.findViewById(R.id.p_nombre);
        fono=view.findViewById(R.id.p_fono);
        apellido=view.findViewById(R.id.p_apellido);
        dni=view.findViewById(R.id.p_dni);
        edad=view.findViewById(R.id.p_edad);
        progressBar=view.findViewById(R.id.progressBar);
        btn=view.findViewById(R.id.btnCerrar);
        imageView=view.findViewById(R.id.p_img);
    }

}