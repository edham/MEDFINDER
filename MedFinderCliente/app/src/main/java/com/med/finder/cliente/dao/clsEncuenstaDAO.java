/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.med.finder.cliente.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.med.finder.cliente.entidades.clsEncuesta;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Toditos
 */
public class clsEncuenstaDAO {
    
    private static String NOMBRE_TABLA="ENCUESTA";
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
                registro.put("int_id_encuesta",json_data.getInt("encuestaId"));
                registro.put("str_pregunta",json_data.getString("encuestaPregunta"));
                if(!json_data.isNull("encuestaOrden"))
                registro.put("int_orden",json_data.getInt("encuestaOrden"));

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

    public static List<clsEncuesta> Listar(Context context)
    {
        List<clsEncuesta> list=new ArrayList<clsEncuesta>();
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        if(bd!=null)
        {
            String query="select int_id_encuesta,str_pregunta,int_orden from "+NOMBRE_TABLA+" order by int_orden";

            Cursor fila=bd.rawQuery(query,null);
            int numRows = fila.getCount();
            fila.moveToFirst();
            for (int i = 0; i < numRows; ++i)
            {
                clsEncuesta entidad= new clsEncuesta();
                entidad.setInt_id_encuesta(fila.getInt(0));
                entidad.setStr_pregunta(fila.getString(1));
                entidad.setInt_orden(fila.getInt(2));
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
