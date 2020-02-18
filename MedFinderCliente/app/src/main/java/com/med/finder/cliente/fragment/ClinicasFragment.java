package com.med.finder.cliente.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.med.finder.cliente.R;
import com.med.finder.cliente.activity.MainActivity;
import com.med.finder.cliente.dao.clsClinicaDAO;
import com.med.finder.cliente.dao.clsEspecialidadDAO;
import com.med.finder.cliente.entidades.clsClinica;
import com.med.finder.cliente.entidades.clsEspecialidad;
import com.med.finder.cliente.utilidades.CustomFontTextView;
import com.med.finder.cliente.utilidades.Utilidades;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;


public class ClinicasFragment extends Fragment implements LocationListener,GoogleMap.OnMarkerClickListener{

    private Spinner ComboEspecialidad;
    private List<clsEspecialidad> listEspecialidad;
    private int idEspecialidad=0;

    private  int distancia=0;
    MapView mapView;
    private GoogleMap googleMap;
    private LatLng puntosGPS;
    // flag for GPS status
    boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;

    // flag for GPS status
    boolean canGetLocation = false;
    private boolean zoon=true;
    Location location; // location

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 20; // 1 minute

    // Declaring a Location Manager
    protected LocationManager locationManager;

    private List<clsClinica> itens;

    private Button btnOrdenar;

    private CustomFontTextView lblTotal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clinicas, container, false);


        btnOrdenar = (Button) view.findViewById(R.id.btnOrdenar);
        btnOrdenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOrdenar();
            }
        });

        lblTotal = (CustomFontTextView)view.findViewById(R.id.lblTotal);

        ComboEspecialidad = (Spinner)view.findViewById(R.id.ComboEspecialidad);
        EspecialidadDDL();


        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(ClinicasFragment.this.getActivity());
        if(status!= ConnectionResult.SUCCESS){ // Google Play Services are not available
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, ClinicasFragment.this.getActivity(), requestCode);
            dialog.show();
        }else {
            mapView = (MapView) view.findViewById(R.id.mapview);
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(new OnMapReadyCallback() {
                @SuppressLint("MissingPermission")
                @Override
                public void onMapReady(GoogleMap mMap) {
                    googleMap = mMap;
                    googleMap.getUiSettings().setZoomControlsEnabled(true);
                    googleMap.setMyLocationEnabled(true);
                    googleMap.setOnMarkerClickListener(ClinicasFragment.this);
                    getLocation();
                    addMaker();
                }
            });
        }
        ((MainActivity) getActivity()).verTitulo(3);
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
    @SuppressLint("MissingPermission")
    public void getLocation() {
        try {
            locationManager = (LocationManager)ClinicasFragment.this.getActivity().getSystemService(LOCATION_SERVICE);

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
        if(zoon)
        {

            puntosGPS = new LatLng(location.getLatitude(), location.getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(puntosGPS));
            zoon=false;

        }
    }

    @Override
    public void onProviderDisabled(String provider)
    {
        // TODO Auto-generated method stub
        Toast.makeText(this.getActivity(), "provider disabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider)
    {
        // TODO Auto-generated method stub
        Toast.makeText(this.getActivity(), "provider enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }
    public void addMaker()
    {
        googleMap.clear();
        List<clsClinica> lista=clsClinicaDAO.Listar(this.getContext(),idEspecialidad);
       itens=Utilidades.getDistanciaMapa(location,distancia,lista);

        if(itens!=null) {
            lblTotal.setText(getString(R.string.nav_clinicas)+" : "+itens.size());
            for (int i = 0; i < itens.size(); i++) {

                BitmapDescriptor bitmapDescriptor=BitmapDescriptorFactory.fromResource(R.drawable.ic_ubicacion);
                googleMap.addMarker(new MarkerOptions().icon(bitmapDescriptor).snippet("" + i).position(new LatLng(itens.get(i).getDou_latitud(), itens.get(i).getDou_longitud())));
            }
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        final int posicion = Integer.parseInt(marker.getSnippet());
        final Dialog dialog = new Dialog(this.getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_map_infowindow);
        CustomFontTextView lblNombre = (CustomFontTextView)dialog.findViewById(R.id.lblNombre);
        lblNombre.setText(itens.get(posicion).getStr_nombre());
        CustomFontTextView lblSlogan = (CustomFontTextView)dialog.findViewById(R.id.lblSlogan);
        lblSlogan.setText(itens.get(posicion).getStr_slogan());



        ImageView image = (ImageView)dialog.findViewById(R.id.image);
        if(itens.get(posicion).getByte_logo()!=null)
            image.setImageDrawable( new BitmapDrawable(BitmapFactory.decodeByteArray(itens.get(posicion).getByte_logo(), 0, itens.get(posicion).getByte_logo().length)));


        final Button btnDetalle = (Button) dialog.findViewById(R.id.btnDetalle);
        btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                int id=itens.get(posicion).getInt_id_clinica();
                Bundle args = new Bundle();
                args.putInt("id", id);
                ((MainActivity) getActivity()).posFragmnet=2;
                Fragment fragment = new ClinicaInfoFragment();
                fragment.setArguments(args);
                ((MainActivity) getActivity()).setFragment(fragment);

            }
        });

        dialog.show();
        return true;
    }


    public void btnOrdenar()
    {
        final CharSequence[] items = getResources().getStringArray(R.array.array_distancia);
        AlertDialog.Builder alert = new AlertDialog.Builder(this.getActivity());
        alert.setTitle(getString(R.string.str_distancia));
        alert.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                distancia=item;
                addMaker();
            }});
        alert.show();
    }

    public void EspecialidadDDL (){
        listEspecialidad= clsEspecialidadDAO.Listar(this.getActivity());
        listEspecialidad.add(0,new clsEspecialidad(0,"Especialidades"));

        ArrayAdapter<clsEspecialidad> adapter = new ArrayAdapter<clsEspecialidad>(this.getActivity(),
                R.layout.spinner,listEspecialidad);
        adapter.setDropDownViewResource(R.layout.spinner_vista);
        ComboEspecialidad.setAdapter(adapter);
        ComboEspecialidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idEspecialidad=listEspecialidad.get(position).getInt_id_especialidad();
                addMaker();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                //User selected same item. Nothing to do.
            }
        });
        ComboEspecialidad.setSelection(0);
    }
}
