package com.app.med.finder.fragment;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.app.med.finder.dao.clsDoctorDAO;
import com.app.med.finder.dao.clsEspecialidadDAO;
import com.app.med.finder.entidades.clsDoctor;
import com.app.med.finder.ui.R;
import com.app.med.finder.utilidades.clsUtilidades;
import com.app.med.finder.utilidades.route.Routing;
import com.app.med.finder.utilidades.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import static android.content.Context.LOCATION_SERVICE;


public class DoctorInfoFragment extends Fragment implements LocationListener,RoutingListener {

    private MenuItem someMenuItem;
    private TextView lblDatos;
    private View viewDatos;

    private TextView lblUbicacion;
    private View viewUbicacion;


    private clsDoctor objDoctor = null;
    private int Id;
    MapView mapView;
    private GoogleMap googleMap;
    private int color = Color.RED;
    private LatLng puntosGPS;
    private LatLng puntosSeleccion;
    // flag for GPS status
    boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;

    // flag for GPS status
    boolean canGetLocation = false;
    Location location; // location

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 10; // 1 minute

    // Declaring a Location Manager
    protected LocationManager locationManager;

    private Button btnMode;
    private boolean travelMode;

    private TextView lblModo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_info, container, false);

        Id=getArguments().getInt("id");

        objDoctor= clsDoctorDAO.Buscar(this.getActivity(), Id);
        puntosSeleccion = new LatLng(objDoctor.getDou_latitud(), objDoctor.getDou_longitud());
        ImageView image = (ImageView)view.findViewById(R.id.image);
        if(objDoctor.getByte_foto()!=null)
            image.setImageBitmap(clsUtilidades.getBitmap(objDoctor.getByte_foto()));

        lblModo = (TextView)view.findViewById(R.id.lblModo);
        TextView lblNombre = (TextView)view.findViewById(R.id.lblNombre);
        lblNombre.setText(objDoctor.getStr_apellido_paterno()+" "+objDoctor.getStr_apellido_materno()+" "+objDoctor.getStr_nombres());

        TextView lblColegiatura = (TextView)view.findViewById(R.id.lblColegiatura);
        lblColegiatura.setText(objDoctor.getStr_codigo_colegiatura());

        TextView lblDNI = (TextView)view.findViewById(R.id.lblDNI);
        lblDNI.setText(objDoctor.getStr_dni());

        TextView lblTelefono = (TextView)view.findViewById(R.id.lblTelefono);
        lblTelefono.setText(objDoctor.getStr_telefono());

        TextView lblDireccion = (TextView)view.findViewById(R.id.lblDireccion);
        lblDireccion.setText(objDoctor.getStr_direccion());

        TextView lblDetalle = (TextView)view.findViewById(R.id.lblDetalle);
        lblDetalle.setText(objDoctor.getStr_direccion_detalle());


        RatingBar rtbPuntos = (RatingBar)view.findViewById(R.id.rtbPuntos);
        rtbPuntos.setRating(objDoctor.getInt_puntuje());



        TextView lblEspecializacion = (TextView)view.findViewById(R.id.lblEspecializacion);
        lblEspecializacion.setText(clsEspecialidadDAO.Buscar(this.getActivity(), objDoctor.getObjEspecialidad().getInt_id_especialidad()).getStr_nombre());

        lblDatos=(TextView)view.findViewById(R.id.lblDatos);
        lblDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblDatos();
            }
        });
        viewDatos=(View)view.findViewById(R.id.viewDatos);

        lblUbicacion=(TextView)view.findViewById(R.id.lblUbicacion);
        lblUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblUbicacion();
            }
        });
        viewUbicacion=(View)view.findViewById(R.id.viewUbicacion);

        btnMode=(Button)view.findViewById(R.id.btnMode);
        btnMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMode();
            }
        });

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
            googleMap.setMyLocationEnabled(true);
            getLocation();
            btnMode();
        }
        setHasOptionsMenu(true);

