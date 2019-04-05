package com.med.finder.cliente.conexion;


import android.os.AsyncTask;
import android.util.Log;

import com.med.finder.cliente.entidades.clsPaciente;
import com.med.finder.cliente.utilidades.ImagenArchivo;
import com.med.finder.cliente.utilidades.Utilidades;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class InsertUsuarioHTTP extends AsyncTask<Object, Integer, String>
{

    private static HttpClient client;
    private static HttpResponse responseGet;
    private static HttpEntity resEntityGet;

	@Override
	protected String doInBackground(Object... params)
	{
        clsPaciente entidad = (clsPaciente)params[0];

        String result = "";
        client = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(Utilidades.url);

        try {
            List<NameValuePair> Value = new ArrayList<NameValuePair>();
            Value.add(new BasicNameValuePair("IdServicio","0"));
            Value.add(new BasicNameValuePair("clave",entidad.getObjUsuario().getStr_clave()));
            Value.add(new BasicNameValuePair("idDistrito",""+entidad.getObjDistrito().getId()));
            Value.add(new BasicNameValuePair("direccion",entidad.getObjUsuario().getStr_direccion()));
            Value.add(new BasicNameValuePair("imgPerfil", ImagenArchivo.getImagenBase64(entidad.getObjUsuario().getByte_foto())));
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
                result= EntityUtils.toString(resEntityGet).trim();
            }
        }catch (Exception e)
        {
            result = "";
        }
        return result;
	}

	@Override
	protected void onPostExecute(String result)
	{
       super.onPostExecute(result);
    }



}
