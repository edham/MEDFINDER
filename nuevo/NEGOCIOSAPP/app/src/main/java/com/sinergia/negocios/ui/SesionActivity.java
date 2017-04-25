package com.sinergia.negocios.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sinergia.negocios.conexion.postDataHTTP;
import com.sinergia.negocios.dao.clsCuadranteDAO;
import com.sinergia.negocios.dao.clsPersonalDAO;
import com.sinergia.negocios.dao.clsSesionVigilanciaDAO;
import com.sinergia.negocios.dao.clsVehiculoDAO;
import com.sinergia.negocios.entidades.clsCuadrante;
import com.sinergia.negocios.entidades.clsSesionVigilancia;
import com.sinergia.negocios.entidades.clsVehiculo;
import com.sinergia.negocios.utilidades.clsUtilidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SesionActivity extends Activity {

    private List<clsCuadrante> listCuandrante;
    private ListView lvCaundrante;
    private ListView lvVehiculos;
    private clsSesionVigilancia objSesionVigilancia;
    private AdaptadorCuandrante adaptadorCuandrante;
    private AdaptadorVehiculo adaptadorVehiculo;
    private  ProgressDialog pd ;
    private List<clsVehiculo> listVehiculos;
    private TextView lblVehiculos;
    private TextView lblCuadrantes;
    private clsCuadrante objCuadrante=null;
    private boolean validador;
    private clsVehiculo objVehiculo=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);

        lblCuadrantes=(TextView)findViewById(R.id.lblCuadrantes);
        lblVehiculos=(TextView)findViewById(R.id.lblVehiculos);

        lvCaundrante=(ListView)findViewById(R.id.lvCaundrante);

        lvVehiculos=(ListView)findViewById(R.id.lvVehiculos);

        listCuandrante= clsCuadranteDAO.ListarCuarantes(this);

        adaptadorCuandrante = new AdaptadorCuandrante(this);

        lvCaundrante.setAdapter(adaptadorCuandrante);
        lvCaundrante.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                lblCuadrantes.setText(getString(R.string.str_seleccione_cuadrante)+": "+listCuandrante.get(position).getStr_nombre());
                objCuadrante=listCuandrante.get(position);

            }
        });
        cargarVehiculos();
    }


    public void btnAceptar(View v)
    {
        if(objCuadrante!=null) {
            if(objVehiculo!=null) {
                pd = new ProgressDialog(this);
                pd.setTitle("Cargando Datos");
                pd.setMessage("Espere un momento");
                pd.show();
                new Thread() {
                    public void run() {
                        try {
                            validador = false;
                            JSONObject loginJson = new JSONObject();
                            loginJson.put("idVehiculo",objVehiculo.getInt_id());
                            loginJson.put("idUsuario", clsPersonalDAO.Buscar(SesionActivity.this).getInt_id());
                            loginJson.put("idCuadrante",objCuadrante.getInt_id());


                            postDataHTTP login = new postDataHTTP();
                            login.execute(loginJson.toString(),"SesionVigilanciaRest");
                            JSONObject entidadJSON = new JSONObject(login.get());
                            if(entidadJSON.getInt("rpta")==1) {
                                validador = true;
                                objSesionVigilancia = new clsSesionVigilancia();
                                objSesionVigilancia.setInt_id(entidadJSON.getInt("id"));
                                objSesionVigilancia.setObjCuadrante(objCuadrante);
                            }



                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                        handlerSesion.sendEmptyMessage(0);
                    }
                }.start();

            }else
            {
                clsUtilidades.alert(this,getString(R.string.str_seleccione_unidad_vigilancia));
            }
        }else
        {
            clsUtilidades.alert(this,getString(R.string.str_seleccione_cuadrante));
        }




    }



    class AdaptadorCuandrante extends ArrayAdapter {

        Activity context;
        AdaptadorCuandrante(Activity context) {
            super(context, R.layout.list_cuadrantes, listCuandrante);
            this.context = context;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_cuadrantes, null);

            TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
            lblNombre.setText(listCuandrante.get(position).getStr_nombre());


            TextView lblDescripcion = (TextView)item.findViewById(R.id.lblDescripcion);
            lblDescripcion.setText(listCuandrante.get(position).getStr_descripcion());



            return(item);
        }
    }

    class AdaptadorVehiculo extends ArrayAdapter {

        Activity context;
        AdaptadorVehiculo(Activity context) {
            super(context, R.layout.list_vehiculos, listVehiculos);
            this.context = context;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_vehiculos, null);

            TextView lblNumero = (TextView)item.findViewById(R.id.lblNumero);
            lblNumero.setText(""+listVehiculos.get(position).getInt_numero());

            TextView lblPlaca = (TextView)item.findViewById(R.id.lblPlaca);
            lblPlaca.setText(listVehiculos.get(position).getStr_placa());

            TextView lblTipo = (TextView)item.findViewById(R.id.lblTipo);
            lblTipo.setText(listVehiculos.get(position).getStr_tipo());

            TextView lblMarca = (TextView)item.findViewById(R.id.lblMarca);
            lblMarca.setText(listVehiculos.get(position).getStr_marca());

            TextView lblClase = (TextView)item.findViewById(R.id.lblClase);
            lblClase.setText(listVehiculos.get(position).getStr_clase());



            return(item);
        }
    }



    public void cargarVehiculos()
    {

                pd = new ProgressDialog(this);
                pd.setTitle("Cargando Datos");
                pd.setMessage("Espere un momento");
                pd.show();
                new Thread() {
                    public void run() {
                        try {

                            postDataHTTP login = new postDataHTTP();
                            login.execute("", "VehiculoVigilanciaRest");
                            JSONObject entidadJSON = new JSONObject(login.get());
                            if (entidadJSON.getInt("rpta") == 1) {
                                listVehiculos = new ArrayList<clsVehiculo>();
                                JSONArray listCoordenadaJSON = new JSONArray(entidadJSON.getString("listaJSON"));
                                for (int j = 0; j < listCoordenadaJSON.length(); j++) {
                                    JSONObject json_vehiculo = listCoordenadaJSON.getJSONObject(j);
                                    clsVehiculo clsVehiculo = new clsVehiculo();
                                    clsVehiculo.setInt_id(json_vehiculo.getInt("id"));
                                    clsVehiculo.setInt_numero(json_vehiculo.getInt("numero"));
                                    clsVehiculo.setStr_placa(json_vehiculo.getString("placa"));
                                    clsVehiculo.setStr_tipo(json_vehiculo.getString("tipo"));
                                    clsVehiculo.setStr_marca(json_vehiculo.getString("marca"));
                                    clsVehiculo.setStr_clase(json_vehiculo.getString("clase"));
                                    listVehiculos.add(clsVehiculo);
                                }

                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        handlerCargarVehiculos.sendEmptyMessage(0);
                    }
                }.start();


    }


    final Handler handlerSesion =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            clsSesionVigilanciaDAO.Agregar(SesionActivity.this,objSesionVigilancia);
            clsVehiculoDAO.Agregar(SesionActivity.this,objVehiculo);
            pd.dismiss();
            Intent i = new Intent(SesionActivity.this, MainActivity.class);
            startActivity(i);
            finish();

        }
    };


    final Handler handlerCargarVehiculos =new Handler(){
        @Override
        public void handleMessage(Message msg) {

            pd.dismiss();

            adaptadorVehiculo = new AdaptadorVehiculo(SesionActivity.this);

            lvVehiculos.setAdapter(adaptadorVehiculo);

            lvVehiculos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    lblVehiculos.setText(getString(R.string.str_seleccione_unidad_vigilancia) + ": " + listVehiculos.get(position).getInt_numero());
                    objVehiculo=listVehiculos.get(position);
                }
            });

        }
    };

}
