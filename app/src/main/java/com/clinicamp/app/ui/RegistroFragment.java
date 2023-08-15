package com.clinicamp.app.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.clinicamp.app.R;
import com.clinicamp.app.models.Usuario;
import com.clinicamp.app.retrofit.UsuarioReository;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class RegistroFragment extends Fragment {

    private Button volver,registrar;
    private TextInputEditText nombre,apellidos,edad,dni,fono,contra;
    private UsuarioReository reository;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reository=new UsuarioReository();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_registro, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        volver.setOnClickListener(v->{
            getParentFragmentManager().popBackStack();

        });

        registrar.setOnClickListener(v->{
            if(validarCamposVacios()){
                progressBar.setVisibility(View.VISIBLE);
                Usuario usuario=new Usuario(null,nombre.getText().toString().trim(),
                        apellidos.getText().toString().trim(),dni.getText().toString().trim(),
                        Integer.parseInt(edad.getText().toString().trim()),fono.getText().toString().trim(),
                        contra.getText().toString().trim());
                reository.registrarUsuario(usuario,res ->{
                    Usuario usu = (Usuario) res;
                    if(usu!=null){
                        progressBar.setVisibility(View.GONE);
                        Snackbar.make(v,"Correctamente Registrado !!",Snackbar.LENGTH_LONG).show();
                        getParentFragmentManager().popBackStack();
                    }else{
                        Toast.makeText(getContext(),"No se pudo registrar al usuario",Toast.LENGTH_LONG).show();
                    }
                });
            }else{
                Toast.makeText(getContext(),"Rellene todos los campos",Toast.LENGTH_LONG).show();
            }


        });

    }

    private void initViews(View view){
        volver=view.findViewById(R.id.btnVolver);
        registrar=view.findViewById(R.id.registro);
        nombre=view.findViewById(R.id.txt_nombre);
        apellidos=view.findViewById(R.id.txt_apellido);
        edad=view.findViewById(R.id.txt_edad);
        dni=view.findViewById(R.id.txt_dni);
        fono=view.findViewById(R.id.txt_fono);
        contra=view.findViewById(R.id.txt_contra);
        progressBar=view.findViewById(R.id.reg_progress);
    }

    private boolean validarCamposVacios(){
        return !nombre.getText().toString().trim().equals("") &&
                !apellidos.getText().toString().trim().equals("") &&
                !edad.getText().toString().trim().equals("") &&
                !fono.getText().toString().trim().equals("") &&
                !contra.getText().toString().trim().equals("") &&
                !dni.getText().toString().trim().equals("");
    }

}