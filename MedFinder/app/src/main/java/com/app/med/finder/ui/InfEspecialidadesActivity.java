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
import com.app.med.finder.dao.clsEspecialidadDAO;
import com.app.med.finder.entidades.clsEspecialidad;
import java.util.List;

/**
 *
 * @author Toditos
 */
public class InfEspecialidadesActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    

    private ListView list;
    private List<clsEspecialidad> listEspecialidades;   
    private Adaptador adaptador;
    
    
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here        
        setContentView(R.layout.info_especialidades);
        
       
        
        list = (ListView)findViewById(R.id.list);     
        
        Buscar();

    }
    
     public void Buscar()
  {    


      listEspecialidades=clsEspecialidadDAO.Listar(this);
    
      if(listEspecialidades!=null && listEspecialidades.size()>0)
      {
      adaptador = new Adaptador(this);   
      list.setAdapter(adaptador);
    
      }
  }
         
    
      class Adaptador extends ArrayAdapter {
    	
    	Activity context;
    	
    	Adaptador(Activity context) {
    		super(context, R.layout.especialidades_lista, listEspecialidades);
    		this.context = context;
    	}


    	public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.especialidades_lista, null);
//                        
                        TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
			lblNombre.setText(listEspecialidades.get(position).getStr_nombre());
//                        
                        TextView lblDetalle = (TextView)item.findViewById(R.id.lblDetalle);
			lblDetalle.setText(listEspecialidades.get(position).getStr_descripcion());
//                        

			return(item);
		}
    }
    
      
      
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        
     if (keyCode == KeyEvent.KEYCODE_BACK) {  
          Intent i=new Intent(this,MenuActivity.class);
                startActivity(i);
       return true;
    }
    return super.onKeyDown(keyCode, event);
    }
    
}
