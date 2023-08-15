package com.clinicamp.app.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.clinicamp.app.PrincipalActivity;
import com.clinicamp.app.R;
import com.clinicamp.app.models.Citas;
import com.clinicamp.app.models.Medico;
import com.clinicamp.app.models.Usuario;
import com.clinicamp.app.retrofit.CitaRepository;
import com.clinicamp.app.utilitarios.Preferencias;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ReservaFragment extends Fragment {

    private Integer idEsp, idMedico;
    private String nombreEsp;
    private TextView titulo;
    private CitaRepository repository;
    private Spinner spinner;
    private CalendarView calendarView;
    private String fechaReserva;
    private MaterialButton button;
    private Preferencias preferencias;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository=new CitaRepository();
        preferencias=new Preferencias(getContext());

        if(getArguments()!=null){
            Integer id = getArguments().getInt("idesp");
            String nombre = getArguments().getString("especialidad");
            idEsp=id;
            nombreEsp=nombre;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_reserva, container, false);
        initView(v);
        repository.clienteXespec(idEsp,lista->{
            List<Medico> list = (List<Medico>) lista;
            ArrayAdapter data=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item,obtenerNombre(list));
            data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(data);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Medico indice = list.get(i);
                    idMedico=indice.getIdMe();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titulo.setText(nombreEsp);
        Calendar calendar = Calendar.getInstance();
        long currentTimeInMillis = calendar.getTimeInMillis();
        calendarView.setMinDate(currentTimeInMillis);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String mes = agregarCero(i1+1);
                String dia = agregarCero(i2);
                fechaReserva=i+"-"+mes+"-"+dia;
                Log.d("fecha",fechaReserva);
            }
        });

        button.setOnClickListener(v->{
            Integer idUser = Integer.parseInt(preferencias.getStringValue("iduser"));
            Citas cita=new Citas(null,new Medico(idMedico) ,new Usuario(idUser),fechaReserva,true);
            progressBar.setVisibility(View.VISIBLE);
            repository.reservarCita(cita,entero->{
                Citas resultado = (Citas) entero;
                if(resultado!=null){
                    progressBar.setVisibility(View.GONE);
                    Snackbar.make(v,"CITA RESERVADA.!!",Snackbar.LENGTH_LONG).setBackgroundTint(Color.CYAN).setTextColor(Color.BLACK).show();
                    getParentFragmentManager().popBackStack();
                }else{
                    Toast.makeText(getContext(), "No se puede sacar cita", Toast.LENGTH_SHORT).show();
                }

            });
        });

    }

    private void initView(View view){
        titulo=view.findViewById(R.id.re_esp);
        spinner=view.findViewById(R.id.sp_medico);
        calendarView=view.findViewById(R.id.calendarView);
        button=view.findViewById(R.id.btn_reservar);
        progressBar=view.findViewById(R.id.reser_progress);
    }

    private List<String> obtenerNombre(List<Medico> lista){
        List<String> list=new ArrayList<>();
        for (Medico m:lista) {
            list.add(m.getApellido()+", "+m.getNombre());
        }
        return list;
    }

    private String agregarCero(int numero){
        if(numero<10 && numero>0){
            return "0"+numero;
        }
        return ""+numero;
    }

}