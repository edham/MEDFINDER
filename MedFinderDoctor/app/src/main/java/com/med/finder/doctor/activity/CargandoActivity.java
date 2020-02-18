package com.med.finder.doctor.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.med.finder.doctor.R;
import com.med.finder.doctor.dao.clsDoctorDAO;

public class CargandoActivity extends AppCompatActivity {

    public boolean estado=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_cargando);

        if( clsDoctorDAO.Buscar(this)!=null)
            estado=true;

        new AsyncTaskCargaDatos(this).execute();


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

           if(estado)
            {
                Intent i=new Intent(mContext,MainActivity.class);
                i.putExtra("dato", 0);
                startActivity(i);
                finish();
            }
           else
            {
                Intent i=new Intent(mContext,LoginActivity.class);
                startActivity(i);
                finish();
            }

        }



    }
}