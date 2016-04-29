package  com.app.med.finder.utilidades;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by EdHam on 13/09/2015.
 */
public class clsUtilidades {

    public static String url="http://192.168.0.7:8080/MedFinderEE-war/servicio_usuario";


    public static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    public static SimpleDateFormat hora=new SimpleDateFormat("h:mm a");





    public static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
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



    public static void alert(Context context, String message) {

        CharSequence text = message;

        int duration = Toast.LENGTH_LONG;

        CustomToast miToast = new CustomToast(context, duration);
        miToast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 200);
        miToast.show(text);

    }


    public static String hash256(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        byte[] bytes = md.digest();

        StringBuffer result = new StringBuffer();
        for (byte byt : bytes)
            result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }



    public static String getMacAddress(Context context){

        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        return info.getMacAddress();

    }
    ///DATA

    public static boolean isDni(String dni){

        Pattern dniPattern = Pattern.compile("(\\d{8})");
        Matcher m = dniPattern.matcher(dni);
        if(m.matches()){
            return true;
        }
        else
            return false;
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

    public static Date getDate(String dateInString)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(dateInString);
        } catch (ParseException e) {
            return null;
        }
    }


    public static int getEdad(Date pNacio){

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
    }



    public static double Redondear(double NDecimal)
    {
        return Math.round(NDecimal*Math.pow(10,2))/Math.pow(10,2);
    }


    public static boolean isValidEmail(String email)
    {

        String emailExpression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(emailExpression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
            return true;
        else
            return false;
    }

    public static boolean isTelefono(String tele){

        Pattern dniPattern = Pattern.compile("(\\d{1,9})");
        Matcher m = dniPattern.matcher(tele);
        if(m.matches()){
            return true;
        }
        else
            return false;
    }

    public static String getMail(Context context) {
        String possibleEmail=null;

        try{
            Account[] accounts = AccountManager.get(context).getAccountsByType("com.google");

            for (Account account : accounts) {

                possibleEmail =account.name;

            }
        }
        catch(Exception e)
        {
            Log.i("Exception", "Exception:" + e) ;
        }
        return possibleEmail;
    }

    public static  Bitmap getBitmap (byte[] imageBytes)
    {
        return  BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }

    public static byte[]  getByte(Bitmap bitmap)
    {
        if (bitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            return stream.toByteArray();
        }
        return null;
    }


    public static int minutos(Date fecha)
    {
        long milis1 = new Date().getTime();
        long milis2 = fecha.getTime();
        long diff = milis1-milis2;
        int minutos = (int)(diff / (60 * 1000));
        return minutos;
    }
}