package com.med.finder.cliente.activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.med.finder.cliente.R;
import com.med.finder.cliente.conexion.ActualizarDataHTTP;
import com.med.finder.cliente.conexion.LoginHTTP;
import com.med.finder.cliente.dao.*;
import com.med.finder.cliente.entidades.*;
import com.med.finder.cliente.utilidades.CustomFontTextView;
import com.med.finder.cliente.utilidades.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class LoginActivity extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtClave;

    private clsUsuario objUsuario;
    public ProgressDialog pd;
    private boolean validador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtClave = (EditText) findViewById(R.id.txtClave);
        Button btnAceptar = (Button) findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAceptar();
            }
        });

        Button btnRegistrarme = (Button) findViewById(R.id.btnRegistrarme);
        btnRegistrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRegistrarme();
            }
        });

        txtUsuario.setText("00000000");
        txtClave.setText("123456");
        CustomFontTextView lblLink = (CustomFontTextView)findViewById(R.id.lblLink);
        lblLink.setText(Html.fromHtml(getString(R.string.web)));
        Linkify.addLinks(lblLink, Linkify.ALL);
        lblLink.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void btnAceptar() {

        if ( Utilidades.checkPermissions(this) && Utilidades.addListaBlanca(this))
        {
           if (txtUsuario.getText().toString().trim().length()>4) {
               pd = new ProgressDialog(this);
               pd.setTitle("Cargando Datos");
               pd.setMessage("Espere un momento");
               pd.setCancelable(false);
               pd.show();
               new Thread() {
                   public void run() {
                       try {
                           validador = false;
                           LoginHTTP login = new LoginHTTP();

                           login.execute(txtUsuario.getText().toString(), txtClave.getText().toString());
                           String result = login.get();
                           if(!result.equals(""))
                           {
                               JSONObject entidadJSON = new JSONObject(result);
                               if (entidadJSON.getInt("rpta") == 1) {
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
                                   if (actualizarJSON.getInt("rpta") == 1) {
                                       validador = true;
                                       if (validador) {
                                           if (!clsPacienteDAO.AgregarLogin(LoginActivity.this, entidadJSON.optString("listPacienteJSON"), true))
                                               validador = false;
                                           if (validador) {
                                               if (!clsPreguntaPacienteDAO.AgregarLogin(LoginActivity.this, actualizarJSON.optString("listPreguntaPacienteJSON"), true))
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
                                                                                       if (!clsRespuestaCasosSaludDAO.VotosLogin(LoginActivity.this,actualizarJSON.optString("listRespuestaCasosSaludVotosJSON")))
                                                                                           validador = false;
                                                                                       if (validador) {
                                                                                           if (!clsCitaPacienteDAO.AgregarLogin(LoginActivity.this, actualizarJSON.optString("listCitaPacienteJSON"), true))
                                                                                               validador = false;
                                                                                           if (validador) {
                                                                                               if (!clsRespuestaPreguntaPacienteDAO.AgregarLogin(LoginActivity.this, actualizarJSON.optString("listRespuestaPreguntaPacienteJSON"), true))
                                                                                                   validador = false;
                                                                                                   if (validador) {
                                                                                                       if (!clsEncuestaDAO.AgregarLogin(LoginActivity.this, entidadJSON.optString("listEncuestaJSON"), true))
                                                                                                           validador = false;///
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

                               }
                           }else
                               pd.dismiss();
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

        }
    }



    final Handler handlerCargar=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();
            if(validador) {
                clsUsuarioDAO.Agregar(LoginActivity.this, objUsuario);
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }else
            {
                Utilidades.alert(LoginActivity.this, getString(R.string.alert_credenciales));
            }
        }
    };

    public void btnRegistrarme()
    {
        Intent i = new Intent(this, RegistroUsuarioActivity.class);
        startActivity(i);
        finish();
    }

}
