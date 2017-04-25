/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.med.finder.dao;




import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.app.med.finder.entidades.clsClinicaSeguro;




/**
 *
 * @author Toditos
 */
public class clsClinicaSeguroDAO {
    
    private static String NOMBRE_TABLA="CLINICA_SEGURO";
    
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
