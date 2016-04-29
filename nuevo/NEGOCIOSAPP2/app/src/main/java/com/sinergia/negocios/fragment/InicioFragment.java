package com.sinergia.seguridad.fragment;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.sinergia.seguridad.dao.clsCuadranteDAO;
import com.sinergia.seguridad.dao.clsSesionVigilanciaDAO;
import com.sinergia.seguridad.dao.clsTrackDAO;
import com.sinergia.seguridad.entidades.clsSesionVigilancia;
import com.sinergia.seguridad.entidades.clsTrack;
import com.sinergia.seguridad.ui.R;
import com.sinergia.seguridad.utilidades.clsUtilidades;
import com.sinergia.seguridad.utilidades.route.Routing;
import com.sinergia.seguridad.utilidades.route.RoutingListener;

import java.util.Timer;
import java.util.TimerTask;


public class InicioFragment extends Fragment implements RoutingListener {


    private Timer timer;
    private TimerTask timerTask;
    final Handler handler = new Handler();
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
    private PolylineOptions cuadrante;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 10; // 1 minute

    // Declaring a Location Manager
    protected LocationManager locationManager;

    private clsSesionVigilancia entidad;
    private boolean travelMode;

    //private TextView lblModo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        //Id=getArguments().getInt("id");
        entidad=clsSesionVigilanciaDAO.Buscar(this.getActivity());
        cuadrante= clsCuadranteDAO.ListarCoordenadasXIdCuadrante(this.getActivity(), entidad.getObjCuadrante().getInt_id());
        cuadrante.color(getResources().getColor(R.color.granate_oscuro));
        cuadrante.width(10);


        lblCuadrante = (TextView) view.findViewById(R.id.lblCuadrante);
        lblCuadrante.setText(getString(R.string.str_cuadrante)+" "+entidad.getObjCuadrante().getStr_nombre());


        puntosSeleccion = new LatLng(37.35, -122.0);


        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.getActivity());

        // Showing status
        if(status!= ConnectionResult.SUCCESS){ // Google Play Services are not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this.getActivity(), requestCode);
            dialog.show();

        }else { // Google Play Services are available

            // Getting reference to the SupportMapFragment of activity_main.xml
            mapView = (MapView) view.findViewById(R.id.mapview);
            mapView.onCreate(savedInstanceState);

            // Getting GoogleMap object from the fragment
            googleMap = mapView.getMap();




            // Enabling MyLocation Layer of Google Map
           // googleMap.setMyLocationEnabled(true);
          //  btnMode();
        }
        setHasOptionsMenu(true);

        return view;
    }
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
        startTimer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        stoptimertask();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        inflater.inflate(R.menu.inicio, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        android.support.v7.app.AlertDialog.Builder alert=null;
        switch (item.getItemId()) {
            case R.id.action_mas:
                if(zoonView>0)
                    zoonView--;
                googleMap.moveCamera(CameraUpdateFactory.zoomTo(zoonView));
                return true;
            case R.id.action_menos:
                if(zoonView<20)
                    zoonView++;
                googleMap.moveCamera(CameraUpdateFactory.zoomTo(zoonView));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        someMenuItem = menu.findItem(R.id.action_mas);
       /* if(objDoctor.isBol_favorito())
            someMenuItem.setIcon(R.drawable.action_favorito_on);
        else
            someMenuItem.setIcon(R.drawable.action_favorito_off);*/
    }

    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 2000, 10000); //
    }

    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        //get the current timeStamp
                        clsTrack entidad = clsTrackDAO.getTrack(InicioFragment.this.getActivity());
                        if(entidad!=null) {
                            puntosGPS= new LatLng(entidad.getDou_latitud(), entidad.getDou_longitud());
                            getPoint();
                        }
                    }
                });
            }
        };
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

}
