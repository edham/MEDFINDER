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

import com.app.med.finder.entidades.clsSeguro;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;




/**
 *
 * @author Toditos
 */
public class clsSeguroDAO {
    
    private static String NOMBRE_TABLA="SEGURO";

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
                registro.put("int_id_seguro",json_data.getInt("seguroId"));
                registro.put("str_nombre",json_data.getString("seguroNombre"));
                registro.put("int_estado",json_data.getInt("seguroEstado"));
                if(!json_data.getString("seguroLogo").equals(""))
                    registro.put("byte_logo", Base64.decode(json_data.getString("seguroLogo"), Base64.NO_WRAP | Base64.URL_SAFE));


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

    public static int Agregar(Context context,clsSeguro entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("int_id_seguro",entidad.getInt_id_seguro());
        registro.put("str_nombre",entidad.getStr_nombre());
        registro.put("int_estado",entidad.getInt_estado());
        if(entidad.getByte_logo()!=null)
            registro.put("byte_logo",entidad.getByte_logo());
        id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        bd.close();    
        return id;

    }   

     public  static boolean Actualizar(Context context,clsSeguro entidad) 
     {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("str_nombre",entidad.getStr_nombre());
        registro.put("int_estado",entidad.getInt_estado());
        if(entidad.getByte_logo()!=null)
            registro.put("byte_logo",entidad.getByte_logo());    
        
        int cant = bd.update(NOMBRE_TABLA, registro, "int_id_seguro="+entidad.getInt_id_seguro(), null);
        bd.close();
        if(cant==1)
            return true;
        else
            return false;
       
    }  
    
     public static  List<clsSeguro> ListarXClinica(Context context,int id)
     {
        List<clsSeguro> list=new ArrayList<clsSeguro>();
        bdSQLite admin=new bdSQLite(context,null); 
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select S.int_id_seguro,S.str_nombre,S.int_estado,S.byte_logo "
                    + "from "+NOMBRE_TABLA+" as S inner join CLINICA_SEGURO as C on "
                    + "S.int_id_seguro=C.int_id_seguro where C.int_id_clinica="+id;

            Cursor fila=bd.rawQuery(query,null);
            int numRows = fila.getCount();   
            fila.moveToFirst();   
                for (int i = 0; i < numRows; ++i) 
                {   
                    clsSeguro entidad= new clsSeguro();            
                    entidad.setInt_id_seguro(fila.getInt(0));
                    entidad.setStr_nombre(fila.getString(1));
                    entidad.setInt_estado(fila.getInt(2));
                    entidad.setByte_logo(fila.getBlob(3));

                    
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
