/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.seguridad.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinergia.seguridad.entidades.clsPersonal;
import com.sinergia.seguridad.entidades.clsVehiculo;


/**
 *
 * @author Toditos
 */
public class clsVehiculoDAO {
    
    private static String NOMBRE_TABLA="VEHICULO";
    
     public static int Agregar(Context context,clsVehiculo entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        if(bd!=null)
        {
            ContentValues registro=new ContentValues();
            registro.put("int_id",entidad.getInt_id());
            registro.put("int_numero",entidad.getInt_numero());
            registro.put("str_placa",entidad.getStr_placa());
            registro.put("str_tipo",entidad.getStr_tipo());
            registro.put("str_marca",entidad.getStr_marca());
            registro.put("str_clase",entidad.getStr_clase());
            registro.put("int_idSesion",entidad.getInt_idSesion());
            id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        }
        bd.close();  
        return id;
    }   
   
     public static clsVehiculo BuscarIdSesion(Context context,int idSesion)
     {
         clsVehiculo entidad=null;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id,int_numero,str_placa,str_tipo,str_marca,str_clase from "+NOMBRE_TABLA
                    +" where int_idSesion="+idSesion;

            Cursor fila=bd.rawQuery(query,null);

            if (fila.moveToFirst())
            {                    
                entidad= new clsVehiculo();
                entidad.setInt_id(fila.getInt(0));
                entidad.setInt_numero(fila.getInt(1));
                entidad.setStr_placa(fila.getString(2));
                entidad.setStr_tipo(fila.getString(3));
                entidad.setStr_marca(fila.getString(4));
                entidad.setStr_clase(fila.getString(5));
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
