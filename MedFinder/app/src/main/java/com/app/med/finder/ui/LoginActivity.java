package com.app.med.finder.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.med.finder.conexion.LoginHTTP;
import com.app.med.finder.dao.*;
import com.app.med.finder.entidades.clsCasosSalud;
import com.app.med.finder.entidades.clsCitaPaciente;
import com.app.med.finder.entidades.clsClinica;
import com.app.med.finder.entidades.clsClinicaEspecialidad;
import com.app.med.finder.entidades.clsClinicaSeguro;
import com.app.med.finder.entidades.clsDoctor;
import com.app.med.finder.entidades.clsEspecialidad;
import com.app.med.finder.entidades.clsPaciente;
import com.app.med.finder.entidades.clsPreguntaPaciente;
import com.app.med.finder.entidades.clsRespuestaCasosSalud;
import com.app.med.finder.entidades.clsRespuestaPreguntaPaciente;
import com.app.med.finder.entidades.clsSeguro;
import com.app.med.finder.entidades.clsUsuario;

import java.util.concurrent.ExecutionException;


public class LoginActivity extends Activity
{
    /** Called when the activity is first created. */
    private EditText txtUsuario;
    private EditText txtPassword;
    private  ProgressDialog pd ;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        txtUsuario = (EditText)findViewById(R.id.txtUsuario);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        
        TextView lblLink = (TextView)findViewById(R.id.lblLink);
        lblLink.setText(Html.fromHtml(getString(R.string.web)));
        Linkify.addLinks(lblLink, Linkify.ALL);
        lblLink.setMovementMethod(LinkMovementMethod.getInstance());
        if(clsUsuarioDAO.Buscar(this)!=null)
        {
            Intent i=new Intent(this,MenuActivity.class);
            startActivity(i); 
        }
        
        
    }
    
    public void btnIngresar(View v)
    {

        LoginHTTP http = new LoginHTTP();
        http.execute(txtUsuario.getText().toString(), txtPassword.getText().toString());
        try {
            final String cadena=http.get();

        if(!cadena.trim().equals("0"))
          {
	     pd = new ProgressDialog(this);
            pd.setTitle("Cargando Datos");
            pd.setMessage("Espere un momento");
            pd.show();

                new Thread() {
                    public void run() {

			  String [] datos = cadena.split("\\<+parametro+>");
			   clsUsuario objUsuario=new clsUsuario();
			   objUsuario.setInt_id_usuario(Integer.parseInt(datos[0].trim()));
			   objUsuario.setStr_nombres(datos[1].trim());
			   objUsuario.setStr_apellido_paterno(datos[2].trim());
			   objUsuario.setStr_apellido_materno(datos[3].trim());
			   objUsuario.setStr_email(datos[4].trim());
			   objUsuario.setStr_dni(datos[5].trim());
			   objUsuario.setBol_sexo(Boolean.parseBoolean(datos[6].trim()));
			   objUsuario.setStr_direccion(datos[7].trim());
			   objUsuario.setStr_telefono(datos[8].trim());
			   objUsuario.setInt_id_persona(Integer.parseInt(datos[9].trim()));
			   if(!datos[10].trim().equals("0"))
				objUsuario.setByte_foto(Base64.decode(datos[10].trim(), Base64.NO_WRAP | Base64.URL_SAFE));
			   objUsuario.setStr_usuario(txtUsuario.getText().toString());
			   objUsuario.setStr_clave(txtPassword.getText().toString());

			   clsUsuarioDAO.Agregar(LoginActivity.this, objUsuario);

			    if(!datos[11].trim().equals("0"))
			    {
				 String [] entidad = datos[11].trim().split("\\<+entidad+>");
				 for(int i=0;i<entidad.length;i++)
				     clsPacienteDAO.Agregar(LoginActivity.this,new clsPaciente(entidad[i]));
			    }

			    if(!datos[12].trim().equals("0"))
			    {
				 String [] entidad = datos[12].trim().split("\\<+entidad+>");
				 for(int i=0;i<entidad.length;i++)
				     clsEspecialidadDAO.Agregar(LoginActivity.this,new clsEspecialidad(entidad[i]));
			    }
	      //
			    if(!datos[13].trim().equals("0"))
			    {
				 String [] entidad = datos[13].trim().split("\\<+entidad+>");
				 for(int i=0;i<entidad.length;i++)
				     clsClinicaDAO.Agregar(LoginActivity.this,new clsClinica(entidad[i]));
			    }

			    if(!datos[14].trim().equals("0"))
			    {
				 String [] entidad = datos[14].trim().split("\\<+entidad+>");
				 for(int i=0;i<entidad.length;i++)
				    clsDoctorDAO.Agregar(LoginActivity.this, new clsDoctor(entidad[i]));
			    }

			    if(!datos[15].trim().equals("0"))
			    {
				 String [] entidad = datos[15].trim().split("\\<+entidad+>");
				 for(int i=0;i<entidad.length;i++)
				    clsClinicaEspecialidadDAO.Agregar(LoginActivity.this, new clsClinicaEspecialidad(entidad[i]));
			    }
	      //
			    if(!datos[16].trim().equals("0"))
			    {
				 String [] entidad = datos[16].trim().split("\\<+entidad+>");
				 for(int i=0;i<entidad.length;i++)
				     clsSeguroDAO.Agregar(LoginActivity.this,new clsSeguro(entidad[i]) );
			    }
	      //
			    if(!datos[17].trim().equals("0"))
			    {
				 String [] entidad = datos[17].trim().split("\\<+entidad+>");
				 for(int i=0;i<entidad.length;i++)
				     clsClinicaSeguroDAO.Agregar(LoginActivity.this,new clsClinicaSeguro(entidad[i]) );
			    }

			    if(!datos[18].trim().equals("0"))
			    {
				 String [] entidad = datos[18].trim().split("\\<+entidad+>");
				 for(int i=0;i<entidad.length;i++)
				     clsDoctorDAO.Favorito(LoginActivity.this,Integer.parseInt(entidad[i].trim()),true);
			    }

			    if(!datos[19].trim().equals("0"))
			    {
				 String [] entidad = datos[19].trim().split("\\<+entidad+>");
				 for(int i=0;i<entidad.length;i++)
				     clsCasosSaludDAO.Agregar(LoginActivity.this,new clsCasosSalud(entidad[i]));
			    }

			     if(!datos[20].trim().equals("0"))
			    {
				 String [] entidad = datos[20].trim().split("\\<+entidad+>");
				 for(int i=0;i<entidad.length;i++)
				     clsRespuestaCasosSaludDAO.Agregar(LoginActivity.this,new clsRespuestaCasosSalud(entidad[i]));
			    }

                            if(!datos[21].trim().equals("0"))
			    {
				 String [] entidad = datos[21].trim().split("\\<+entidad+>");
				 for(int i=0;i<entidad.length;i++)
				     clsRespuestaCasosSaludDAO.Favorito(LoginActivity.this,Integer.parseInt(entidad[i].trim()),true);
			    }

                             if(!datos[22].trim().equals("0"))
			    {
				 String [] entidad = datos[22].trim().split("\\<+entidad+>");
				 for(int i=0;i<entidad.length;i++)
				     clsCitaPacienteDAO.Agregar(LoginActivity.this,new clsCitaPaciente(entidad[i]));
			    }
                            if(!datos[23].trim().equals("0"))
			    {
				 String [] entidad = datos[23].trim().split("\\<+entidad+>");
				 for(int i=0;i<entidad.length;i++)
				     clsPreguntaPacienteDAO.Agregar(LoginActivity.this,new clsPreguntaPaciente(entidad[i]));
			    }
                            if(!datos[24].trim().equals("0"))
			    {
				 String [] entidad = datos[24].trim().split("\\<+entidad+>");
				 for(int i=0;i<entidad.length;i++)
				     clsRespuestaPreguntaPacienteDAO.Agregar(LoginActivity.this,new clsRespuestaPreguntaPaciente(entidad[i]));
			    }



                          handler.sendEmptyMessage(0);
                    }
               }.start();
           

          }
	 else
	    Toast.makeText(this,"Error de Credenciales.", Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
    
    
        final Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //update UI here depending on what message is received.
            super.handleMessage(msg);
            pd.dismiss();             
            Intent i=new Intent(LoginActivity.this,MenuActivity.class);
            startActivity(i); 
       
       
        }
    };
        
    public void btnResgistrame(View v)
    {
       Intent i=new Intent(this,RegistroUsuarioActivity.class);
         startActivity(i); 
       
    }
    
    public void btnShare(View v)
    {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.str_compartir_sms));
        startActivity(Intent.createChooser(sharingIntent, "Compartir via"));
        startActivity(Intent.createChooser(sharingIntent,"Compartir con"));
        
    }
}
