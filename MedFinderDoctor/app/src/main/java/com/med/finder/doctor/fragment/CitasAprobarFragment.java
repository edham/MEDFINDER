package com.med.finder.doctor.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.med.finder.doctor.R;
import com.med.finder.doctor.activity.MainActivity;
import com.med.finder.doctor.conexion.CitaAprobarPacienteHTTP;
import com.med.finder.doctor.conexion.ResponderPreguntaPacienteHTTP;
import com.med.finder.doctor.dao.clsCitaPacienteDAO;
import com.med.finder.doctor.dao.clsDoctorDAO;
import com.med.finder.doctor.dao.clsPreguntaPacienteDAO;
import com.med.finder.doctor.dao.clsRespuestaPreguntaPacienteDAO;
import com.med.finder.doctor.entidades.clsCitaPaciente;
import com.med.finder.doctor.entidades.clsRespuestaPreguntaPaciente;
import com.med.finder.doctor.panel.PacientePanel;
import com.med.finder.doctor.utilidades.CustomFontTextView;
import com.med.finder.doctor.utilidades.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class CitasAprobarFragment extends Fragment {

    private CustomFontTextView lblHora;
    private CustomFontTextView lblFecha;
    private EditText txtComentario;
    private clsCitaPaciente entidad;
    private CheckBox chbAprobar;
    private CheckBox chbCancelar;
    private View viewFechaHora;
    private ProgressDialog pd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_citas_aprobar, container, false);
        txtComentario = (EditText) view.findViewById(R.id.txtComentario);
        txtComentario.setText("");
        viewFechaHora = (View) view.findViewById(R.id.viewFechaHora);
        viewFechaHora.setVisibility(View.GONE);
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
        lblHora = (CustomFontTextView) view.findViewById(R.id.lblHora);
        lblHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilidades.setTimer(CitasAprobarFragment.this.getContext(),lblHora);
            }
        });
        lblFecha = (CustomFontTextView) view.findViewById(R.id.lblFecha);
        lblFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilidades.setCalendar(CitasAprobarFragment.this.getContext(),lblFecha,new Date(),null);
            }
        });

        chbAprobar = (CheckBox)view.findViewById(R.id.chbAprobar);
        chbCancelar = (CheckBox)view.findViewById(R.id.chbCancelar);
        chbAprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox)view).isChecked();
                if(isChecked)
                {
                    chbCancelar.setChecked(false);
                    viewFechaHora.setVisibility(View.VISIBLE);
                }else
                {
                    chbCancelar.setChecked(true);
                    viewFechaHora.setVisibility(View.GONE);
                }
            }
        });
        chbCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox)view).isChecked();
                if(isChecked)
                {
                    viewFechaHora.setVisibility(View.GONE);
                    chbAprobar.setChecked(false);
                }else
                {
                    viewFechaHora.setVisibility(View.VISIBLE);
                    chbAprobar.setChecked(true);
                }
            }
        });

        CustomFontTextView lblDetalle = (CustomFontTextView) view.findViewById(R.id.lblDetalle);
        int id=getArguments().getInt("id");
        entidad= clsCitaPacienteDAO.Buscar(this.getContext(),id);
        if(entidad!=null)
        {
            lblDetalle.setText(entidad.getStr_detalle());
            Bundle args = new Bundle();
            args.putInt("idPaciente", entidad.getObjPaciente().getInt_id_paciente());
            Fragment fragment = new PacientePanel();
            fragment.setArguments(args);
            setFragment(fragment);
        }
        ((MainActivity) getActivity()).setTitle(getString(R.string.str_aprobar_cita));
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

    public void btnAceptar(){
        entidad.setStr_respuesta(txtComentario.getText().toString());
        if(chbAprobar.isChecked() || chbCancelar.isChecked())
        {
            entidad.setInt_estado((chbAprobar.isChecked())?1:3);
        }
        else
        {
            Utilidades.alert(this.getActivity(), getString(R.string.str_seleccione_opcion));
            return;
        }
        if(chbAprobar.isChecked() && lblFecha.getText().toString().length()==0)
        {
            Utilidades.alert(this.getActivity(), getString(R.string.str_seleccione_fecha));
            return;
        }
        if(chbAprobar.isChecked() && lblHora.getText().toString().length()==0 )
        {
            Utilidades.alert(this.getActivity(), getString(R.string.str_seleccione_hora));
            return;
        }
        if(chbCancelar.isChecked() || txtComentario.getText().toString().length()==0) {

            Utilidades.alert(this.getActivity(), getString(R.string.str_ingrese_comentario));
            return;
        }
        String fecha=lblFecha.getText().toString().trim()+" "+lblHora.getText().toString().trim();
        entidad.setDat_atencion(Utilidades.getDateHour(fecha));
            if ( Utilidades.checkPermissions(this.getActivity())) {
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

                            CitaAprobarPacienteHTTP http = new CitaAprobarPacienteHTTP();

                            http.execute(entidad);
                            String result = http.get();
                            if (!result.equals("")) {
                                JSONObject entidadJSON = new JSONObject(result);
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
    }

    final Handler handlerCargar=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();
            Bundle bundle = msg.getData();
            if(bundle.getInt("rpta")==1) {
               Utilidades.alert(CitasAprobarFragment.this.getActivity(), getString(R.string.str_registro_correcto));
               clsCitaPacienteDAO.Actualizar(CitasAprobarFragment.this.getActivity(), entidad);
                ((MainActivity) getActivity()).setFragment(new InicioFragment());
            }else
            {
               Utilidades.alert(CitasAprobarFragment.this.getActivity(), getString(R.string. str_error_registrar));

            }
        }
    };
}
