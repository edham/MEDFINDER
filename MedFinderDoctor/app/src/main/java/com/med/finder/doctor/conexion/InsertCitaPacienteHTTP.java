package com.med.finder.doctor.conexion;


import android.os.AsyncTask;
import android.util.Log;

import com.med.finder.doctor.entidades.clsCitaPaciente;
import com.med.finder.doctor.utilidades.Utilidades;

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

public class InsertCitaPacienteHTTP extends AsyncTask<Object, Integer, String>
{

    private static HttpClient client;
    private static HttpResponse responseGet;
    private static HttpEntity resEntityGet;

	@Override
	protected String doInBackground(Object... params)
	{
        clsCitaPaciente entidad= (clsCitaPaciente)params[0];

        String result = null;
        client = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(Utilidades.url);

        try {
            List<NameValuePair> Value = new ArrayList<NameValuePair>();
            Value.add(new BasicNameValuePair("IdServicio","7"));
            Value.add(new BasicNameValuePair("idPaciente",""+entidad.getObjPaciente().getInt_id_paciente()));
            Value.add(new BasicNameValuePair("idDoctor",""+entidad.getObjDoctor().getInt_id_doctor()));
            Value.add(new BasicNameValuePair("detalle",entidad.getStr_detalle()));

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
