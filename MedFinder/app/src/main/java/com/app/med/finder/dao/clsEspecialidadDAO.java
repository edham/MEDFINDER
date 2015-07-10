/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.med.finder.dao;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.app.med.finder.entidades.clsEspecialidad;
import java.util.ArrayList;
import java.util.List;




/**
 *
 * @author Toditos
 */
public class clsEspecialidadDAO {
    
    private static String NOMBRE_TABLA="ESPECIALIDAD";
     public static int Agregar(Context context,clsEspecialidad entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("int_id_especialidad",entidad.getInt_id_especialidad());
        registro.put("str_nombre",entidad.getStr_nombre());
        registro.put("str_descripcion",entidad.getStr_descripcion());
        registro.put("int_estado",entidad.getInt_estado());
        
        id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        bd.close();    
        return id;
    }   

     public  static boolean Actualizar(Context context,clsEspecialidad entidad) 
     {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("str_nombre",entidad.getStr_nombre());
        registro.put("str_descripcion",entidad.getStr_descripcion());
        registro.put("int_estado",entidad.getInt_estado());
        
        int cant = bd.update(NOMBRE_TABLA, registro, "int_id_especialidad="+entidad.getInt_id_especialidad(), null);
        bd.close();
        if(cant==1)
            return true;
        else
            return false;
       
    }  
     
     public static  clsEspecialidad Buscar(Context context,int id)
     {
        clsEspecialidad  entidad=null;
        bdSQLite admin=new bdSQLite(context,null); 
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id_especialidad,str_nombre,str_descripcion,"
                    + "int_estado from "+NOMBRE_TABLA+" where int_id_especialidad="+id;

            Cursor fila=bd.rawQuery(query,null);
            if (fila.moveToFirst())
            {                    
                entidad= new clsEspecialidad();            
                entidad.setInt_id_especialidad(fila.getInt(0));
                entidad.setStr_nombre(fila.getString(1));
                entidad.setStr_descripcion(fila.getString(2));
                entidad.setInt_estado(fila.getInt(3));
                
            }
        }
        bd.close();   
        return entidad;
     }
     
     
   
  
       
    
       
       
     public static  List<clsEspecialidad> Listar(Context context)
     {
        List<clsEspecialidad> list=new ArrayList<clsEspecialidad>();
        bdSQLite admin=new bdSQLite(context,null); 
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id_especialidad,str_nombre,str_descripcion,int_estado from "+NOMBRE_TABLA;

            Cursor fila=bd.rawQuery(query,null);
            int numRows = fila.getCount();   
            fila.moveToFirst();   
                for (int i = 0; i < numRows; ++i) 
                {   
                    clsEspecialidad entidad= new clsEspecialidad();            
                    entidad.setInt_id_especialidad(fila.getInt(0));
                    entidad.setStr_nombre(fila.getString(1));
                    entidad.setStr_descripcion(fila.getString(2));
                    entidad.setInt_estado(fila.getInt(3));
                    
                    list.add(entidad);
                       
                    fila.moveToNext();   
                }   
         }
        bd.close();   
        return list;
     }
//       
        
     public static void Borrar(Context context) {
     bdSQLite admin=new bdSQLite(context,null);
     SQLiteDatabase bd=admin.getWritableDatabase();
     bd.delete(NOMBRE_TABLA, null, null);
     bd.close();
    }
   
}
