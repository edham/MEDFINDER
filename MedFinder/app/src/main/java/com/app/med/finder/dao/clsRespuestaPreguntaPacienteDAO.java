/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.med.finder.dao;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.app.med.finder.entidades.clsDoctor;
import com.app.med.finder.entidades.clsRespuestaPreguntaPaciente;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




/**
 *
 * @author Toditos
 */
public class clsRespuestaPreguntaPacienteDAO {
    
    private static String NOMBRE_TABLA="RESPUESTA_PREGUNTA_PACIENTE";
     public static int Agregar(Context context,clsRespuestaPreguntaPaciente entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("int_id_respuesta_pregunta_paciente",entidad.getInt_id_respuesta_pregunta_paciente());
        registro.put("int_id_pregunta_paciente",entidad.getInt_id_pregunta_paciente());
        registro.put("int_id_doctor",entidad.getObjDoctor().getInt_id_doctor());
        registro.put("str_detalle",entidad.getStr_detalle());
        registro.put("int_puntaje",entidad.getInt_puntaje());
        registro.put("dat_fecha",entidad.getDat_fecha().getTime());

        id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        bd.close();    
        return id;
    }   

     public  static boolean Actualizar(Context context,clsRespuestaPreguntaPaciente entidad) 
     {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("int_id_pregunta_paciente",entidad.getInt_id_pregunta_paciente());
        registro.put("int_id_doctor",entidad.getObjDoctor().getInt_id_doctor());
        registro.put("str_detalle",entidad.getStr_detalle());
        registro.put("int_puntaje",entidad.getInt_puntaje());
        registro.put("dat_fecha",entidad.getDat_fecha().getTime());
       
        int cant = bd.update(NOMBRE_TABLA, registro, "int_id_respuesta_pregunta_paciente="+entidad.getInt_id_respuesta_pregunta_paciente(), null);
        bd.close();
        if(cant==1)
            return true;
        else
            return false;
       
    }  
        public static  int getMaxId(Context context,int id)
     {
        int idPregunta=0;
        bdSQLite admin=new bdSQLite(context,null); 
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
             String query="select int_id_respuesta_pregunta_paciente from "+NOMBRE_TABLA
                     +" where int_id_pregunta_paciente="+id+" order by int_id_respuesta_pregunta_paciente desc limit 1";
            Cursor fila=bd.rawQuery(query,null);
            if (fila.moveToFirst())
               idPregunta=fila.getInt(0);
        }
        bd.close();   
        return idPregunta;
     }
   
     public static  List<clsRespuestaPreguntaPaciente> Listar(Context context,int id)
     {
        List<clsRespuestaPreguntaPaciente> list=new ArrayList<clsRespuestaPreguntaPaciente>();
        bdSQLite admin=new bdSQLite(context,null); 
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
        String query="select int_id_respuesta_pregunta_paciente,int_id_pregunta_paciente,"
                    + "int_id_doctor,str_detalle,int_puntaje,dat_fecha from "+NOMBRE_TABLA
                    +" where int_id_pregunta_paciente="+id;
        
        Cursor fila=bd.rawQuery(query,null);
        int numRows = fila.getCount();   
        fila.moveToFirst();   
                for (int i = 0; i < numRows; ++i) 
                {   
                    clsRespuestaPreguntaPaciente entidad = new clsRespuestaPreguntaPaciente();            
                    entidad.setInt_id_respuesta_pregunta_paciente(fila.getInt(0));
                    entidad.setInt_id_pregunta_paciente(fila.getInt(1));
                    entidad.setObjDoctor(new clsDoctor(fila.getInt(2)));
                    entidad.setStr_detalle(fila.getString(3));
                    entidad.setInt_puntaje(fila.getInt(4));
                    entidad.setDat_fecha(new Date(fila.getLong(5)));
                    
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
