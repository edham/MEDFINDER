/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.med.finder.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.app.med.finder.conexion.http;
import com.app.med.finder.dao.clsDoctorDAO;
import com.app.med.finder.dao.clsPreguntaPacienteDAO;
import com.app.med.finder.dao.clsRespuestaPreguntaPacienteDAO;
import com.app.med.finder.entidades.clsDoctor;
import com.app.med.finder.entidades.clsRespuestaPreguntaPaciente;
import com.app.med.finder.utilidades.Funciones;
import java.util.List;

/**
 *
 * @author Babsy Gamboa
 */
public class RespuestasConsultasActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    private List<clsRespuestaPreguntaPaciente> listCasos;  
    private Adaptador adaptador;
    private ListView list;
    private int idConsultas=0;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.respuestas_casos_salud);
         Bundle bundle=getIntent().getExtras();
        idConsultas=Integer.valueOf(bundle.getString("Id"));
        
        TextView lblTitulo = (TextView)findViewById(R.id.lblTitulo);
	lblTitulo.setText(clsPreguntaPacienteDAO.Buscar(this, idConsultas).getStr_asunto());
        list = (ListView)findViewById(R.id.list);    
        
        
       
      Buscar(idConsultas);     
    }
    
   
  public void Buscar(int id)
  {    


      listCasos=clsRespuestaPreguntaPacienteDAO.Listar(this, id);
    
     
      adaptador = new Adaptador(this);         
      list.setAdapter(adaptador);
    
     
  }
         
    
     class Adaptador extends ArrayAdapter {
    	
    	Activity context;
    	
    	Adaptador(Activity context) {
    		super(context, R.layout.respuestas_casos_salud_ista, listCasos);
    		this.context = context;
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {

                        final int posicion=position;
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.respuestas_casos_salud_ista, null);
                        
                        View ViewCalificar = (View)item.findViewById(R.id.ViewCalificar);
                        
                        TextView lblTema = (TextView)item.findViewById(R.id.lblTema);
			lblTema.setText(listCasos.get(position).getStr_detalle());
                     
			clsDoctor doctor=clsDoctorDAO.Buscar(context, listCasos.get(position).getObjDoctor().getInt_id_doctor());
			
                        TextView lblDoctor = (TextView)item.findViewById(R.id.lblDoctor);                        
			lblDoctor.setText("Dr. "+doctor.getStr_apellido_paterno()+" "+doctor.getStr_apellido_materno()+" "+doctor.getStr_nombres());
     
                        RatingBar rtbPuntos = (RatingBar)item.findViewById(R.id.rtbPuntos);
                        rtbPuntos.setRating( listCasos.get(position).getInt_puntaje());
                        
                        ImageView image = (ImageView)item.findViewById(R.id.image);
                        if(doctor.getByte_foto()!=null)
                        image.setImageBitmap(Funciones.getBitmap(doctor.getByte_foto()));
                        
                        if(listCasos.get(position).getInt_puntaje()>0)
                            ViewCalificar.setVisibility(View.GONE);
                        
                         Button btnCalificar = (Button)item.findViewById(R.id.btnCalificar);           
                        btnCalificar.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              getDialogo(listCasos.get(posicion));
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
          Intent i=new Intent(this,ConsultasActivity.class);
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
    
    public void getDialogo(clsRespuestaPreguntaPaciente entidad)
    {
        
                   final clsRespuestaPreguntaPaciente objeto=entidad;
                   final Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.calificar_respuesta);
                    dialog.setTitle("CALIFICAR");
                    final RatingBar rtbPuntos = (RatingBar) dialog.findViewById(R.id.rtbPuntos);                
     
//
                    Button btnAceptar = (Button) dialog.findViewById(R.id.btnAceptar);
                    btnAceptar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                    if(rtbPuntos.getRating()>0)
                                    {
                                       setCalificar((int)rtbPuntos.getRating(),objeto);
                                        dialog.dismiss();
                                    }
                                    else
                                        Toast.makeText(RespuestasConsultasActivity.this,"Ingrese una califacion", Toast.LENGTH_SHORT).show();
                            }
                    });
                    Button btnCancelar = (Button) dialog.findViewById(R.id.btnCancelar);
                    btnCancelar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                    dialog.dismiss();                              
                            }
                    });
                    dialog.show();
    }
    
    
    public void setCalificar(int puntaje,clsRespuestaPreguntaPaciente entidad)
    {
           String cadena= http.puntajeRespuestaPreguntaPaciente(entidad.getInt_id_respuesta_pregunta_paciente(), puntaje);
            if(!cadena.trim().equals("0"))
            {
                entidad.setInt_puntaje(puntaje);
                clsRespuestaPreguntaPacienteDAO.Actualizar(this, entidad);
                Buscar(idConsultas); ;

            }
    }
}
