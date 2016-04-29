/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.seguridad.dao;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.sinergia.seguridad.entidades.clsCuadrante;
import com.sinergia.seguridad.entidades.clsCuadranteCoordenadas;
import com.sinergia.seguridad.entidades.clsTrack;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Toditos
 */
public class clsCuadranteDAO {


    public static boolean AgregarLogin(Context context,String data)
    {
        boolean retorno=true;
        int id;
        bdSQLite admin=new bdSQLite(context, null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        try
        {
            bd.delete("CUADRANTE", null, null);
            bd.delete("CUADRANTE_COORDENADAS", null, null);
            JSONArray listCuadranteJSON = new JSONArray(data);
            for(int i=0;i<listCuadranteJSON.length();i++){
                JSONObject json_Cuadrante = listCuadranteJSON.getJSONObject(i);
                ContentValues registro = new ContentValues();
                registro.put("int_id", json_Cuadrante.getInt("id"));
                registro.put("str_nombre", json_Cuadrante.getString("nombre"));
                registro.put("str_descripcion", json_Cuadrante.getString("descripcion"));
                id = (int) bd.insert("CUADRANTE", null, registro);
                {
                    JSONArray listCoordenadaJSON = new JSONArray(json_Cuadrante.getString("listaJSON"));
                    for(int j=0;j<listCoordenadaJSON.length();j++){
                        JSONObject json_Coordenada = listCoordenadaJSON.getJSONObject(j);
                        ContentValues coordemada = new ContentValues();
                        coordemada.put("int_id", json_Coordenada.getInt("id"));
                        coordemada.put("dou_latitud", json_Coordenada.getDouble("latitud"));
                        coordemada.put("dou_longitud", json_Coordenada.getDouble("longitud"));
                        coordemada.put("int_id_cuadrante", id);
                        bd.insert("CUADRANTE_COORDENADAS", null, coordemada);
                    }
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

    public static PolylineOptions ListarCoordenadasXIdCuadrante(Context context,int id)
    {
        PolylineOptions  list=new PolylineOptions ();
        bdSQLite admin=new bdSQLite(context, null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        if(bd!=null)
        {
            String query="select dou_latitud,dou_longitud from CUADRANTE_COORDENADAS where int_id_cuadrante="+id;

            Cursor fila=bd.rawQuery(query,null);
            int numRows = fila.getCount();
            fila.moveToFirst();
            for (int i = 0; i < numRows; ++i)
            {
                list.add(new LatLng(fila.getDouble(0),fila.getDouble(1)));
                fila.moveToNext();
            }
        }
        bd.close();
        return list;
    }


    public static List<clsCuadrante> ListarCuarantes(Context context)
    {
        List<clsCuadrante> list=new ArrayList<clsCuadrante>();
        bdSQLite admin=new bdSQLite(context, null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        if(bd!=null)
        {
            String query="select int_id,str_nombre,str_descripcion from CUADRANTE";

            Cursor fila=bd.rawQuery(query,null);
            int numRows = fila.getCount();
            fila.moveToFirst();
            for (int i = 0; i < numRows; ++i)
            {
                clsCuadrante entidad= new clsCuadrante();
                entidad.setInt_id(fila.getInt(0));
                entidad.setStr_nombre(fila.getString(1));
                entidad.setStr_descripcion(fila.getString(1));
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
         bd.delete("CUADRANTE", null, null);
         bd.delete("CUADRANTE_COORDENADAS", null, null);
     bd.close();
    }
   
}
