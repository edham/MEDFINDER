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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_respuesta_casos_salud, container, false);
        idConsultas=getArguments().getInt("id");
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

            clsDoctor doctor= clsDoctorDAO.Buscar(context, listCasos.get(position).getObjDoctor().getInt_id_doctor());
            if(!doctor.isBol_favorito())
            {
                ImageView imageFavorito = (ImageView) item.findViewById(R.id.imageFavorito);
                imageFavorito.setVisibility(View.GONE);

            }

            TextView lblDoctor = (TextView)item.findViewById(R.id.lblDoctor);
            lblDoctor.setText("Dr. "+doctor.getStr_apellido_paterno()+" "+doctor.getStr_apellido_materno()+" "+doctor.getStr_nombres());

            RatingBar rtbPuntos = (RatingBar)item.findViewById(R.id.rtbPuntos);
            rtbPuntos.setRating( listCasos.get(position).getInt_puntaje());

            ImageView image = (ImageView)item.findViewById(R.id.image);
            if(doctor.getByte_foto()!=null)
                image.setImageBitmap(Utilidades.getBitmap(doctor.getByte_foto()));



            Button btnCalificar = (Button)item.findViewById(R.id.btnCalificar);
            btnCalificar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getDialogo(listCasos.get(posicion));
                }
            });
            if(listCasos.get(position).getInt_puntaje()>0)
            {
                View viewCalificar = (View)item.findViewById(R.id.viewCalificar);
                viewCalificar.setVisibility(View.GONE);

            }



            return(item);
        }
    }

    public void getDialogo(clsRespuestaPreguntaPaciente entidad)
    {
        final clsRespuestaPreguntaPaciente objeto=entidad;
        final Dialog dialog = new Dialog(this.getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_califcar_respuesta);
        final RatingBar rtbPuntos = (RatingBar) dialog.findViewById(R.id.rtbPuntos);

        FloatingActionButton btnAceptar = (FloatingActionButton) dialog.findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rtbPuntos.getRating()>0)
                {
                    setCalificar((int)rtbPuntos.getRating(),objeto);
                    dialog.dismiss();
                }
                else
                    Utilidades.alert(RespuestaConsultaFragment.this.getActivity(), getString(R.string.str_ingrese_calificacion));
            }
        });
        FloatingActionButton btnCancelar = (FloatingActionButton) dialog.findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    public void setCalificar(int puntaje,clsRespuestaPreguntaPaciente entidad)
    {
        /*
        String cadena= http.puntajeRespuestaPreguntaPaciente(entidad.getInt_id_respuesta_pregunta_paciente(), puntaje);
        if(!cadena.trim().equals("0"))
        {
            entidad.setInt_puntaje(puntaje);
            clsRespuestaPreguntaPacienteDAO.Actualizar(this, entidad);
            Buscar(idConsultas); ;

        }*/
    }

}
