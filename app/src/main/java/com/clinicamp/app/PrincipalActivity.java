package com.clinicamp.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.clinicamp.app.ui.HomeFragment;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor_principal,new HomeFragment())
                    .commit();
        }


    }

}