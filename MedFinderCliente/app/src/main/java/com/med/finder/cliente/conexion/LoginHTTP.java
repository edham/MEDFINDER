package com.med.finder.cliente.conexion;


import android.os.AsyncTask;
import android.util.Log;

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

public class LoginHTTP extends AsyncTask<Object, Integer, String>
{

    private static HttpClient client;
    private static HttpResponse responseGet;
    private static HttpEntity resEntityGet;

	@Override
	protected String doInBackground(Object... params)
	{
        String usuario = params[0].toString();
        String clave =  params[1].toString();

        String result = "";
        client = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(Utilidades.url);
       // httppost.setHeader("Content-Type", "text/plain; charset=UTF-8");
        Log.e("----------",Utilidades.url);
        try {
            List<NameValuePair> Value = new ArrayList<NameValuePair>();
            Value.add(new BasicNameValuePair("IdServicio","3"));
            Value.add(new BasicNameValuePair("usuario",usuario));
            Value.add(new BasicNameValuePair("clave",clave));

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
