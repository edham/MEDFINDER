/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.med.finder.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.med.finder.conexion.ActualizarPacienteHTTP;
import com.app.med.finder.conexion.InsertPacienteHTTP;
import com.app.med.finder.dao.clsPacienteDAO;
import com.app.med.finder.dao.clsPreguntaPacienteDAO;
import com.app.med.finder.dao.clsUsuarioDAO;

import com.app.med.finder.entidades.clsPaciente;
import com.app.med.finder.utilidades.Funciones;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Toditos
 */
public class FamiliarActivity extends Activity {

    /**
     * Called when the activity is first created.
     */

    private int idPaciente;
    private boolean sexo=true;
    private Spinner ComboSexo;     
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
     
     
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.familiar);
        Bundle bundle=getIntent().getExtras();
        idPaciente=Integer.parseInt(bundle.getString("idPaciente"));
        lblTitulo = (TextView)findViewById(R.id.lblTitulo);
        ComboSexo = (Spinner)findViewById(R.id.ComboSexo);
        txtNombres = (EditText)findViewById(R.id.txtNombres);
        txtApellidoPaterno = (EditText)findViewById(R.id.txtApellidoPaterno);
        txtDNI = (EditText)findViewById(R.id.txtDNI);
        txtApellidoMaterno = (EditText)findViewById(R.id.txtApellidoMaterno);
        txtFNacimiento = (EditText)findViewById(R.id.txtFNacimiento);
        txtEstatura = (EditText)findViewById(R.id.txtEstatura);
         chbCardioVascular = (CheckBox)findViewById(R.id.chbCardioVascular);  
        chbAlcohol = (CheckBox)findViewById(R.id.chbAlcohol);  
        chbMusculares = (CheckBox)findViewById(R.id.chbMusculares);  
        chbTabaquismo = (CheckBox)findViewById(R.id.chbTabaquismo);  
        chbDigestivos = (CheckBox)findViewById(R.id.chbDigestivos);  
        chbDrogas = (CheckBox)findViewById(R.id.chbDrogas);  
        chbAlergicos = (CheckBox)findViewById(R.id.chbAlergicos);  
        chbPsicologicos = (CheckBox)findViewById(R.id.chbPsicologicos);  
         llenarDDL ();
        if(idPaciente>0)
        {
            entidad=clsPacienteDAO.Buscar(this, idPaciente);
            if(entidad!=null)
            {
                lblTitulo.setText("Id Paciente: "+entidad.getInt_id_paciente());
                 SimpleDateFormat  fecha=new SimpleDateFormat("dd/MM/yyyy");
                txtNombres.setText(entidad.getStr_nombres());
                txtApellidoPaterno.setText(entidad.getStr_apellido_paterno());
                txtApellidoMaterno.setText(entidad.getStr_apellido_materno());
                txtFNacimiento.setText(fecha.format(entidad.getDat_fecha_nacimiento()));
                txtEstatura.setText(""+entidad.getInt_estatura());
                txtDNI.setText(entidad.getStr_dni());
                 if(entidad.isBol_sexo())
                    ComboSexo.setSelection(0);
                else
                     ComboSexo.setSelection(1);
                 
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
        // ToDo add your GUI initialization code here        
       
    }
     public void llenarDDL (){

        List<String> lista =new ArrayList<String>();
        lista.add("Hombre");
        lista.add("Mujer");
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lista);       
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
     

    
  
     
     public void btnCancelar(View v)
     {
         Intent i=new Intent(this,MisFamiliaresActivity.class);
                                    startActivity(i);
     }
     public void btnAceptar(View v)
     {
          if(!txtNombres.getText().toString().equals("") &&  txtNombres.getText().toString()!=null )
          {
                if(!txtApellidoPaterno.getText().toString().equals("") &&  txtApellidoPaterno.getText().toString()!=null )
                   {
                       if(!txtApellidoMaterno.getText().toString().equals("") &&  txtApellidoMaterno.getText().toString()!=null )
                        {
                            if(Funciones.isDate(txtFNacimiento.getText().toString()))
                            {
                                if(!txtDNI.getText().toString().equals("") &&  txtDNI.getText().toString()!=null  )
                                 {  
                                    if(!txtEstatura.getText().toString().equals("") &&  txtEstatura.getText().toString()!=null  )
                                    {  
                                    
                                            entidad.setStr_nombres(txtNombres.getText().toString());
                                            entidad.setStr_apellido_paterno(txtApellidoPaterno.getText().toString());
                                            entidad.setStr_apellido_materno(txtApellidoMaterno.getText().toString());//                               
                                            entidad.setBol_sexo(sexo);
                                            entidad.setDat_fecha_nacimiento(Funciones.getDate(txtFNacimiento.getText().toString()));
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
                                                 String id="0";
                                                ActualizarPacienteHTTP http = new ActualizarPacienteHTTP();
                                                http.execute(entidad);
                                                try {
                                                    id=http.get();
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                } catch (ExecutionException e) {
                                                    e.printStackTrace();
                                                }
                                                if(!id.trim().equals("0"))
                                                {
                                                  clsPacienteDAO.Actualizar(this, entidad);
                                                  Toast.makeText(this,"El Familiar se Registro Correctamente", Toast.LENGTH_SHORT).show();
                                                  Intent i=new Intent(this,MisFamiliaresActivity.class);
                                                  startActivity(i);
                                                }       
                                                else
                                                    Toast.makeText(this,"Error al Insertar intentelo mas tarde", Toast.LENGTH_SHORT).show();  
                                            }
                                            else
                                            {         
                                                entidad.setObjUsuario(clsUsuarioDAO.Buscar(this));
                                                String id= "0";
                                                InsertPacienteHTTP http = new InsertPacienteHTTP();
                                                http.execute(entidad);
                                                try {
                                                    id=http.get();
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                } catch (ExecutionException e) {
                                                    e.printStackTrace();
                                                }
                                                if(!id.trim().equals("0"))
                                                {
                                                      String [] ids = id.split("\\<+parametro+>");   
                                                      entidad.setInt_id_paciente(Integer.parseInt(ids[0].trim()));
                                                      entidad.setInt_id_persona(Integer.parseInt(ids[1].trim()));
                                                      
                                                  clsPacienteDAO.Agregar(this, entidad);
                                                  Toast.makeText(this,"El Familiar se Registro Correctamente", Toast.LENGTH_SHORT).show();
                                                  Intent i=new Intent(this,MisFamiliaresActivity.class);
                                                  startActivity(i);
                                                }       
                                                else
                                                    Toast.makeText(this,"Error al Insertar intentelo mas tarde", Toast.LENGTH_SHORT).show();   
                                            }
                                     }
                                    else
                                    Toast.makeText(this,"Ingrese estatura en centimetros.", Toast.LENGTH_SHORT).show();
                                 }
                                else
                                    Toast.makeText(this,"Ingrese Documento de Indentidad", Toast.LENGTH_SHORT).show();
                            }
                            else
                                 Toast.makeText(this,"Ingrese una fecha en formato dd/mm/yyyy", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(this,"Ingrese Apellido Materno", Toast.LENGTH_SHORT).show();
                   }
                  else
                  Toast.makeText(this,"Ingrese Apellido Paterno", Toast.LENGTH_SHORT).show();
          }
          else
              Toast.makeText(this,"Ingrese Nombres", Toast.LENGTH_SHORT).show(); 

     }
     
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {    
        return false;
    }
    
     public void btnHome(View v)
    {
        Intent i=new Intent(this,MenuActivity.class);
         startActivity(i); 
    } 
    
     public void btnCosnulta(View v)
    {
        if(clsPreguntaPacienteDAO.BuscarXEstado(this)==null)
       {
         Intent i=new Intent(this,RealizarConsultaActivity.class);
         startActivity(i); 
       }else
            Toast.makeText(this,"Tiene una Consulta Activa", Toast.LENGTH_SHORT).show();
    } 
     
    public void btnRespuestas(View v)
    {
       Intent i=new Intent(this,ConsultasActivity.class);
         startActivity(i); 
    } 
    
}
