package com.med.finder.cliente.servicio;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.med.finder.cliente.R;
import com.med.finder.cliente.activity.MainActivity;
import com.med.finder.cliente.dao.clsUsuarioDAO;
import com.med.finder.cliente.entidades.clsUsuario;
import com.med.finder.cliente.utilidades.Utilidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class MyJobService extends JobService {
    public static final String TAG = "MyJobService";
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Log.e(TAG, currentDateTimeString);
          Registra();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.e(TAG, "Job cancelled!");

        return false;
    }

    public void Registra()
    {
        new Thread() {
            public void run() {
                Log.e("servicio","---------------------------------------------------");
                clsUsuario usuario = clsUsuarioDAO.Buscar(MyJobService.this.getApplication());
                if (usuario!=null && Utilidades.verificaConexion(MyJobService.this.getApplication()))
                {
                    int totalPreguntas=0;
                    int totalCitas=0;
                    /*
                    try {

                        ActualizarHTTP login = new ActualizarHTTP();
                        login.execute(usuario.getInt_id_doctor());
                        String result=login.get();
                        if(result!=null && result!="") {
                            JSONObject entidadJSON  = new JSONObject(result);
                                if (entidadJSON.getInt("rpta") == 1) {
                                    clsPacienteDAO.AgregarLogin(MyJobService.this.getApplication(), entidadJSON.optString("listPacienteJSON"), true);

                                    if (clsPreguntaPacienteDAO.AgregarLogin(MyJobService.this.getApplication(), entidadJSON.optString("listPreguntaPacienteJSON"), false))
                                    {
                                        totalPreguntas=clsPreguntaPacienteDAO.pendienteRespuesta(MyJobService.this.getApplication());
                                        if(totalPreguntas>0)
                                        {
                                            NotificacionConsulta(totalPreguntas);
                                        }

                                    }
                                    if (clsCitaPacienteDAO.AgregarLogin(MyJobService.this.getApplication(), entidadJSON.optString("listCitaPacienteJSON"), false))
                                    {
                                        totalCitas=clsCitaPacienteDAO.pendienteRespuesta(MyJobService.this.getApplication());
                                        if(totalCitas>0)
                                        {
                                            NotificacionCitas(totalCitas);
                                        }
                                    }

                                }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    */
                }
            }
        }.start();
    }


    public void borrarNotificacionxId(int Id)
    {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager notManager =
                (NotificationManager) getSystemService(ns);
        notManager.cancel(Id);
    }

    public void NotificacionCitas(int total)
    {
        /**Creates an explicit intent for an Activity in your app**/

        CharSequence titulo =getString(R.string.app_name);
        CharSequence descripcion  = "";//getString(R.string.str_aprobar_cita)+" : "+total;
        int dato=1;

        Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
        resultIntent.putExtra("dato", dato);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(),
                dato /* Request code */, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setSmallIcon(R.drawable.ic_citas);
        } else {
            mBuilder.setSmallIcon(R.drawable.ic_citas);
        }
        mBuilder.setContentTitle(titulo)
                .setContentText(descripcion)
                .setAutoCancel(false)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(resultPendingIntent);



        NotificationManager  mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String NOTIFICATION_CHANNEL_ID = "10002";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(dato /* Request Code */, mBuilder.build());
    }



    public void NotificacionConsulta(int total)
    {
        /**Creates an explicit intent for an Activity in your app**/

        CharSequence titulo =getString(R.string.app_name);
        CharSequence descripcion  = "";//getString(R.string.str_responder_consulta)+" : "+total;
        int dato=2;

        Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
        resultIntent.putExtra("dato", dato);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(),
                dato /* Request code */, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBuilder.setSmallIcon(R.drawable.ic_inicio);//small
        } else {
            mBuilder.setSmallIcon(R.drawable.ic_inicio);
        }
        mBuilder.setContentTitle(titulo)
                .setContentText(descripcion)
                .setAutoCancel(false)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(resultPendingIntent);



        NotificationManager  mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String NOTIFICATION_CHANNEL_ID = "10001";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(dato /* Request Code */, mBuilder.build());
    }

}