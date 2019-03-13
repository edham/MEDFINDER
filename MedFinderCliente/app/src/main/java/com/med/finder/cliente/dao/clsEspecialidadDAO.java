/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.med.finder.cliente.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.med.finder.cliente.entidades.clsEspecialidad;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Toditos
 */
public class clsEspecialidadDAO {
    
    private static String NOMBRE_TABLA="ESPECIALIDAD";
    public static boolean AgregarLogin(Context context,String data,boolean login)
    {
        boolean retorno=true;
        int id;
        bdSQLite admin=new bdSQLite(context, null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        try
        {
            if(login)
                bd.delete(NOMBRE_TABLA, null, null);
            JSONArray listEmpresaJSON = new JSONArray(data);
            for(int i=0;i<listEmpresaJSON.length();i++){
                JSONObject json_data = listEmpresaJSON.getJSONObject(i);
                ContentValues registro = new ContentValues();
                registro.put("int_id_especialidad",json_data.getInt("especialidadId"));
                registro.put("str_nombre",json_data.getString("especialidadNombre"));
                registro.put("str_descripcion",json_data.getString("especialidadDescripcion"));
                registro.put("int_estado",json_data.getInt("especialidadEstado"));

                id = (int) bd.insert(NOMBRE_TABLA, null, registro);
                if(id==0)
                {
                    retorno=false;
                }
            }
        }
        catch (Exception e)
        {
            retorno=false;
        }
        bd.close();
        return retorno;
    }
     public static int Agregar(Context context,clsEspecialidad entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("int_id_especialidad",entidad.getInt_id_especialidad());
        registro.put("str_nombre",entidad.getStr_nombre());
        registro.put("str_descripcion",entidad.getStr_descripcion());
        registro.put("int_estado",entidad.getInt_estado());
        
        id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        bd.close();    
        return id;
    }   

     public  static boolean Actualizar(Context context,clsEspecialidad entidad)
     {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("str_nombre",entidad.getStr_nombre());
        registro.put("str_descripcion",entidad.getStr_descripcion());
        registro.put("int_estado",entidad.getInt_estado());
        
        int cant = bd.update(NOMBRE_TABLA, registro, "int_id_especialidad="+entidad.getInt_id_especialidad(), null);
        bd.close();
        if(cant==1)
            return true;
        else
            return false;
       
    }  
     
     public static clsEspecialidad Buscar(Context context,int id)
     {
        clsEspecialidad entidad=null;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id_especialidad,str_nombre,str_descripcion,"
                    + "int_estado from "+NOMBRE_TABLA+" where int_id_especialidad="+id;

            Cursor fila=bd.rawQuery(query,null);
            if (fila.moveToFirst())
            {                    
                entidad= new clsEspecialidad();
                entidad.setInt_id_especialidad(fila.getInt(0));
                entidad.setStr_nombre(fila.getString(1));
                entidad.setStr_descripcion(fila.getString(2));
                entidad.setInt_estado(fila.getInt(3));
                
            }
        }
        bd.close();   
        return entidad;
     }
     
     
   
  
       
    
       
       
     public static  List<clsEspecialidad> Listar(Context context)
     {
        List<clsEspecialidad> list=new ArrayList<clsEspecialidad>();
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id_especialidad,str_nombre,str_descripcion,int_estado from "+NOMBRE_TABLA+" order by str_nombre";

            Cursor fila=bd.rawQuery(query,null);
            int numRows = fila.getCount();   
            fila.moveToFirst();   
                for (int i = 0; i < numRows; ++i) 
                {   
                    clsEspecialidad entidad= new clsEspecialidad();
                    entidad.setInt_id_especialidad(fila.getInt(0));
                    entidad.setStr_nombre(fila.getString(1));
                    entidad.setStr_descripcion(fila.getString(2));
                    entidad.setInt_estado(fila.getInt(3));
                    
                    list.add(entidad);
                       
                    fila.moveToNext();   
                }   
         }
        bd.close();   
        return list;
     }
//       
        
     public static void Borrar(Context context) {
     bdSQLite admin=new bdSQLite(context,null);
     SQLiteDatabase bd=admin.getWritableDatabase();
     bd.delete(NOMBRE_TABLA, null, null);
     bd.close();
    }
   
}
