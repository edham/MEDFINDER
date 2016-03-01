/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.med.finder.utilidades;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Toditos
 */
public class Funciones {
    public static String url="http://192.168.0.3:8080/MedFinderEE-war/servicio_usuario";
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
                   Log.i("Exception", "Exception:"+e) ; 
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
