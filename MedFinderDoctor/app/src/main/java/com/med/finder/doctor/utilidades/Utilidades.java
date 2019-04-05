package com.med.finder.doctor.utilidades;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.med.finder.doctor.dao.clsDoctorDAO;
import com.med.finder.doctor.servicio.MyJobService;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static android.content.Context.POWER_SERVICE;

public class Utilidades {

    //public static String url = "http://192.168.1.7:8080/edsoft-war/";


    public static String url="http://192.168.1.62:8080/MedFinderEE-war/servicio_doctor";
    public static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat datehourFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
    public static SimpleDateFormat hourFormatter = new SimpleDateFormat("hh:mm a");

    public static Bitmap getBitmap(byte[] imageBytes) {
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }

    public static Date getDate(String dateInString)
    {
        try {
            return dateFormatter.parse(dateInString);
        } catch (ParseException e) {
            return null;
        }
    }
    public static Date getDateHour(String dateInString)
    {
        try {
            return datehourFormatter.parse(dateInString);
        } catch (ParseException e) {
            return null;
        }
    }


    public static boolean isDate(String fecha) {

        if (fecha == null)
            return false;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (fecha.trim().length() != dateFormat.toPattern().length())
            return false;

        dateFormat.setLenient(false);

        try {
            dateFormat.parse(fecha.trim());
        }
        catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static int minutos(Date fecha)
    {
        long milis1 = new Date().getTime();
        long milis2 = fecha.getTime();
        long diff = milis1-milis2;
        int minutos = (int)(diff / (60 * 1000));
        return minutos;
    }

    public static void alert(Context context, String message) {

        CharSequence text = message;
        int duration = Toast.LENGTH_LONG;
        CustomToast miToast = new CustomToast(context, duration);
        miToast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 200);
        miToast.show(text);
    }

    public static String getMD5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Boolean conectadoWifi(Context context){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }
    public static Boolean conectadoRedMovil(Context context){
        ConnectivityManager connectivity = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void BorrarDatos(Context context){

        clsDoctorDAO.Borrar(context);
    }

    public static boolean iniciarGPS(Context context)
    {
        LocationManager locationManager=locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean  isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetworkEnabled) {
            // no network provider is enabled
            return true;
        }
        return false;
    }

    public static double Redondear(double NDecimal)
    {
        return Math.round(NDecimal*Math.pow(10,4))/Math.pow(10,4);
    }
/*
    public static double getResult(double peso, String formula) {
        Expression e = new ExpressionBuilder(formula.trim())
                .variables("x")
                .build().setVariable("x", peso);
        return  e.evaluate();
    }
*/
    public static boolean verificaConexion(Context context) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        for (int i = 0; i < redes.length; i++) {
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }



    public static boolean checkPermissions(Context context)
    {
        boolean permiso= Permisos.hasPermissions(context);
        if(!permiso){
            ActivityCompat.requestPermissions((Activity) context, Permisos.PERMISSIONS, Permisos.PERMISSION_ALL);
        }
        return permiso;
    }

