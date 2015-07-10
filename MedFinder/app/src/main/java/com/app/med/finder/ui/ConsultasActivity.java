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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.app.med.finder.dao.clsEspecialidadDAO;
import com.app.med.finder.dao.clsPacienteDAO;
import com.app.med.finder.dao.clsPreguntaPacienteDAO;
import com.app.med.finder.entidades.clsPaciente;
import com.app.med.finder.entidades.clsPreguntaPaciente;
import java.text.SimpleDateFormat;
import java.util.List;


public class ConsultasActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    private List<clsPreguntaPaciente> listPreguntaPaciente;  
    private Adaptador adaptador;
    private ListView list;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.consultas_citas);
        TextView lblTitulo = (TextView)findViewById(R.id.lblTitulo);
	lblTitulo.setText("Consultas");
        list = (ListView)findViewById(R.id.list);    

      Buscar();     
    }
    
   
  public void Buscar()
  {   
    listPreguntaPaciente=clsPreguntaPacienteDAO.Listar(this);
  
     
      adaptador = new Adaptador(this);   
      
      list.setAdapter(adaptador);
    
  }
         
    
     class Adaptador extends ArrayAdapter {
    	
    	Activity context;
    	
    	Adaptador(Activity context) {
    		super(context, R.layout.consultas_lista, listPreguntaPaciente);
    		this.context = context;
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {
                        final int posicion=position;
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.consultas_lista, null);
                        
                        TextView lblAsunto = (TextView)item.findViewById(R.id.lblAsunto);
			lblAsunto.setText(listPreguntaPaciente.get(position).getStr_asunto());
                        
                        TextView lblDetalle = (TextView)item.findViewById(R.id.lblDetalle);
			lblDetalle.setText(listPreguntaPaciente.get(position).getStr_paciente_detalle());
                        
                        
                        TextView lblEspecialidad = (TextView)item.findViewById(R.id.lblEspecialidad);
                        lblEspecialidad.setText(clsEspecialidadDAO.Buscar(context, listPreguntaPaciente.get(position).getObjEspecialidad().getInt_id_especialidad()).getStr_nombre());
                        
                        if(listPreguntaPaciente.get(position).getInt_estado()==0)
                        lblEspecialidad.setText(clsEspecialidadDAO.Buscar(context, listPreguntaPaciente.get(position).getObjEspecialidad().getInt_id_especialidad()).getStr_nombre()+" - Activo");
                        
                        TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
                        
                        clsPaciente objPaciente=clsPacienteDAO.Buscar(context, listPreguntaPaciente.get(position).getObjPaciente().getInt_id_paciente());
                        
			lblNombre.setText(objPaciente.getStr_apellido_paterno()
                                +" "+objPaciente.getStr_apellido_materno()+" "+
                                objPaciente.getStr_nombres());
                        
                          SimpleDateFormat  fecha=new SimpleDateFormat("dd/MM/yyyy");
                            SimpleDateFormat hora=new SimpleDateFormat("h:mm a");
                            
                            TextView lblFecha = (TextView)item.findViewById(R.id.lblFecha);                            
                            lblFecha.setText(fecha.format(listPreguntaPaciente.get(position).getDat_inicio()));
                            
                            TextView lblHora = (TextView)item.findViewById(R.id.lblHora);                            
                            lblHora.setText(hora.format(listPreguntaPaciente.get(position).getDat_inicio()));
                            
                            
                        Button btnContacto = (Button)item.findViewById(R.id.btnContacto);           
                        btnContacto.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              Intent i=new Intent(ConsultasActivity.this,RespuestasConsultasActivity.class);
                                i.putExtra("Id",""+listPreguntaPaciente.get(posicion).getInt_id_pregunta_paciente());
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
    
}
