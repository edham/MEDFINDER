/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.med.finder.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.app.med.finder.conexion.http;
import com.app.med.finder.dao.clsCitaPacienteDAO;
import com.app.med.finder.dao.clsDoctorDAO;
import com.app.med.finder.dao.clsEspecialidadDAO;
import com.app.med.finder.dao.clsPacienteDAO;
import com.app.med.finder.dao.clsUsuarioDAO;
import com.app.med.finder.entidades.clsCitaPaciente;
import com.app.med.finder.entidades.clsDoctor;
import com.app.med.finder.entidades.clsPaciente;
import com.app.med.finder.utilidades.Funciones;
import java.util.List;

/**
 *
 * @author Toditos
 */
public class InfDoctorTabActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    
    private clsDoctor objDoctor = null;
    private Button btn_Favorito;   
    private String titulo;
    private int Id;
    private clsPaciente objPaciente;
    
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here        
        setContentView(R.layout.doctor_infromacion);
        Bundle bundle=getIntent().getExtras();
        Id=Integer.valueOf(bundle.getString("Id"));
        
        objDoctor=clsDoctorDAO.Buscar(this, Id);
        
        btn_Favorito = (Button)findViewById(R.id.btn_Favorito);   
        
        ImageView image = (ImageView)findViewById(R.id.image);
        if(objDoctor.getByte_foto()!=null)
        image.setImageBitmap(Funciones.getBitmap(objDoctor.getByte_foto()));
        
        TextView lblNombre = (TextView)findViewById(R.id.lblNombre);
	lblNombre.setText("Dr. "+objDoctor.getStr_apellido_paterno()+" "+objDoctor.getStr_apellido_materno()+" "+objDoctor.getStr_nombres());
        
        TextView lblColegiatura = (TextView)findViewById(R.id.lblColegiatura);
	lblColegiatura.setText(objDoctor.getStr_codigo_colegiatura());
        
        TextView lblDNI = (TextView)findViewById(R.id.lblDNI);
	lblDNI.setText(objDoctor.getStr_dni());
        
        TextView lblTelefono = (TextView)findViewById(R.id.lblTelefono);
	lblTelefono.setText(objDoctor.getStr_telefono());
        
        TextView lblDireccion = (TextView)findViewById(R.id.lblDireccion);
	lblDireccion.setText(objDoctor.getStr_direccion());
        
        TextView lblDetalle = (TextView)findViewById(R.id.lblDetalle);
	lblDetalle.setText(objDoctor.getStr_direccion_detalle());
        
        
        RatingBar rtbPuntos = (RatingBar)findViewById(R.id.rtbPuntos);
        rtbPuntos.setRating(objDoctor.getInt_puntuje());
        
        
        
        TextView lblEspecializacion = (TextView)findViewById(R.id.lblEspecializacion);
	lblEspecializacion.setText(clsEspecialidadDAO.Buscar(this, objDoctor.getObjEspecialidad().getInt_id_especialidad()).getStr_nombre());
             
        if(objDoctor.isBol_favorito())
	 {
            btn_Favorito.setBackgroundResource(R.drawable.favorito_on);
            
	     titulo="Quitar de Favoritos";
	 }
        else
	 {
             btn_Favorito.setBackgroundResource(R.drawable.favorito_off);
	      titulo="Agregar a Favoritos";
	 }
        
    }
    
    public void btnFavorito(View v)
    {
	     AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle(titulo);
                alert.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {  
		      if(objDoctor.isBol_favorito())
			{
			   btn_Favorito.setBackgroundResource(R.drawable.favorito_off);
			   clsDoctorDAO.Favorito(InfDoctorTabActivity.this, Id, false);
                           objDoctor.setBol_favorito(false);
                           http.insertarFavorito(clsUsuarioDAO.Buscar(InfDoctorTabActivity.this).getInt_id_usuario(),Id,false);
                           
			}
		      else
			{
			    btn_Favorito.setBackgroundResource(R.drawable.favorito_on);
			    clsDoctorDAO.Favorito(InfDoctorTabActivity.this, Id, true);
                            objDoctor.setBol_favorito(true);
                            http.insertarFavorito(clsUsuarioDAO.Buscar(InfDoctorTabActivity.this).getInt_id_usuario(),Id,true);
			}
                    
                }});
                alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {  
                public void onClick(DialogInterface dialog, int whichButton) {    
                }});
                alert.show();
        
    }

    public void btnCita(View v)
    {
        
          if(clsCitaPacienteDAO.BuscarXEstado(this)==null)
       {
           final Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.solicitar_cita);
                    dialog.setTitle("SOLICITAR CITA");
                    final EditText txtDetalle = (EditText) dialog.findViewById(R.id.txtDetalle);                
                    txtDetalle.setText("");
                    
                    Spinner ComboPaciente = (Spinner)dialog.findViewById(R.id.ComboPaciente);
                    final List<clsPaciente> lista =clsPacienteDAO.Listar(this);
                    ArrayAdapter<clsPaciente> adapter = new ArrayAdapter<clsPaciente>(this,android.R.layout.simple_spinner_item,lista);       
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        ComboPaciente.setAdapter(adapter);     
                         ComboPaciente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {          
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        objPaciente=lista.get(position);
                        }
                        public void onNothingSelected(AdapterView<?> parent) {
                            //User selected same item. Nothing to do.
                        }
                        });
                    ComboPaciente.setSelection(0);
//
                    Button btnAceptar = (Button) dialog.findViewById(R.id.btnAceptar);
                    btnAceptar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                    if(!txtDetalle.getText().toString().equals(""))
                                    {
                                        clsCitaPaciente entidad = new clsCitaPaciente();
                                        entidad.setObjPaciente(objPaciente);
                                        entidad.setObjDoctor(objDoctor);
                                        entidad.setStr_detalle(txtDetalle.getText().toString());    
                                        
                                        
                                        String id= http.insertarCitaPaciente(entidad);
                                                if(!id.trim().equals("0"))
                                                {
                                                  entidad.setInt_id_cita_paciente(Integer.parseInt(id));
                                                  clsCitaPacienteDAO.Agregar(InfDoctorTabActivity.this, entidad);
                                                  Toast.makeText(InfDoctorTabActivity.this,"Se Registro Correctamente", Toast.LENGTH_SHORT).show();
                                                  Intent i=new Intent(InfDoctorTabActivity.this,MenuActivity.class);
                                                  startActivity(i);
                                                }       
                                                else
                                                    Toast.makeText(InfDoctorTabActivity.this,"Error al Insertar intentelo mas tarde", Toast.LENGTH_SHORT).show();  
                                                
                                        dialog.dismiss();
                                    }
                                    else
                                        Toast.makeText(InfDoctorTabActivity.this,"Ingrese un Motivo", Toast.LENGTH_SHORT).show();
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
       else
            Toast.makeText(this,"Tiene una Cita Esperando en espera de respuesta", Toast.LENGTH_SHORT).show();

        
         
        
    }
    
     @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
   
    
    return false;

    }
    
}
