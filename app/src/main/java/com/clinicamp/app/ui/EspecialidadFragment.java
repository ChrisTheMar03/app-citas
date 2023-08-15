package com.clinicamp.app.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.clinicamp.app.R;
import com.clinicamp.app.adapter.EspecialidadAdapter;
import com.clinicamp.app.adapter.EspecialidadClick;
import com.clinicamp.app.models.Especialidad;
import com.clinicamp.app.retrofit.EspecialidadRepository;

import java.util.List;

public class EspecialidadFragment extends Fragment {

    private EspecialidadRepository repository;
    private RecyclerView rv;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository=new EspecialidadRepository();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_especialidad, container, false);
        initViews(view);
        progressBar.setVisibility(View.VISIBLE);
        rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        repository.obtenerEspecialidades(list ->{
            Log.d("lista",list.toString());
            progressBar.setVisibility(View.GONE);
            rv.setAdapter(new EspecialidadAdapter((List<Especialidad>) list, new EspecialidadClick() {
                @Override
                public void listener(Especialidad especialidad) {
                    Bundle b=new Bundle();
                    b.putString("especialidad",especialidad.getEspecialidad());
                    b.putInt("idesp",especialidad.getIdEsp());
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.contenedor_principal, ReservaFragment.class,b)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null)
                            .commit();

                }
            }));
        });
        return view;
    }

    private void initViews(View view){
        rv=view.findViewById(R.id.rv_especialidad);
        progressBar=view.findViewById(R.id.esp_progress);
    }

}