package com.med.finder.doctor.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.med.finder.doctor.dao.clsCasosSaludDAO;
import com.med.finder.doctor.dao.clsDoctorDAO;
import com.med.finder.doctor.dao.clsRespuestaCasosSaludDAO;
import com.med.finder.doctor.entidades.clsDoctor;
import com.med.finder.doctor.entidades.clsRespuestaCasosSalud;
import com.med.finder.doctor.utilidades.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class RespuestaCasosSaludFragment extends Fragment {


    private List<clsRespuestaCasosSalud> listCasos;
    private Adaptador adaptador;
    private ListView list;
    private int idCasoSalud=0;
    public ProgressDialog pd;
    private  clsDoctor doctor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_respuesta_casos_salud, container, false);
        idCasoSalud=getArguments().getInt("id");
        doctor= clsDoctorDAO.Buscar(this.getContext());
        list = (ListView)view.findViewById(R.id.list);
        TextView lblTitulo = (TextView)view.findViewById(R.id.lblTitulo);
        lblTitulo.setText(clsCasosSaludDAO.Buscar(this.getActivity(), idCasoSalud).getStr_tema());


        Buscar(idCasoSalud);
        return view;
    }


    public void Buscar(int id)
    {


        listCasos= clsRespuestaCasosSaludDAO.ListarXCasoSalud(this.getActivity(), id);

        if(listCasos!=null && listCasos.size()>0)
        {
            adaptador = new Adaptador(this.getActivity());

            list.setAdapter(adaptador);

        }
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
            lblTema.setText(listCasos.get(position).getStr_descripcion());



            TextView lblDoctor = (TextView)item.findViewById(R.id.lblDoctor);
            lblDoctor.setText("Dr. "+doctor.getStr_apellido_paterno()+" "+doctor.getStr_apellido_materno()+" "+doctor.getStr_nombres());

            RatingBar rtbPuntos = (RatingBar)item.findViewById(R.id.rtbPuntos);
            rtbPuntos.setRating( listCasos.get(position).getInt_puntaje());

            ImageView image = (ImageView)item.findViewById(R.id.image);
            if(doctor.getByte_foto()!=null)
                image.setImageBitmap(Utilidades.getBitmap(doctor.getByte_foto()));

            if(listCasos.get(position).getInt_puntaje()>0)
            {
                //View viewCalificar = (View)item.findViewById(R.id.viewCalificar);
               // viewCalificar.setVisibility(View.GONE);

            }
            return(item);
        }
    }


}
