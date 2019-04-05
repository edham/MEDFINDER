/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.med.finder.doctor.dao;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.med.finder.doctor.entidades.clsCasosSalud;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Toditos
 */
public class clsCasosSaludDAO {
    
    private static String NOMBRE_TABLA="CASOS_SALUD";
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
                registro.put("int_id_casos_salud",json_data.getInt("casosSaludId"));
                registro.put("str_tema",json_data.getString("casosSaludTema"));
                registro.put("dat_inicio", json_data.getLong("casosSaludFechaInicio"));
                registro.put("dat_fin",json_data.getLong("casosSaludFechaFin"));


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
     public static int Agregar(Context context, clsCasosSalud entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("int_id_casos_salud",entidad.getInt_id_casos_salud());
        registro.put("str_tema",entidad.getStr_tema());
        registro.put("dat_inicio",entidad.getDat_inicio().getTime());
        registro.put("dat_fin",entidad.getDat_fin().getTime());
        
        id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        bd.close();    
        return id;
    }   

     public  static boolean Actualizar(Context context,clsCasosSalud entidad) 
     {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("str_tema",entidad.getStr_tema());
        registro.put("dat_inicio",entidad.getDat_inicio().getTime());
        registro.put("dat_fin",entidad.getDat_fin().getTime());
        
        int cant = bd.update(NOMBRE_TABLA, registro, "int_id_casos_salud="+entidad.getInt_id_casos_salud(), null);
        bd.close();
        if(cant==1)
            return true;
        else
            return false;
       
    }  

     public static  clsCasosSalud Buscar(Context context,int id)
     {
        clsCasosSalud  entidad=null;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
             String query="select int_id_casos_salud,str_tema,dat_inicio,dat_fin from "+NOMBRE_TABLA
                     +" where int_id_casos_salud="+id;

            Cursor fila=bd.rawQuery(query,null);
            if (fila.moveToFirst())
            {                    
                entidad= new clsCasosSalud();            
                entidad.setInt_id_casos_salud(fila.getInt(0));
                entidad.setStr_tema(fila.getString(1));
                entidad.setDat_inicio(new Date(fila.getLong(2)));
                entidad.setDat_fin(new Date(fila.getLong(3)));

            }
        }
        bd.close();   
        return entidad;
     }
     
     public static  List<clsCasosSalud> Listar(Context context)
     {
        List<clsCasosSalud> list=new ArrayList<clsCasosSalud>();
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id_casos_salud,str_tema,dat_inicio,dat_fin from "+NOMBRE_TABLA;

            Cursor fila=bd.rawQuery(query,null);
            int numRows = fila.getCount();   
            fila.moveToFirst();   
                for (int i = 0; i < numRows; ++i) 
                {   
                    clsCasosSalud entidad= new clsCasosSalud();            
                    entidad.setInt_id_casos_salud(fila.getInt(0));
                    entidad.setStr_tema(fila.getString(1));
                    entidad.setDat_inicio(new Date(fila.getLong(2)));
                    entidad.setDat_fin(new Date(fila.getLong(3)));
                    
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
