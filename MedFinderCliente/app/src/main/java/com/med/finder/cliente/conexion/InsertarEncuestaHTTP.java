package com.med.finder.cliente.conexion;


import android.os.AsyncTask;

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

public class InsertarEncuestaHTTP extends AsyncTask<Object, Integer, String>
{

    private static HttpClient client;
    private static HttpResponse responseGet;
    private static HttpEntity resEntityGet;

	@Override
	protected String doInBackground(Object... params)
	{
        int idUsuario=(int)params[0];
        int p1=(int)params[1];
        int t1=(int)params[2];
        int p2=(int)params[3];
        int t2=(int)params[4];
        int p3=(int)params[5];
        int t3=(int)params[6];

        String result = "";
        client = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(Utilidades.url);

        try {
            List<NameValuePair> Value = new ArrayList<NameValuePair>();
            Value.add(new BasicNameValuePair("IdServicio","11"));
            Value.add(new BasicNameValuePair("idPersona",""+idUsuario));
            Value.add(new BasicNameValuePair("p1",""+p1));
            Value.add(new BasicNameValuePair("t1",""+t1));

            Value.add(new BasicNameValuePair("p2",""+p2));
            Value.add(new BasicNameValuePair("t2",""+t2));

            Value.add(new BasicNameValuePair("p3",""+p3));
            Value.add(new BasicNameValuePair("t3",""+t3));

            httppost.setEntity(new UrlEncodedFormEntity(Value));
            responseGet = client.execute(httppost);
            resEntityGet = responseGet.getEntity();
            if (resEntityGet != null) {
                result= EntityUtils.toString(resEntityGet).trim();
            }
        }catch (Exception e)
        {
        	result="";
        }
        return result;
	}

	@Override
	protected void onPostExecute(String result)
	{
       super.onPostExecute(result);
    }



}
