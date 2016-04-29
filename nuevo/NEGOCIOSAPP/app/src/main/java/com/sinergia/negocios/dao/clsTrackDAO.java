/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.negocios.dao;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;

import com.sinergia.negocios.entidades.clsTrack;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;


/**
 *
 * @author Toditos
 */
public class clsTrackDAO {
    
    private static String NOMBRE_TABLA="TRACK";


        public static int Agregar(Context context,clsTrack entidad)
        {
            int id = 0;
            bdSQLite admin=new bdSQLite(context,null);
            SQLiteDatabase bd=admin.getWritableDatabase();
            ContentValues registro=new ContentValues();
            registro.put("dou_latitud",entidad.getDou_latitud());
            registro.put("dou_longitud",entidad.getDou_longitud());
            registro.put("dat_fec_reg",entidad.getDat_fec_reg().getTime());
            id = (int) bd.insert(NOMBRE_TABLA, null, registro);
            bd.close();
            return id;

        }
    public static  clsTrack getTrack(Context context)
    {
        clsTrack  entidad=null;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        if(bd!=null)
        {
            String query="select int_id,dou_latitud,dou_longitud,dat_fec_reg from "+
                    NOMBRE_TABLA+" ORDER BY int_id asc LIMIT 1";

            Cursor fila=bd.rawQuery(query,null);
            if (fila.moveToFirst())
            {
                entidad= new clsTrack();
                entidad.setInt_id(fila.getInt(0));
                entidad.setDou_latitud(fila.getDouble(1));
                entidad.setDou_longitud(fila.getDouble(2));
                entidad.setDat_fec_reg(new Date(fila.getLong(3)));
            }
        }
        bd.close();
        return entidad;
    }


     public static void Borrar(Context context) {
     bdSQLite admin=new bdSQLite(context,null);
     SQLiteDatabase bd=admin.getWritableDatabase();
     bd.delete(NOMBRE_TABLA, null, null);
     bd.close();
    }
   
}
