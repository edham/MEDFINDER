package com.med.finder.doctor.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.med.finder.doctor.R;
import com.med.finder.doctor.activity.MainActivity;
import com.med.finder.doctor.conexion.http;
import com.med.finder.doctor.dao.clsPacienteDAO;
import com.med.finder.doctor.dao.clsUsuarioDAO;
import com.med.finder.doctor.entidades.clsPaciente;
import com.med.finder.doctor.utilidades.CustomFontTextView;
import com.med.finder.doctor.utilidades.Utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FamiliarFragment extends Fragment {

    private int idPaciente;
    private boolean sexo=true;
    //private Spinner ComboSexo;
    private EditText txtNombres;
    private EditText txtApellidoPaterno;
    private EditText txtApellidoMaterno;
    private EditText txtFNacimiento;
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
                SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");
                txtNombres.setText(entidad.getStr_nombres());
                txtApellidoPaterno.setText(entidad.getStr_apellido_paterno());
                txtApellidoMaterno.setText(entidad.getStr_apellido_materno());
                txtFNacimiento.setText(fecha.format(entidad.getDat_fecha_nacimiento()));
                txtEstatura.setText(""+entidad.getInt_estatura());
                txtDNI.setText(entidad.getStr_dni());
                if(entidad.isBol_sexo()) {
                    chbHombre.setSelected(true);
                    chbMujer.setSelected(false);
                }
                else
                {
                    chbHombre.setSelected(false);
                    chbMujer.setSelected(true);
                }

                chbCardioVascular.setChecked(entidad.isBol_cardiovasculares());
                chbAlcohol.setChecked(entidad.isBol_alcohol());
                chbMusculares.setChecked(entidad.isBol_musculares());
                chbTabaquismo.setChecked(entidad.isBol_tabaquismo());
                chbDigestivos.setChecked(entidad.isBol_digestivos());
                chbDrogas.setChecked(entidad.isBol_drogas());
                chbAlergicos.setChecked(entidad.isBol_alergicos());
                chbPsicologicos.setChecked(entidad.isBol_psicologicos());
            }
        }
        else
            entidad = new clsPaciente();
        return view;
    }

/*

    public void llenarDDL (){

        List<String> lista =new ArrayList<String>();
        lista.add("Hombre");
        lista.add("Mujer");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item,lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ComboSexo.setAdapter(adapter);
        ComboSexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                    sexo=true;
                else
                    sexo=false;
            }
            public void onNothingSelected(AdapterView<?> parent) {
                //User selected same item. Nothing to do.
            }
        });
        ComboSexo.setSelection(0);
    }

*/
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
                    if(Utilidades.isDate(txtFNacimiento.getText().toString()))
                    {
                        if(!txtDNI.getText().toString().equals("") &&  txtDNI.getText().toString()!=null  )
                        {
                            if(!txtEstatura.getText().toString().equals("") &&  txtEstatura.getText().toString()!=null  )
                            {

                                entidad.setStr_nombres(txtNombres.getText().toString());
                                entidad.setStr_apellido_paterno(txtApellidoPaterno.getText().toString());
                                entidad.setStr_apellido_materno(txtApellidoMaterno.getText().toString());//
                                entidad.setBol_sexo(sexo);
                                entidad.setDat_fecha_nacimiento(Utilidades.getDate(txtFNacimiento.getText().toString()));
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

                            }
                            else
                                Toast.makeText(this.getContext(),"Ingrese estatura en centimetros.", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(this.getContext(),"Ingrese Documento de Indentidad", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(this.getContext(),"Ingrese una fecha en formato dd/mm/yyyy", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this.getContext(),"Ingrese Apellido Materno", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this.getContext(),"Ingrese Apellido Paterno", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this.getContext(),"Ingrese Nombres", Toast.LENGTH_SHORT).show();

    }



}
