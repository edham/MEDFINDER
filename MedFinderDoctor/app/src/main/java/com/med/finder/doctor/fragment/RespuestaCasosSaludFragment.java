package com.med.finder.doctor.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.med.finder.doctor.R;
import com.med.finder.doctor.conexion.ResponderCasoSaludHTTP;
import com.med.finder.doctor.dao.clsCasosSaludDAO;
import com.med.finder.doctor.dao.clsDoctorDAO;
import com.med.finder.doctor.dao.clsRespuestaCasosSaludDAO;
import com.med.finder.doctor.entidades.clsCasosSalud;
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
    private clsCasosSalud casosSalud;
    private clsRespuestaCasosSalud respuestaCasosSalud;
    public ProgressDialog pd;
    private  clsDoctor doctor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_respuesta_casos_salud, container, false);
        casosSalud=clsCasosSaludDAO.Buscar(this.getActivity(),getArguments().getInt("id"));
        doctor= clsDoctorDAO.Buscar(this.getContext());
        list = (ListView)view.findViewById(R.id.list);
        TextView lblTitulo = (TextView)view.findViewById(R.id.lblTitulo);
        lblTitulo.setText(casosSalud.getStr_tema());

        Button btnAgregar = (Button)view.findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAgregar();
//
            }
        });

        Buscar();
        return view;
    }


    public void Buscar()
    {


        listCasos= clsRespuestaCasosSaludDAO.ListarXCasoSalud(this.getActivity(), casosSalud.getInt_id_casos_salud());

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

        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_respuesta_casos_salud, null);

            TextView lblTema = (TextView)item.findViewById(R.id.lblTema);
            lblTema.setText(listCasos.get(position).getStr_descripcion());

            RatingBar rtbPuntos = (RatingBar)item.findViewById(R.id.rtbPuntos);
            rtbPuntos.setRating( listCasos.get(position).getInt_puntaje());
            return(item);
        }
    }

    public void btnAgregar(){
            final Dialog dialog = new Dialog(this.getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setContentView(R.layout.dialog_responder_caso_salud);
            dialog.setCancelable(false);
            TextView lblCasoSalud = (TextView)dialog.findViewById(R.id.lblCasoSalud);
            lblCasoSalud.setText(casosSalud.getStr_tema());
            final EditText txtDetalle = (EditText) dialog.findViewById(R.id.txtDetalle);
            txtDetalle.setText("");


//
            FloatingActionButton btnAceptar = (FloatingActionButton) dialog.findViewById(R.id.btnAceptar);
            btnAceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!txtDetalle.getText().toString().equals("")) {

                        respuestaCasosSalud =new clsRespuestaCasosSalud();
                        respuestaCasosSalud.setInt_id_casos_salud(casosSalud.getInt_id_casos_salud());
                        respuestaCasosSalud.setInt_id_doctor(doctor.getInt_id_doctor());
                        respuestaCasosSalud.setStr_descripcion(txtDetalle.getText().toString());
                        registrar();
                        dialog.dismiss();
                    }
                    else {
                        Utilidades.alert(RespuestaCasosSaludFragment.this.getContext(), getString(R.string.str_ingrese_asunto));
                    }
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

    public void registrar()
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
                        ResponderCasoSaludHTTP http = new ResponderCasoSaludHTTP();
                        http.execute(respuestaCasosSalud);
                        String result = http.get();
                        if (!result.equals("")) {
                            JSONObject entidadJSON = new JSONObject(result);
                            if (entidadJSON.getInt("rpta") == 1) {

                                respuestaCasosSalud.setInt_id_respuesta_casos_salud(entidadJSON.getInt("respuestaCasoSaludId"));
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
                clsRespuestaCasosSaludDAO.Agregar(RespuestaCasosSaludFragment.this.getActivity(),respuestaCasosSalud);
                Buscar();

            }else
            {
                Utilidades.alert(RespuestaCasosSaludFragment.this.getContext(), getString(R.string. str_error_registrar));

            }

        }
    };

}
