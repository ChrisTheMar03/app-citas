package com.clinicamp.app.ui;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.clinicamp.app.R;
import com.clinicamp.app.adapter.CancelarAdapter;
import com.clinicamp.app.adapter.CitasAdapter;
import com.clinicamp.app.models.Citas;
import com.clinicamp.app.retrofit.CitaRepository;
import com.clinicamp.app.utilitarios.Preferencias;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CancelarFragment extends Fragment {

    private RecyclerView rv_cancelar;
    private CitaRepository repository;
    private Preferencias preferencias;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository=new CitaRepository();
        preferencias=new Preferencias(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_cancelar, container, false);
        initViews(view);
        rv_cancelar.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        Integer idUser = Integer.parseInt(preferencias.getStringValue("iduser"));
        repository.citasXusuario(idUser,list->{
            List<Citas> listadoCitas= (List<Citas>) list;
            if(!listadoCitas.isEmpty()){
                List<Citas> lst = new ArrayList<>();
                for (Citas c:listadoCitas) {
                    //&& new Date(c.getFechaAtencion()).before(new Date(System.currentTimeMillis()))
                    if(c.getEstado().equals(true) ){
                        lst.add(c);
                    }
                }
                rv_cancelar.setAdapter(new CancelarAdapter(lst,citas ->{
                    Citas ct= (Citas) citas;
                    AlertDialog.Builder alert=new AlertDialog.Builder(getContext());
                    alert.setMessage("Seguro Desea Cancelar la Cita?");
                    alert.setTitle("Cita");
                    alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            repository.actualizarEstado(ct,res ->{
                                Citas citaUpdate = (Citas) res;
                                if(citaUpdate!=null){
                                    Snackbar.make(getView(),"Se cancelo su cita",Snackbar.LENGTH_LONG).setBackgroundTint(Color.RED).setTextColor(Color.WHITE) .show();
                                    //getParentFragmentManager().popBackStack();
                                }
                                getParentFragmentManager().popBackStack();
                            });


                        }
                    });
                    alert.setNegativeButton("Cancelar",null);
                    alert.create().show();
                }));
            }
        });
        return view;
    }

    private void initViews(View view){
        rv_cancelar=view.findViewById(R.id.rv_cancelar);
    }

}