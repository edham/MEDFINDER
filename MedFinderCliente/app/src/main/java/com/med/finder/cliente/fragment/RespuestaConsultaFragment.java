package com.med.finder.cliente.fragment;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.med.finder.cliente.R;
import com.med.finder.cliente.conexion.InsertarVotoRespuestaConsultaHTTP;
import com.med.finder.cliente.dao.clsDoctorDAO;
import com.med.finder.cliente.dao.clsEspecialidadDAO;
import com.med.finder.cliente.dao.clsRespuestaPreguntaPacienteDAO;
import com.med.finder.cliente.entidades.clsDoctor;
import com.med.finder.cliente.entidades.clsPreguntaPaciente;
import com.med.finder.cliente.entidades.clsRespuestaPreguntaPaciente;
import com.med.finder.cliente.utilidades.CustomFontTextView;
import com.med.finder.cliente.utilidades.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class RespuestaConsultaFragment extends Fragment {


    private List<clsRespuestaPreguntaPaciente> listCasos;
    private Adaptador adaptador;
    private ListView list;
    private clsPreguntaPaciente entidad;
    public ProgressDialog pd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_respuesta_consulta, container, false);
        entidad=(clsPreguntaPaciente) getArguments().getSerializable("entidad");
        list = (ListView)view.findViewById(R.id.list);

        CustomFontTextView lblAsunto = (CustomFontTextView)view.findViewById(R.id.lblAsunto);
        lblAsunto.setText(entidad.getStr_asunto());

        CustomFontTextView lblDetalle = (CustomFontTextView)view.findViewById(R.id.lblDetalle);
        lblDetalle.setText(entidad.getStr_paciente_detalle());


        CustomFontTextView lblEspecialidad = (CustomFontTextView)view.findViewById(R.id.lblEspecialidad);
        lblEspecialidad.setText(clsEspecialidadDAO.Buscar(this.getContext(), entidad.getObjEspecialidad().getInt_id_especialidad()).getStr_nombre());


        CustomFontTextView lblFecha = (CustomFontTextView)view.findViewById(R.id.lblFecha);
        lblFecha.setText(Utilidades.dateFormatter.format(entidad.getDat_inicio()));

        CustomFontTextView lblHora = (CustomFontTextView)view.findViewById(R.id.lblHora);
        lblHora.setText(Utilidades.hora.format(entidad.getDat_inicio()));

        CustomFontTextView lblEstado = (CustomFontTextView)view.findViewById(R.id.lblEstado);
        lblEstado.setText((entidad.getInt_estado()==2)?getString(R.string.str_finalizada):getString(R.string.str_pendiente));

        CustomFontTextView lblRespuestas = (CustomFontTextView)view.findViewById(R.id.lblRespuestas);
        lblRespuestas.setText(""+entidad.getInt_respuestas());
        buscar();


        return view;
    }



    public void buscar()
    {
        listCasos= clsRespuestaPreguntaPacienteDAO.Listar(this.getActivity(), entidad.getInt_id_pregunta_paciente());
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

    public void getDialogo(final clsRespuestaPreguntaPaciente respuestaPreguntaPaciente)
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
                    respuestaPreguntaPaciente.setInt_puntaje((int)rtbPuntos.getRating());
                    setCalificar(respuestaPreguntaPaciente);
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




    public void setCalificar(final clsRespuestaPreguntaPaciente respuestaPreguntaPaciente)
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
                        InsertarVotoRespuestaConsultaHTTP http = new InsertarVotoRespuestaConsultaHTTP();
                        http.execute(respuestaPreguntaPaciente);
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
                     bundle.putInt("id",respuestaPreguntaPaciente.getInt_id_respuesta_pregunta_paciente());
                    bundle.putInt("puntaje",respuestaPreguntaPaciente.getInt_puntaje());
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
                Utilidades.alert(RespuestaConsultaFragment.this.getContext(), getString(R.string. str_registro_correcto));
                clsRespuestaPreguntaPacienteDAO.Puntaje(RespuestaConsultaFragment.this.getActivity(),bundle.getInt("id"), bundle.getInt("puntaje"));
                buscar();
            }else
            {
                Utilidades.alert(RespuestaConsultaFragment.this.getContext(), getString(R.string. str_error_registrar));

            }

        }
    };

}
