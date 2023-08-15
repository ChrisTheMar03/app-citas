package com.clinicamp.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.clinicamp.app.R;
import com.clinicamp.app.models.Especialidad;

import java.util.List;

public class EspecialidadAdapter extends RecyclerView.Adapter<EspecialidadAdapter.EspecialidadViewHolder> {

    private List<Especialidad> especialidadList;
    private EspecialidadClick listener;

    public EspecialidadAdapter(List<Especialidad> especialidadList,EspecialidadClick listener){
        this.especialidadList=especialidadList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public EspecialidadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reserva,parent,false);
        return new EspecialidadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EspecialidadViewHolder holder, int position) {
        Especialidad item = this.especialidadList.get(position);
        holder.render(item,listener);
    }

    @Override
    public int getItemCount() {
        return this.especialidadList.size();
    }

    static class EspecialidadViewHolder extends RecyclerView.ViewHolder{

        private TextView especialidad,descripcion;

        public EspecialidadViewHolder(@NonNull View itemView) {
            super(itemView);
            especialidad=itemView.findViewById(R.id.item_especialidad);
            descripcion=itemView.findViewById(R.id.item_descripcion);

        }

        private void render(Especialidad especialidades,EspecialidadClick escucha){
            especialidad.setText(especialidades.getEspecialidad());
            descripcion.setText(especialidades.getDescripcion());

            itemView.setOnClickListener(v->{
                escucha.listener(especialidades);
            });

        }

    }

}

