package com.med.finder.doctor.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.med.finder.doctor.R;
import com.med.finder.doctor.dao.clsDoctorDAO;
import com.med.finder.doctor.dao.clsPreguntaPacienteDAO;
import com.med.finder.doctor.dao.clsRespuestaPreguntaPacienteDAO;
import com.med.finder.doctor.entidades.clsDoctor;
import com.med.finder.doctor.entidades.clsRespuestaPreguntaPaciente;
import com.med.finder.doctor.utilidades.Utilidades;

import java.util.List;


public class RespuestaConsultaFragment extends Fragment {


    private List<clsRespuestaPreguntaPaciente> listCasos;
    private Adaptador adaptador;
    private ListView list;
    private int idConsultas=0;
    private clsDoctor doctor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_respuesta_casos_salud, container, false);
        idConsultas=getArguments().getInt("id");
        doctor= clsDoctorDAO.Buscar(this.getActivity());

        list = (ListView)view.findViewById(R.id.list);
        TextView lblTitulo = (TextView)view.findViewById(R.id.lblTitulo);
        lblTitulo.setText(clsPreguntaPacienteDAO.Buscar(this.getActivity(), idConsultas).getStr_asunto());
        Buscar(idConsultas);
        return view;
    }



    public void Buscar(int id)
    {


        listCasos= clsRespuestaPreguntaPacienteDAO.Listar(this.getActivity(), id);


        adaptador = new Adaptador(this.getActivity());
        list.setAdapter(adaptador);


    }


    class Adaptador extends ArrayAdapter {

        Activity context;

        Adaptador(Activity context) {
            super(context, R.layout.list_respuesta_casos_salud, listCasos);
            this.context = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            final int posicion=position;
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_respuesta_casos_salud, null);


            TextView lblTema = (TextView)item.findViewById(R.id.lblTema);
            lblTema.setText(listCasos.get(position).getStr_detalle());

            TextView lblDoctor = (TextView)item.findViewById(R.id.lblDoctor);
            lblDoctor.setText("Dr. "+doctor.getStr_apellido_paterno()+" "+doctor.getStr_apellido_materno()+" "+doctor.getStr_nombres());

            RatingBar rtbPuntos = (RatingBar)item.findViewById(R.id.rtbPuntos);
            rtbPuntos.setRating( listCasos.get(position).getInt_puntaje());

            ImageView image = (ImageView)item.findViewById(R.id.image);
            if(doctor.getByte_foto()!=null)
                image.setImageBitmap(Utilidades.getBitmap(doctor.getByte_foto()));


            return(item);
        }
    }
}
