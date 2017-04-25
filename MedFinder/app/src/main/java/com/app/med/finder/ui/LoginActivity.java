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

import com.app.med.finder.conexion.ActualizarDataHTTP;
import com.app.med.finder.conexion.LoginHTTP;
import com.app.med.finder.dao.*;
import com.app.med.finder.entidades.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class LoginActivity extends Activity
{
    /** Called when the activity is first created. */
    private EditText txtUsuario;
    private EditText txtPassword;
    private  ProgressDialog pd ;
	private clsUsuario objUsuario;
	private boolean validador;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        txtUsuario = (EditText)findViewById(R.id.txtUsuario);
        txtPassword = (EditText)findViewById(R.id.txtPassword);

		txtUsuario.setText("12345678");
		txtPassword.setText("123456");
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



	     	pd = new ProgressDialog(this);
            pd.setTitle("Cargando Datos");
            pd.setMessage("Espere un momento");
            pd.show();
                new Thread() {
                    public void run() {
						try {
							validador = false;
							LoginHTTP login = new LoginHTTP();
							login.execute(txtUsuario.getText().toString(), txtPassword.getText().toString());
							JSONObject entidadJSON = new JSONObject(login.get());
							if(entidadJSON.getInt("rpta")==1) {


								objUsuario = new clsUsuario();
								objUsuario.setInt_id_usuario(entidadJSON.getInt("usuarioId"));
								objUsuario.setStr_nombres(entidadJSON.getString("personaNombre"));
								objUsuario.setStr_apellido_paterno(entidadJSON.getString("personaApellidoPaterno"));
								objUsuario.setStr_apellido_materno(entidadJSON.getString("personaApellidoMaterno"));
								objUsuario.setStr_email(entidadJSON.getString("personaEmail"));
								objUsuario.setStr_dni(entidadJSON.getString("personaDni"));
								objUsuario.setBol_sexo(entidadJSON.getBoolean("personaSexo"));
								objUsuario.setStr_direccion(entidadJSON.getString("personaDireccion"));
								objUsuario.setStr_telefono(entidadJSON.getString("personaTelefono"));
								objUsuario.setInt_id_persona(entidadJSON.getInt("personaId"));
								if (!entidadJSON.getString("personaFoto").equals(""))
									objUsuario.setByte_foto(Base64.decode(entidadJSON.getString("personaFoto"), Base64.NO_WRAP | Base64.URL_SAFE));
								objUsuario.setStr_usuario(txtUsuario.getText().toString());
								objUsuario.setStr_clave(txtPassword.getText().toString());

								ActualizarDataHTTP actualizar = new ActualizarDataHTTP();
								actualizar.execute(objUsuario.getInt_id_usuario());
								JSONObject actualizarJSON = new JSONObject(login.get());
								if(actualizarJSON.getInt("rpta")==1) {
									validador = true;
									if (validador) {
										if (!clsPacienteDAO.AgregarLogin(LoginActivity.this, entidadJSON.optString("listPacienteJSON"), true))
											validador = false;
										if (validador) {
											if (!clsPreguntaPacienteDAO.AgregarLogin(LoginActivity.this, entidadJSON.optString("listPreguntaPacienteJSON"), true))
												validador = false;
											if (validador) {
												if (!clsEspecialidadDAO.AgregarLogin(LoginActivity.this, actualizarJSON.optString("listEspecialidadJSON"), true))
													validador = false;
												if (validador) {
													if (!clsEspecialidadDAO.AgregarLogin(LoginActivity.this, actualizarJSON.optString("listDetalleClinicaEspecialidadJSON"), true))
														validador = false;
												}
											}
										}
									}
								}

							}




						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (ExecutionException e) {
							e.printStackTrace();
						} catch (JSONException e) {
							e.printStackTrace();
						}
			  /*


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
                    
			  */

                          handler.sendEmptyMessage(0);
                    }
               }.start();




    }
    
    
        final Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //update UI here depending on what message is received.
            super.handleMessage(msg);
            pd.dismiss();             
          //  Intent i=new Intent(LoginActivity.this,MenuActivity.class);
           // startActivity(i);
       
       
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
