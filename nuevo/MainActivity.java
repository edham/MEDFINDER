package com.sinergia.seguridad.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.sinergia.seguridad.conexion.postDataHTTP;
import com.sinergia.seguridad.dao.clsCuadranteDAO;
import com.sinergia.seguridad.dao.clsEntidadDAO;
import com.sinergia.seguridad.dao.clsPersonalDAO;
import com.sinergia.seguridad.dao.clsSesionVigilanciaDAO;
import com.sinergia.seguridad.dao.clsTrackDAO;
import com.sinergia.seguridad.dao.clsVehiculoDAO;
import com.sinergia.seguridad.entidades.clsSesionVigilancia;
import com.sinergia.seguridad.entidades.clsTrack;
import com.sinergia.seguridad.fragment.*;
import com.sinergia.seguridad.servicio.clsGPSTrackService;
import com.sinergia.seguridad.utilidades.clsUtilidades;
import com.sinergia.seguridad.utilidades.route.Routing;
import com.sinergia.seguridad.utilidades.route.RoutingListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements RoutingListener {


    private ReceiverGPS receiverGPS;
    private IntentFilter filterGPS;

    private boolean zoon=true;
    private int zoonView=18;

    private MenuItem someMenuItem;

    private int Id;
    MapView mapView;
    private GoogleMap googleMap;
    private int color = Color.RED;
    private LatLng puntosGPS;
    private LatLng puntosSeleccion;
    // flag for GPS status

    private TextView lblCuadrante;
    private TextView lblDireccion;
    private PolylineOptions cuadrante;



    private boolean travelMode;


    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    private String drawerTitle;
    private TextView lblUsuario;
    private CircleImageView imgUsuario;
    private clsSesionVigilancia entidad;
    private static int tomarfoto=0;
    private int total=0;
    boolean validador = true;
    private  ProgressDialog pd ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entidad= clsSesionVigilanciaDAO.Buscar(this);

        setToolbar(); // Setear Toolbar como action bar
        lblUsuario = (TextView) findViewById(R.id.lblUsuario);
      //  lblUsuario.setText(entidad.getStr_nombres()+" "+entidad.getStr_apellido_paterno()+" "+entidad.getStr_apellido_materno());

        Intent svc = new Intent(this, clsGPSTrackService.class);
        this.startService(svc);


        imgUsuario = (CircleImageView) findViewById(R.id.imgUsuario);
        /*
        if(entidad.getFoto()!=null)
            imgUsuario.setImageBitmap(clsUtilidades.getBitmap(entidad.getFoto()));}
            */
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
         navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        if (savedInstanceState == null) {
            selectItem(R.id.nav_inicio);
        }

        setTitle(getString(R.string.inicio_item));

        entidad=clsSesionVigilanciaDAO.Buscar(this);
        cuadrante= clsCuadranteDAO.ListarCoordenadasXIdCuadrante(this, entidad.getObjCuadrante().getInt_id());
        cuadrante.color(getResources().getColor(R.color.granate_oscuro));
        cuadrante.width(10);


        lblCuadrante = (TextView) findViewById(R.id.lblCuadrante);
        lblCuadrante.setText(getString(R.string.str_cuadrante) + " " + entidad.getObjCuadrante().getStr_nombre());
        lblDireccion = (TextView) findViewById(R.id.lblDireccion);
        lblDireccion.setText("");
        puntosSeleccion = new LatLng(37.35, -122.0);


        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        // Showing status
        if(status!= ConnectionResult.SUCCESS){ // Google Play Services are not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        }else { // Google Play Services are available

            // Getting reference to the SupportMapFragment of activity_main.xml
            mapView = (MapView) findViewById(R.id.mapview);
            mapView.onCreate(savedInstanceState);

            // Getting GoogleMap object from the fragment
            googleMap = mapView.getMap();


            receiverGPS = new ReceiverGPS();
            filterGPS = new IntentFilter("clsGPSTrackService");
            registerReceiver(receiverGPS, filterGPS);

            // Enabling MyLocation Layer of Google Map
            // googleMap.setMyLocationEnabled(true);
            //  btnMode();
        }
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Marcar item presionado
                        menuItem.setChecked(true);
                        String title = menuItem.getTitle().toString();
                        setTitle(title);

                        selectItem(menuItem.getItemId());
                        return true;
                    }
                }
        );
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void selectItem(int id) {
        Bundle arguments = new Bundle();
        android.support.v7.app.AlertDialog.Builder alert=null;
       switch (id)
        {

            case R.id.nav_inicio:
                setFragment(new DataFragment());

                break;

            case R.id.nav_ayuda:
                break;

            case R.id.nav_cerrar:

                alert = new android.support.v7.app.AlertDialog.Builder(this);
                alert.setTitle(getString(R.string.str_cerrar_sesion));
                alert.setPositiveButton(getString(R.string.btn_aceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        CerrarSesion();
                    }
                });
                alert.setNegativeButton(getString(R.string.btn_cancelar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alert.show();


        }

        drawerLayout.closeDrawers();

    }


    public void setFragment( Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction() .replace(R.id.main_data, fragment).commit();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == tomarfoto && resultCode == RESULT_OK) {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            if (fragments != null) {
            /*    for (Fragment fragment : fragments) {
                    if(fragment instanceof CarNuevoFragment) {
                        ((CarNuevoFragment) fragment).cargarGaleria();
                    }
                    else if(fragment instanceof FichaVariableFragment) {
                        ((FichaVariableFragment) fragment).cargarGaleria();
                    }
                    else if(fragment instanceof FichaEstandarFragment) {
                        ((FichaEstandarFragment) fragment).cargarGaleria();
                    }
                    else if(fragment instanceof SupervisionFragment) {
                        ((SupervisionFragment) fragment).cargarGaleria();
                    }

                }*/
            }

        }

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            AlertDialog.Builder alert;
            Fragment currentFrag =  getSupportFragmentManager().findFragmentById(R.id.main_data);
            if (currentFrag!=null) {
                switch (currentFrag.getClass().getSimpleName())
                {
                    case "InicioFragment":
                    case "EspecialidadesFragment":
                    case "ConsultasFragment":
                    case "CasosSaludFragment":

                        alert = new android.support.v7.app.AlertDialog.Builder(this);
                        alert.setTitle(getString(R.string.str_salir));
                        alert.setPositiveButton(getString(R.string.btn_aceptar), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                MainActivity.this.finish();
                            }
                        });
                        alert.setNegativeButton(getString(R.string.btn_cancelar), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        });
                        alert.show();

                        break;
                    case "ClinicaInfoFragment":
                            setFragment(new DataFragment());
                        break;
                    case "DoctorInfoFragment":
                            setFragment(new DataFragment());
                        break;

                    case "RespuestaConsultaFragment":
                      //  setFragment(new ConsultasFragment());
                        break;

                    case "RespuestaCasosSaludFragment":
                      //  setFragment(new CasosSaludFragment());
                        break;
                    default:
                        break;
                }
            }
            // this.finish();
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }


    public void btnRealizarConsulta(View v)
    {
        clsUtilidades.alert(this, "XD");
    }



    public void CerrarSesion()
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
                    loginJson.put("idSesion",entidad.getInt_id());
                    postDataHTTP login = new postDataHTTP();
                    login.execute(loginJson.toString(),"SesionCerrar");
                    JSONObject entidadJSON = new JSONObject(login.get());
                    if(entidadJSON.getInt("rpta")==1) {
                        validador = true;
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





    }
    final Handler handlerSesion =new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if(validador) {

                List<Fragment> fragments = getSupportFragmentManager().getFragments();
                if (fragments != null) {
                    for (Fragment fragment : fragments) {
                        if(fragment instanceof InicioFragment) {
                            ((InicioFragment) fragment).stoptimertask();
                        }
                    }
                }
                clsSesionVigilanciaDAO.Borrar(MainActivity.this);
                clsTrackDAO.Borrar(MainActivity.this);
                clsVehiculoDAO.Borrar(MainActivity.this);
                clsCuadranteDAO.Borrar(MainActivity.this);
                clsEntidadDAO.Borrar(MainActivity.this);
                clsPersonalDAO.Borrar(MainActivity.this);
                Intent svc = new Intent(MainActivity.this, clsGPSTrackService.class);
                stopService(svc);
                pd.dismiss();
                finish();
            }


        }
    };

    @Override
    public void onRoutingFailure() {

    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(PolylineOptions mPolyOptions) {
        PolylineOptions polyoptions = new PolylineOptions();
        polyoptions.color(color);
        polyoptions.width(5);
        polyoptions.addAll(mPolyOptions.getPoints());
        googleMap.addPolyline(polyoptions);

    }

    public void btnMode(){

        Routing routing = new Routing(Routing.TravelMode.DRIVING);
        if (travelMode) {
            routing = new Routing(Routing.TravelMode.WALKING);
            color = getResources().getColor(R.color.verde);
            //btnMode.setBackgroundResource(R.drawable.driving);
            travelMode=false;
            // lblModo.setText(getString(R.string.str_caminando));
        } else {
            color = getResources().getColor(R.color.rojo);
            // btnMode.setBackgroundResource(R.drawable.walking);
            //  lblModo.setText(getString(R.string.str_conduciendo));
            travelMode=true;
        }

        googleMap.clear();
        routing.registerListener(this);
        routing.execute(puntosGPS, puntosSeleccion);

        BitmapDescriptor bitmapDescriptor= BitmapDescriptorFactory.fromResource(R.drawable.ubicacion);

        googleMap.addMarker(new MarkerOptions().icon(bitmapDescriptor).position(new LatLng(37.35, -122.0)));


    }



    public void getPoint()
    {
        googleMap.clear();

        googleMap.addPolyline(cuadrante);
        googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.icono_gps)).position(puntosGPS));
        if (zoon) {
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(zoonView));
            zoon = false;
        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(puntosGPS));
        if(clsUtilidades.insidePolygon(cuadrante.getPoints(),puntosGPS)) {
            lblCuadrante.setText(getString(R.string.str_cuadrante) + " " + entidad.getObjCuadrante().getStr_nombre());
            lblCuadrante.setTextColor(Color.BLACK);
        }
        else {
            lblCuadrante.setText(getString(R.string.str_cuadrante_fuera) + " " + entidad.getObjCuadrante().getStr_nombre());
            lblCuadrante.setTextColor(Color.RED);
        }
    }


    private class ReceiverGPS extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            double Lat = intent.getDoubleExtra("Lat", 0.0);
            double Long = intent.getDoubleExtra("Long", 0.0);
            String direccion = intent.getStringExtra("direccion");


                puntosGPS= new LatLng(Lat,Long);
                getPoint();
            lblDireccion.setText(direccion);
        }
    }



}
