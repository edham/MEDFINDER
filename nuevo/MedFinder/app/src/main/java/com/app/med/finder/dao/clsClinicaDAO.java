/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.med.finder.dao;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;

import com.app.med.finder.entidades.clsClinica;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Toditos
 */
public class clsClinicaDAO {
    
    private static String NOMBRE_TABLA="CLINICA";
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
                registro.put("int_id_clinica", json_data.getInt("clinicaId"));
                registro.put("str_nombre", json_data.getString("clinicaNombre"));
                registro.put("str_slogan",json_data.getString("clinicaSlogan"));
                registro.put("str_direccion",json_data.getString("clinicaDireccion"));
                registro.put("str_descripcion",json_data.getString("clinicaDescripcion"));
                registro.put("dat_hora_inicio",json_data.getLong("clinicaHorarioInicio"));
                registro.put("dat_hora_fin",json_data.getLong("clinicaHorarioFin"));
                registro.put("str_clinica_atencion",json_data.getString("clinicaDetalleAtencion"));
                registro.put("str_telefono",json_data.getString("clinicaTelefono"));
                registro.put("str_detalle_atencion",json_data.getString("clinicaDetalleAtencion"));
                registro.put("dou_longitud",json_data.getDouble("clinicaLongitud"));
                registro.put("dou_latitud", json_data.getDouble("clinicaLatitud"));
                registro.put("int_estado", json_data.getString("clinicaEstado"));

                if(!json_data.getString("clinicaLogo").equals(""))
                 registro.put("byte_logo", Base64.decode(json_data.getString("clinicaLogo"), Base64.NO_WRAP | Base64.URL_SAFE));

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
     public static int Agregar(Context context,clsClinica entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("int_id_clinica",entidad.getInt_id_clinica());
        registro.put("str_nombre",entidad.getStr_nombre());
        registro.put("str_slogan",entidad.getStr_slogan());
        registro.put("str_direccion",entidad.getStr_direccion());
        registro.put("str_descripcion",entidad.getStr_descripcion());
        registro.put("dat_hora_inicio",entidad.getDat_hora_inicio().getTime());
        registro.put("dat_hora_fin",entidad.getDat_hora_fin().getTime());
        registro.put("str_clinica_atencion",entidad.getStr_clinica_atencion());
        registro.put("str_telefono",entidad.getStr_telefono());
        registro.put("str_detalle_atencion",entidad.getStr_detalle_atencion());
        registro.put("dou_longitud",entidad.getDou_longitud());
        registro.put("dou_latitud",entidad.getDou_latitud());
        registro.put("int_estado",entidad.getInt_estado());
        
        if(entidad.getByte_logo()!=null)
            registro.put("byte_logo",entidad.getByte_logo());
        
        id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        bd.close();    
        return id;

    }   

     public  static boolean Actualizar(Context context,clsClinica entidad) 
     {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("str_nombre",entidad.getStr_nombre());
        registro.put("str_slogan",entidad.getStr_slogan());
        registro.put("str_direccion",entidad.getStr_direccion());
        registro.put("str_descripcion",entidad.getStr_descripcion());
        registro.put("dat_hora_inicio",entidad.getDat_hora_inicio().getTime());
        registro.put("dat_hora_fin",entidad.getDat_hora_fin().getTime());
        registro.put("str_clinica_atencion",entidad.getStr_clinica_atencion());
        registro.put("str_telefono",entidad.getStr_telefono());
        registro.put("str_detalle_atencion",entidad.getStr_detalle_atencion());
        registro.put("dou_longitud",entidad.getDou_longitud());
        registro.put("dou_latitud",entidad.getDou_latitud());
        registro.put("int_estado",entidad.getInt_estado());
        
        if(entidad.getByte_logo()!=null)
            registro.put("byte_logo",entidad.getByte_logo());
        
        int cant = bd.update(NOMBRE_TABLA, registro, "int_id_clinica="+entidad.getInt_id_clinica(), null);
        bd.close();
        if(cant==1)
            return true;
        else
            return false;
       
    }  
     
     public static  clsClinica Buscar(Context context,int id)
     {
        clsClinica  entidad=null;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id_clinica,str_nombre,str_slogan,"
                    + "str_direccion,str_descripcion,dat_hora_inicio,"
                    + "dat_hora_fin,str_clinica_atencion,str_telefono,"
                    + "str_detalle_atencion,dou_longitud,dou_latitud,"
                    + "int_estado,byte_logo from "+NOMBRE_TABLA+" where int_id_clinica="+id;

            Cursor fila=bd.rawQuery(query,null);
            if (fila.moveToFirst())
            {                    
                entidad= new clsClinica();            
                entidad.setInt_id_clinica(fila.getInt(0));
                entidad.setStr_nombre(fila.getString(1));
                entidad.setStr_slogan(fila.getString(2));
                entidad.setStr_direccion(fila.getString(3));
                entidad.setStr_descripcion(fila.getString(4));
                entidad.setDat_hora_inicio(new Date(fila.getLong(5)));
                entidad.setDat_hora_fin(new Date(fila.getLong(6)));
                entidad.setStr_clinica_atencion(fila.getString(7));
                entidad.setStr_telefono(fila.getString(8));
                entidad.setStr_detalle_atencion(fila.getString(9));
                entidad.setDou_longitud(fila.getDouble(10));
                entidad.setDou_latitud(fila.getDouble(11));
                entidad.setInt_estado(fila.getInt(12));
                entidad.setByte_logo(fila.getBlob(13));
            }
        }
        bd.close();   
        return entidad;
     }
     
     public static  List<clsClinica> Listar(Context context,int IdEspecialidad)
     {
        List<clsClinica> list=new ArrayList<clsClinica>();
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select distinct C.int_id_clinica,C.str_nombre,C.str_slogan,"
                    + "C.str_direccion,C.str_descripcion,C.dat_hora_inicio,"
                    + "C.dat_hora_fin,C.str_clinica_atencion,C.str_telefono,"
                    + "C.str_detalle_atencion,C.dou_longitud,C.dou_latitud,"
                    + "C.int_estado,C.byte_logo from "+NOMBRE_TABLA
		      +" AS C INNER JOIN CLINICA_ESPECIALIDAD AS E ON C.int_id_clinica=E.int_id_clinica";
	      if(IdEspecialidad>0)
		 query+=" where  E.int_id_especialidad="+IdEspecialidad;

            Cursor fila=bd.rawQuery(query,null);
            int numRows = fila.getCount();   
            fila.moveToFirst();   
                for (int i = 0; i < numRows; ++i) 
                {   
                    clsClinica entidad= new clsClinica();            
                    entidad.setInt_id_clinica(fila.getInt(0));
                    entidad.setStr_nombre(fila.getString(1));
                    entidad.setStr_slogan(fila.getString(2));
                    entidad.setStr_direccion(fila.getString(3));
                    entidad.setStr_descripcion(fila.getString(4));
                    entidad.setDat_hora_inicio(new Date(fila.getLong(5)));
                    entidad.setDat_hora_fin(new Date(fila.getLong(6)));
                    entidad.setStr_clinica_atencion(fila.getString(7));
                    entidad.setStr_telefono(fila.getString(8));
                    entidad.setStr_detalle_atencion(fila.getString(9));
                    entidad.setDou_longitud(fila.getDouble(10));
                    entidad.setDou_latitud(fila.getDouble(11));
                    entidad.setInt_estado(fila.getInt(12));
                    entidad.setByte_logo(fila.getBlob(13));
                    
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
