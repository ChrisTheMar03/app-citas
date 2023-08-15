package com.clinicamp.app.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clinicamp.app.R;
import com.clinicamp.app.adapter.CitasAdapter;
import com.clinicamp.app.models.Citas;
import com.clinicamp.app.retrofit.CitaRepository;
import com.clinicamp.app.utilitarios.Preferencias;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class CitasFragment extends Fragment {

    private RecyclerView rvcitas,rvcitaspasadas;
    private CitaRepository repository;
    private List<Citas> listadoCitas;
    private Preferencias preferencias;
    private TextView list_vacia;

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
        View view =inflater.inflate(R.layout.fragment_citas, container, false);
        initViews(view);
        rvcitas.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));        //rvcitaspasadas.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        Integer idUser = Integer.parseInt(preferencias.getStringValue("iduser"));
        repository.citasXusuario(idUser,lista->{
            listadoCitas=(List<Citas>) lista;
            if(!listadoCitas.isEmpty()){
                list_vacia.setVisibility(View.GONE);
                List<Citas> lst = new ArrayList<>();
                for (Citas c:listadoCitas) {
                    if(c.getEstado().equals(true)){
                        lst.add(c);
                    }
                }
                rvcitas.setAdapter(new CitasAdapter(lst));
            }else{
                list_vacia.setVisibility(View.VISIBLE);
                rvcitas.setVisibility(View.GONE);
            }

        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initViews(View view){
        rvcitas=view.findViewById(R.id.rv_citas);
//        rvcitaspasadas=view.findViewById(R.id.rv_citas_pasadas);
        list_vacia=view.findViewById(R.id.lista_vacia);
    }


}