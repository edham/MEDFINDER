/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.med.finder.doctor.dao;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;

import com.med.finder.doctor.entidades.clsDoctor;
import com.med.finder.doctor.entidades.clsEspecialidad;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Toditos
 */
public class clsDoctorDAO {
    
    private static String NOMBRE_TABLA="DOCTOR";

    public static boolean AgregarLogin(Context context,String data)
    {
        boolean retorno=true;
        int id;
        bdSQLite admin=new bdSQLite(context, null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        try{
                bd.delete(NOMBRE_TABLA, null, null);

                JSONObject json_data = new JSONObject(data);
                ContentValues registro = new ContentValues();
                registro.put("int_id_doctor",json_data.getInt("doctorId"));
                registro.put("str_nombres",json_data.getString("personaNombre"));
                registro.put("str_apellido_paterno",json_data.getString("personaApellidoPaterno"));
                registro.put("str_dni",json_data.getString("personaDni"));
                registro.put("str_codigo_colegiatura",json_data.getString("doctorCodigoColegiatura"));
                registro.put("str_direccion",json_data.getString("doctorDireccion"));
                registro.put("str_direccion_detalle",json_data.getString("doctorDireccionDetalle"));
                registro.put("str_telefono",json_data.getString("doctorTelefono"));
                registro.put("dou_longitud",json_data.getDouble("doctorLongitud"));
                registro.put("dou_latitud",json_data.getDouble("doctorLatitud"));
                registro.put("int_puntuje",json_data.getString("doctorPuntaje"));
                registro.put("str_apellido_materno",json_data.getString("personaApellidoMaterno"));
                registro.put("int_id_especialidad",json_data.getInt("doctorEspecialidadId"));

                if(!json_data.getString("personaFoto").equals(""))
                    registro.put("byte_foto",Base64.decode(json_data.getString("personaFoto"), Base64.NO_WRAP | Base64.URL_SAFE));
/*+
            + "str_usuario text,"
            + "str_clave text,"
 */

                id = (int) bd.insert(NOMBRE_TABLA, null, registro);
                if(id<1)
                {
                    retorno=false;
                }
        }
        catch (Exception e)
        {
            retorno=false;
        }
        bd.close();
        return retorno;
    }

     public static int Agregar(Context context,clsDoctor entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("int_id_doctor",entidad.getInt_id_doctor());
        registro.put("str_nombres",entidad.getStr_nombres());
        registro.put("str_apellido_paterno",entidad.getStr_apellido_paterno());
        registro.put("str_dni",entidad.getStr_dni());
        registro.put("str_codigo_colegiatura",entidad.getStr_codigo_colegiatura());
        registro.put("str_direccion",entidad.getStr_direccion());
        registro.put("str_direccion_detalle",entidad.getStr_direccion_detalle());
        registro.put("str_telefono",entidad.getStr_telefono());
        registro.put("dou_longitud",entidad.getDou_longitud());
        registro.put("dou_latitud",entidad.getDou_latitud());
        registro.put("int_puntuje",entidad.getInt_puntuje());
        registro.put("str_apellido_materno",entidad.getStr_apellido_materno());
        registro.put("int_id_especialidad",entidad.getObjEspecialidad().getInt_id_especialidad());
        
        if(entidad.getByte_foto()!=null)
            registro.put("byte_foto",entidad.getByte_foto());
        
        registro.put("bol_favorito",0);
        
        
                
        id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        bd.close();    
        return id;

    }   

     public  static boolean Actualizar(Context context,clsDoctor entidad) 
     {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("str_nombres",entidad.getStr_nombres());
        registro.put("str_apellido_paterno",entidad.getStr_apellido_paterno());
        registro.put("str_dni",entidad.getStr_dni());
        registro.put("str_codigo_colegiatura",entidad.getStr_codigo_colegiatura());
        registro.put("str_direccion",entidad.getStr_direccion());
        registro.put("str_direccion_detalle",entidad.getStr_direccion_detalle());
        registro.put("str_telefono",entidad.getStr_telefono());
        registro.put("dou_longitud",entidad.getDou_longitud());
        registro.put("dou_latitud",entidad.getDou_latitud());
        registro.put("int_puntuje",entidad.getInt_puntuje());
        registro.put("str_apellido_materno",entidad.getStr_apellido_materno());
        registro.put("int_id_especialidad",entidad.getObjEspecialidad().getInt_id_especialidad());
        if(entidad.getByte_foto()!=null)
        registro.put("byte_foto",entidad.getByte_foto());
        
        int cant = bd.update(NOMBRE_TABLA, registro, "int_id_doctor="+entidad.getInt_id_doctor(), null);
        bd.close();
        if(cant==1)
            return true;
        else
            return false;
       
    }  

     public static  clsDoctor Buscar(Context context)
     {
        clsDoctor  entidad=null;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select doc.int_id_doctor,doc.str_nombres,doc.str_apellido_paterno,doc.str_dni,"
                    + "doc.str_codigo_colegiatura,doc.str_direccion,doc.str_direccion_detalle,"
                    + "doc.str_telefono,doc.dou_longitud,doc.dou_latitud,doc.int_puntuje,doc.str_apellido_materno,"
                    + "doc.byte_foto,doc.int_id_especialidad,esp.str_nombre from "+NOMBRE_TABLA+" as doc inner join ESPECIALIDAD esp "
                    + "on doc.int_id_especialidad=esp.int_id_especialidad";
            


            Cursor fila=bd.rawQuery(query,null);
            if (fila.moveToFirst())
            {                    
                entidad= new clsDoctor();            
                entidad.setInt_id_doctor(fila.getInt(0));
                entidad.setStr_nombres(fila.getString(1));
                entidad.setStr_apellido_paterno(fila.getString(2));
                entidad.setStr_dni(fila.getString(3));
                entidad.setStr_codigo_colegiatura(fila.getString(4));
                entidad.setStr_direccion(fila.getString(5));
                entidad.setStr_direccion_detalle(fila.getString(6));
                entidad.setStr_telefono(fila.getString(7));
                entidad.setDou_longitud(fila.getDouble(8));
                entidad.setDou_latitud(fila.getDouble(9));
                entidad.setInt_puntuje(fila.getInt(10));
                entidad.setStr_apellido_materno(fila.getString(11));
                entidad.setByte_foto(fila.getBlob(12));
                entidad.setObjEspecialidad(new clsEspecialidad(fila.getInt(13),fila.getString(14)));
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
