package com.app.med.finder.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.app.med.finder.dao.clsCasosSaludDAO;
import com.app.med.finder.dao.clsCitaPacienteDAO;
import com.app.med.finder.dao.clsClinicaDAO;
import com.app.med.finder.dao.clsClinicaEspecialidadDAO;
import com.app.med.finder.dao.clsClinicaSeguroDAO;
import com.app.med.finder.dao.clsDoctorDAO;
import com.app.med.finder.dao.clsEspecialidadDAO;
import com.app.med.finder.dao.clsPacienteDAO;
import com.app.med.finder.dao.clsPreguntaPacienteDAO;
import com.app.med.finder.dao.clsRespuestaCasosSaludDAO;
import com.app.med.finder.dao.clsRespuestaPreguntaPacienteDAO;
import com.app.med.finder.dao.clsSeguroDAO;
import com.app.med.finder.dao.clsUsuarioDAO;
import com.app.med.finder.servicio.clsServicio;

public class MenuActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
         if(clsUsuarioDAO.Buscar(this)==null)
        {
            Intent i=new Intent(MenuActivity.this,LoginActivity.class);
            startActivity(i); 
        }
        else
        {
           // Intent svc = new Intent(this, clsServicio.class);
           // startService(svc);
        }
    }
    
    public void btnHome(View v)
    { 
        Toast.makeText(this,"Tiene una Consulta Activa", Toast.LENGTH_SHORT).show();
       
    } 
    
     public void btnCosnulta(View v)
    {
       if(clsPreguntaPacienteDAO.BuscarXEstado(this)==null)
       {
         Intent i=new Intent(this,RealizarConsultaActivity.class);
         startActivity(i); 
       }
       else
            Toast.makeText(this,"Tiene una Consulta Activa", Toast.LENGTH_SHORT).show();
        
    } 
    public void btnCosnultas(View v)
    {
        Intent i=new Intent(this,ConsultasActivity.class);
         startActivity(i); 
    } 
     
    public void btnRespuestas(View v)
    {
        Intent i=new Intent(this,ConsultasActivity.class);
         startActivity(i); 
    } 
     
    
      
    public void btnCasos(View v)
    {
        Intent i=new Intent(this,CasosSaludActivity.class);
         startActivity(i);    
    } 
       
    public void btnFavoritos(View v)
    {
        Intent i=new Intent(this,FavoritosActivity.class);
         startActivity(i);          
    } 
        
    public void btnInformacion(View v)
    {
        Intent i=new Intent(this,InfEspecialidadesActivity.class);
         startActivity(i);        
    } 
         
    public void btnBusqueda(View v)
    {       
         Intent i=new Intent(this,BuscarActivity.class);
         startActivity(i);    
    } 
    
    public void btnCitas(View v)
    {
        Intent i=new Intent(this,CitasActivity.class);
         startActivity(i);   
       
    }
    
    public void btnClinicas(View v)
    {
         Intent i=new Intent(this,BuscarClinicasActivity.class);
         startActivity(i);        
    } 
     
    public void btnFamilia(View v)
    {
       Intent i=new Intent(this,MisFamiliaresActivity.class);
         startActivity(i); 
    } 
          
        @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
   
    
    return false;

    }
    
       @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);   
            return true;
       
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         AlertDialog.Builder alert = new AlertDialog.Builder(this);
        switch (item.getItemId()) {
        case R.id.MnuOpc1:  
                alert.setTitle("Cerrar Sesion");
                alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {  
                            clsCasosSaludDAO.Borrar(MenuActivity.this);
                            clsCitaPacienteDAO.Borrar(MenuActivity.this);
                            clsDoctorDAO.Borrar(MenuActivity.this);
                            clsEspecialidadDAO.Borrar(MenuActivity.this);
                            clsPreguntaPacienteDAO.Borrar(MenuActivity.this);
                            clsRespuestaCasosSaludDAO.Borrar(MenuActivity.this); 
                            clsClinicaDAO.Borrar(MenuActivity.this); 
                            clsClinicaEspecialidadDAO.Borrar(MenuActivity.this); 
                            clsClinicaSeguroDAO.Borrar(MenuActivity.this); 
                            clsPacienteDAO.Borrar(MenuActivity.this); 
                            clsRespuestaPreguntaPacienteDAO.Borrar(MenuActivity.this); 
                            clsSeguroDAO.Borrar(MenuActivity.this); 
                            clsUsuarioDAO.Borrar(MenuActivity.this); 

                            Intent svc = new Intent(MenuActivity.this, clsServicio.class);
                            stopService(svc); 
                            Intent i=new Intent(MenuActivity.this,LoginActivity.class);
                            startActivity(i); 
                         
                        }});
                alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {  
                       public void onClick(DialogInterface dialog, int whichButton) {    
                    }});
                       alert.show();
            return true;
   
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
}
