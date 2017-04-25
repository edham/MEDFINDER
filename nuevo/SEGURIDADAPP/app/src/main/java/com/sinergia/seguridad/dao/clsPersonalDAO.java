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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Toditos
 */
public class clsPersonalDAO {
    
    private static String NOMBRE_TABLA="PERSONAL";

    public static boolean AgregarLogin(Context context,String data,int idSesion)
    {
        boolean retorno=true;
        int id;
        bdSQLite admin=new bdSQLite(context, null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        try
        {
            //bd.delete(NOMBRE_TABLA, null, null);
            JSONArray listPersonalJSON = new JSONArray(data);
            for(int i=0;i<listPersonalJSON.length();i++){
                JSONObject json_Entidad = listPersonalJSON.getJSONObject(i);
                ContentValues registro = new ContentValues();
                registro.put("int_id",json_Entidad.getInt("id"));
                registro.put("str_nombres", json_Entidad.getString("nombres"));
                registro.put("str_ape_paterno",json_Entidad.getString("ape_paterno"));
                registro.put("str_usuario","");
                registro.put("str_clave","");
                registro.put("int_idSesion",idSesion);
                registro.put("str_ape_materno",json_Entidad.getString("ape_materno"));
                registro.put("str_telefono",json_Entidad.getString("telefono"));
                registro.put("str_cargo",json_Entidad.getString("cargo"));
                registro.put("bool_principal",json_Entidad.getInt("principal"));
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
    
     public static int Agregar(Context context,clsPersonal entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        if(bd!=null)
        {
            ContentValues registro=new ContentValues();
            registro.put("int_id",entidad.getInt_id());
            registro.put("str_nombres",entidad.getStr_nombres());
            registro.put("str_ape_paterno",entidad.getStr_ape_paterno());
            registro.put("str_usuario",entidad.getStr_usuario());
            registro.put("str_clave",entidad.getStr_clave());
            registro.put("str_ape_materno",entidad.getStr_ape_materno());
            registro.put("str_telefono",entidad.getStr_telefono());
            registro.put("str_cargo",entidad.getStr_cargo());
            registro.put("bool_principal",(entidad.isBool_principal())?1:0);
            id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        }
        bd.close();  
        return id;
    }

    public static List<clsPersonal> ListarIdSesion(Context context,int idSesion)
    {
        List<clsPersonal> list=new ArrayList<clsPersonal>();
        bdSQLite admin=new bdSQLite(context, null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        if(bd!=null)
        {
            String query="select int_id,str_nombres,str_ape_paterno,str_ape_materno,str_usuario," +
                    "str_clave,str_telefono,str_cargo,bool_principal from "+NOMBRE_TABLA+" where int_idSesion="
                    +idSesion+" order by bool_principal desc";

            Cursor fila=bd.rawQuery(query,null);
            int numRows = fila.getCount();
            fila.moveToFirst();
            for (int i = 0; i < numRows; ++i)
            {
                clsPersonal  entidad= new clsPersonal();
                entidad.setInt_id(fila.getInt(0));
                entidad.setStr_nombres(fila.getString(1));
                entidad.setStr_ape_paterno(fila.getString(2));
                entidad.setStr_ape_materno(fila.getString(3));
                entidad.setStr_usuario(fila.getString(4));
                entidad.setStr_clave(fila.getString(5));
                entidad.setStr_telefono(fila.getString(6));
                entidad.setStr_cargo(fila.getString(7));
                if(fila.getInt(8)==1)
                    entidad.setBool_principal(true);
                else
                    entidad.setBool_principal(false);
                list.add(entidad);

                fila.moveToNext();
            }
        }
        bd.close();
        return list;
    }
   
     public static clsPersonal Buscar(Context context)
     {
        clsPersonal entidad=null;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id,str_nombres,str_ape_paterno,str_ape_materno,str_usuario," +
                    "str_clave,str_telefono,str_cargo,bool_principal from "+NOMBRE_TABLA+" where bool_principal=1";

            Cursor fila=bd.rawQuery(query,null);

            if (fila.moveToFirst())
            {                    
                entidad= new clsPersonal();
                entidad.setInt_id(fila.getInt(0));
                entidad.setStr_nombres(fila.getString(1));
                entidad.setStr_ape_paterno(fila.getString(2));
                entidad.setStr_ape_materno(fila.getString(3));
                entidad.setStr_usuario(fila.getString(4));
                entidad.setStr_clave(fila.getString(5));
                entidad.setStr_telefono(fila.getString(6));
                entidad.setStr_cargo(fila.getString(7));
                if(fila.getInt(8)==1)
                entidad.setBool_principal(true);
                else
                entidad.setBool_principal(false);
                      

                

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
