/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.med.finder.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.med.finder.conexion.CancelarCitaPacienteHTTP;
import com.app.med.finder.dao.clsCitaPacienteDAO;
import com.app.med.finder.dao.clsDoctorDAO;
import com.app.med.finder.dao.clsEspecialidadDAO;
import com.app.med.finder.dao.clsPreguntaPacienteDAO;
import com.app.med.finder.entidades.clsCitaPaciente;
import com.app.med.finder.entidades.clsDoctor;
import com.app.med.finder.utilidades.Funciones;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 */
public class CitasActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    private List<clsCitaPaciente> listCitasPaciente;  
    private Adaptador adaptador;
    private ListView list;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.consultas_citas);
        TextView lblTitulo = (TextView)findViewById(R.id.lblTitulo);
	lblTitulo.setText("Citas");
        list = (ListView)findViewById(R.id.list);    
        
        
       
      Buscar();     
    }
    
   
  public void Buscar()
  {    


      listCitasPaciente=clsCitaPacienteDAO.Listar(this);
    
   
      adaptador = new Adaptador(this);   
      
      list.setAdapter(adaptador);

  }
         
    
     class Adaptador extends ArrayAdapter {
    	
    	Activity context;
    	
    	Adaptador(Activity context) {
    		super(context,R.layout.cita_doctores_lista, listCitasPaciente);
    		this.context = context;
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {
                                
                        final int posicion=position;
			LayoutInflater inflater = context.getLayoutInflater();
                        View item = inflater.inflate(R.layout.cita_doctores_lista, null); 
                        
                        clsDoctor doctor=clsDoctorDAO.Buscar(context, listCitasPaciente.get(position).getObjDoctor().getInt_id_doctor());
                        TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
			lblNombre.setText("Dr. "+doctor.getStr_apellido_paterno()+" "+doctor.getStr_apellido_materno()+" "+doctor.getStr_nombres());
                        
                        TextView lblEspecialidad = (TextView)item.findViewById(R.id.lblEspecialidad);                        
			lblEspecialidad.setText(clsEspecialidadDAO.Buscar(context,doctor.getObjEspecialidad().getInt_id_especialidad()).getStr_nombre());
                        
                        TextView lblTipo = (TextView)item.findViewById(R.id.lblTipo);
                        if(listCitasPaciente.get(position).getInt_estado()==0)
                            lblTipo.setText("CITA ACTIVADO");
                        else if(listCitasPaciente.get(position).getInt_estado()==1)
                        {
                            lblTipo.setText("CITA PENDIENTE");
                      
                          if(listCitasPaciente.get(position).getDat_atencion().getTime()<new Date().getTime())
                          {
                            lblTipo.setText("CITA FINALIZADA");
                            View view = (View)item.findViewById(R.id.view);    
                            view.setVisibility(View.GONE);
                          }
                        }
                        TextView lblDetalle = (TextView)item.findViewById(R.id.lblDetalle);
                        lblDetalle.setText(listCitasPaciente.get(position).getStr_detalle());                        
                        
                        ImageView image = (ImageView)item.findViewById(R.id.image);
                        if(doctor.getByte_foto()!=null)
                        image.setImageBitmap(Funciones.getBitmap(doctor.getByte_foto()));
//                      
                            SimpleDateFormat  fecha=new SimpleDateFormat("dd/MM/yyyy");
                            SimpleDateFormat hora=new SimpleDateFormat("h:mm a");
                            
                            TextView lblFecha = (TextView)item.findViewById(R.id.lblFecha);                            
                            lblFecha.setText(fecha.format(listCitasPaciente.get(position).getDat_atencion()));
                            
                            TextView lblHora = (TextView)item.findViewById(R.id.lblHora);                            
                            lblHora.setText(hora.format(listCitasPaciente.get(position).getDat_atencion()));
                        
                            
                            
                        Button btnCancelar = (Button)item.findViewById(R.id.btnCancelar);           
                        btnCancelar.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                               Cancelar(listCitasPaciente.get(posicion).getInt_id_cita_paciente());
//                          
                          }
                      });
                        
                        Button btnAceptar = (Button)item.findViewById(R.id.btnAceptar);           
                        btnAceptar.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              Intent i=new Intent(CitasActivity.this,DoctorTabsActivity.class);
                                i.putExtra("Id",""+listCitasPaciente.get(posicion).getObjDoctor().getInt_id_doctor());
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
    
    
     private void Cancelar(int id)
    {
            final int IdCita=id;
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("CANCELAR CITA");
                alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {  
		            String cadena="0";
                    CancelarCitaPacienteHTTP http = new CancelarCitaPacienteHTTP();
                    http.execute(IdCita);
                    try {
                        cadena=http.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    if(!cadena.trim().equals("0"))
                    {
                        clsCitaPacienteDAO.BorrarXId(CitasActivity.this, IdCita);
                        Buscar();    

                    }
                    
                }});
                alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {  
                public void onClick(DialogInterface dialog, int whichButton) {    
                }});
                alert.show();
                
        
    }
    
}
