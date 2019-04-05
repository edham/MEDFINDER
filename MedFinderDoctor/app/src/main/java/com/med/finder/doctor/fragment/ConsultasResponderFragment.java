package com.med.finder.doctor.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.med.finder.doctor.R;
import com.med.finder.doctor.activity.MainActivity;
import com.med.finder.doctor.conexion.ResponderPreguntaPacienteHTTP;
import com.med.finder.doctor.dao.clsDoctorDAO;
import com.med.finder.doctor.dao.clsPreguntaPacienteDAO;
import com.med.finder.doctor.dao.clsRespuestaPreguntaPacienteDAO;
import com.med.finder.doctor.entidades.clsPreguntaPaciente;
import com.med.finder.doctor.entidades.clsRespuestaPreguntaPaciente;
import com.med.finder.doctor.panel.PacientePanel;
import com.med.finder.doctor.utilidades.CustomFontTextView;
import com.med.finder.doctor.utilidades.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.concurrent.ExecutionException;


public class ConsultasResponderFragment extends Fragment {

    private clsRespuestaPreguntaPaciente respuestaPreguntaPaciente;
    private clsPreguntaPaciente entidad;
    private ImageView imgFoto;
    private View viewImgFoto;
    private Bitmap bitmap;
    private ProgressDialog pd;
    private EditText txtRespuesta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_consultas_responder, container, false);
        imgFoto = (ImageView) view.findViewById(R.id.imgFoto);
        imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgFoto();
            }
        });
        viewImgFoto = (View) view.findViewById(R.id.viewImgFoto);
        txtRespuesta = (EditText) view.findViewById(R.id.txtRespuesta);
        txtRespuesta.setText("");
        Button btnCancelar = (Button) view.findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCancelar();
            }
        });
        Button btnAceptar = (Button) view.findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAceptar();
            }
        });


        CustomFontTextView lblAsunto = (CustomFontTextView) view.findViewById(R.id.lblAsunto);
        CustomFontTextView lblSintomas = (CustomFontTextView) view.findViewById(R.id.lblSintomas);


        int id=getArguments().getInt("id");
        entidad= clsPreguntaPacienteDAO.Buscar(this.getContext(),id);
        if(entidad!=null)
        {
            if(entidad.getByte_imagen()!=null)
            {
                bitmap=Utilidades.getBitmap(entidad.getByte_imagen());
                if(bitmap!=null) {
                    imgFoto.setImageBitmap(bitmap);
                }
                else
                {
                    viewImgFoto.setVisibility(View.GONE);
                }
            }else
            {
                viewImgFoto.setVisibility(View.GONE);
            }
            lblAsunto.setText(entidad.getStr_asunto());
            lblSintomas.setText(entidad.getStr_paciente_detalle());
            Bundle args = new Bundle();
            args.putInt("idPaciente", entidad.getObjPaciente().getInt_id_paciente());
            Fragment fragment = new PacientePanel();
            fragment.setArguments(args);
            setFragment(fragment);
        }
        ((MainActivity) getActivity()).setTitle(getString(R.string.str_responder_consulta));
        return view;
    }

    public void setFragment( Fragment fragment)
    {
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction() .replace(R.id.fragment_paciente, fragment).commit();
    }
    public void btnCancelar()
    {
        ((MainActivity) getActivity()).setFragment(new InicioFragment());
    }

    public void imgFoto()
    {
        final Dialog dialog = new Dialog(this.getActivity());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_foto);
        final PhotoView imageView =dialog.findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);


        Button btnCerrar = (Button) dialog.findViewById(R.id.btnCerrar);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void btnAceptar(){
        if(txtRespuesta.getText().toString().length()>0){
            if ( Utilidades.checkPermissions(this.getActivity())) {
                respuestaPreguntaPaciente = new clsRespuestaPreguntaPaciente();
                respuestaPreguntaPaciente.setInt_id_pregunta_paciente(entidad.getInt_id_pregunta_paciente());
                respuestaPreguntaPaciente.setDat_fecha(new Date());
                respuestaPreguntaPaciente.setObjDoctor(clsDoctorDAO.Buscar(this.getContext()));
                respuestaPreguntaPaciente.setInt_puntaje(0);
                respuestaPreguntaPaciente.setStr_detalle(txtRespuesta.getText().toString());
                pd = new ProgressDialog(this.getActivity());
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

                            ResponderPreguntaPacienteHTTP http = new ResponderPreguntaPacienteHTTP();

                            http.execute(respuestaPreguntaPaciente);
                            String result = http.get();
                            if (!result.equals("")) {
                                JSONObject entidadJSON = new JSONObject(result);
                                if (entidadJSON.getInt("rpta") == 1) {
                                    respuestaPreguntaPaciente.setInt_id_pregunta_paciente(entidadJSON.getInt("respuestaPreguntaPacienteId"));
                                }
                                rpta=entidadJSON.getInt("rpta");
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
        }else
        {
            Utilidades.alert(this.getActivity(), getString(R.string.str_ingrese_respuesta_consulta));

        }
    }

    final Handler handlerCargar=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();
            Bundle bundle = msg.getData();
            if(bundle.getInt("rpta")==1) {
                Utilidades.alert(ConsultasResponderFragment.this.getActivity(), getString(R.string.str_registro_correcto));
                entidad.setInt_estado(2);
                clsPreguntaPacienteDAO.Actualizar(ConsultasResponderFragment.this.getActivity(), entidad);
                clsRespuestaPreguntaPacienteDAO.Agregar(ConsultasResponderFragment.this.getActivity(), respuestaPreguntaPaciente);
                ((MainActivity) getActivity()).setFragment(new InicioFragment());
            }else
            {
                Utilidades.alert(ConsultasResponderFragment.this.getActivity(), getString(R.string. str_error_registrar));

            }
        }
    };
}
