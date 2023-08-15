package com.clinicamp.app.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.clinicamp.app.PrincipalActivity;
import com.clinicamp.app.R;
import com.clinicamp.app.models.Usuario;
import com.clinicamp.app.retrofit.UsuarioReository;
import com.clinicamp.app.utilitarios.Preferencias;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends Fragment {

    private Button iniciar,registrar;
    private TextInputEditText dni,password;
    private UsuarioReository usuarioReository;
    private ProgressBar progressBar;
    private Preferencias preferencias;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarioReository=new UsuarioReository();
        preferencias=new Preferencias(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registrar.setOnClickListener(v->{
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container,new RegistroFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    .addToBackStack(null)
                    .commit();
        });

        iniciar.setOnClickListener(v->{
            if(!dni.getText().toString().trim().equals("") && !password.getText().toString().trim().equals("")){

                progressBar.setVisibility(View.VISIBLE);
                usuarioReository.iniciarSesion(dni.getText().toString().trim(),password.getText().toString().trim(),obj->{
                    if(obj!=null){
                        Usuario user= (Usuario) obj;
                        preferencias.setStringValue("iduser",user.getIdUser().toString());
                        progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(getActivity(), PrincipalActivity.class));
                    }else{
                        Toast.makeText(getContext(),"Paciente no registrado",Toast.LENGTH_LONG).show();
                    }
                });

            }else{
                Toast.makeText(getContext(),"Complete los camos necesarios",Toast.LENGTH_LONG).show();
            }

        });

    }

    private void initViews(View view){
        iniciar=view.findViewById(R.id.btnIniciar);
        registrar=view.findViewById(R.id.btnRegistrar);
        dni=view.findViewById(R.id.txtDNI);
        password=view.findViewById(R.id.txtPASSW);
        progressBar=view.findViewById(R.id.login_progress);
    }


}