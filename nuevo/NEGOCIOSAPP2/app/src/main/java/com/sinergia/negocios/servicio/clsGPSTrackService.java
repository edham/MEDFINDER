package com.sinergia.seguridad.servicio;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.sinergia.seguridad.conexion.postDataHTTP;
import com.sinergia.seguridad.dao.clsSesionVigilanciaDAO;
import com.sinergia.seguridad.dao.clsTrackDAO;
import com.sinergia.seguridad.entidades.clsTrack;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class clsGPSTrackService extends Service  {

    private MyLocationListener mll;
    // flag for GPS status
    boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;

    // flag for GPS status
    boolean canGetLocation = false;

    Location location; // location
    private double latitud=0;
    private double longitud=0;

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 10; // 1 minute

    // Declaring a Location Manager
    protected LocationManager locationManager;



    @Override
    public void onCreate() {
        super.onCreate();
        getLocation();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopUsingGPS();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(mll);
        }
    }

    public Location getLocation() {
        try {
            locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
            mll = new MyLocationListener();
            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, mll);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, mll);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }




    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {

            clsTrack entidad = new clsTrack();
            entidad.setDou_longitud(location.getLongitude());
            entidad.setDou_latitud(location.getLatitude());
            clsTrackDAO.Agregar(clsGPSTrackService.this.getApplication(),entidad);


            try {
                JSONObject json_Object = new JSONObject();
                json_Object.put("latitud",location.getLatitude());
                json_Object.put("longitud",location.getLongitude());
                json_Object.put("idSesion", clsSesionVigilanciaDAO.Buscar(clsGPSTrackService.this.getApplication()).getInt_id());


                postDataHTTP data = new postDataHTTP();
                data.execute(json_Object.toString(), "SesionTrackRest");
                String dato = data.get().trim();

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }

    }
}