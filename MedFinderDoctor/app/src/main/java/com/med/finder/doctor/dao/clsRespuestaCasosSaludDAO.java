/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.med.finder.doctor.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.med.finder.doctor.entidades.clsRespuestaCasosSalud;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Toditos
 */
public class clsRespuestaCasosSaludDAO {
    
    private static String NOMBRE_TABLA="RESPUESTA_CASOS_SALUD";
    public static boolean FavoritoLogin(Context context,String data)
    {
        boolean retorno=true;
        int id;
        bdSQLite admin=new bdSQLite(context, null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        try{
            JSONArray listEmpresaJSON = new JSONArray(data);
            for(int i=0;i<listEmpresaJSON.length();i++){
                JSONObject json_data = listEmpresaJSON.getJSONObject(i);
                ContentValues registro = new ContentValues();
                registro.put("bol_puntaje",1);

                id = bd.update(NOMBRE_TABLA, registro, "int_id_respuesta_casos_salud="+json_data.getInt("respuestaCasoSaludId"), null);
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
                registro.put("int_id_respuesta_casos_salud",json_data.getInt("respuestaCasosSaludId"));
                registro.put("int_id_doctor",json_data.getInt("doctorId"));
                registro.put("int_id_casos_salud",json_data.getInt("casosSaludId"));
                registro.put("str_descripcion",json_data.getString("respuestaCasosSaludDescripcion"));
                registro.put("int_puntaje",json_data.getInt("respuestaCasosSaludPuntajeTotal"));
                registro.put("bol_puntaje",0);


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
     public static int Agregar(Context context,clsRespuestaCasosSalud entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("int_id_respuesta_casos_salud",entidad.getInt_id_respuesta_casos_salud());
        registro.put("int_id_doctor",entidad.getInt_id_doctor());
        registro.put("int_id_casos_salud",entidad.getInt_id_casos_salud());
        registro.put("str_descripcion",entidad.getStr_descripcion());
        registro.put("int_puntaje",entidad.getInt_puntaje());
        if(entidad.isBol_puntaje())
        registro.put("bol_puntaje",1);
        else
        registro.put("bol_puntaje",0);
          
        id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        bd.close();    
        return id;
    }   

     public  static boolean Actualizar(Context context,clsRespuestaCasosSalud entidad) 
     {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("int_id_doctor",entidad.getInt_id_doctor());
        registro.put("int_id_casos_salud",entidad.getInt_id_casos_salud());
        registro.put("str_descripcion",entidad.getStr_descripcion());
        registro.put("int_puntaje",entidad.getInt_puntaje());
        if(entidad.isBol_puntaje())
        registro.put("bol_puntaje",1);
        else
        registro.put("bol_puntaje",0);
        
        int cant = bd.update(NOMBRE_TABLA, registro, "int_id_respuesta_casos_salud="+entidad.getInt_id_respuesta_casos_salud(), null);
        bd.close();
        if(cant==1)
            return true;
        else
            return false;
       
    }  

     public  static boolean Favorito(Context context,int Id,boolean estado) 
     {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        if(estado)
        registro.put("bol_puntaje",1);
        else
            registro.put("bol_puntaje",0);
        
        int cant = bd.update(NOMBRE_TABLA, registro, "int_id_respuesta_casos_salud="+Id, null);
        bd.close();
        if(cant==1)
            return true;
        else
            return false;
       
    }  
     public static  List<clsRespuestaCasosSalud> ListarXCasoSalud(Context context,int id)
     {
        List<clsRespuestaCasosSalud> list=new ArrayList<clsRespuestaCasosSalud>();
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id_respuesta_casos_salud,int_id_doctor,int_id_casos_salud,str_descripcion,"
                    + "int_puntaje,bol_puntaje from "+NOMBRE_TABLA+" where int_id_casos_salud="+id;

            Cursor fila=bd.rawQuery(query,null);
            int numRows = fila.getCount();   
            fila.moveToFirst();   
                for (int i = 0; i < numRows; ++i) 
                {   
                    clsRespuestaCasosSalud entidad= new clsRespuestaCasosSalud();            
                    entidad.setInt_id_respuesta_casos_salud(fila.getInt(0));
                    entidad.setInt_id_doctor(fila.getInt(1));
                    entidad.setInt_id_casos_salud(fila.getInt(2));
                    entidad.setStr_descripcion(fila.getString(3));
                    entidad.setInt_puntaje(fila.getInt(4));
                    if(fila.getInt(5)==1)                
                    entidad.setBol_puntaje(true);
                    else
                    entidad.setBol_puntaje(false);
                    
                    list.add(entidad);
                       
                    fila.moveToNext();   
                }   
         }
        bd.close();   
        return list;
     }       
        
     public static void Borrar(Context context) {
     bdSQLite admin=new bdSQLite(context,null);
     SQLiteDatabase bd=admin.getWritableDatabase();
     bd.delete(NOMBRE_TABLA, null, null);
     bd.close();
    }
   
}
