/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.seguridad.dao;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sinergia.seguridad.entidades.clsCuadrante;
import com.sinergia.seguridad.entidades.clsSesionVigilancia;
import com.sinergia.seguridad.entidades.clsTrack;

import java.util.Date;


/**
 *
 * @author Toditos
 */
public class clsSesionVigilanciaDAO {
    
    private static String NOMBRE_TABLA="SESION_VIGILANCIA";



    public static int Agregar(Context context,clsSesionVigilancia entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        if(bd!=null)
        {
            ContentValues registro=new ContentValues();
            registro.put("int_id",entidad.getInt_id());
            registro.put("dat_fec_ini",entidad.getDat_fec_ini().getTime());
            registro.put("int_id_cuadrante",entidad.getObjCuadrante().getInt_id());
            registro.put("dat_fec_vigilancia",0L);


            id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        }
        bd.close();
        return id;
    }

    public static clsSesionVigilancia Buscar(Context context)
    {
        clsSesionVigilancia entidad=null;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        if(bd!=null)
        {
            String query="select sv.int_id,sv.dat_fec_ini,sv.int_id_cuadrante,cu.str_nombre,cu.str_descripcion,sv.dat_fec_vigilancia from "
                    +NOMBRE_TABLA+" as sv inner join CUADRANTE as cu on cu.int_id=sv.int_id_cuadrante";

            Cursor fila=bd.rawQuery(query,null);

            if (fila.moveToFirst())
            {
                clsCuadrante objCuadrante = new clsCuadrante();
                objCuadrante.setInt_id(fila.getInt(2));
                objCuadrante.setStr_nombre(fila.getString(3));
                objCuadrante.setStr_descripcion(fila.getString(4));

                entidad= new clsSesionVigilancia();
                entidad.setInt_id(fila.getInt(0));
                entidad.setDat_fec_ini(new Date(fila.getLong(1)));
                entidad.setObjCuadrante(objCuadrante);
                entidad.setDat_fec_vigilancia(fila.getLong(5));
            }
        }
        bd.close();
        return entidad;
    }


    public  static boolean ActualizarFecha(Context context,clsSesionVigilancia entidad) {
        int cant = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        try
        {
            ContentValues registro=new ContentValues();
            registro.put("dat_fec_vigilancia", entidad.getDat_fec_vigilancia());

            cant = bd.update(NOMBRE_TABLA, registro, "int_id=" + entidad.getInt_id(), null);
        }
        catch (Exception e)
        {
            Log.wtf("clsOrganismoSQL", "Actualizar");
        }
        bd.close();
        return (cant == 1)?true:false;
    }




    public static void Borrar(Context context) {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        bd.delete(NOMBRE_TABLA, null, null);
        bd.close();
    }
}
