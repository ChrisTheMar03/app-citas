package com.clinicamp.app.utilitarios;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {

    private SharedPreferences sharedPreferences;

    public Preferencias(Context context) {
        sharedPreferences = context.getSharedPreferences("UTILS", Context.MODE_PRIVATE);
    }

    public String getStringValue(String clave) {
        return sharedPreferences.getString(clave, "");
    }

    public void setStringValue(String clave, String valor) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(clave, valor);
        editor.apply();
    }

    public void deleteStringValue(String clave){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(clave);
        edit.apply();
    }


}
