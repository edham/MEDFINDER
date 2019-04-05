package com.med.finder.doctor.servicio;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.med.finder.doctor.R;
import com.med.finder.doctor.activity.LoginActivity;
import com.med.finder.doctor.activity.MainActivity;
import com.med.finder.doctor.conexion.ActualizarHTTP;
import com.med.finder.doctor.conexion.LoginHTTP;
import com.med.finder.doctor.dao.clsCitaPacienteDAO;
import com.med.finder.doctor.dao.clsDoctorDAO;
import com.med.finder.doctor.dao.clsPacienteDAO;
import com.med.finder.doctor.dao.clsPreguntaPacienteDAO;
import com.med.finder.doctor.entidades.clsDoctor;
import com.med.finder.doctor.utilidades.Utilidades;

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
                clsDoctor usuario = clsDoctorDAO.Buscar(MyJobService.this.getApplication());
                if (usuario!=null && Utilidades.verificaConexion(MyJobService.this.getApplication()))
                {
                    int totalPreguntas=0;
                    int totalCitas=0;
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
                                        }else
                                        {
                                            borrarNotificacionxId(2);
                                        }

                                    }
                                    if (clsCitaPacienteDAO.AgregarLogin(MyJobService.this.getApplication(), entidadJSON.optString("listCitaPacienteJSON"), false))
                                    {
                                        totalCitas=clsCitaPacienteDAO.pendienteRespuesta(MyJobService.this.getApplication());
                                        if(totalCitas>0)
                                        {
                                            NotificacionCitas(totalCitas);
                                        }else
                                        {
                                            borrarNotificacionxId(1);
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
                    /*
                    if(flag)
                        try{

                            PostHTTP post = new PostHTTP();
                            JSONObject json = new JSONObject();
                            json.put("id", "6");
                            int pk_mensajes = clsMensajeSQL.getUltimoPk(MyJobService.this.getApplication());

                            int pk_sintesis = clsSintesisSQL.getUltimoPk(MyJobService.this.getApplication());

                            JSONObject consulta = new JSONObject();
                            consulta.put("pk_mensajes",pk_mensajes);
                            consulta.put("pk_sintesis",pk_sintesis);
                            consulta.put("tipo",0);
                            consulta.put("longitud",(usuario.getLongitud()!=longitud)?longitud:0d);
                            consulta.put("latitud",(usuario.getLatitud()!=latitud)?latitud:0d);
                            consulta.put("track_id",track_id);
                            Log.e("startService","longitud "+longitud+" - latitud "+latitud);
                            String data = Utilidades.enviaData(MyJobService.this.getApplicationContext(), consulta.toString());
                            JSONObject jsonData = new JSONObject();
                            jsonData.put("idUsuario",usuario.getPk_id());
                            jsonData.put("data",  data);
                            json.put("data", Utilidades.enviaLogin(MyJobService.this.getApplicationContext(),jsonData.toString()));
                            post.execute(json.toString());
                            JSONObject objeto = new JSONObject(post.get());
                            if(objeto.getInt("estado")>0) {
                                JSONObject recibe = new JSONObject(Utilidades.recibeData(MyJobService.this.getApplicationContext(), objeto.getString("data")));

                                if(!recibe.isNull("clave")) {
                                    clsUsuarioDAO.getCambioClave(MyJobService.this.getApplicationContext(), recibe.getString("clave"));
                                }
                                if(!recibe.isNull("track_id")) {
                                    track_id=recibe.getInt("track_id");
                                }
                                clsUsuarioDAO.setCordenadas(MyJobService.this.getApplication(),track_id,latitud,longitud);
                                Log.e("startService",recibe.toString());
                                int totalSMS=clsMensajeSQL.Agregar(MyJobService.this.getApplicationContext(),recibe.getString("jsonListTid"));

                                int totalNinf=clsSintesisSQL.Agregar(MyJobService.this.getApplicationContext(),recibe.getString("jsonListNotasInformativas"));

                                clsUsuarioDAO.getPerfil(MyJobService.this.getApplication(),recibe.getString("listPerfil"));
                                clsTipoSintesisSQL.Agregar(MyJobService.this.getApplication(),recibe.getString("listTipoSintesis"));
                                if(totalSMS>0){
                                    NotificacionSms(totalSMS);
                                }
                                if(totalNinf>0){
                                    NotificacionNinf(totalNinf);
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
        CharSequence descripcion  = getString(R.string.str_aprobar_cita)+" : "+total;
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
        CharSequence descripcion  = getString(R.string.str_responder_consulta)+" : "+total;
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