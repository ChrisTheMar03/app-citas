package com.clinicamp.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.clinicamp.app.ui.HomeFragment;
import com.clinicamp.app.ui.LoginFragment;
import com.clinicamp.app.ui.RegistroFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,new LoginFragment())
                .commit();

    }
}