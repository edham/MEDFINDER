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
								JSONObject actualizarJSON = new JSONObject(actualizar.get());
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
													if (!clsClinicaEspecialidadDAO.AgregarLogin(LoginActivity.this, actualizarJSON.optString("listDetalleClinicaEspecialidadJSON"), true))
														validador = false;
													if (validador) {
														if (!clsClinicaDAO.AgregarLogin(LoginActivity.this, actualizarJSON.optString("listClinicaJSON"), true))
															validador = false;
														if (validador) {
															if (!clsClinicaSeguroDAO.AgregarLogin(LoginActivity.this, actualizarJSON.optString("listDetalleClinicaSeguroJSON"), true))
																validador = false;
															if (validador) {
																if (!clsDoctorDAO.AgregarLogin(LoginActivity.this, actualizarJSON.optString("listDoctorJSON"), true))
																	validador = false;
																if (validador) {
																	if (!clsDoctorDAO.FavoritoLogin(LoginActivity.this, entidadJSON.optString("listFavoritosJSON")))
																		validador = false;
																	if (validador) {
																		if (!clsSeguroDAO.AgregarLogin(LoginActivity.this, actualizarJSON.optString("listSeguroJSON"),true))
																			validador = false;
																		if (validador) {
																			if (!clsCasosSaludDAO.AgregarLogin(LoginActivity.this, actualizarJSON.optString("listCasosSaludJSON"),true))
																				validador = false;
																			if (validador) {
																				if (!clsRespuestaCasosSaludDAO.AgregarLogin(LoginActivity.this, actualizarJSON.optString("listRespuestaCasosSaludJSON"),true))
																					validador = false;
																				if (validador) {
																					if (!clsRespuestaCasosSaludDAO.FavoritoLogin(LoginActivity.this, entidadJSON.optString("listRespuestaCasosSaludVotosJSON")))
																						validador = false;
																					if (validador) {
																						if (!clsCitaPacienteDAO.AgregarLogin(LoginActivity.this, entidadJSON.optString("listCitaPacienteJSON"),true))
																							validador = false;
																						if (validador) {
																							if (!clsRespuestaPreguntaPacienteDAO.AgregarLogin(LoginActivity.this, entidadJSON.optString("listRespuestaPreguntaPacienteJSON"),true))
																								validador = false;
																						}
																					}
																				}
																			}
																		}
																	}
																}

															}
														}
													}
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
			if(validador) {
				clsUsuarioDAO.Agregar(LoginActivity.this,objUsuario);
				Intent i = new Intent(LoginActivity.this, MenuActivity.class);
				startActivity(i);
			}
       
       
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
