/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sinergia.seguridad.dao;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.sinergia.seguridad.entidades.clsNegocio;
import com.sinergia.seguridad.entidades.clsPersona;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Toditos
 */
public class clsNegocioDAO {

    private static String NOMBRE_TABLA="ENTIDAD";
    public static boolean AgregarLogin(Context context,String data)
    {
        boolean retorno=true;
        int id;
        bdSQLite admin=new bdSQLite(context, null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        try
        {
            bd.delete(NOMBRE_TABLA, null, null);
            bd.delete("PERSONA", null, null);
            JSONArray listaJSON = new JSONArray(data);
            for(int i=0;i<listaJSON.length();i++){
                JSONObject json_Entidad = listaJSON.getJSONObject(i);
                ContentValues registro = new ContentValues();
                registro.put("int_id", json_Entidad.getInt("negocio_id"));
                registro.put("str_nombre", json_Entidad.getString("negocio_nombre"));
                registro.put("str_comercial", json_Entidad.getString("negocio_nomcom"));
                registro.put("str_tipo", json_Entidad.getString("negocio_tipo"));
                registro.put("str_ruc", json_Entidad.getString("negocio_ruc"));
                registro.put("dat_registro", json_Entidad.getLong("negocio_fecreg"));
                registro.put("int_activacion", json_Entidad.getInt("negocio_activacion"));

                registro.put("int_ubicacion_id", json_Entidad.getInt("ubicacion_id"));
                registro.put("str_ubicacion_direccion", json_Entidad.getString("ubicacion_direccion"));
                registro.put("dat_ubicacion_registro", json_Entidad.getLong("ubicacion_fecreg"));
                registro.put("int_ubicacion_activacion", json_Entidad.getInt("ubicacion_activacion"));
                registro.put("dou_ubicacion_latitud", json_Entidad.getDouble("ubicacion_latitud"));
                registro.put("dou_ubicacion_longitud", json_Entidad.getDouble("ubicacion_longitud"));
                registro.put("str_ubicacion_referencia", json_Entidad.getString("ubicacion_referencia"));
                registro.put("str_ubicacion_telefono", json_Entidad.getString("ubicacion_telefono"));
                registro.put("id_persona", json_Entidad.getInt("persona_id"));

                id = (int) bd.insert(NOMBRE_TABLA, null, registro);
                {
                        ContentValues persona = new ContentValues();
                        persona.put("int_id", json_Entidad.getInt("persona_id"));
                        persona.put("str_nombres", json_Entidad.getString("persona_nombres"));
                        persona.put("str_paterno", json_Entidad.getString("persona_patermo"));
                        persona.put("str_materno", json_Entidad.getString("persona_matermo"));
                        persona.put("str_dni", json_Entidad.getString("persona_dni"));
                        persona.put("dat_naciomiento", json_Entidad.getLong("persona_nacimiento"));
                        persona.put("bool_sexo",(json_Entidad.getBoolean("persona_sexo"))?1:0);
                        persona.put("bool_identificado",(json_Entidad.getBoolean("persona_identificado"))?1:0);
                        bd.insert("PERSONA", null, persona);

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
    public static List<clsNegocio> Listar(Context context)
    {
        List<clsNegocio> list=new ArrayList<clsNegocio>();
        bdSQLite admin=new bdSQLite(context, null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        if(bd!=null)
        {
            String query="select en.int_id,en.str_nombre,en.str_comercial,en.str_tipo,en.str_ruc," +
                    "en.dat_registro,en.int_activacion,en.int_ubicacion_id,en.str_ubicacion_direccion," +
                    "en.dat_ubicacion_registro,en.int_ubicacion_activacion,en.dou_ubicacion_latitud," +
                    "en.dou_ubicacion_longitud,en.str_ubicacion_referencia,en.str_ubicacion_telefono," +
                    "en.id_persona,pe.str_nombres,pe.str_paterno,pe.str_materno,pe.str_dni,pe.dat_naciomiento," +
                    "pe.bool_sexo,pe.bool_identificado from "+NOMBRE_TABLA+" as en inner join PERSONA as pe " +
                    "on pe.int_id=en.id_persona";

            Cursor fila=bd.rawQuery(query,null);
            int numRows = fila.getCount();
            fila.moveToFirst();
            for (int i = 0; i < numRows; ++i)
            {
                clsPersona persona = new clsPersona();
                persona.setInt_id(fila.getInt(15));
                persona.setStr_nombres(fila.getString(16));
                persona.setStr_paterno(fila.getString(17));
                persona.setStr_materno(fila.getString(18));
                persona.setStr_dni(fila.getString(19));
                persona.setDat_naciomiento(new Date(fila.getLong(20)));
                persona.setBool_sexo((fila.getInt(21) == 1) ? true : false);
                persona.setBool_identificado((fila.getInt(22)==1)?true:false);

                clsNegocio entidad= new clsNegocio();
                entidad.setInt_id(fila.getInt(0));
                entidad.setStr_nombre(fila.getString(1));
                entidad.setStr_comercial(fila.getString(2));
                entidad.setStr_tipo(fila.getString(3));
                entidad.setStr_ruc(fila.getString(4));
                entidad.setDat_registro(new Date(fila.getLong(5)));
                entidad.setInt_activacion(fila.getInt(6));
                entidad.setInt_ubicacion_id(fila.getInt(7));
                entidad.setStr_ubicacion_direccion(fila.getString(8));
                entidad.setDat_ubicacion_registro(new Date(fila.getLong(9)));
                entidad.setInt_ubicacion_activacion(fila.getInt(10));
                entidad.setDou_ubicacion_latitud(fila.getDouble(11));
                entidad.setDou_ubicacion_longitud(fila.getDouble(12));
                entidad.setStr_ubicacion_referencia(fila.getString(13));
                entidad.setStr_ubicacion_telefono(fila.getString(14));


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
