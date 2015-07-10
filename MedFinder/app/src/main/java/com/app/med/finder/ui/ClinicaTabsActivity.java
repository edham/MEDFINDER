/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.med.finder.ui;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;
import com.app.med.finder.dao.clsPreguntaPacienteDAO;

/**
 *
 * @author Toditos
 */
public class ClinicaTabsActivity extends TabActivity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here     
        setContentView(R.layout.doctor_tabs);
         Bundle bundle=getIntent().getExtras();
         int Id=Integer.valueOf(bundle.getString("Id"));
         
         Resources res= getResources();          
		TabHost tabHost = getTabHost(); 
                
         Intent intentDatos = new Intent().setClass(this, InfClinicaTabActivity.class);
         intentDatos.putExtra("Id",""+Id);
         TabHost.TabSpec tabDatos = tabHost.newTabSpec("Información").setIndicator("Información", res.getDrawable(R.drawable.info)).setContent(intentDatos);
         
         Intent especialidades = new Intent().setClass(this, EspClinicaTabActivity.class);
         especialidades.putExtra("Id",""+Id);
         TabHost.TabSpec tabEspecialidades = tabHost.newTabSpec("Especialidades").setIndicator("Especialidades", res.getDrawable(R.drawable.especialidades)).setContent(especialidades);
         
         Intent seguros = new Intent().setClass(this, SegClinicaTabActivity.class);
         seguros.putExtra("Id",""+Id);
         TabHost.TabSpec tabseguros = tabHost.newTabSpec("Seguros").setIndicator("Seguros", res.getDrawable(R.drawable.seguros)).setContent(seguros);
         
         Intent ubicacion = new Intent().setClass(this, UbiClinicaTabActivity.class);
         ubicacion.putExtra("Id",""+Id);
         TabHost.TabSpec tabUbicacion = tabHost.newTabSpec("Ubicación").setIndicator("Ubicación", res.getDrawable(R.drawable.ubicacion)).setContent(ubicacion);

         tabHost.addTab(tabDatos);
         tabHost.addTab(tabEspecialidades);
         tabHost.addTab(tabseguros);
         tabHost.addTab(tabUbicacion);
         
         
         
         
         tabHost.setCurrentTab(0);
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
