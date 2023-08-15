package com.clinicamp.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.clinicamp.app.R;
import com.clinicamp.app.models.Citas;

import java.util.Date;
import java.util.List;

public class CitasAdapter extends RecyclerView.Adapter<CitasAdapter.CitasViewHolder> {

    private List<Citas> citas;

    public CitasAdapter(List<Citas> citas){
        this.citas=citas;
    }

    @NonNull
    @Override
    public CitasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_citas,parent,false);
        return new CitasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitasViewHolder holder, int position) {
        Citas cita=this.citas.get(position);
        holder.render(cita);
    }

    @Override
    public int getItemCount() {
        return citas.size();
    }

    static class CitasViewHolder extends RecyclerView.ViewHolder{

        private TextView doctor,especialidad,fecha;
        private ImageView icono;

        public CitasViewHolder(@NonNull View itemView) {
            super(itemView);
            doctor=itemView.findViewById(R.id.c_doctor);
            especialidad=itemView.findViewById(R.id.c_espec);
            fecha=itemView.findViewById(R.id.c_atencion);
            icono=itemView.findViewById(R.id.c_tiempo);
        }

        public void render(Citas citaItem){
            doctor.setText(citaItem.getIdMed().getApellido()+", "+citaItem.getIdMed().getNombre());
            especialidad.setText(citaItem.getIdMed().getIdEsp().getEspecialidad());
            fecha.setText(cortarPalabra(citaItem.getFechaAtencion(),"T"));
            /*Date actual = new Date(System.currentTimeMillis());
            Date fecha= new Date(citaItem.getFechaAtencion());
            if(fecha.before(actual)){
                icono.setVisibility(View.INVISIBLE);
            }*/
        }

        private String cortarPalabra(String fecha,String letra){
            return fecha.substring(0,fecha.indexOf(letra));
        }

    }

}
