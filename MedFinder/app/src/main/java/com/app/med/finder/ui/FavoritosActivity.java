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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.app.med.finder.dao.clsDoctorDAO;
import com.app.med.finder.dao.clsEspecialidadDAO;
import com.app.med.finder.dao.clsPreguntaPacienteDAO;
import com.app.med.finder.entidades.clsDoctor;
import com.app.med.finder.utilidades.Funciones;
import java.util.List;

/**
 *
 * @author Babsy Gamboa
 */
public class FavoritosActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    private List<clsDoctor> listDoctores;  
    private Adaptador adaptador;
    private ListView list;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.favoritos);
        
        list = (ListView)findViewById(R.id.list);       
        
       
      Buscar();     
    }
    
   
  public void Buscar()
  {    


      listDoctores=clsDoctorDAO.ListarFavoritos(this);
    
      if(listDoctores!=null && listDoctores.size()>0)
      {
      adaptador = new Adaptador(this);   
      
      list.setAdapter(adaptador);
      list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                    Intent i=new Intent(FavoritosActivity.this,DoctorTabsActivity.class);
                    i.putExtra("Id",""+listDoctores.get(posicion).getInt_id_doctor());
                    startActivity(i);                    
            }
        });
     
      }
  }
         
    
      class Adaptador extends ArrayAdapter {
    	
    	Activity context;
    	
    	Adaptador(Activity context) {
    		super(context, R.layout.doctores_lista, listDoctores);
    		this.context = context;
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.doctores_lista, null);
                        
                        TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
			lblNombre.setText("Dr. "+listDoctores.get(position).getStr_apellido_paterno()+" "+listDoctores.get(position).getStr_apellido_materno()+" "+listDoctores.get(position).getStr_nombres());
                        
                        TextView lblEspecialidad = (TextView)item.findViewById(R.id.lblEspecialidad);                        
			lblEspecialidad.setText(clsEspecialidadDAO.Buscar(context,listDoctores.get(position).getObjEspecialidad().getInt_id_especialidad()).getStr_nombre());
//                        
                        
                                
                        RatingBar rtbPuntos = (RatingBar)item.findViewById(R.id.rtbPuntos);
                        rtbPuntos.setRating(listDoctores.get(position).getInt_puntuje());
                        
                        ImageView image = (ImageView)item.findViewById(R.id.image);
                        if(listDoctores.get(position).getByte_foto()!=null)
                        image.setImageBitmap(Funciones.getBitmap(listDoctores.get(position).getByte_foto()));

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
