/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.med.finder.cliente.conexion;

import android.util.Base64;

import com.med.finder.cliente.entidades.clsCitaPaciente;
import com.med.finder.cliente.entidades.clsPaciente;
import com.med.finder.cliente.entidades.clsPreguntaPaciente;
import com.med.finder.cliente.utilidades.Utilidades;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class http {

    private static HttpClient client;
    private static HttpResponse responseGet;
    private static HttpEntity resEntityGet;
    

     public static String insertarVotoRespuestaCasosSalud(int idUsuario,int idRespuestaCasosSalud,int Puntaje) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(Utilidades.url);
    
    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(4);
        Value.add(new BasicNameValuePair("IdServicio","5"));
        Value.add(new BasicNameValuePair("idUsuario",""+idUsuario));
        Value.add(new BasicNameValuePair("idRespuestaCasosSalud",""+idRespuestaCasosSalud));
        Value.add(new BasicNameValuePair("puntaje",""+Puntaje));
        httppost.setEntity(new UrlEncodedFormEntity(Value));
        responseGet = client.execute(httppost);
        resEntityGet = responseGet.getEntity();
        if (resEntityGet != null) {
                return  EntityUtils.toString(resEntityGet).trim();
        }
        } catch (ClientProtocolException e) {
            return "-1";
        } catch (IOException e) {
            return "-1";
        }
     return "-1";
}
     


        
    public static String cancelarCitaPaciente(int IdCita) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(Utilidades.url);
    
    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(2);
        Value.add(new BasicNameValuePair("IdServicio","8"));
        Value.add(new BasicNameValuePair("idCita",""+IdCita));
        
        httppost.setEntity(new UrlEncodedFormEntity(Value));
        responseGet = client.execute(httppost);
        resEntityGet = responseGet.getEntity();
        if (resEntityGet != null) {
                return  EntityUtils.toString(resEntityGet).trim();
        }
        } catch (ClientProtocolException e) {
            return "-1";
        } catch (IOException e) {
            return "-1";
        }
     return "-1";
    }
    
    public static String puntajeRespuestaPreguntaPaciente(int idRespuesta,int puntaje) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(Utilidades.url);
    
    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(3);
        Value.add(new BasicNameValuePair("IdServicio","9"));
        Value.add(new BasicNameValuePair("idRespuesta",""+idRespuesta));
        Value.add(new BasicNameValuePair("puntaje",""+puntaje));
        
        httppost.setEntity(new UrlEncodedFormEntity(Value));
        responseGet = client.execute(httppost);
        resEntityGet = responseGet.getEntity();
        if (resEntityGet != null) {
                return  EntityUtils.toString(resEntityGet).trim();
        }
        } catch (ClientProtocolException e) {
            return "-1";
        } catch (IOException e) {
            return "-1";
        }
     return "-1";
    }
    
    public static String actualizarData(int idUsuario) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(Utilidades.url);
    
    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(2);
        Value.add(new BasicNameValuePair("IdServicio","10"));
        Value.add(new BasicNameValuePair("idUsuario",""+idUsuario));
        
        httppost.setEntity(new UrlEncodedFormEntity(Value));
        responseGet = client.execute(httppost);
        resEntityGet = responseGet.getEntity();
        if (resEntityGet != null) {
                return  EntityUtils.toString(resEntityGet).trim();
        }
        } catch (ClientProtocolException e) {
            return "-1";
        } catch (IOException e) {
            return "-1";
        }
     return "-1";
    }
    
    public static String verificaPreguntaPaciente(int idPregunta) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(Utilidades.url);
    
    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(2);
        Value.add(new BasicNameValuePair("IdServicio","11"));
        Value.add(new BasicNameValuePair("idPregunta",""+idPregunta));
        
        httppost.setEntity(new UrlEncodedFormEntity(Value));
        responseGet = client.execute(httppost);
        resEntityGet = responseGet.getEntity();
        if (resEntityGet != null) {
                return  EntityUtils.toString(resEntityGet).trim();
        }
        } catch (ClientProtocolException e) {
            return "-1";
        } catch (IOException e) {
            return "-1";
        }
     return "-1";
    }
    
     public static String ListarRespuestaPreguntaPaciente(int idPregunta,int idRespuesta) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(Utilidades.url);
    
    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(3);
        Value.add(new BasicNameValuePair("IdServicio","12"));
        Value.add(new BasicNameValuePair("idPregunta",""+idPregunta));
        Value.add(new BasicNameValuePair("idRespuesta",""+idRespuesta));
        
        
        httppost.setEntity(new UrlEncodedFormEntity(Value));
        responseGet = client.execute(httppost);
        resEntityGet = responseGet.getEntity();
        if (resEntityGet != null) {
                return  EntityUtils.toString(resEntityGet).trim();
        }
        } catch (ClientProtocolException e) {
            return "-1";
        } catch (IOException e) {
            return "-1";
        }
     return "-1";
    }
     
      public static String cancelaPreguntaPaciente(int idPregunta) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(Utilidades.url);
    
    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(2);
        Value.add(new BasicNameValuePair("IdServicio","13"));
        Value.add(new BasicNameValuePair("idPregunta",""+idPregunta));
        
        
        httppost.setEntity(new UrlEncodedFormEntity(Value));
        responseGet = client.execute(httppost);
        resEntityGet = responseGet.getEntity();
        if (resEntityGet != null) {
                return  EntityUtils.toString(resEntityGet).trim();
        }
        } catch (ClientProtocolException e) {
            return "-1";
        } catch (IOException e) {
            return "-1";
        }
     return "-1";
    }
    
    public static String buscarCitaPaciente(int idCita) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(Utilidades.url);
    
    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(2);
        Value.add(new BasicNameValuePair("IdServicio","14"));
        Value.add(new BasicNameValuePair("idCita",""+idCita));
        
        
        httppost.setEntity(new UrlEncodedFormEntity(Value));
        responseGet = client.execute(httppost);
        resEntityGet = responseGet.getEntity();
        if (resEntityGet != null) {
                return  EntityUtils.toString(resEntityGet).trim();
        }
        } catch (ClientProtocolException e) {
            return "-1";
        } catch (IOException e) {
            return "-1";
        }
     return "-1";
    }
}
