package com.app.med.finder.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;

import com.app.med.finder.conexion.ActualizarDataHTTP;
import com.app.med.finder.conexion.LoginHTTP;
import com.app.med.finder.dao.clsCasosSaludDAO;
import com.app.med.finder.dao.clsCitaPacienteDAO;
import com.app.med.finder.dao.clsClinicaDAO;
import com.app.med.finder.dao.clsClinicaEspecialidadDAO;
import com.app.med.finder.dao.clsClinicaSeguroDAO;
import com.app.med.finder.dao.clsDoctorDAO;
import com.app.med.finder.dao.clsEspecialidadDAO;
import com.app.med.finder.dao.clsPacienteDAO;
import com.app.med.finder.dao.clsPreguntaPacienteDAO;
import com.app.med.finder.dao.clsRespuestaCasosSaludDAO;
import com.app.med.finder.dao.clsRespuestaPreguntaPacienteDAO;
import com.app.med.finder.dao.clsSeguroDAO;
import com.app.med.finder.dao.clsUsuarioDAO;
import com.app.med.finder.entidades.clsUsuario;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends Activity {

    private EditText txtUsuario;
    private EditText txtClave;
    private  ProgressDialog pd ;
    private clsUsuario objUsuario;
    private boolean validador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsuario=(EditText)findViewById(R.id.txtUsuario);
        txtClave=(EditText)findViewById(R.id.txtClave);
        txtUsuario.setText("12345678");
        txtClave.setText("123456");
       // txtClave.setText("");
        //txtUsuario.setText("");
    }
    public void btnRestaurar(View v)
    {



    }

    public void btnAceptar(View v)
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
                    login.execute(txtUsuario.getText().toString(), txtClave.getText().toString());
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
                        objUsuario.setStr_clave(txtClave.getText().toString());

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
                                                                if (!clsSeguroDAO.AgregarLogin(LoginActivity.this, actualizarJSON.optString("listSeguroJSON"), true))
                                                                    validador = false;
                                                                if (validador) {
                                                                    if (!clsCasosSaludDAO.AgregarLogin(LoginActivity.this, actualizarJSON.optString("listCasosSaludJSON"), true))
                                                                        validador = false;
                                                                    if (validador) {
                                                                        if (!clsRespuestaCasosSaludDAO.AgregarLogin(LoginActivity.this, actualizarJSON.optString("listRespuestaCasosSaludJSON"), true))
                                                                            validador = false;
                                                                        if (validador) {
                                                                            if (!clsRespuestaCasosSaludDAO.FavoritoLogin(LoginActivity.this, entidadJSON.optString("listRespuestaCasosSaludVotosJSON")))
                                                                                validador = false;
                                                                            if (validador) {
                                                                                if (!clsCitaPacienteDAO.AgregarLogin(LoginActivity.this, entidadJSON.optString("listCitaPacienteJSON"), true))
                                                                                    validador = false;
                                                                                if (validador) {
                                                                                    if (!clsRespuestaPreguntaPacienteDAO.AgregarLogin(LoginActivity.this, entidadJSON.optString("listRespuestaPreguntaPacienteJSON"), true))
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




                handlerCargar.sendEmptyMessage(0);
            }
        }.start();




    }




    final Handler handlerCargar=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            pd.dismiss();
            if(validador) {
                clsUsuarioDAO.Agregar(LoginActivity.this, objUsuario);
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }

        }
    };




}
