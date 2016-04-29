package com.sinergia.seguridad.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sinergia.seguridad.conexion.postDataHTTP;
import com.sinergia.seguridad.dao.clsCuadranteDAO;
import com.sinergia.seguridad.dao.clsEntidadDAO;
import com.sinergia.seguridad.dao.clsNegocioDAO;
import com.sinergia.seguridad.dao.clsPersonalDAO;
import com.sinergia.seguridad.entidades.clsEntidad;
import com.sinergia.seguridad.entidades.clsPersonal;
import com.sinergia.seguridad.entidades.clsUsuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends Activity {

    private EditText txtUsuario;
    private EditText txtClave;
    private  ProgressDialog pd ;
    private boolean validador;
    private  clsPersonal objPersonal;
    private  clsEntidad objEntidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsuario=(EditText)findViewById(R.id.txtUsuario);
        txtClave=(EditText)findViewById(R.id.txtClave);
        //txtUsuario.setText("edham");
        //txtClave.setText("123456");
        txtClave.setText("");
        txtUsuario.setText("");

        clsPersonalDAO.Borrar(LoginActivity.this);
        clsEntidadDAO.Borrar(LoginActivity.this);
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
                    JSONObject loginJson = new JSONObject();
                    loginJson.put("usuario",txtUsuario.getText().toString());
                    loginJson.put("clave",txtClave.getText().toString());

                    postDataHTTP login = new postDataHTTP();
                    login.execute(loginJson.toString(),"PersonalRest");
                    JSONObject entidadJSON = new JSONObject(login.get());
                    if(entidadJSON.getInt("rpta")==1) {
                        objPersonal = new clsPersonal();
                        objPersonal.setBool_principal(true);
                        objPersonal.setInt_id(entidadJSON.getInt("id"));
                        objPersonal.setStr_nombres(entidadJSON.getString("nombres"));
                        objPersonal.setStr_ape_paterno(entidadJSON.getString("ape_paterno"));
                        objPersonal.setStr_ape_materno(entidadJSON.getString("ape_materno"));
                        objPersonal.setStr_telefono(entidadJSON.getString("telefono"));
                        objPersonal.setStr_cargo(entidadJSON.getString("cargo"));
                        objPersonal.setStr_clave(txtClave.getText().toString());
                        objPersonal.setStr_usuario(txtUsuario.getText().toString());
                        objPersonal.setBool_principal(true);

                        objEntidad = new clsEntidad();
                        objEntidad.setInt_id(entidadJSON.getInt("entidad_id"));
                        objEntidad.setStr_nombre(entidadJSON.getString("entidad_nombre"));
                        objEntidad.setStr_descripcion(entidadJSON.getString("entidad_descripcion"));
                        objEntidad.setStr_tipo(entidadJSON.getString("entidad_tipo"));

                         validador=clsCuadranteDAO.AgregarLogin(LoginActivity.this,entidadJSON.getString("listaJSON"));



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
                clsPersonalDAO.Agregar(LoginActivity.this, objPersonal);
                clsEntidadDAO.Agregar(LoginActivity.this, objEntidad);
                Intent i = new Intent(LoginActivity.this, SesionActivity.class);
                startActivity(i);
               finish();
           }

        }
    };




}
