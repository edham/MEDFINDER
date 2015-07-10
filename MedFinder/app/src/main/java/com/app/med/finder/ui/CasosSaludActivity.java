/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.med.finder.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.app.med.finder.dao.clsCasosSaludDAO;
import com.app.med.finder.dao.clsPreguntaPacienteDAO;
import com.app.med.finder.entidades.clsCasosSalud;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Babsy Gamboa
 */
public class CasosSaludActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    private List<clsCasosSalud> listCasosTemp; 
    private List<clsCasosSalud> listCasos;  
    private Adaptador adaptador;
    private ListView list;
    private EditText txtFiltro;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.favoritos);
        TextView lblTitulo = (TextView)findViewById(R.id.lblTitulo);
	lblTitulo.setText("Casos de Salud");
        list = (ListView)findViewById(R.id.list);    
        listCasos=clsCasosSaludDAO.Listar(this);
        txtFiltro = (EditText) findViewById(R.id.txtFiltro);
        txtFiltro.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,int count) {                                
                            Buscar(s.toString().trim()); 
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
        
        
       
      Buscar("");     
    }
    
   
  public void Buscar(String filtro)
  {    

      listCasosTemp=new ArrayList<clsCasosSalud>();
       for(clsCasosSalud entidad:listCasos)
         if(entidad.getStr_tema().toLowerCase().indexOf(filtro.toLowerCase()) != -1)
         listCasosTemp.add(entidad);
    
      
      adaptador = new Adaptador(this);   
      
      list.setAdapter(adaptador);
    
  }
         
         
    
     class Adaptador extends ArrayAdapter {
    	
    	Activity context;
    	
    	Adaptador(Activity context) {
    		super(context, R.layout.casos_salud_lista, listCasosTemp);
    		this.context = context;
    	}
    	
    	public View getView(int position, View convertView, ViewGroup parent) {
                        SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat hora=new SimpleDateFormat("h:mm a");
 
                                
                        final int posicion=position;
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.casos_salud_lista, null);
                        
                        TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
			lblNombre.setText(listCasosTemp.get(position).getStr_tema());
                        
                         TextView lblInicio = (TextView)item.findViewById(R.id.lblInicio);
			lblInicio.setText(fecha.format(listCasosTemp.get(position).getDat_inicio())+" "+hora.format(listCasosTemp.get(position).getDat_inicio()));
                        
                        Button btnContacto = (Button)item.findViewById(R.id.btnContacto);           
                        btnContacto.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              Intent i=new Intent(CasosSaludActivity.this,RespuestasCasosSaludActivity.class);
                                i.putExtra("Id",""+listCasosTemp.get(posicion).getInt_id_casos_salud());
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
