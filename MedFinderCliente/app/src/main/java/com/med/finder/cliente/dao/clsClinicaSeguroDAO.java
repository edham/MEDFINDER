/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.med.finder.cliente.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.med.finder.cliente.entidades.clsClinicaSeguro;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author Toditos
 */
public class clsClinicaSeguroDAO {
    
    private static String NOMBRE_TABLA="CLINICA_SEGURO";
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
                registro.put("int_id_clinica_seguro",json_data.getInt("detalleClinicaSeguroId"));
                registro.put("int_id_clinica",json_data.getInt("clinicaId"));
                registro.put("int_id_seguro",json_data.getInt("seguroId"));
                registro.put("int_estado",json_data.getInt("detalleClinicaSeguroEstado"));
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
     public static int Agregar(Context context,clsClinicaSeguro entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("int_id_clinica_seguro",entidad.getInt_id_clinica_seguro());
        registro.put("int_id_clinica",entidad.getInt_id_clinica());
        registro.put("int_id_seguro",entidad.getInt_id_seguro());
        registro.put("int_estado",entidad.getInt_estado());
        
        id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        bd.close();    
        return id;

    }   

     public  static boolean Actualizar(Context context,clsClinicaSeguro entidad) 
     {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("int_id_clinica",entidad.getInt_id_clinica());
        registro.put("int_id_seguro",entidad.getInt_id_seguro());
        registro.put("int_estado",entidad.getInt_estado());
        
        int cant = bd.update(NOMBRE_TABLA, registro, "int_id_clinica_seguro="+entidad.getInt_id_clinica_seguro(), null);
        bd.close();
        if(cant==1)
            return true;
        else
            return false;
       
    }  
    
       
        
     public static void Borrar(Context context) {
     bdSQLite admin=new bdSQLite(context,null);
     SQLiteDatabase bd=admin.getWritableDatabase();
     bd.delete(NOMBRE_TABLA, null, null);
     bd.close();
    }
   
}
