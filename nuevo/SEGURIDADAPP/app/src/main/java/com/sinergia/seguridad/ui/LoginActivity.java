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
import com.sinergia.seguridad.dao.clsSesionVigilanciaDAO;
import com.sinergia.seguridad.dao.clsVehiculoDAO;
import com.sinergia.seguridad.entidades.clsCuadrante;
import com.sinergia.seguridad.entidades.clsEntidad;
import com.sinergia.seguridad.entidades.clsPersonal;
import com.sinergia.seguridad.entidades.clsSesionVigilancia;
import com.sinergia.seguridad.entidades.clsUsuario;
import com.sinergia.seguridad.entidades.clsVehiculo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends Activity {

    private EditText txtUsuario;
    private EditText txtClave;
    private  ProgressDialog pd ;
    private boolean validador;
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
                    login.execute(loginJson.toString(),"SesionInicioRest");
                    JSONObject entidadJSON = new JSONObject(login.get());
                    if(entidadJSON.getInt("rpta")==1) {

                        clsSesionVigilancia objSesionVigilancia = new clsSesionVigilancia();
                        objSesionVigilancia.setInt_id(entidadJSON.getInt("id"));
                        objSesionVigilancia.setObjCuadrante(new clsCuadrante(entidadJSON.getInt("id_cuadrante")));
                        clsSesionVigilanciaDAO.Agregar(LoginActivity.this, objSesionVigilancia, 1);

                        clsEntidad objEntidad = new clsEntidad();
                        objEntidad.setInt_id(entidadJSON.getInt("entidad_id"));
                        objEntidad.setStr_nombre(entidadJSON.getString("entidad_nombre"));
                        objEntidad.setStr_descripcion(entidadJSON.getString("entidad_descripcion"));
                        objEntidad.setStr_tipo(entidadJSON.getString("entidad_tipo"));
                        clsEntidadDAO.Agregar(LoginActivity.this, objEntidad);

                        JSONObject json_vehiculo = new JSONObject(entidadJSON.getString("json_Vehiculo"));
                        clsVehiculo clsVehiculo = new clsVehiculo();
                        clsVehiculo.setInt_id(json_vehiculo.getInt("id"));
                        clsVehiculo.setInt_numero(json_vehiculo.getInt("numero"));
                        clsVehiculo.setStr_placa(json_vehiculo.getString("placa"));
                        clsVehiculo.setStr_tipo(json_vehiculo.getString("tipo"));
                        clsVehiculo.setStr_marca(json_vehiculo.getString("marca"));
                        clsVehiculo.setStr_clase(json_vehiculo.getString("clase"));
                        clsVehiculo.setInt_idSesion(objSesionVigilancia.getInt_id());
                        clsVehiculoDAO.Agregar(LoginActivity.this, clsVehiculo);

                         validador=clsCuadranteDAO.AgregarLogin(LoginActivity.this,entidadJSON.getString("listaCuandranteJSON"));
                        if(validador)
                        {
                            validador=clsPersonalDAO.AgregarLogin(LoginActivity.this, entidadJSON.getString("listaPersomalJSON"),objSesionVigilancia.getInt_id());

                            if(validador)
                            {
                                validador= clsNegocioDAO.AgregarLogin(LoginActivity.this, entidadJSON.getString("listaNegociosJSON"));
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

               Intent i = new Intent(LoginActivity.this, MainActivity.class);
               startActivity(i);
               finish();
           }

        }
    };




}
