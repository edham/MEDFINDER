package com.app.med.finder.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.app.med.finder.dao.clsUsuarioDAO;
import com.app.med.finder.entidades.clsUsuario;


public class CargandoActivity extends Activity {

    public boolean configuracion=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargando);
        clsUsuario entidad= clsUsuarioDAO.Buscar(this);
        if(entidad!=null)
            configuracion=false;
        AsyncTaskCargaDatos ATCargaDatos = new AsyncTaskCargaDatos(this);
        ATCargaDatos.execute();
    }
    public class AsyncTaskCargaDatos extends AsyncTask<Void, Integer, Void> {

        Context mContext;
        AsyncTaskCargaDatos(Context context) {
            mContext = context;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }



        @Override
        protected void onPostExecute(Void result) {
            Intent i;
              if(configuracion)
                i = new Intent(mContext, LoginActivity.class);
              else
                i = new Intent(mContext, MainActivity.class);

            startActivity(i);
            finish();
        }

    }
}
