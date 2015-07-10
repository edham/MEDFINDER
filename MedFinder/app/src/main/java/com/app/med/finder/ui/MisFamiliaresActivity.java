/*
 * To change this template, choose Tools | Templates
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.app.med.finder.dao.clsPacienteDAO;
import com.app.med.finder.dao.clsPreguntaPacienteDAO;
import com.app.med.finder.entidades.clsPaciente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Babsy Gamboa
 */
public class MisFamiliaresActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
   private List<clsPaciente> itens;   
   private Adaptador adaptador;
    private ListView listMisContactos;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.mis_familiares);
        
        listMisContactos = (ListView)findViewById(R.id.listMisFamiliares);       
              
      Buscar();     
    }
      
  public void Buscar()
  {    


      itens=clsPacienteDAO.Listar(this);
    
      if(itens!=null && itens.size()>0)
      {
      adaptador = new Adaptador(this);   
      
      listMisContactos.setAdapter(adaptador);
     
      }
  }
         
    
      class Adaptador extends ArrayAdapter {
    	
    	Activity context;
    	
    	Adaptador(Activity context) {
    		super(context, R.layout.mis_familiares_lista, itens);
    		this.context = context;
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {
                        final int posicion=position;
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.mis_familiares_lista, null);
                        TextView lblNombreContacto = (TextView)item.findViewById(R.id.lblNombreContacto);
			lblNombreContacto.setText(itens.get(position).getStr_apellido_paterno()+" "+itens.get(position).getStr_apellido_materno()+", "+itens.get(position).getStr_nombres());
                        Button btnContacto = (Button)item.findViewById(R.id.btnContacto);           
                        btnContacto.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              Intent i=new Intent(MisFamiliaresActivity.this,FamiliarActivity.class);
                                i.putExtra("idPaciente",""+itens.get(posicion).getInt_id_paciente());
                                      startActivity(i);
                          }
                      });
                        
			return(item);
		}
    }

      public void btnCancelar(View v)
      {
                 Intent i=new Intent(this,MenuActivity.class);
                startActivity(i);

          
      }
      
      public void btnAgregar(View v)
      {
         Intent i=new Intent(this,FamiliarActivity.class);
          i.putExtra("idPaciente",""+0);
                startActivity(i);

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