    public static boolean isNetDisponible(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo actNetInfo = connectivityManager.getActiveNetworkInfo();

        return (actNetInfo != null && actNetInfo.isConnected());
    }
    public static Boolean isOnlineNet() {

        try {
            Process p = Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;
        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private static final String characterEncoding = "UTF-8";
    private static final String cipherTransformation = "AES/CBC/PKCS5Padding";
    private static final String aesEncryptionAlgorithm = "AES";

    private static byte[] decrypt(byte[] cipherText, byte[] key, byte[] initialVector) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(cipherTransformation);
        SecretKeySpec secretKeySpecy = new SecretKeySpec(key, aesEncryptionAlgorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpecy, ivParameterSpec);
        cipherText = cipher.doFinal(cipherText);
        return cipherText;
    }

    private static byte[] encrypt(byte[] plainText, byte[] key, byte[] initialVector) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(cipherTransformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, aesEncryptionAlgorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        plainText = cipher.doFinal(plainText);
        return plainText;
    }

    private static byte[] getKeyBytes(String key) throws UnsupportedEncodingException {
        byte[] keyBytes = new byte[16];
        byte[] parameterKeyBytes = key.getBytes(characterEncoding);
        System.arraycopy(parameterKeyBytes, 0, keyBytes, 0, Math.min(parameterKeyBytes.length, keyBytes.length));
        return keyBytes;
    }

    public static String encrypt(String plainText, String key) {
        String dato = "";
        try {

            byte[] plainTextbytes = plainText.getBytes(characterEncoding);
            byte[] keyBytes = getKeyBytes(key);
            dato = Base64.encodeToString(encrypt(plainTextbytes, keyBytes, keyBytes), Base64.DEFAULT);

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dato;
    }

    public static String decrypt(String encryptedText, String key) {
        String dato = "";
        try {
            byte[] cipheredBytes = Base64.decode(encryptedText, Base64.DEFAULT);
            byte[] keyBytes = getKeyBytes(key);
            dato = new String(decrypt(cipheredBytes, keyBytes, keyBytes), characterEncoding);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dato;
    }



    public static String formatNumberDecimal(Double number)
    {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');

        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);
        String[] parts = formatter.format(number).split("\\.");
        String dato=parts[0];
        if(parts.length==1)
        {
            dato=dato+".00";
        }
        else if(parts.length==2)
        {
           if(parts[1].length()==1)
           {
               dato=dato+"."+parts[1]+"0";
           }else
           {
               dato=dato+"."+parts[1];
           }
        }
         return dato;
    }
    public static String formatNumberInt(int number)
    {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(number);
    }
    public static int pantalla(Activity context)
    {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        float scaleFactor = metrics.density;
        float widthDp = widthPixels / scaleFactor;
        float heightDp = heightPixels / scaleFactor;
        float smallestWidth = Math.min(widthDp, heightDp);
        int dato=0;
        if (smallestWidth > 720) {
            dato=2;
        }
        else if (smallestWidth > 600) {
            dato=1;
        }
        return  dato;
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }



    public static int getEdad(Date pNacio){

        try
        {
            Calendar fecha = new GregorianCalendar();
            fecha.setTime(pNacio);
            int diaNacio = fecha.get(Calendar.DATE);
            int mesNacio = fecha.get(Calendar.MONTH)+1;
            int anoNacio = fecha.get(Calendar.YEAR);
            fecha.setTime(new Date());

            int anoFecha = fecha.get(Calendar.YEAR);
            int mesFecha = fecha.get(Calendar.MONTH)+1;
            int diaFecha = fecha.get(Calendar.DATE);

            int difAno = anoFecha - anoNacio;
            int difMes = mesFecha - mesNacio;
            int difDia = diaFecha - diaNacio;

            if(difMes <0){

                difAno-=1;

            }else if(difMes == 0){

                if(difDia < 0){
                    difAno-=1;
                }

            }
            return difAno;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    public static void setCalendar(Context context, final TextView lblCalendar , Date min, Date max )
    {
        final Date dateMax=max;
        final Date dateMin=min;
        final Calendar newCalendar = Calendar.getInstance();

        final DatePickerDialog fromDatePickerDialog = new DatePickerDialog(context, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                lblCalendar.setText(Utilidades.dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        lblCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dateMax!=null) {
                    fromDatePickerDialog.getDatePicker().setMaxDate(dateMax.getTime());
                }
                if(dateMin!=null) {
                    fromDatePickerDialog.getDatePicker().setMinDate(dateMin.getTime());
                }
                fromDatePickerDialog.show();
            }
        });
    }
    private static boolean sInitialized;

    synchronized public static void scheduleChargingReminder(@NonNull final Context context){
        if (sInitialized) return;

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job constrintReminderJob = dispatcher.newJobBuilder()
                .setService(MyJobService.class)
                .setTag(MyJobService.TAG)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(5, 30))
                .setLifetime(Lifetime.FOREVER)
                .setReplaceCurrent(true)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                .build();
        dispatcher.schedule(constrintReminderJob);
/*

          .setService(MyJobService.class)
                .setTag(JOB_TAG)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(0, 30))
                .setLifetime(Lifetime.FOREVER)
                .setReplaceCurrent(false)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .build();
          */
        sInitialized = true;
    }

    synchronized public static void cancelAll(@NonNull final Context context){
        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
        dispatcher.cancelAll();
    }

    public static boolean addListaBlanca(Context ctx){
        boolean permiso=true;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent();
            String packageName = ctx.getPackageName();
            PowerManager pm = (PowerManager) ctx.getSystemService(POWER_SERVICE);
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                permiso=false;
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + packageName));
                ctx.startActivity(intent);
            }
        }

        return permiso;
    }

    public static void setTimer(Context context, final TextView lblCalendar)
    {
        final Calendar newCalendar = Calendar.getInstance();
       final TimePickerDialog timePickerDialog = new TimePickerDialog(context,AlertDialog.THEME_HOLO_LIGHT,
               new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH),hourOfDay,minute);

                        lblCalendar.setText(hourFormatter.format(newDate.getTime()));
                    }
                },newCalendar.get(Calendar.HOUR),newCalendar.get(Calendar.MINUTE), false);
        timePickerDialog.show();


        lblCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
    }
}