package com.med.finder.doctor.activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.med.finder.doctor.R;
import com.med.finder.doctor.conexion.LoginHTTP;
import com.med.finder.doctor.dao.clsCasosSaludDAO;
import com.med.finder.doctor.dao.clsCitaPacienteDAO;
import com.med.finder.doctor.dao.clsDoctorDAO;
import com.med.finder.doctor.dao.clsEspecialidadDAO;
import com.med.finder.doctor.dao.clsPacienteDAO;
import com.med.finder.doctor.dao.clsPreguntaPacienteDAO;
import com.med.finder.doctor.dao.clsRespuestaCasosSaludDAO;
import com.med.finder.doctor.dao.clsRespuestaPreguntaPacienteDAO;
import com.med.finder.doctor.entidades.*;
import com.med.finder.doctor.utilidades.CustomFontTextView;
import com.med.finder.doctor.utilidades.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class LoginActivity extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtClave;

    private clsDoctor objDoctor;
    public ProgressDialog pd;
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
        });;

        txtUsuario.setText("22222222");
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
                       Message message = handlerCargar.obtainMessage();
                       Bundle bundle = new Bundle();
                       boolean validador = true;
                       String doctor="";
                       int rpta=0;
                       try {

                           LoginHTTP login = new LoginHTTP();
                           login.execute(txtUsuario.getText().toString(), txtClave.getText().toString());
                           String result=login.get();
                           if(result!=null && result!="") {
                               JSONObject entidadJSON = new JSONObject(result);
                               if (entidadJSON.getInt("rpta") == 1) {
                                   rpta=1;
                                   doctor=entidadJSON.getString("doctorJSON");
                                   if (validador) {
                                       if (!clsPreguntaPacienteDAO.AgregarLogin(LoginActivity.this, entidadJSON.optString("listPreguntaPacienteJSON"), true))
                                           validador = false;
                                       if (validador) {
                                           if (!clsCasosSaludDAO.AgregarLogin(LoginActivity.this, entidadJSON.optString("listCasosSaludJSON"), true))
                                               validador = false;
                                           if (validador) {
                                               if (!clsRespuestaCasosSaludDAO.AgregarLogin(LoginActivity.this,entidadJSON.optString("listRespuestaCasosSaludJSON"), true))
                                                   validador = false;
                                               if (validador) {
                                                   if (!clsPacienteDAO.AgregarLogin(LoginActivity.this, entidadJSON.optString("listPacienteJSON"), true))
                                                       validador = false;
                                                   if (validador) {
                                                       if (!clsCitaPacienteDAO.AgregarLogin(LoginActivity.this, entidadJSON.optString("listCitaPacienteJSON"), true))
                                                           validador = false;
                                                       if (validador) {
                                                           if (!clsRespuestaPreguntaPacienteDAO.AgregarLogin(LoginActivity.this, entidadJSON.optString("listRespuestaPreguntaPacienteJSON"), true))
                                                               validador = false;
                                                           if (validador) {
                                                               if (!clsEspecialidadDAO.AgregarLogin(LoginActivity.this, entidadJSON.optString("listEspecialidadJSON"), true)) {
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
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       } catch (ExecutionException e) {
                           e.printStackTrace();
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                       if(!validador)
                       {
                           rpta=-1;
                       }
                       bundle.putInt("rpta",rpta);
                       bundle.putString("doctor",doctor);

                       message.setData(bundle);
                       handlerCargar.sendMessage(message);
                   }
               }.start();
           }

        }
    }



    final Handler handlerCargar=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();
            Bundle bundle = msg.getData();
            if(bundle.getInt("rpta")==1){
                if(clsDoctorDAO.AgregarLogin(LoginActivity.this,bundle.getString("doctor")))
                {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("dato", 0);
                    startActivity(i);
                }else
                {
                    Utilidades.alert(LoginActivity.this, getString(R.string.str_error_registrar));
                }

            }else
            {
                Utilidades.alert(LoginActivity.this, getString(R.string.alert_credenciales));
            }
        }
    };


}
