package com.med.finder.cliente.fragment;

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

import com.med.finder.cliente.R;
import com.med.finder.cliente.conexion.InsertarVotoRespuestaCasosSaludHTTP;
import com.med.finder.cliente.dao.clsCasosSaludDAO;
import com.med.finder.cliente.dao.clsDoctorDAO;
import com.med.finder.cliente.dao.clsRespuestaCasosSaludDAO;
import com.med.finder.cliente.dao.clsUsuarioDAO;
import com.med.finder.cliente.entidades.clsDoctor;
import com.med.finder.cliente.entidades.clsRespuestaCasosSalud;
import com.med.finder.cliente.utilidades.Utilidades;

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
    public void getDialogo(final clsRespuestaCasosSalud entidad)
    {

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
                    setCalificar((int)rtbPuntos.getRating(),entidad.getInt_id_respuesta_casos_salud());
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


    public void setCalificar(final int puntaje,final int id)
    {
        if ( Utilidades.checkPermissions(this.getContext())) {
            pd = new ProgressDialog(this.getContext());
            pd.setTitle("Cargando Datos");
            pd.setMessage("Espere un momento");
            pd.setCancelable(false);
            pd.show();
            new Thread() {
                public void run() {
                    Message message = handlerCargar.obtainMessage();
                    Bundle bundle = new Bundle();
                    int rpta=0;
                    try {
                        InsertarVotoRespuestaCasosSaludHTTP http = new InsertarVotoRespuestaCasosSaludHTTP();
                        http.execute(clsUsuarioDAO.Buscar(RespuestaCasosSaludFragment.this.getContext()).getInt_id_usuario(),id,puntaje);
                        String result = http.get();
                        if (!result.equals("")) {
                            JSONObject entidadJSON = new JSONObject(result);
                            if (entidadJSON.getInt("rpta") == 1) {
                                rpta=1;
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    bundle.putInt("rpta",rpta);
                    bundle.putInt("id",id);
                    bundle.putInt("puntaje",puntaje);
                    message.setData(bundle);
                    handlerCargar.sendMessage(message);
                }
            }.start();
        }
    }

    final Handler handlerCargar=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();
            Bundle bundle = msg.getData();
            if(bundle.getInt("rpta")==1)
            {
                Utilidades.alert(RespuestaCasosSaludFragment.this.getContext(), getString(R.string. str_registro_correcto));
                clsRespuestaCasosSaludDAO.Puntaje(RespuestaCasosSaludFragment.this.getActivity(),bundle.getInt("id"), bundle.getInt("puntaje"));
                Buscar(idCasoSalud); ;

            }else
            {
                Utilidades.alert(RespuestaCasosSaludFragment.this.getContext(), getString(R.string. str_error_registrar));

            }

        }
    };
}
