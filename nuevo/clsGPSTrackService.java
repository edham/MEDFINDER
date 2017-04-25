package com.sinergia.seguridad.servicio;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.sinergia.seguridad.conexion.postDataHTTP;
import com.sinergia.seguridad.dao.clsSesionVigilanciaDAO;
import com.sinergia.seguridad.dao.clsTrackDAO;
import com.sinergia.seguridad.entidades.clsSesionVigilancia;
import com.sinergia.seguridad.entidades.clsTrack;
import com.sinergia.seguridad.ui.R;
import com.sinergia.seguridad.utilidades.clsUtilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class clsGPSTrackService extends Service implements LocationListener {

    private Timer timer;
    private TimerTask timerTask;
    final Handler handler = new Handler();

    private clsSesionVigilancia objclsSesionVigilancia=null;

    String direccion="";



    double longitud=0D;
    double latitud=0D;

    // flag for GPS status
    boolean canGetLocation = false;

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 0; // 1 minute

    // Declaring a Location Manager
    protected LocationManager locationManager;



    @Override
    public void onCreate() {
        super.onCreate();

            startTimer();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stoptimertask();
        stopUsingGPS();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(this);
        }
    }

    public void startLocation() {
        Location location=null;

        try {
            locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

            // getting GPS status
            boolean isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status

            boolean isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled) {
                NotificacionGPS();
            } else {
                borrarNotificacionxId(0);
                this.canGetLocation = true;
                // First get location from Network Provider

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

                if (isNetworkEnabled) {
                    if (location == null) {
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
                }
            }


            if(location!=null) {

                    try {
                        Geocoder gc = new Geocoder(clsGPSTrackService.this.getApplication(), Locale.getDefault());

                        List<Address> addresses = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                        StringBuilder sb = new StringBuilder();
                        if (addresses.size() > 0) {
                            Address address = addresses.get(0);
                            for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
                                sb.append(address.getAddressLine(i)).append(" - ");
                            sb.append(address.getLocality());
                        }
                        direccion=sb.toString();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                clsTrack entidad = new clsTrack();
                entidad.setDou_longitud(location.getLongitude());
                entidad.setDou_latitud(location.getLatitude());
                clsTrackDAO.Agregar(clsGPSTrackService.this.getApplication(), entidad);



               // int distancia=clsUtilidades.distanciaMetros(latitud, longitud, location.getLatitude(), location.getLongitude());
               // Log.wtf("---------------- distancia",""+distancia);
              //  if (distancia > 0) {

                    try {
                        JSONObject json_Object = new JSONObject();
                        json_Object.put("latitud", location.getLatitude());
                        json_Object.put("longitud", location.getLongitude());
                        json_Object.put("idSesion", objclsSesionVigilancia.getInt_id());
                        postDataHTTP data = new postDataHTTP();
                        data.execute(json_Object.toString(), "SesionTrackRest");
                        String dato = data.get().trim();
                        latitud = location.getLatitude();
                        longitud = location.getLongitude();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent("clsGPSTrackService");
                    intent.putExtra("Lat", location.getLatitude());
                    intent.putExtra("Long", location.getLongitude());
                    intent.putExtra("direccion", direccion);

                    sendBroadcast(intent);

               // }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        stopUsingGPS();

    }

    @Override
    public void onLocationChanged(Location location) {


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


    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 0, 5000); //
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
                handler.post(new Runnable() {
                    public void run() {
                        objclsSesionVigilancia=clsSesionVigilanciaDAO.Buscar(clsGPSTrackService.this.getApplication());
                        if(objclsSesionVigilancia!=null) {
                           // if (clsUtilidades.conectadoWifi(clsGPSTrackService.this.getApplication())) {
                                startLocation();
                                try {
                                    JSONObject json_Object = new JSONObject();
                                    json_Object.put("idSesion", objclsSesionVigilancia.getInt_id());
                                    json_Object.put("fec_vigilancia", objclsSesionVigilancia.getDat_fec_vigilancia());
                                    json_Object.put("fec_ini", objclsSesionVigilancia.getDat_fec_ini().getTime());
                                    postDataHTTP data = new postDataHTTP();
                                    data.execute(json_Object.toString(), "SesionComunicacion");
                                    String dato = data.get().trim();
                                    Log.wtf("-------------------dato", dato);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }

                            //}
                            objclsSesionVigilancia.setDat_fec_vigilancia(new Date().getTime());
                            clsSesionVigilanciaDAO.ActualizarFecha(clsGPSTrackService.this.getApplication(), objclsSesionVigilancia);
                        }
                    }
                });
            }
        };
    }


    public void NotificacionGPS()
    {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager notManager = (NotificationManager) getSystemService(ns);
        int icono = R.drawable.gps;
        CharSequence textoEstado = "Active su GPS";
        long hora = System.currentTimeMillis();
        Notification notif = new Notification(icono, textoEstado, hora);
        Context contexto = getApplicationContext();
        CharSequence titulo =  "Active su GPS";
        CharSequence descripcion =  "Por favor Active su GPS";
        Intent notIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        PendingIntent contIntent = PendingIntent.getActivity(contexto, 0, notIntent, 0);
        notif.setLatestEventInfo(contexto, titulo, descripcion, contIntent);
        notif.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.flags |= Notification.FLAG_ONGOING_EVENT;
        notif.defaults |= Notification.DEFAULT_SOUND;
        notManager.notify(0,notif);
    }

    public void NotificacionRed()
    {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager notManager = (NotificationManager) getSystemService(ns);
        int icono = R.drawable.gps;
        CharSequence textoEstado = "Active su red";
        long hora = System.currentTimeMillis();
        Notification notif = new Notification(icono, textoEstado, hora);
        Context contexto = getApplicationContext();
        CharSequence titulo =  "Active su red";
        CharSequence descripcion =  "Por favor Active su red";
        Intent notIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        PendingIntent contIntent = PendingIntent.getActivity(contexto, 0, notIntent, 0);
        notif.setLatestEventInfo(contexto, titulo, descripcion, contIntent);
        notif.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.flags |= Notification.FLAG_ONGOING_EVENT;
        notif.defaults |= Notification.DEFAULT_SOUND;
        notManager.notify(1,notif);
    }

    public void borrarNotificacionxId(int Id)
    {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager notManager = (NotificationManager) getSystemService(ns);
        notManager.cancel(Id);
    }




}