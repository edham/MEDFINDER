package com.sinergia.negocios.conexion;
import android.os.AsyncTask;
import android.util.Log;


import com.sinergia.negocios.utilidades.clsUtilidades;

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

public class postDataHTTP extends AsyncTask<Object, Integer, String>
{

    private static HttpClient client;
    private static HttpResponse responseGet;
    private static HttpEntity resEntityGet;


    @Override
    protected String doInBackground(Object... params)
    {
        String dato= params[0].toString().trim();
        String url= clsUtilidades.url+params[1].toString().trim();
        String result = null;
        client = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url.trim());

        try {
            List<NameValuePair> Value = new ArrayList<NameValuePair>();
            Value.add(new BasicNameValuePair("dato",dato));
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