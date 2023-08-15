package com.clinicamp.app.adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.clinicamp.app.R;
import com.clinicamp.app.models.Citas;

import java.util.List;

public class CancelarAdapter extends RecyclerView.Adapter<CancelarAdapter.CancelarViewHolder> {

    private List<Citas> lista;
    private CitaClick citaClick;

    public CancelarAdapter(List<Citas> lista,CitaClick citaClick){
        this.lista=lista;
        this.citaClick=citaClick;
    }

    @NonNull
    @Override
    public CancelarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cancelar_item,parent,false);
        return new CancelarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CancelarViewHolder holder, int position) {
        Citas c=this.lista.get(position);
        holder.render(c,citaClick);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class CancelarViewHolder extends RecyclerView.ViewHolder{

        private TextView doctor,especialidad,fecha;
//        private RadioButton radioButton;

        public CancelarViewHolder(@NonNull View itemView) {
            super(itemView);
            doctor=itemView.findViewById(R.id.c_doctor_c);
            especialidad=itemView.findViewById(R.id.c_espec_c);
            fecha=itemView.findViewById(R.id.c_atencion_c);
//            radioButton=itemView.findViewById(R.id.radio);
        }

        public void render(Citas citaItem,CitaClick citaClick){
            doctor.setText(citaItem.getIdMed().getApellido()+", "+citaItem.getIdMed().getNombre());
            especialidad.setText(citaItem.getIdMed().getIdEsp().getEspecialidad());
            fecha.setText(cortarPalabra(citaItem.getFechaAtencion(),"T"));
            itemView.setOnClickListener(v->{
               citaClick.click(citaItem);
            });

        }

        private String cortarPalabra(String fecha,String letra){
            return fecha.substring(0,fecha.indexOf(letra));
        }

    }
}
