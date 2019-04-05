/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.med.finder.doctor.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.med.finder.doctor.entidades.clsCitaPaciente;
import com.med.finder.doctor.entidades.clsDoctor;
import com.med.finder.doctor.entidades.clsPaciente;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Toditos
 */
public class clsCitaPacienteDAO {
    
    private static String NOMBRE_TABLA="CITA_PACIENTE";

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
            else
                bd.delete(NOMBRE_TABLA, "int_estado=0", null);

            JSONArray listEmpresaJSON = new JSONArray(data);
            for(int i=0;i<listEmpresaJSON.length();i++){
                JSONObject json_data = listEmpresaJSON.getJSONObject(i);
                ContentValues registro = new ContentValues();
                registro.put("int_id_cita_paciente",json_data.getInt("citaPacienteId"));
                registro.put("int_id_doctor",json_data.getInt("doctorId"));
                registro.put("str_detalle",json_data.getString("citaPacienteDetalle"));
                registro.put("dat_creacion",json_data.getLong("citaPacienteFechaRegistro"));
                registro.put("dat_atencion",json_data.getLong("citaPacienteAtencion"));
                registro.put("int_estado",json_data.getInt("citaPacienteEstado"));
                registro.put("int_id_paciente",json_data.getInt("pacienteId"));
                if(!json_data.isNull("citaPacienteRespuesta"))
                registro.put("str_respuesta",json_data.getString("citaPacienteRespuesta"));

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
     public static int Agregar(Context context, clsCitaPaciente entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("int_id_cita_paciente",entidad.getInt_id_cita_paciente());
        registro.put("int_id_doctor",entidad.getObjDoctor().getInt_id_doctor());
        registro.put("str_detalle",entidad.getStr_detalle());
        registro.put("dat_creacion",entidad.getDat_creacion().getTime());
        registro.put("dat_atencion",entidad.getDat_atencion().getTime());
        registro.put("int_estado",entidad.getInt_estado());
        registro.put("int_id_paciente",entidad.getObjPaciente().getInt_id_paciente());
        registro.put("str_respuesta",entidad.getStr_respuesta());


        id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        bd.close();    
        return id;

    }   

     public  static boolean Actualizar(Context context,clsCitaPaciente entidad)
     {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("int_id_doctor",entidad.getObjDoctor().getInt_id_doctor());
        registro.put("str_detalle",entidad.getStr_detalle());
        registro.put("dat_creacion",entidad.getDat_creacion().getTime());
        registro.put("dat_atencion",entidad.getDat_atencion().getTime());
        registro.put("int_estado",entidad.getInt_estado());
        registro.put("int_id_paciente",entidad.getObjPaciente().getInt_id_paciente());
        registro.put("str_respuesta",entidad.getStr_respuesta());

         int cant = bd.update(NOMBRE_TABLA, registro, "int_id_cita_paciente="+entidad.getInt_id_cita_paciente(), null);
        bd.close();
        if(cant==1)
            return true;
        else
            return false;
       
    }  
     
     public static clsCitaPaciente Buscar(Context context,int id)
     {
        clsCitaPaciente entidad=null;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id_cita_paciente,int_id_doctor,str_detalle,dat_creacion,dat_atencion,int_estado,"
                    + "int_id_paciente,str_respuesta from "+NOMBRE_TABLA+" where int_id_cita_paciente="+id;
            


            Cursor fila=bd.rawQuery(query,null);
            if (fila.moveToFirst())
            {                    
                entidad= new clsCitaPaciente();
                entidad.setInt_id_cita_paciente(fila.getInt(0));
                entidad.setObjDoctor(new clsDoctor(fila.getInt(1)));
                entidad.setStr_detalle(fila.getString(2));
                entidad.setDat_creacion(new Date(fila.getLong(3)));
                entidad.setDat_atencion(new Date(fila.getLong(4)));
                entidad.setInt_estado(fila.getInt(5));
                entidad.setObjPaciente(new clsPaciente(fila.getInt(6)));
                entidad.setStr_respuesta(fila.getString(7));
            }
        }
        bd.close();   
        return entidad;
     }
     
      public static clsCitaPaciente BuscarXEstado(Context context)
     {
        clsCitaPaciente entidad=null;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id_cita_paciente,int_id_doctor,str_detalle,dat_creacion,dat_atencion,int_estado,"
                    + "int_id_paciente,str_respuesta from "+NOMBRE_TABLA
                    +" where int_estado=0 order by int_id_cita_paciente desc limit 1";
            


            Cursor fila=bd.rawQuery(query,null);
            if (fila.moveToFirst())
            {                    
                entidad= new clsCitaPaciente();
                entidad.setInt_id_cita_paciente(fila.getInt(0));
                entidad.setObjDoctor(new clsDoctor(fila.getInt(1)));
                entidad.setStr_detalle(fila.getString(2));
                entidad.setDat_creacion(new Date(fila.getLong(3)));
                entidad.setDat_atencion(new Date(fila.getLong(4)));
                entidad.setInt_estado(fila.getInt(5));
                entidad.setObjPaciente(new clsPaciente(fila.getInt(6)));
                entidad.setStr_respuesta(fila.getString(7));
            }
        }
        bd.close();   
        return entidad;
     }
     
     public static  List<clsCitaPaciente> Listar(Context context)
     {
        List<clsCitaPaciente> list=new ArrayList<clsCitaPaciente>();
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id_cita_paciente,int_id_doctor,str_detalle,dat_creacion,dat_atencion,int_estado,"
                    + "int_id_paciente,str_respuesta from "+NOMBRE_TABLA+" order by dat_atencion desc";


            Cursor fila=bd.rawQuery(query,null);
            int numRows = fila.getCount();   
            fila.moveToFirst();   
                for (int i = 0; i < numRows; ++i) 
                {   
                    clsCitaPaciente entidad= new clsCitaPaciente();
                    entidad.setInt_id_cita_paciente(fila.getInt(0));
                    entidad.setObjDoctor(new clsDoctor(fila.getInt(1)));
                    entidad.setStr_detalle(fila.getString(2));
                    entidad.setDat_creacion(new Date(fila.getLong(3)));
                    entidad.setDat_atencion(new Date(fila.getLong(4)));
                    entidad.setInt_estado(fila.getInt(5));
                    entidad.setObjPaciente(new clsPaciente(fila.getInt(6)));
                    entidad.setStr_respuesta(fila.getString(7));
                    
                    list.add(entidad);
                       
                    fila.moveToNext();   
                }   
         }
        bd.close();   
        return list;
     }
     
   public static void BorrarXId(Context context,int id) {
     bdSQLite admin=new bdSQLite(context,null);
     SQLiteDatabase bd=admin.getWritableDatabase();
     bd.delete(NOMBRE_TABLA, "int_id_cita_paciente="+id, null);
     bd.close();
    }
     
     public static void Borrar(Context context) {
     bdSQLite admin=new bdSQLite(context,null);
     SQLiteDatabase bd=admin.getWritableDatabase();
     bd.delete(NOMBRE_TABLA, null, null);
     bd.close();
    }

    public static  int pendienteRespuesta(Context context)
    {
        int total=0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        if(bd!=null)
        {
            String query="select count(*) from "+NOMBRE_TABLA+" where int_estado=0";
            Cursor fila=bd.rawQuery(query,null);
            if (fila.moveToFirst())
            {
                total=fila.getInt(0);
            }
        }
        bd.close();
        return total;
    }
   
}
