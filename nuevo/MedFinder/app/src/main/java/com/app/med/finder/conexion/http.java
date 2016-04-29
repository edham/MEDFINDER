/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.med.finder.conexion;

import android.util.Base64;

import com.app.med.finder.entidades.clsCitaPaciente;
import com.app.med.finder.entidades.clsPaciente;
import com.app.med.finder.entidades.clsPreguntaPaciente;
import com.app.med.finder.utilidades.clsUtilidades;

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
    
    
    
   

    public static String insertarUsuario(clsPaciente entidad) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(clsUtilidades.url);

    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(20);
        Value.add(new BasicNameValuePair("IdServicio","0"));
        Value.add(new BasicNameValuePair("clave",entidad.getObjUsuario().getStr_clave()));
        Value.add(new BasicNameValuePair("direccion",entidad.getObjUsuario().getStr_direccion()));
        Value.add(new BasicNameValuePair("dni",entidad.getObjUsuario().getStr_dni()));
        Value.add(new BasicNameValuePair("email",entidad.getObjUsuario().getStr_email()));
        Value.add(new BasicNameValuePair("telefono",entidad.getObjUsuario().getStr_telefono()));
        Value.add(new BasicNameValuePair("usuario",entidad.getObjUsuario().getStr_usuario()));
        Value.add(new BasicNameValuePair("alcohol",""+entidad.isBol_alcohol()));
        Value.add(new BasicNameValuePair("alergicos",""+entidad.isBol_alergicos()));
        Value.add(new BasicNameValuePair("cardio",""+entidad.isBol_cardiovasculares()));
        Value.add(new BasicNameValuePair("drogas",""+entidad.isBol_drogas()));
        Value.add(new BasicNameValuePair("musculares",""+entidad.isBol_musculares()));
        Value.add(new BasicNameValuePair("psicologicos",""+entidad.isBol_psicologicos()));
        Value.add(new BasicNameValuePair("digestivos",""+entidad.isBol_digestivos()));        
        Value.add(new BasicNameValuePair("sexo",""+entidad.isBol_sexo()));
        Value.add(new BasicNameValuePair("tabaquismo",""+entidad.isBol_tabaquismo()));
        Value.add(new BasicNameValuePair("fnacimiento",""+entidad.getDat_fecha_nacimiento().getTime()));
        Value.add(new BasicNameValuePair("estatura",""+entidad.getInt_estatura()));
        Value.add(new BasicNameValuePair("paterno",entidad.getStr_apellido_paterno()));
        Value.add(new BasicNameValuePair("materno",entidad.getStr_apellido_materno()));
        Value.add(new BasicNameValuePair("nombre",entidad.getStr_nombres()));
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

    public static String insertarPaciente(clsPaciente entidad) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(clsUtilidades.url);

    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(16);
        Value.add(new BasicNameValuePair("IdServicio","1"));
        Value.add(new BasicNameValuePair("alcohol",""+entidad.isBol_alcohol()));
        Value.add(new BasicNameValuePair("alergicos",""+entidad.isBol_alergicos()));
        Value.add(new BasicNameValuePair("cardio",""+entidad.isBol_cardiovasculares()));
        Value.add(new BasicNameValuePair("drogas",""+entidad.isBol_drogas()));
        Value.add(new BasicNameValuePair("musculares",""+entidad.isBol_musculares()));
        Value.add(new BasicNameValuePair("digestivos",""+entidad.isBol_digestivos())); 
        Value.add(new BasicNameValuePair("psicologicos",""+entidad.isBol_psicologicos()));
        Value.add(new BasicNameValuePair("sexo",""+entidad.isBol_sexo()));
        Value.add(new BasicNameValuePair("tabaquismo",""+entidad.isBol_tabaquismo()));
        Value.add(new BasicNameValuePair("fnacimiento",""+entidad.getDat_fecha_nacimiento().getTime()));
        Value.add(new BasicNameValuePair("estatura",""+entidad.getInt_estatura()));
        Value.add(new BasicNameValuePair("paterno",entidad.getStr_apellido_paterno()));
        Value.add(new BasicNameValuePair("materno",entidad.getStr_apellido_materno()));
        Value.add(new BasicNameValuePair("nombre",entidad.getStr_nombres()));
        Value.add(new BasicNameValuePair("dni",entidad.getStr_dni()));
        Value.add(new BasicNameValuePair("idUsuario",""+entidad.getObjUsuario().getInt_id_usuario()));
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
     public static String actualizarPaciente(clsPaciente entidad) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(clsUtilidades.url);

    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(17);
        Value.add(new BasicNameValuePair("IdServicio","2"));
        Value.add(new BasicNameValuePair("alcohol",""+entidad.isBol_alcohol()));
        Value.add(new BasicNameValuePair("alergicos",""+entidad.isBol_alergicos()));
        Value.add(new BasicNameValuePair("cardio",""+entidad.isBol_cardiovasculares()));
        Value.add(new BasicNameValuePair("drogas",""+entidad.isBol_drogas()));
        Value.add(new BasicNameValuePair("musculares",""+entidad.isBol_musculares()));
        Value.add(new BasicNameValuePair("digestivos",""+entidad.isBol_digestivos()));        
        Value.add(new BasicNameValuePair("psicologicos",""+entidad.isBol_psicologicos()));
        Value.add(new BasicNameValuePair("sexo",""+entidad.isBol_sexo()));
        Value.add(new BasicNameValuePair("tabaquismo",""+entidad.isBol_tabaquismo()));
        Value.add(new BasicNameValuePair("fnacimiento",""+entidad.getDat_fecha_nacimiento().getTime()));
        Value.add(new BasicNameValuePair("estatura",""+entidad.getInt_estatura()));
        Value.add(new BasicNameValuePair("paterno",entidad.getStr_apellido_paterno()));
        Value.add(new BasicNameValuePair("materno",entidad.getStr_apellido_materno()));
        Value.add(new BasicNameValuePair("nombre",entidad.getStr_nombres()));
        Value.add(new BasicNameValuePair("idUsuario",""+entidad.getObjUsuario().getInt_id_usuario()));
        Value.add(new BasicNameValuePair("idPaciente",""+entidad.getInt_id_paciente()));
        Value.add(new BasicNameValuePair("idPersona",""+entidad.getInt_id_persona()));
        
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
     

        public static String insertarPreguntaPacuente(clsPreguntaPaciente entidad)
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(clsUtilidades.url);
    
    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(6);
        Value.add(new BasicNameValuePair("IdServicio","4"));
        Value.add(new BasicNameValuePair("idPaciente",""+entidad.getObjPaciente().getInt_id_paciente()));
        Value.add(new BasicNameValuePair("idEspecialidad",""+entidad.getObjEspecialidad().getInt_id_especialidad()));
        Value.add(new BasicNameValuePair("detalle",entidad.getStr_paciente_detalle()));
        Value.add(new BasicNameValuePair("asunto",entidad.getStr_asunto()));
        if(entidad.getByte_imagen()!=null)
        Value.add(new BasicNameValuePair("imagen",Base64.encodeToString(entidad.getByte_imagen(),Base64.NO_WRAP|Base64.URL_SAFE)));
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

     public static String insertarVotoRespuestaCasosSalud(int idUsuario,int idRespuestaCasosSalud,int Puntaje) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(clsUtilidades.url);
    
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
     
      public static String insertarFavorito(int idUsuario,int idDoctor,boolean estado) 
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(clsUtilidades.url);
    
    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(4);
        Value.add(new BasicNameValuePair("IdServicio","6"));
        Value.add(new BasicNameValuePair("idUsuario",""+idUsuario));
        Value.add(new BasicNameValuePair("idDoctor",""+idDoctor));
        Value.add(new BasicNameValuePair("estado",""+estado));
        
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
      
        public static String insertarCitaPaciente(clsCitaPaciente entidad)
    {
    client = new DefaultHttpClient();
    HttpPost httppost = new HttpPost(clsUtilidades.url);
    
    try {
        List<NameValuePair> Value = new ArrayList<NameValuePair>(4);
        Value.add(new BasicNameValuePair("IdServicio","7"));
        Value.add(new BasicNameValuePair("idPaciente",""+entidad.getObjPaciente().getInt_id_paciente()));
        Value.add(new BasicNameValuePair("idDoctor",""+entidad.getObjDoctor().getInt_id_doctor()));
        Value.add(new BasicNameValuePair("detalle",entidad.getStr_detalle()));
        
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
    HttpPost httppost = new HttpPost(clsUtilidades.url);
    
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
    HttpPost httppost = new HttpPost(clsUtilidades.url);
    
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
    HttpPost httppost = new HttpPost(clsUtilidades.url);
    
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
    HttpPost httppost = new HttpPost(clsUtilidades.url);
    
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
    HttpPost httppost = new HttpPost(clsUtilidades.url);
    
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
    HttpPost httppost = new HttpPost(clsUtilidades.url);
    
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
    HttpPost httppost = new HttpPost(clsUtilidades.url);
    
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
