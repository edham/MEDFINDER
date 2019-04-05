package com.med.finder.cliente.conexion;


import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.med.finder.cliente.entidades.clsPreguntaPaciente;
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

public class InsertarPreguntaPacienteHTTP extends AsyncTask<Object, Integer, String>
{

    private static HttpClient client;
    private static HttpResponse responseGet;
    private static HttpEntity resEntityGet;

	@Override
	protected String doInBackground(Object... params)
	{
        clsPreguntaPaciente entidad= (clsPreguntaPaciente)params[0];

        String result = null;
        client = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(Utilidades.url);

        try {
            List<NameValuePair> Value = new ArrayList<NameValuePair>();
            Value.add(new BasicNameValuePair("IdServicio","4"));
            Value.add(new BasicNameValuePair("idPaciente",""+entidad.getObjPaciente().getInt_id_paciente()));
            Value.add(new BasicNameValuePair("idEspecialidad",""+entidad.getObjEspecialidad().getInt_id_especialidad()));
            Value.add(new BasicNameValuePair("detalle",entidad.getStr_paciente_detalle()));
            Value.add(new BasicNameValuePair("asunto",entidad.getStr_asunto()));
            if(entidad.getByte_imagen()!=null)
                Value.add(new BasicNameValuePair("imagen", ImagenArchivo.getImagenBase64(entidad.getByte_imagen())));

            httppost.setEntity(new UrlEncodedFormEntity(Value));
            responseGet = client.execute(httppost);
            resEntityGet = responseGet.getEntity();
            if (resEntityGet != null) {
                result= EntityUtils.toString(resEntityGet).trim();
            }
        }catch (Exception e)
        {
        	Log.e(e.getClass().getName(), e.getMessage());
        }
        return result;
	}

	@Override
	protected void onPostExecute(String result)
	{
       super.onPostExecute(result);
    }



}
