package com.app.med.finder.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.app.med.finder.dao.clsClinicaDAO;
import com.app.med.finder.dao.clsClinicaEspecialidadDAO;
import com.app.med.finder.dao.clsSeguroDAO;
import com.app.med.finder.entidades.clsClinica;
import com.app.med.finder.entidades.clsClinicaEspecialidad;
import com.app.med.finder.entidades.clsSeguro;
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
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;


public class ClinicaInfoFragment extends Fragment implements LocationListener,RoutingListener {

    private ListView listViewEspecialidades;
    private List<clsClinicaEspecialidad> listEspecialidades;
    private AdaptadorEspecialidades adaptadorEspecialidades;

    private ListView listViewSeguros;
    private List<clsSeguro> listSeguro;
    private AdaptadorSeguros adaptadorSeguros;

    private TextView lblDatos;
    private View viewDatos;

    private TextView lblUbicacion;
    private View viewUbicacion;

    private TextView lblEspecialidades;
    private View viewEspecialidades;
    private View viewEspecialidadesTotal;

    private TextView lblSeguros;
    private View viewSeguros;
    private View viewSegurosTotal;



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

    private clsClinica entidad = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clinica_info, container, false);

        Id=getArguments().getInt("id");

        entidad= clsClinicaDAO.Buscar(this.getActivity(), Id);
        puntosSeleccion = new LatLng(entidad.getDou_latitud(), entidad.getDou_longitud());
        ImageView image = (ImageView)view.findViewById(R.id.image);
        if(entidad.getByte_logo()!=null)
            image.setImageBitmap(clsUtilidades.getBitmap(entidad.getByte_logo()));

        TextView lblNombre = (TextView)view.findViewById(R.id.lblNombre);
        lblNombre.setText(entidad.getStr_nombre());
//
        TextView lblSlogan = (TextView)view.findViewById(R.id.lblSlogan);
        lblSlogan.setText(entidad.getStr_slogan());

        TextView lblInicio = (TextView)view.findViewById(R.id.lblInicio);
        lblInicio.setText(clsUtilidades.hora.format(entidad.getDat_hora_inicio()));

        TextView lblFin = (TextView)view.findViewById(R.id.lblFin);
        lblFin.setText(clsUtilidades.hora.format(entidad.getDat_hora_fin()));

        TextView lblTelefono = (TextView)view.findViewById(R.id.lblTelefono);
        lblTelefono.setText(entidad.getStr_telefono());

        TextView lblDireccion = (TextView)view.findViewById(R.id.lblDireccion);
        lblDireccion.setText(entidad.getStr_direccion());

        TextView lblAtencion = (TextView)view.findViewById(R.id.lblAtencion);
        lblAtencion.setText(entidad.getStr_detalle_atencion());

        TextView lblDetalle = (TextView)view.findViewById(R.id.lblDetalle);
        lblDetalle.setText(entidad.getStr_descripcion());


        listViewEspecialidades = (ListView) view.findViewById(R.id.listViewEspecialidades);
        listViewSeguros = (ListView)view.findViewById(R.id.listViewSeguros);
        Buscar(Id);



        lblModo = (TextView)view.findViewById(R.id.lblModo);
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

        lblEspecialidades=(TextView)view.findViewById(R.id.lblEspecialidades);
        lblEspecialidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblEspecialidades();
            }
        });
        viewEspecialidades=(View)view.findViewById(R.id.viewEspecialidades);
        viewEspecialidadesTotal=(View)view.findViewById(R.id.viewEspecialidadesTotal);

        lblSeguros=(TextView)view.findViewById(R.id.lblSeguros);
        lblSeguros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblSeguros();
            }
        });
        viewSeguros=(View)view.findViewById(R.id.viewSeguros);
        viewSegurosTotal=(View)view.findViewById(R.id.viewSegurosTotal);

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

    public void lblSeguros()
    {
        if(viewSeguros.getVisibility()==View.GONE)
        {
            viewSeguros.setVisibility(View.VISIBLE);
            lblSeguros.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.open), null);
        }
        else
        {
            viewSeguros.setVisibility(View.GONE);
            lblSeguros.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.closed), null);
        }
    }
    public void lblEspecialidades()
    {
        if(viewEspecialidades.getVisibility()==View.GONE)
        {
            viewEspecialidades.setVisibility(View.VISIBLE);
            lblEspecialidades.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.open), null);
        }
        else
        {
            viewEspecialidades.setVisibility(View.GONE);
            lblEspecialidades.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.closed), null);
        }
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

        googleMap.addMarker(new MarkerOptions().icon(bitmapDescriptor).position(new LatLng(entidad.getDou_latitud(), entidad.getDou_longitud())));


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

    public void Buscar(int id)
    {


        listEspecialidades= clsClinicaEspecialidadDAO.BuscarXClinica(this.getActivity(), id);

        if(listEspecialidades!=null && listEspecialidades.size()>0)
        {
            adaptadorEspecialidades = new AdaptadorEspecialidades(this.getActivity());
            listViewEspecialidades.setAdapter(adaptadorEspecialidades);
            clsUtilidades.setListViewHeightBasedOnChildren(listViewEspecialidades);

        }
        else
            viewEspecialidadesTotal.setVisibility(View.GONE);


        listSeguro= clsSeguroDAO.ListarXClinica(this.getActivity(), id);

        if(listSeguro!=null && listSeguro.size()>0)
        {
            adaptadorSeguros = new AdaptadorSeguros(this.getActivity());
            listViewSeguros.setAdapter(adaptadorSeguros);
            clsUtilidades.setListViewHeightBasedOnChildren(listViewSeguros);

        }
        else
            viewSegurosTotal.setVisibility(View.GONE);
    }


    class AdaptadorEspecialidades extends ArrayAdapter {

        Activity context;

        AdaptadorEspecialidades(Activity context) {
            super(context, R.layout.list_especialidades, listEspecialidades);
            this.context = context;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_especialidades, null);
//
            TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
            lblNombre.setText(listEspecialidades.get(position).getObjEspecialidad().getStr_nombre());
//
            TextView lblDetalle = (TextView)item.findViewById(R.id.lblDetalle);
            lblDetalle.setText(listEspecialidades.get(position).getObjEspecialidad().getStr_descripcion());

            TextView lblInicio = (TextView)item.findViewById(R.id.lblInicio);
            lblInicio.setText(clsUtilidades.hora.format(listEspecialidades.get(position).getDat_hora_inicio()));

            TextView lblFin = (TextView)item.findViewById(R.id.lblFin);
            lblFin.setText(clsUtilidades.hora.format(listEspecialidades.get(position).getDat_hora_fin()));

            TextView lblAtencion = (TextView)item.findViewById(R.id.lblAtencion);
            lblAtencion.setText(listEspecialidades.get(position).getStr_detalle_horario());
//

            return(item);
        }
    }

    class AdaptadorSeguros extends ArrayAdapter {

        Activity context;

        AdaptadorSeguros(Activity context) {
            super(context, R.layout.list_seguros, listSeguro);
            this.context = context;
        }


        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.list_seguros, null);

            TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
            lblNombre.setText(listSeguro.get(position).getStr_nombre());

            ImageView image = (ImageView)item.findViewById(R.id.image);

            if(listSeguro.get(position).getByte_logo()!=null)
                image.setImageBitmap(clsUtilidades.getBitmap(listSeguro.get(position).getByte_logo()));


            return(item);
        }
    }


}
