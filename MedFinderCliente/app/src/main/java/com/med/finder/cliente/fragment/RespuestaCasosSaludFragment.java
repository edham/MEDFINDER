package com.med.finder.cliente.fragment;

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

import com.med.finder.cliente.R;
import com.med.finder.cliente.dao.clsCasosSaludDAO;
import com.med.finder.cliente.dao.clsDoctorDAO;
import com.med.finder.cliente.dao.clsRespuestaCasosSaludDAO;
import com.med.finder.cliente.entidades.clsDoctor;
import com.med.finder.cliente.entidades.clsRespuestaCasosSalud;
import com.med.finder.cliente.utilidades.Utilidades;

import java.util.List;


public class RespuestaCasosSaludFragment extends Fragment {


    private List<clsRespuestaCasosSalud> listCasos;
    private Adaptador adaptador;
    private ListView list;
    private int idCasoSalud=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_respuesta_casos_salud, container, false);
        idCasoSalud=getArguments().getInt("id");
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

            clsDoctor doctor= clsDoctorDAO.Buscar(context, listCasos.get(position).getInt_id_doctor());

            TextView lblDoctor = (TextView)item.findViewById(R.id.lblDoctor);
            lblDoctor.setText("Dr. "+doctor.getStr_apellido_paterno()+" "+doctor.getStr_apellido_materno()+" "+doctor.getStr_nombres());

            RatingBar rtbPuntos = (RatingBar)item.findViewById(R.id.rtbPuntos);
            rtbPuntos.setRating( listCasos.get(position).getInt_puntaje());

            ImageView image = (ImageView)item.findViewById(R.id.image);
            if(doctor.getByte_foto()!=null)
                image.setImageBitmap(Utilidades.getBitmap(doctor.getByte_foto()));

            if(!doctor.isBol_favorito())
            {
                ImageView imageFavorito = (ImageView) item.findViewById(R.id.imageFavorito);
                imageFavorito.setVisibility(View.GONE);

            }

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
    public void getDialogo(clsRespuestaCasosSalud entidad)
    {

        final int id=entidad.getInt_id_respuesta_casos_salud();
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
                    setCalificar((int)rtbPuntos.getRating(),id);
                    dialog.dismiss();
                }
                else
                    Utilidades.alert(RespuestaCasosSaludFragment.this.getActivity(), getString(R.string.str_ingrese_calificacion));
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


    public void setCalificar(int puntaje,int id)
    {/*
        String cadena= http.insertarVotoRespuestaCasosSalud(clsUsuarioDAO.Buscar(this.getActivity()).getInt_id_usuario(), id, puntaje);
        if(!cadena.trim().equals("0"))
        {
            clsRespuestaCasosSaludDAO.Favorito(this.getActivity(), id, true);
            Buscar(idCasoSalud); ;

        }*/
    }
}
