/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.med.finder.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.med.finder.conexion.InsertarPreguntaHTTP;
import com.app.med.finder.dao.clsEspecialidadDAO;
import com.app.med.finder.dao.clsPacienteDAO;
import com.app.med.finder.dao.clsPreguntaPacienteDAO;
import com.app.med.finder.entidades.clsEspecialidad;
import com.app.med.finder.entidades.clsPaciente;
import com.app.med.finder.entidades.clsPreguntaPaciente;
import com.app.med.finder.utilidades.Funciones;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author ROSEMARY
 */
public class RealizarConsultaActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    private EditText txtSintomas;
     private EditText txtAsunto;
     private Spinner ComboPaciente;     
     private Spinner ComboEspecialidad;     
     private  ImageView image;
     private clsEspecialidad objEspecialidad;
     private clsPaciente objPaciente;
     private Bitmap bp;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here       
         setContentView(R.layout.realizar_consulta);
          ComboPaciente = (Spinner)findViewById(R.id.ComboPaciente);
          ComboEspecialidad = (Spinner)findViewById(R.id.ComboEspecialidad);
         txtSintomas = (EditText)findViewById(R.id.txtSintomas);
         txtAsunto = (EditText)findViewById(R.id.txtAsunto);
         image = (ImageView)findViewById(R.id.image);
         getUsuarioDDL();
         getEspecialidadDDL();
    }
    
      public void getUsuarioDDL(){

        final List<clsPaciente> lista =clsPacienteDAO.Listar(this);
        ArrayAdapter<clsPaciente> adapter = new ArrayAdapter<clsPaciente>(this,android.R.layout.simple_spinner_item,lista);       
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            ComboPaciente.setAdapter(adapter);     
             ComboPaciente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {          
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            objPaciente=lista.get(position);
            }
            public void onNothingSelected(AdapterView<?> parent) {
                //User selected same item. Nothing to do.
            }
        });
             ComboPaciente.setSelection(0);
     }  
      
     public void getEspecialidadDDL(){

        final List<clsEspecialidad> lista = clsEspecialidadDAO.Listar(this);
        
        ArrayAdapter<clsEspecialidad> adapter = new ArrayAdapter<clsEspecialidad>(this,android.R.layout.simple_spinner_item,lista);       
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            ComboEspecialidad.setAdapter(adapter);     
             ComboEspecialidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {          
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                objEspecialidad=lista.get(position);
            }
            public void onNothingSelected(AdapterView<?> parent) {
                //User selected same item. Nothing to do.
            }
        });
             ComboEspecialidad.setSelection(0);
     }    
    public void btnAceptar(View v)
    {
            if(!txtSintomas.getText().toString().equals("") &&  txtSintomas.getText().toString()!=null )
            {
                if(!txtAsunto.getText().toString().equals("") &&  txtAsunto.getText().toString()!=null )
                {
                    clsPreguntaPaciente entidad = new clsPreguntaPaciente();
                    entidad.setObjPaciente(objPaciente);
                    entidad.setObjEspecialidad(objEspecialidad);
                    entidad.setStr_asunto(txtAsunto.getText().toString());
                    entidad.setStr_paciente_detalle(txtSintomas.getText().toString());
                    if(bp!=null)
                    entidad.setByte_imagen(Funciones.getByte(bp));
                    
                    String cadena="0";
                    InsertarPreguntaHTTP http = new InsertarPreguntaHTTP();
                    http.execute(entidad);
                    try {
                        cadena=http.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                     if(!cadena.trim().equals("0"))
                     {
                        entidad.setInt_id_pregunta_paciente(Integer.parseInt(cadena));
                        clsPreguntaPacienteDAO.Agregar(this, entidad);                    
                        Toast.makeText(this,"Su consulta se envio Satisfactoriamente", Toast.LENGTH_SHORT).show(); 
                        Intent i=new Intent(this,MenuActivity.class);
                        startActivity(i); 
                     }
                }
                else
                    Toast.makeText(this,"Ingrese Sintomas", Toast.LENGTH_SHORT).show(); 
            }
            else
                Toast.makeText(this,"Ingrese Informacion Adicional", Toast.LENGTH_SHORT).show(); 
    }
    public void btnCancelar(View v)
    {
         Intent i=new Intent(this,MenuActivity.class);
         startActivity(i); 
    }
    
    public void btnTomarFoto(View v)
    {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
      startActivityForResult(intent, 0);
    }
    
     @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      // TODO Auto-generated method stub
      super.onActivityResult(requestCode, resultCode, data);
      bp = (Bitmap) data.getExtras().get("data");
      image.setImageBitmap(bp);
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
