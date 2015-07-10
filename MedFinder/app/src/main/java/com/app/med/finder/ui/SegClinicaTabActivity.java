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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.app.med.finder.dao.clsSeguroDAO;
import com.app.med.finder.entidades.clsSeguro;
import com.app.med.finder.utilidades.Funciones;
import java.util.List;

/**
 *
 * @author Toditos
 */
public class SegClinicaTabActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    

    private ListView list;
    private List<clsSeguro> listSeguro;   
    private Adaptador adaptador;
    
    
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here        
        setContentView(R.layout.especialidades);
        Bundle bundle=getIntent().getExtras();
        int Id=Integer.valueOf(bundle.getString("Id"));
        
       
        TextView lblTitulo = (TextView)findViewById(R.id.lblTitulo);
	lblTitulo.setText("Seguros");
        
        list = (ListView)findViewById(R.id.list);     
        
        Buscar(Id);

    }
    
     public void Buscar(int id)
  {    


      listSeguro=clsSeguroDAO.ListarXClinica(this,id);
    
      if(listSeguro!=null && listSeguro.size()>0)
      {
      adaptador = new Adaptador(this);   
      list.setAdapter(adaptador);
    
      }
  }
         
    
      class Adaptador extends ArrayAdapter {
    	
    	Activity context;
    	
    	Adaptador(Activity context) {
    		super(context, R.layout.seguros_lista, listSeguro);
    		this.context = context;
    	}


    	public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.seguros_lista, null);
                        
                        TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
			lblNombre.setText(listSeguro.get(position).getStr_nombre());

                        ImageView image = (ImageView)item.findViewById(R.id.image);
                        
                        if(listSeguro.get(position).getByte_logo()!=null)
                        image.setImageBitmap(Funciones.getBitmap(listSeguro.get(position).getByte_logo()));
                      

			return(item);
		}
    }
    
      
      
     @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
   
    
    return false;

    }
    
}
