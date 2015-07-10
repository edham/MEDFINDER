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
import android.widget.Toast;
import com.app.med.finder.conexion.http;
import com.app.med.finder.entidades.clsPaciente;
import com.app.med.finder.entidades.clsUsuario;
import java.util.ArrayList;
import java.util.List;
import com.app.med.finder.utilidades.Funciones;

/**
 *
 * @author ROSEMARY
 */
public class RegistroUsuarioActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    private boolean sexo=true;
    private Spinner ComboSexo;    
    
    private EditText txtNombres;
    private EditText txtApellidoPaterno;
    private EditText txtApellidoMaterno;
    private EditText txtFNacimiento;
    private EditText txtDNI;
    private EditText txtTelefono;
    private EditText txtEmail;
    private EditText txtDireccion;
    private EditText txtUsuario;
    private EditText txtClave;
    private EditText txtRClave;
    private EditText txtEstatura;
    
    private CheckBox chbAcepto;    
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
        // ToDo add your GUI initialization code here    
         setContentView(R.layout.registro_paciente);
         ComboSexo = (Spinner)findViewById(R.id.ComboSexo);
        txtNombres = (EditText)findViewById(R.id.txtNombres);
        txtApellidoPaterno = (EditText)findViewById(R.id.txtApellidoPaterno);
        txtApellidoMaterno = (EditText)findViewById(R.id.txtApellidoMaterno);
        txtFNacimiento = (EditText)findViewById(R.id.txtFNacimiento);
        txtDNI = (EditText)findViewById(R.id.txtDNI);
        txtTelefono = (EditText)findViewById(R.id.txtTelefono);                
        txtEmail = (EditText)findViewById(R.id.txtEmail);                
        txtDireccion = (EditText)findViewById(R.id.txtDireccion);
        txtUsuario = (EditText)findViewById(R.id.txtUsuario); 
        txtClave = (EditText)findViewById(R.id.txtClave);
        txtRClave = (EditText)findViewById(R.id.txtRClave);
        txtEstatura = (EditText)findViewById(R.id.txtEstatura);
        chbAcepto = (CheckBox)findViewById(R.id.chbAcepto);  
        
        chbCardioVascular = (CheckBox)findViewById(R.id.chbCardioVascular);  
        chbAlcohol = (CheckBox)findViewById(R.id.chbAlcohol);  
        chbMusculares = (CheckBox)findViewById(R.id.chbMusculares);  
        chbTabaquismo = (CheckBox)findViewById(R.id.chbTabaquismo);  
        chbDigestivos = (CheckBox)findViewById(R.id.chbDigestivos);  
        chbDrogas = (CheckBox)findViewById(R.id.chbDrogas);  
        chbAlergicos = (CheckBox)findViewById(R.id.chbAlergicos);  
        chbPsicologicos = (CheckBox)findViewById(R.id.chbPsicologicos);  
         llenarDDL ();
     
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
         Intent i=new Intent(this,LoginActivity.class);
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
                                 if(!txtEstatura.getText().toString().equals("") &&  txtEstatura.getText().toString()!=null  )
                                 {                                
                                         if(Funciones.isDni(txtDNI.getText().toString()))
                                        {
                                            if(Funciones.isTelefono(txtTelefono.getText().toString()))
                                            {
                                                if(Funciones.isValidEmail(txtEmail.getText().toString()))
                                                {
                                                     if(!txtDireccion.getText().toString().equals("") && !txtDireccion.getText().toString().equals(null))
                                                    {
                                                        if(!txtUsuario.getText().toString().equals("") && !txtUsuario.getText().toString().equals(null))
                                                        {
                                                            if(!txtClave.getText().toString().equals("") && !txtClave.getText().toString().equals(null))
                                                            {
                                                                if(txtRClave.getText().toString().equals(txtClave.getText().toString()))
                                                                {    
                                                                    if(chbAcepto.isChecked())
                                                                    {
                                                                        clsUsuario usuario=new clsUsuario();
                                                                        usuario.setStr_dni(txtDNI.getText().toString());   
                                                                        usuario.setStr_telefono(txtTelefono.getText().toString());
                                                                        usuario.setStr_email(txtEmail.getText().toString());
                                                                        usuario.setStr_direccion(txtDireccion.getText().toString());
                                                                        usuario.setStr_usuario(txtUsuario.getText().toString());
                                                                        usuario.setStr_clave(txtClave.getText().toString());
                                                                        usuario.setStr_nombres(txtNombres.getText().toString());
                                                                        usuario.setStr_apellido_paterno(txtApellidoPaterno.getText().toString());
                                                                        usuario.setStr_apellido_materno(txtApellidoMaterno.getText().toString());
                                                                        usuario.setBol_sexo(sexo);
                                                                         
                                                                        clsPaciente entidad =new clsPaciente();
                                                                        entidad.setStr_nombres(txtNombres.getText().toString());
                                                                        entidad.setStr_apellido_paterno(txtApellidoPaterno.getText().toString());
                                                                        entidad.setStr_apellido_materno(txtApellidoMaterno.getText().toString());//                                                                                           
                                                                        entidad.setBol_sexo(sexo);
                                                                        entidad.setStr_dni(txtDNI.getText().toString());       
                                                                        entidad.setDat_fecha_nacimiento(Funciones.getDate(txtFNacimiento.getText().toString()));        //                                                               
                                                                        entidad.setBol_tipo(true);
                                                                        entidad.setBol_cardiovasculares(chbCardioVascular.isChecked());
                                                                        entidad.setBol_alcohol(chbAlcohol.isChecked());                                                        
                                                                        entidad.setBol_musculares(chbMusculares.isChecked());
                                                                        entidad.setBol_tabaquismo(chbTabaquismo.isChecked());
                                                                        entidad.setBol_digestivos(chbDigestivos.isChecked());
                                                                        entidad.setBol_drogas(chbDrogas.isChecked());
                                                                        entidad.setBol_alergicos(chbAlergicos.isChecked());
                                                                        entidad.setBol_psicologicos(chbPsicologicos.isChecked());
                                                                        entidad.setInt_estado(0);
                                                                        entidad.setInt_estatura(Integer.parseInt(txtEstatura.getText().toString()));
                                                                        entidad.setObjUsuario(usuario);


                                                                        String data= http.insertarUsuario(entidad);
                                                                         if(!data.trim().equals("0"))
                                                                         {

                                                                            Toast.makeText(this,"El Usuario se Registro Correctamente", Toast.LENGTH_SHORT).show();
                                                                            Intent i=new Intent(this,LoginActivity.class);
                                                                            startActivity(i);
                                                                         }       
                                                                         else
                                                                             Toast.makeText(this,"Error al Insertar intentelo mas tarde", Toast.LENGTH_SHORT).show();
                                                                             }
                                                                    else
                                                                        Toast.makeText(this,"Tiene que aceptar Terminos y Condiciones", Toast.LENGTH_SHORT).show(); 
                                                            }
                                                                else
                                                                    Toast.makeText(this,"La Clave no coincide", Toast.LENGTH_SHORT).show(); 
                                                            }
                                                            else
                                                                Toast.makeText(this,"Ingrese una Clave", Toast.LENGTH_SHORT).show(); 
                                                        }
                                                        else
                                                            Toast.makeText(this,"Ingrese un Usuario", Toast.LENGTH_SHORT).show();  
                                                    }
                                                    else
                                                        Toast.makeText(this,"Ingrese una dirección", Toast.LENGTH_SHORT).show(); 
                                                }
                                                else
                                                    Toast.makeText(this,"Ingrese un Telefono de 9 Digitos", Toast.LENGTH_SHORT).show();           
                                            }
                                            else
                                                Toast.makeText(this,"Ingrese un Telefono de 9 Digitos", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                            Toast.makeText(this,"Ingrese un DNI valido", Toast.LENGTH_SHORT).show();
                                    }
                                else
                                    Toast.makeText(this,"Ingrese estatura en centimetros.", Toast.LENGTH_SHORT).show();
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
}
