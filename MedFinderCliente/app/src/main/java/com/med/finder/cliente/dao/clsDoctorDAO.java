/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.med.finder.cliente.dao;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;

import com.med.finder.cliente.entidades.clsDoctor;
import com.med.finder.cliente.entidades.clsEspecialidad;

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

                registro.put("bol_favorito",0);


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
                    registro.put("bol_favorito",1);

                id = bd.update(NOMBRE_TABLA, registro, "int_id_doctor="+json_data.getInt("doctorId"), null);
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
        
         if(entidad.isBol_favorito())
        registro.put("bol_favorito",1);
        else
        registro.put("bol_favorito",0);
        
        if(entidad.getByte_foto()!=null)
        registro.put("byte_foto",entidad.getByte_foto());
        
        int cant = bd.update(NOMBRE_TABLA, registro, "int_id_doctor="+entidad.getInt_id_doctor(), null);
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
        registro.put("bol_favorito",1);
        else
            registro.put("bol_favorito",0);
        
        int cant = bd.update(NOMBRE_TABLA, registro, "int_id_doctor="+Id, null);
        bd.close();
        if(cant==1)
            return true;
        else
            return false;
       
    }  
     
     public static  clsDoctor Buscar(Context context,int id)
     {
        clsDoctor  entidad=null;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id_doctor,str_nombres,str_apellido_paterno,str_dni,"
                    + "str_codigo_colegiatura,str_direccion,str_direccion_detalle,"
                    + "str_telefono,dou_longitud,dou_latitud,int_puntuje,str_apellido_materno,"
                    + "byte_foto,bol_favorito,int_id_especialidad from "+NOMBRE_TABLA+" where int_id_doctor="+id;
            


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
                if(fila.getInt(13)==1)                
                entidad.setBol_favorito(true);
                else
                entidad.setBol_favorito(false);
                
                entidad.setObjEspecialidad(new clsEspecialidad(fila.getInt(14)));
            }
        }
        bd.close();   
        return entidad;
     }
     
     public static  List<clsDoctor> Listar(Context context,int IdEspecialidad,boolean favorito)
     {
        List<clsDoctor> list=new ArrayList<clsDoctor>();
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id_doctor,str_nombres,str_apellido_paterno,str_dni,"
                    + "str_codigo_colegiatura,str_direccion,str_direccion_detalle,"
                    + "str_telefono,dou_longitud,dou_latitud,int_puntuje,str_apellido_materno,"
                    + "byte_foto,bol_favorito,int_id_especialidad from "+NOMBRE_TABLA;
	     if(IdEspecialidad>0 || favorito)
         {
             query+=" where ";
             if(IdEspecialidad>0)
             {
                 query+=" int_id_especialidad="+IdEspecialidad;
             }
             if(favorito)
             {
                 query+=((IdEspecialidad>0)?" and ":"")+" bol_favorito=1";
             }

         }


            Cursor fila=bd.rawQuery(query,null);
            int numRows = fila.getCount();   
            fila.moveToFirst();   
                for (int i = 0; i < numRows; ++i) 
                {   
                    clsDoctor entidad= new clsDoctor();            
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
                    if(fila.getInt(13)==1)                
                    entidad.setBol_favorito(true);
                    else
                    entidad.setBol_favorito(false);
                    
                    entidad.setObjEspecialidad(new clsEspecialidad(fila.getInt(14)));
                    
                    list.add(entidad);
                       
                    fila.moveToNext();   
                }   
         }
        bd.close();   
        return list;
     }
     
     public static  List<clsDoctor> ListarFavoritos(Context context)
     {
        List<clsDoctor> list=new ArrayList<clsDoctor>();
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id_doctor,str_nombres,str_apellido_paterno,str_dni,"
                    + "str_codigo_colegiatura,str_direccion,str_direccion_detalle,"
                    + "str_telefono,dou_longitud,dou_latitud,int_puntuje,str_apellido_materno,"
                    + "byte_foto,bol_favorito,int_id_especialidad from "+NOMBRE_TABLA+" where bol_favorito=1";

            Cursor fila=bd.rawQuery(query,null);
            int numRows = fila.getCount();   
            fila.moveToFirst();   
                for (int i = 0; i < numRows; ++i) 
                {   
                    clsDoctor entidad= new clsDoctor();            
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
                    if(fila.getInt(13)==1)                
                    entidad.setBol_favorito(true);
                    else
                    entidad.setBol_favorito(false);
                    
                    entidad.setObjEspecialidad(new clsEspecialidad(fila.getInt(14)));
                    
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
