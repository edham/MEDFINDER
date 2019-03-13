/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.med.finder.cliente.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.med.finder.cliente.entidades.clsUsuario;


/**
 *
 * @author Toditos
 */
public class clsUsuarioDAO {
    
    private static String NOMBRE_TABLA="USUARIO";
    
     public static int Agregar(Context context,clsUsuario entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        if(bd!=null)
        {
            ContentValues registro=new ContentValues();
            registro.put("int_id_usuario",entidad.getInt_id_usuario());
            registro.put("str_nombres",entidad.getStr_nombres());
            registro.put("str_apellido_paterno",entidad.getStr_apellido_paterno());
            registro.put("str_apellido_materno",entidad.getStr_apellido_materno());
            registro.put("str_usuario",entidad.getStr_usuario());
            registro.put("str_clave",entidad.getStr_clave());
            registro.put("str_email",entidad.getStr_email());
            registro.put("str_dni",entidad.getStr_dni());
            registro.put("str_telefono",entidad.getStr_telefono());
            registro.put("str_direccion",entidad.getStr_direccion());
            registro.put("int_id_persona",entidad.getInt_id_persona());
            
            if(entidad.getByte_foto()!=null)
            registro.put("byte_foto",entidad.getByte_foto());

            if(entidad.isBol_sexo())
            registro.put("bol_sexo",1);
            else
            registro.put("bol_sexo",0);
            id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        }
        bd.close();  
        return id;
    }   
   
     public static clsUsuario Buscar(Context context)
     {
        clsUsuario entidad=null;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id_usuario,str_nombres,str_apellido_paterno,str_apellido_materno,"
                    + "str_usuario,str_clave,str_email,str_dni,str_telefono,str_direccion,"
                    + "bol_sexo,byte_foto,int_id_persona from "+NOMBRE_TABLA;

            Cursor fila=bd.rawQuery(query,null);

            if (fila.moveToFirst())
            {                    
                entidad= new clsUsuario();
                entidad.setInt_id_usuario(fila.getInt(0));
                entidad.setStr_nombres(fila.getString(1));
                entidad.setStr_apellido_paterno(fila.getString(2));
                entidad.setStr_apellido_materno(fila.getString(3));
                entidad.setStr_usuario(fila.getString(4));
                entidad.setStr_clave(fila.getString(5));
                entidad.setStr_email(fila.getString(6));
                entidad.setStr_dni(fila.getString(7));
                entidad.setStr_telefono(fila.getString(8));
                entidad.setStr_direccion(fila.getString(9));              
                if(fila.getInt(10)==1)                
                entidad.setBol_sexo(true);
                else
                entidad.setBol_sexo(false);
                      
                entidad.setByte_foto(fila.getBlob(11));
                entidad.setInt_id_persona(fila.getInt(12));
                

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