/*
        if(objDoctor.isBol_favorito())
        {
            btn_Favorito.setBackgroundResource(R.drawable.favorito_on);

            titulo="Quitar de Favoritos";
        }
        else
        {
            btn_Favorito.setBackgroundResource(R.drawable.favorito_off);
            titulo="Agregar a Favoritos";
        }*/
        return view;
    }
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if(locationManager!=null)
            locationManager.removeUpdates(this);
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public void lblUbicacion()
    {
        if(viewUbicacion.getVisibility()==View.GONE)
        {
            viewUbicacion.setVisibility(View.VISIBLE);
            lblUbicacion.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.open), null);
        }
        else
        {
            viewUbicacion.setVisibility(View.GONE);
            lblUbicacion.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.closed), null);
        }
    }

    public void lblDatos()
    {
        if(viewDatos.getVisibility()==View.GONE)
        {
            viewDatos.setVisibility(View.VISIBLE);
            lblDatos.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.open), null);
        }
        else
        {
            viewDatos.setVisibility(View.GONE);
            lblDatos.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.closed), null);
        }
    }
    public void btnMode(){

        Routing routing = new Routing(Routing.TravelMode.DRIVING);
        if (travelMode) {
            routing = new Routing(Routing.TravelMode.WALKING);
            color = getResources().getColor(R.color.verde);
            btnMode.setBackgroundResource(R.drawable.driving);
            travelMode=false;
            lblModo.setText(getString(R.string.str_caminando));
        } else {
            color = getResources().getColor(R.color.rojo);
            btnMode.setBackgroundResource(R.drawable.walking);
            lblModo.setText(getString(R.string.str_conduciendo));
            travelMode=true;
        }

        googleMap.clear();
        routing.registerListener(this);
        routing.execute(puntosGPS, puntosSeleccion);

        BitmapDescriptor bitmapDescriptor= BitmapDescriptorFactory.fromResource(R.drawable.ubicacion);

        googleMap.addMarker(new MarkerOptions().icon(bitmapDescriptor).position(new LatLng(objDoctor.getDou_latitud(), objDoctor.getDou_longitud())));


    }
    public void getLocation() {
        try {
            locationManager = (LocationManager)this.getActivity().getSystemService(LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
                Intent viewIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(viewIntent);
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            onLocationChanged(location);
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                onLocationChanged(location);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        puntosGPS = new LatLng(location.getLatitude(), location.getLongitude());
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(puntosGPS));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(puntosGPS);
        builder.include(puntosSeleccion);
        LatLngBounds bounds = builder.build();
        int padding = 100;
        if(viewUbicacion.getVisibility()==View.VISIBLE)
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

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
        inflater.inflate(R.menu.doctor, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        android.support.v7.app.AlertDialog.Builder alert=null;
        switch (item.getItemId()) {
            case R.id.action_citas:
                // do s.th.
                alert = new android.support.v7.app.AlertDialog.Builder(this.getActivity());
                alert.setTitle(getString(R.string.str_solicitar_cita));
                alert.setPositiveButton(getString(R.string.btn_aceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {


                    }
                });
                alert.setNegativeButton(getString(R.string.btn_cancelar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alert.show();

                return true;
            case R.id.action_favoritos:
                alert = new android.support.v7.app.AlertDialog.Builder(this.getActivity());
                alert.setTitle(getString(R.string.str_favorito_on));
                if(objDoctor.isBol_favorito())
                    alert.setTitle(getString(R.string.str_favorito_off));
                alert.setPositiveButton(getString(R.string.btn_aceptar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if(objDoctor.isBol_favorito()) {
                            someMenuItem.setIcon(R.drawable.action_favorito_off);
                            objDoctor.setBol_favorito(false);
                        }
                        else {
                            someMenuItem.setIcon(R.drawable.action_favorito_on);
                            objDoctor.setBol_favorito(true);
                        }
                    }
                });
                alert.setNegativeButton(getString(R.string.btn_cancelar), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });
                alert.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        someMenuItem = menu.findItem(R.id.action_favoritos);
        if(objDoctor.isBol_favorito())
            someMenuItem.setIcon(R.drawable.action_favorito_on);
        else
            someMenuItem.setIcon(R.drawable.action_favorito_off);
    }
}
