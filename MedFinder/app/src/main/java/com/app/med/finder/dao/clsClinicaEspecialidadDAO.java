/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.med.finder.dao;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.app.med.finder.entidades.clsClinicaEspecialidad;
import com.app.med.finder.entidades.clsEspecialidad;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;




/**
 *
 * @author Toditos
 */
public class clsClinicaEspecialidadDAO {
    
    private static String NOMBRE_TABLA="CLINICA_ESPECIALIDAD";


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
                registro.put("int_id_clinica_especialidad", json_data.getInt("detalleClinicaEspecialidadId"));
                registro.put("int_id_clinica", json_data.getInt("clinicaId"));
                registro.put("int_id_especialidad", json_data.getInt("EspecialidadId"));
                registro.put("dat_hora_inicio", json_data.getInt("detalleClinicaEspecialidadHorarioInicio"));
                registro.put("dat_hora_fin", json_data.getInt("detalleClinicaEspecialidadHorarioFin"));
                registro.put("str_detalle_horario", json_data.getString("detalleClinicaEspecialidadDetalleHorario"));
                registro.put("int_estado", json_data.getInt("detalleClinicaEspecialidadEstado"));

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
     public static int Agregar(Context context,clsClinicaEspecialidad entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("int_id_clinica_especialidad",entidad.getInt_id_clinica_especialidad());
        registro.put("int_id_clinica",entidad.getInt_id_clinica());
        registro.put("int_id_especialidad",entidad.getObjEspecialidad().getInt_id_especialidad());
        registro.put("dat_hora_inicio",entidad.getDat_hora_inicio().getTime());
        registro.put("dat_hora_fin",entidad.getDat_hora_fin().getTime());
        registro.put("str_detalle_horario",entidad.getStr_detalle_horario());
        registro.put("int_estado",entidad.getInt_estado());
        id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        bd.close();    
        return id;

    }   

     public  static boolean Actualizar(Context context,clsClinicaEspecialidad entidad) 
     {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("int_id_clinica",entidad.getInt_id_clinica());
        registro.put("int_id_especialidad",entidad.getObjEspecialidad().getInt_id_especialidad());
        registro.put("dat_hora_inicio",entidad.getDat_hora_inicio().getTime());
        registro.put("dat_hora_fin",entidad.getDat_hora_fin().getTime());
        registro.put("str_detalle_horario",entidad.getStr_detalle_horario());
        registro.put("int_estado",entidad.getInt_estado()); 
        
        int cant = bd.update(NOMBRE_TABLA, registro, "int_id_clinica_especialidad="+entidad.getInt_id_clinica_especialidad(), null);
        bd.close();
        if(cant==1)
            return true;
        else
            return false;
       
    }  
    
        public static  List<clsClinicaEspecialidad> BuscarXClinica(Context context,int id)
     {
        List<clsClinicaEspecialidad> list=new ArrayList<clsClinicaEspecialidad>();
        bdSQLite admin=new bdSQLite(context,null); 
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
             String query="select E.int_id_especialidad,E.str_nombre,E.str_descripcion,"
                    + "E.int_estado,C.dat_hora_inicio,C.dat_hora_fin,C.str_detalle_horario"
                     + " from ESPECIALIDAD as E inner join "+NOMBRE_TABLA+" as C"
                    + " on E.int_id_especialidad=C.int_id_especialidad  where C.int_id_clinica="+id;
            Cursor fila=bd.rawQuery(query,null);
            int numRows = fila.getCount();   
            fila.moveToFirst();   
                for (int i = 0; i < numRows; ++i) 
                {   
                    clsEspecialidad especialidad= new clsEspecialidad();            
                    especialidad.setInt_id_especialidad(fila.getInt(0));
                    especialidad.setStr_nombre(fila.getString(1));
                    especialidad.setStr_descripcion(fila.getString(2));
                    especialidad.setInt_estado(fila.getInt(3));
                    
                    clsClinicaEspecialidad entidad= new clsClinicaEspecialidad();      
                    entidad.setDat_hora_inicio(new Date(fila.getLong(4)));
                    entidad.setDat_hora_fin(new Date(fila.getLong(5)));
                    entidad.setStr_detalle_horario(fila.getString(6));
                    entidad.setObjEspecialidad(especialidad);
                    
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
