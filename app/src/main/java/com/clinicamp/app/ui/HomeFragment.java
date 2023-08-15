package com.clinicamp.app.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.clinicamp.app.PrincipalActivity;
import com.clinicamp.app.R;
import com.google.android.material.card.MaterialCardView;


public class HomeFragment extends Fragment {

    private MaterialCardView materialCardView,citas,phone,perfil,cancelar;
    private String numero = "123456789";//Numero del hospital

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        materialCardView.setOnClickListener(v->{
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.contenedor_principal,new EspecialidadFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
        });

        citas.setOnClickListener(v->{
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.contenedor_principal,new CitasFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
        });

        phone.setOnClickListener(v->{
            //ACTION_DIAL -> Redirecciona al telefono *
            //ACTION_CALL -> LLama defrente wa
            Uri uri=Uri.parse("tel:"+numero);
            Intent llamar =new Intent(Intent.ACTION_DIAL,uri);
            startActivity(llamar);
        });

        perfil.setOnClickListener(v->{
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.contenedor_principal,new PerfilFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
        });

        cancelar.setOnClickListener(v->{
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.contenedor_principal,new CancelarFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
        });

    }


    private void initViews(View view){
        materialCardView=view.findViewById(R.id.card_calendar);
        citas=view.findViewById(R.id.card_citas);
        phone=view.findViewById(R.id.card_plus);
        perfil=view.findViewById(R.id.card_perfil);
        cancelar=view.findViewById(R.id.card_cancelar);
    }



}