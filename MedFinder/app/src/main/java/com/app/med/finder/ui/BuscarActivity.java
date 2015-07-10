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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.app.med.finder.dao.clsClinicaDAO;
import com.app.med.finder.dao.clsDoctorDAO;
import com.app.med.finder.dao.clsEspecialidadDAO;
import com.app.med.finder.dao.clsPreguntaPacienteDAO;
import com.app.med.finder.entidades.clsClinica;
import com.app.med.finder.entidades.clsDoctor;
import com.app.med.finder.entidades.clsEspecialidad;
import com.app.med.finder.utilidades.Funciones;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Babsy Gamboa
 */
public class BuscarActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    private List<clsDoctor> listDoctores;  
    private List<clsClinica> listClinica;  
    private List<clsEspecialidad> listEspecialidad;   
    private AdaptadorDoctor adaptadorDoctor;
    private AdaptadorClinica adaptadorClinica;
    
    private ListView list;
    private Spinner ComboBusqueda;    
    private Spinner ComboEspecialidad;
    private boolean busqueda=true;
    private int idEspecialidad=0;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.buscar);
        
        list = (ListView)findViewById(R.id.list);       
        ComboBusqueda = (Spinner)findViewById(R.id.ComboBusqueda);   
        ComboEspecialidad = (Spinner)findViewById(R.id.ComboEspecialidad);   
        
        TipoDLL();
        EspecialidadDDL();
      Buscar("");     
    }
    
      public void EspecialidadDDL (){
        listEspecialidad=clsEspecialidadDAO.Listar(this);
        listEspecialidad.add(0,new clsEspecialidad(0,"Todas las Especialidades"));
        
        ArrayAdapter<clsEspecialidad> adapter = new ArrayAdapter<clsEspecialidad>(this,
        android.R.layout.simple_spinner_item,listEspecialidad);       
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ComboEspecialidad.setAdapter(adapter);     
        ComboEspecialidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {          
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idEspecialidad=listEspecialidad.get(position).getInt_id_especialidad();
		  Buscar("");
            }
            public void onNothingSelected(AdapterView<?> parent) {
                //User selected same item. Nothing to do.
            }
        });
        ComboEspecialidad.setSelection(0);
    }
    
     public void TipoDLL (){

        List<String> lista =new ArrayList<String>();
        lista.add("Doctores");
        lista.add("Clinicas");
        
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lista);       
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            ComboBusqueda.setAdapter(adapter);     
        ComboBusqueda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {          
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             if(position==0)
             {                
                 busqueda=true;
                 Buscar("");
             }
             else
             {
                 busqueda=false;
                 Buscar("");
             }
            }
            public void onNothingSelected(AdapterView<?> parent) {
                //User selected same item. Nothing to do.
            }
        });
        ComboBusqueda.setSelection(0);
     }  
  public void Buscar(String buscar)
  {    

      if(busqueda)
      {
      listDoctores=clsDoctorDAO.Listar(this,idEspecialidad);
    
      if(listDoctores!=null && listDoctores.size()>0)
      {
        adaptadorDoctor = new AdaptadorDoctor(this);   

        list.setAdapter(adaptadorDoctor);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                  Intent i=new Intent(BuscarActivity.this,DoctorTabsActivity.class);
                      i.putExtra("Id",""+listDoctores.get(posicion).getInt_id_doctor());
                      startActivity(i); 
              }
          });

        }
      }
      else
      {
        listClinica=clsClinicaDAO.Listar(this,idEspecialidad);    
        if(listClinica!=null && listClinica.size()>0)
        {
          adaptadorClinica = new AdaptadorClinica(this);   

          list.setAdapter(adaptadorClinica);
          list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                        Intent i=new Intent(BuscarActivity.this,ClinicaTabsActivity.class);
                        i.putExtra("Id",""+listClinica.get(posicion).getInt_id_clinica());
                        startActivity(i);                     
                    
                }
            });

          }
          
      }
  }
         
    
      class AdaptadorDoctor extends ArrayAdapter {
    	
    	Activity context;
    	
    	AdaptadorDoctor(Activity context) {
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
                        
                        RatingBar rtbPuntos = (RatingBar)item.findViewById(R.id.rtbPuntos);
                        rtbPuntos.setRating(listDoctores.get(position).getInt_puntuje());
                        
                        ImageView image = (ImageView)item.findViewById(R.id.image);
                        if(listDoctores.get(position).getByte_foto()!=null)
                        image.setImageBitmap(Funciones.getBitmap(listDoctores.get(position).getByte_foto()));

			return(item);
		}
    }
      
      
      class AdaptadorClinica extends ArrayAdapter {
    	
    	Activity context;
    	
    	AdaptadorClinica(Activity context) {
    		super(context, R.layout.clinicas_lista, listClinica);
    		this.context = context;
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.clinicas_lista, null);
                        
                        TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
			lblNombre.setText(listClinica.get(position).getStr_nombre());
                        
                        TextView lblDetalle = (TextView)item.findViewById(R.id.lblDetalle);
			lblDetalle.setText(listClinica.get(position).getStr_slogan());
                        
                        
                        ImageView image = (ImageView)item.findViewById(R.id.image);
                        if(listClinica.get(position).getByte_logo()!=null)
                        image.setImageBitmap(Funciones.getBitmap(listClinica.get(position).getByte_logo()));

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
