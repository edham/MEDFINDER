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
import com.med.finder.cliente.entidades.clsPaciente;
import com.med.finder.cliente.entidades.clsPreguntaPaciente;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Toditos
 */
public class clsPreguntaPacienteDAO {
    
    private static String NOMBRE_TABLA="PREGUNTA_PACIENTE";

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
                registro.put("int_id_pregunta_paciente",json_data.getInt("preguntaPacienteId"));
                registro.put("int_id_paciente",json_data.getInt("pacienteId"));
                registro.put("int_id_especialidad",json_data.getInt("especialidadId"));
                registro.put("str_asunto",json_data.getString("preguntaPacienteAsunto"));
                registro.put("str_paciente_detalle",json_data.getString("preguntaPacienteDetalle"));
                registro.put("dat_inicio", new Date(json_data.getLong("preguntaPacienteFechaInicio")).getTime());
                registro.put("int_estado",json_data.getInt("preguntaPacienteEstado"));

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
     public static int Agregar(Context context,clsPreguntaPaciente entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("int_id_pregunta_paciente",entidad.getInt_id_pregunta_paciente());
        registro.put("int_id_paciente",entidad.getObjPaciente().getInt_id_paciente());
        registro.put("int_id_especialidad",entidad.getObjEspecialidad().getInt_id_especialidad());
        registro.put("str_asunto",entidad.getStr_asunto());
        registro.put("str_paciente_detalle",entidad.getStr_paciente_detalle());
        registro.put("dat_inicio",entidad.getDat_inicio().getTime());
        registro.put("int_estado",entidad.getInt_estado());

        id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        bd.close();    
        return id;
    }   

     public  static boolean Actualizar(Context context,clsPreguntaPaciente entidad)
     {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("int_id_paciente",entidad.getObjPaciente().getInt_id_paciente());
        registro.put("int_id_especialidad",entidad.getObjEspecialidad().getInt_id_especialidad());
        registro.put("str_asunto",entidad.getStr_asunto());
        registro.put("str_paciente_detalle",entidad.getStr_paciente_detalle());
        registro.put("dat_inicio",entidad.getDat_inicio().getTime());
        registro.put("int_estado",entidad.getInt_estado());
       
        int cant = bd.update(NOMBRE_TABLA, registro, "int_id_pregunta_paciente="+entidad.getInt_id_pregunta_paciente(), null);
        bd.close();
        if(cant==1)
            return true;
        else
            return false;
       
    }  
     
     public static clsPreguntaPaciente Buscar(Context context,int id)
     {
        clsPreguntaPaciente entidad=null;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id_pregunta_paciente,int_id_paciente,int_id_especialidad,str_asunto,"
                    + "str_paciente_detalle,dat_inicio,int_estado from "+NOMBRE_TABLA+" where int_id_pregunta_paciente="+id;

            Cursor fila=bd.rawQuery(query,null);
            if (fila.moveToFirst())
            {                    
                entidad= new clsPreguntaPaciente();
                entidad.setInt_id_pregunta_paciente(fila.getInt(0));
                entidad.setObjPaciente(new clsPaciente(fila.getInt(1)));
                entidad.setObjEspecialidad(new clsEspecialidad(fila.getInt(2)));
                entidad.setStr_asunto(fila.getString(3));
                entidad.setStr_paciente_detalle(fila.getString(4));
                entidad.setDat_inicio(new Date(fila.getLong(5)));
                entidad.setInt_estado(fila.getInt(6));

              

            }
        }
        bd.close();   
        return entidad;
     }
     
     
      public static clsPreguntaPaciente BuscarXEstado(Context context)
     {
        clsPreguntaPaciente entidad=null;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id_pregunta_paciente,int_id_paciente,int_id_especialidad,str_asunto,"
                    + "str_paciente_detalle,dat_inicio,int_estado from "+NOMBRE_TABLA
                    +" where int_estado=0 order by int_id_pregunta_paciente desc limit 1";

            Cursor fila=bd.rawQuery(query,null);
            if (fila.moveToFirst())
            {                    
                entidad= new clsPreguntaPaciente();
                entidad.setInt_id_pregunta_paciente(fila.getInt(0));
                entidad.setObjPaciente(new clsPaciente(fila.getInt(1)));
                entidad.setObjEspecialidad(new clsEspecialidad(fila.getInt(2)));
                entidad.setStr_asunto(fila.getString(3));
                entidad.setStr_paciente_detalle(fila.getString(4));
                entidad.setDat_inicio(new Date(fila.getLong(5)));
                entidad.setInt_estado(fila.getInt(6));
                
              

            }
        }
        bd.close();   
        return entidad;
     }
     
     public static  List<clsPreguntaPaciente> Listar(Context context)
     {
        List<clsPreguntaPaciente> list=new ArrayList<clsPreguntaPaciente>();
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
        String query="select pre.int_id_pregunta_paciente,pre.int_id_paciente,pre.int_id_especialidad,pre.str_asunto,"
                    + "pre.str_paciente_detalle,pre.dat_inicio,pre.int_estado,(select count(*) from RESPUESTA_PREGUNTA_PACIENTE where int_id_pregunta_paciente=pre.int_id_pregunta_paciente) from "+NOMBRE_TABLA+" as pre order by pre.dat_inicio desc";
        
        Cursor fila=bd.rawQuery(query,null);
        int numRows = fila.getCount();   
        fila.moveToFirst();   
                for (int i = 0; i < numRows; ++i) 
                {   
                    clsPreguntaPaciente entidad= new clsPreguntaPaciente();
                    entidad.setInt_id_pregunta_paciente(fila.getInt(0));
                    entidad.setObjPaciente(new clsPaciente(fila.getInt(1)));
                    entidad.setObjEspecialidad(new clsEspecialidad(fila.getInt(2)));
                    entidad.setStr_asunto(fila.getString(3));
                    entidad.setStr_paciente_detalle(fila.getString(4));
                    entidad.setDat_inicio(new Date(fila.getLong(5)));
                    entidad.setInt_estado(fila.getInt(6));
                    entidad.setInt_respuestas(fila.getInt(7));


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
