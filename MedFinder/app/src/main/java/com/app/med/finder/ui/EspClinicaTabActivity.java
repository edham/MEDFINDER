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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.app.med.finder.dao.clsClinicaEspecialidadDAO;
import com.app.med.finder.entidades.clsClinicaEspecialidad;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Toditos
 */
public class EspClinicaTabActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    

    private ListView list;
    private List<clsClinicaEspecialidad> listEspecialidades;   
    private Adaptador adaptador;
    
    
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here        
        setContentView(R.layout.especialidades);
        Bundle bundle=getIntent().getExtras();
        int Id=Integer.valueOf(bundle.getString("Id"));
        
       
        
        list = (ListView)findViewById(R.id.list);     
        
        Buscar(Id);

    }
    
     public void Buscar(int id)
  {    


      listEspecialidades=clsClinicaEspecialidadDAO.BuscarXClinica(this,id);
    
      if(listEspecialidades!=null && listEspecialidades.size()>0)
      {
      adaptador = new Adaptador(this);   
      list.setAdapter(adaptador);
    
      }
  }
         
    
      class Adaptador extends ArrayAdapter {
    	
    	Activity context;
    	
    	Adaptador(Activity context) {
    		super(context, R.layout.especialidades_clinica_ista, listEspecialidades);
    		this.context = context;
    	}


    	public View getView(int position, View convertView, ViewGroup parent) {
                        SimpleDateFormat hora=new SimpleDateFormat("h:mm a");
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.especialidades_clinica_ista, null);
//                        
                        TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
			lblNombre.setText(listEspecialidades.get(position).getObjEspecialidad().getStr_nombre());
//                        
                        TextView lblDetalle = (TextView)item.findViewById(R.id.lblDetalle);
			lblDetalle.setText(listEspecialidades.get(position).getObjEspecialidad().getStr_descripcion());
                        
                        TextView lblInicio = (TextView)item.findViewById(R.id.lblInicio);
			lblInicio.setText(hora.format(listEspecialidades.get(position).getDat_hora_inicio()));
                        
                        TextView lblFin = (TextView)item.findViewById(R.id.lblFin);
			lblFin.setText(hora.format(listEspecialidades.get(position).getDat_hora_fin()));
                        
                        TextView lblAtencion = (TextView)item.findViewById(R.id.lblAtencion);
			lblAtencion.setText(listEspecialidades.get(position).getStr_detalle_horario());
//                        

			return(item);
		}
    }
    
      
      
     @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
   
    
    return false;

    }
    
}
