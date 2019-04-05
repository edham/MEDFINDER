package com.med.finder.cliente.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.med.finder.cliente.R;
import com.med.finder.cliente.activity.MainActivity;
import com.med.finder.cliente.conexion.ActualizarPacienteHTTP;
import com.med.finder.cliente.conexion.InsertPacienteHTTP;
import com.med.finder.cliente.conexion.http;
import com.med.finder.cliente.dao.clsPacienteDAO;
import com.med.finder.cliente.dao.clsUsuarioDAO;
import com.med.finder.cliente.entidades.clsPaciente;
import com.med.finder.cliente.utilidades.CustomFontTextView;
import com.med.finder.cliente.utilidades.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FamiliarFragment extends Fragment {

    private int idPaciente;
    private boolean sexo=true;
    private EditText txtNombres;
    private EditText txtApellidoPaterno;
    private EditText txtApellidoMaterno;
    private EditText txtEstatura;
    private EditText txtDNI;

    private clsPaciente entidad;
    private TextView lblTitulo;
    private CheckBox chbCardioVascular;
    private CheckBox chbAlcohol;
    private CheckBox chbMusculares;
    private CheckBox chbTabaquismo;
    private CheckBox chbDigestivos;
    private CheckBox chbDrogas;
    private CheckBox chbAlergicos;
    private CheckBox chbPsicologicos;
    private CheckBox chbHombre;
    private CheckBox chbMujer;
    private CustomFontTextView lblFecNac;
    private Date fecNac;
    public ProgressDialog pd;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_familiar, container, false);

        idPaciente=getArguments().getInt("idPaciente");
        lblTitulo = (TextView)view.findViewById(R.id.lblTitulo);
       // ComboSexo = (Spinner)view.findViewById(R.id.ComboSexo);
        txtNombres = (EditText)view.findViewById(R.id.txtNombres);
        txtApellidoPaterno = (EditText)view.findViewById(R.id.txtApellidoPaterno);

        txtDNI = (EditText)view.findViewById(R.id.txtDNI);
        txtApellidoMaterno = (EditText)view.findViewById(R.id.txtApellidoMaterno);
        txtEstatura = (EditText)view.findViewById(R.id.txtEstatura);
        chbCardioVascular = (CheckBox)view.findViewById(R.id.chbCardioVascular);
        chbAlcohol = (CheckBox)view.findViewById(R.id.chbAlcohol);
        chbMusculares = (CheckBox)view.findViewById(R.id.chbMusculares);
        chbTabaquismo = (CheckBox)view.findViewById(R.id.chbTabaquismo);
        chbDigestivos = (CheckBox)view.findViewById(R.id.chbDigestivos);
        chbDrogas = (CheckBox)view.findViewById(R.id.chbDrogas);
        chbAlergicos = (CheckBox)view.findViewById(R.id.chbAlergicos);
        chbPsicologicos = (CheckBox)view.findViewById(R.id.chbPsicologicos);
        chbHombre = (CheckBox)view.findViewById(R.id.chbHombre);
        chbMujer = (CheckBox)view.findViewById(R.id.chbMujer);
        chbHombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox)view).isChecked();
                if(isChecked)
                {
                    chbMujer.setChecked(false);
                }else
                {
                    chbMujer.setChecked(true);
                }
            }
        });
        chbMujer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isChecked = ((CheckBox)view).isChecked();
                if(isChecked)
                {
                    chbHombre.setChecked(false);
                }else
                {
                    chbHombre.setChecked(true);
                }
            }
        });

        //llenarDDL ();
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

        fecNac= new Date();
        lblFecNac = (CustomFontTextView) view.findViewById(R.id.lblFecNac);
        lblFecNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilidades.setCalendar(FamiliarFragment.this.getContext(),lblFecNac,null,fecNac);
            }
        });

        if(idPaciente>0)
        {
            entidad= clsPacienteDAO.Buscar(this.getContext(), idPaciente);
            if(entidad!=null)
            {
                lblTitulo.setText(getString(R.string.str_editar_familiar));
                txtNombres.setText(entidad.getStr_nombres());
                txtApellidoPaterno.setText(entidad.getStr_apellido_paterno());
                txtApellidoMaterno.setText(entidad.getStr_apellido_materno());
                fecNac=entidad.getDat_fecha_nacimiento();
                if(fecNac!=null){
                    lblFecNac.setText(Utilidades.dateFormatter.format(fecNac));
                }
                txtEstatura.setText(""+entidad.getInt_estatura());
                txtDNI.setText(entidad.getStr_dni());
                if(entidad.isBol_sexo()) {
                    chbHombre.setChecked(true);
                    chbMujer.setChecked(false);
                }
                else
                {
                    chbHombre.setChecked(false);
                    chbMujer.setChecked(true);
                }

                chbCardioVascular.setChecked(entidad.isBol_cardiovasculares());
                chbAlcohol.setChecked(entidad.isBol_alcohol());
                chbMusculares.setChecked(entidad.isBol_musculares());
                chbTabaquismo.setChecked(entidad.isBol_tabaquismo());
                chbDigestivos.setChecked(entidad.isBol_digestivos());
                chbDrogas.setChecked(entidad.isBol_drogas());
                chbAlergicos.setChecked(entidad.isBol_alergicos());
                chbPsicologicos.setChecked(entidad.isBol_psicologicos());

                if(entidad.isBol_tipo())
                {
                    txtDNI.setEnabled(false);
                }
            }
        }
        else
            entidad = new clsPaciente();
        return view;
    }

    public void btnCancelar()
    {
        ((MainActivity) getActivity()).setFragment(new FamiliaresFragment());
    }
    public void btnAceptar()
    {
        if(!txtNombres.getText().toString().equals("") &&  txtNombres.getText().toString()!=null )
        {
            if(!txtApellidoPaterno.getText().toString().equals("") &&  txtApellidoPaterno.getText().toString()!=null )
            {
                if(!txtApellidoMaterno.getText().toString().equals("") &&  txtApellidoMaterno.getText().toString()!=null )
                {
                    if(Utilidades.isDate(lblFecNac.getText().toString()))
                    {
                        if(!txtDNI.getText().toString().equals("") &&  txtDNI.getText().toString()!=null  )
                        {
                            if(!txtEstatura.getText().toString().equals("") &&  txtEstatura.getText().toString()!=null  )
                            {

                                entidad.setStr_nombres(txtNombres.getText().toString());
                                entidad.setStr_apellido_paterno(txtApellidoPaterno.getText().toString());
                                entidad.setStr_apellido_materno(txtApellidoMaterno.getText().toString());//
                                entidad.setBol_sexo(sexo);
                                entidad.setDat_fecha_nacimiento(Utilidades.getDate(lblFecNac.getText().toString()));
                                entidad.setBol_cardiovasculares(chbCardioVascular.isChecked());
                                entidad.setBol_alcohol(chbAlcohol.isChecked());
                                entidad.setBol_musculares(chbMusculares.isChecked());
                                entidad.setBol_tabaquismo(chbTabaquismo.isChecked());
                                entidad.setBol_digestivos(chbDigestivos.isChecked());
                                entidad.setBol_drogas(chbDrogas.isChecked());
                                entidad.setBol_alergicos(chbAlergicos.isChecked());
                                entidad.setBol_psicologicos(chbPsicologicos.isChecked());
                                entidad.setStr_dni(txtDNI.getText().toString());
                                entidad.setBol_tipo(false);
                                entidad.setInt_estado(0);
                                entidad.setInt_estatura(Integer.parseInt(txtEstatura.getText().toString()));
                                enviarData();

                            }
                            else{

                                Utilidades.alert(this.getContext(), getString(R.string.str_ingrese_estatura));
                            }
                        }
                        else{

                            Utilidades.alert(this.getContext(), getString(R.string.str_ingrese_direccion));
                        }
                    }
                    else{

                        Utilidades.alert(this.getContext(), getString(R.string.str_ingrese_fecnac));
                    }
                }
                else{

                    Utilidades.alert(this.getContext(), getString(R.string.str_ingrese_materno));
                }
            }
            else{
                Utilidades.alert(this.getContext(), getString(R.string.str_ingrese_paterno));
            }
        }
        else{

            Utilidades.alert(this.getContext(), getString(R.string.str_ingrese_nombres));
        }

    }


    public void enviarData(){
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
                        String result;
                        if(idPaciente>0)
                        {
                            ActualizarPacienteHTTP http = new ActualizarPacienteHTTP();
                            http.execute(entidad);
                            result = http.get();
                        }else
                        {
                            entidad.setObjUsuario(clsUsuarioDAO.Buscar(FamiliarFragment.this.getContext()));
                            InsertPacienteHTTP http = new InsertPacienteHTTP();
                            http.execute(entidad);
                            result = http.get();

                        }

                        if (!result.equals("")) {
                            JSONObject entidadJSON = new JSONObject(result);
                            if (entidadJSON.getInt("rpta") == 1) {
                                rpta=1;
                                if(idPaciente==0)
                                {
                                    entidad.setInt_id_persona(entidadJSON.getInt("personaId"));
                                    entidad.setInt_id_paciente(entidadJSON.getInt("pacienteId"));
                                }
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

        /*
        if(idPaciente>0)
                                {
                                    String id= http.actualizarPaciente(entidad);
                                    if(!id.trim().equals("0"))
                                    {

                                        clsPacienteDAO.Actualizar(this.getContext(), entidad);
                                        Toast.makeText(this.getContext(),"El Familiar se Registro Correctamente", Toast.LENGTH_SHORT).show();
                                        ((MainActivity) getActivity()).setFragment(new FamiliaresFragment());

                                    }
                                    else
                                        Toast.makeText(this.getContext(),"Error al Insertar intentelo mas tarde", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    entidad.setObjUsuario(clsUsuarioDAO.Buscar(this.getContext()));
                                    String id= http.insertarPaciente(entidad);
                                    if(!id.trim().equals("0"))
                                    {
                                        String [] ids = id.split("\\<+parametro+>");
                                        entidad.setInt_id_paciente(Integer.parseInt(ids[0].trim()));
                                        entidad.setInt_id_persona(Integer.parseInt(ids[1].trim()));

                                        clsPacienteDAO.Agregar(this.getContext(), entidad);
                                        Toast.makeText(this.getContext(),"El Familiar se Registro Correctamente", Toast.LENGTH_SHORT).show();
                                        ((MainActivity) getActivity()).setFragment(new FamiliaresFragment());

                                    }
                                    else
                                        Toast.makeText(this.getContext(),"Error al Insertar intentelo mas tarde", Toast.LENGTH_SHORT).show();
                                }
         */
    }

    final Handler handlerCargar=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();
            Bundle bundle = msg.getData();
            if(bundle.getInt("rpta")==1)
            {
                if(idPaciente>0){
                    clsPacienteDAO.Actualizar(FamiliarFragment.this.getContext(), entidad);
                }else{
                    clsPacienteDAO.Agregar(FamiliarFragment.this.getContext(), entidad);
                }
                Utilidades.alert(FamiliarFragment.this.getContext(), getString(R.string. str_registro_correcto));
                ((MainActivity) getActivity()).setFragment(new FamiliaresFragment());
            }else
            {
                Utilidades.alert(FamiliarFragment.this.getContext(), getString(R.string. str_error_registrar));

            }

        }
    };



}
