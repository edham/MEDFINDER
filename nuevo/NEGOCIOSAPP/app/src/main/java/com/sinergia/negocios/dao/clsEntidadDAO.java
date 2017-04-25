/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.negocios.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinergia.negocios.entidades.clsEntidad;


/**
 *
 * @author Toditos
 */
public class clsEntidadDAO {
    
    private static String NOMBRE_TABLA="ENTIDAD";
    
     public static int Agregar(Context context,clsEntidad entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        if(bd!=null)
        {
            ContentValues registro=new ContentValues();
            registro.put("int_id",entidad.getInt_id());
            registro.put("str_nombre",entidad.getStr_nombre());
            registro.put("str_descripcion",entidad.getStr_descripcion());
            registro.put("str_tipo",entidad.getStr_tipo());


            id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        }
        bd.close();  
        return id;
    }   
   
     public static clsEntidad Buscar(Context context)
     {
         clsEntidad entidad=null;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id,str_nombre,str_descripcion,str_tipo  from "+NOMBRE_TABLA;

            Cursor fila=bd.rawQuery(query,null);

            if (fila.moveToFirst())
            {                    
                entidad= new clsEntidad();
                entidad.setInt_id(fila.getInt(0));
                entidad.setStr_nombre(fila.getString(1));
                entidad.setStr_descripcion(fila.getString(2));
                entidad.setStr_tipo(fila.getString(3));
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
