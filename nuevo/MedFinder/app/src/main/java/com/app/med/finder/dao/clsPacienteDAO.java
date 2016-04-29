/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.med.finder.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.med.finder.entidades.clsPaciente;
import com.app.med.finder.entidades.clsUsuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Toditos
 */
public class clsPacienteDAO {
    
    private static String NOMBRE_TABLA="PACIENTE";
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
                registro.put("int_id_paciente",json_data.getInt("pacienteId"));
                registro.put("str_nombres",json_data.getString("personaNombre"));
                registro.put("str_apellido_paterno",json_data.getString("personaApellidoPaterno"));
                registro.put("str_apellido_materno",json_data.getString("personaApellidoMaterno"));
                registro.put("dat_fecha_nacimiento",new Date(json_data.getLong("personaFechaNacimiento")).getTime());
                registro.put("int_estatura",json_data.getInt("pacienteEstatura"));
                registro.put("int_estado",json_data.getInt("pacienteEstado"));
                registro.put("id_usuario",json_data.getInt("usuarioId"));
                registro.put("str_dni",json_data.getString("personaDni"));
                registro.put("int_id_persona",json_data.getInt("personaId"));

                if(json_data.getBoolean("personaSexo"))
                    registro.put("bol_sexo",1);
                else
                    registro.put("bol_sexo",0);

                if(json_data.getInt("pacienteTipo")==1)
                    registro.put("bol_tipo",1);
                else
                    registro.put("bol_tipo",0);


                if(json_data.getBoolean("pacienteCardiovascular"))
                    registro.put("bol_cardiovasculares",1);
                else
                    registro.put("bol_cardiovasculares",0);

                if(json_data.getBoolean("pacienteMusculares"))
                    registro.put("bol_musculares",1);
                else
                    registro.put("bol_musculares",0);

                if(json_data.getBoolean("pacienteDigestivos"))
                    registro.put("bol_digestivos",1);
                else
                    registro.put("bol_digestivos",0);

                if(json_data.getBoolean("pacienteAlergicos"))
                    registro.put("bol_alergicos",1);
                else
                    registro.put("bol_alergicos",0);

                if(json_data.getBoolean("pacienteAlcohol"))
                    registro.put("bol_alcohol",1);
                else
                    registro.put("bol_alcohol",0);

                if(json_data.getBoolean("pacienteTabaquismo"))
                    registro.put("bol_tabaquismo",1);
                else
                    registro.put("bol_tabaquismo",0);

                if(json_data.getBoolean("pacienteDrogas"))
                    registro.put("bol_drogas",1);
                else
                    registro.put("bol_drogas",0);

                if(json_data.getBoolean("pacientePsicologicos"))
                    registro.put("bol_psocilogicos",1);
                else
                    registro.put("bol_psocilogicos",0);
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
     public static int Agregar(Context context,clsPaciente entidad)
    {
        int id = 0;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("int_id_paciente",entidad.getInt_id_paciente());
        registro.put("str_nombres",entidad.getStr_nombres());
        registro.put("str_apellido_paterno",entidad.getStr_apellido_paterno());
        registro.put("str_apellido_materno",entidad.getStr_apellido_materno());
        registro.put("dat_fecha_nacimiento",entidad.getDat_fecha_nacimiento().getTime());
        registro.put("int_estatura",entidad.getInt_estatura());
        registro.put("int_estado",entidad.getInt_estado());
        registro.put("id_usuario",entidad.getObjUsuario().getInt_id_usuario());
        registro.put("str_dni",entidad.getStr_dni());
        registro.put("int_id_persona",entidad.getInt_id_persona());
        
        if(entidad.isBol_sexo())
        registro.put("bol_sexo",1);
        else
        registro.put("bol_sexo",0);
        
        if(entidad.isBol_tipo())
        registro.put("bol_tipo",1);
        else
        registro.put("bol_tipo",0);
        
        
        if(entidad.isBol_cardiovasculares())
        registro.put("bol_cardiovasculares",1);
        else
        registro.put("bol_cardiovasculares",0);
        
        if(entidad.isBol_musculares())
        registro.put("bol_musculares",1);
        else
        registro.put("bol_musculares",0);
        
        if(entidad.isBol_digestivos())
        registro.put("bol_digestivos",1);
        else
        registro.put("bol_digestivos",0);
        
        if(entidad.isBol_alergicos())
        registro.put("bol_alergicos",1);
        else
        registro.put("bol_alergicos",0);
        
        if(entidad.isBol_alcohol())
        registro.put("bol_alcohol",1);
        else
        registro.put("bol_alcohol",0);
        
        if(entidad.isBol_tabaquismo())
        registro.put("bol_tabaquismo",1);
        else
        registro.put("bol_tabaquismo",0);
        
        if(entidad.isBol_drogas())
        registro.put("bol_drogas",1);
        else
        registro.put("bol_drogas",0);
        
        if(entidad.isBol_psicologicos())
        registro.put("bol_psocilogicos",1);
        else
        registro.put("bol_psocilogicos",0);
        
        id = (int) bd.insert(NOMBRE_TABLA, null, registro);
        bd.close();    
        return id;
    }   

     public  static boolean Actualizar(Context context,clsPaciente entidad) 
     {
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("str_nombres",entidad.getStr_nombres());
        registro.put("str_apellido_paterno",entidad.getStr_apellido_paterno());
        registro.put("str_apellido_materno",entidad.getStr_apellido_materno());
        registro.put("dat_fecha_nacimiento",entidad.getDat_fecha_nacimiento().getTime());
        registro.put("int_estatura",entidad.getInt_estatura());
        registro.put("int_estado",entidad.getInt_estado());
        registro.put("id_usuario",entidad.getObjUsuario().getInt_id_usuario());
        registro.put("str_dni",entidad.getStr_dni());
        registro.put("int_id_persona",entidad.getInt_id_persona());
        
        if(entidad.isBol_sexo())
        registro.put("bol_sexo",1);
        else
        registro.put("bol_sexo",0);
        
        if(entidad.isBol_tipo())
        registro.put("bol_tipo",1);
        else
        registro.put("bol_tipo",0);
        
        
        if(entidad.isBol_cardiovasculares())
        registro.put("bol_cardiovasculares",1);
        else
        registro.put("bol_cardiovasculares",0);
        
        if(entidad.isBol_musculares())
        registro.put("bol_musculares",1);
        else
        registro.put("bol_musculares",0);
        
        if(entidad.isBol_digestivos())
        registro.put("bol_digestivos",1);
        else
        registro.put("bol_digestivos",0);
        
        if(entidad.isBol_alergicos())
        registro.put("bol_alergicos",1);
        else
        registro.put("bol_alergicos",0);
        
        if(entidad.isBol_alcohol())
        registro.put("bol_alcohol",1);
        else
        registro.put("bol_alcohol",0);
        
        if(entidad.isBol_tabaquismo())
        registro.put("bol_tabaquismo",1);
        else
        registro.put("bol_tabaquismo",0);
        
        if(entidad.isBol_drogas())
        registro.put("bol_drogas",1);
        else
        registro.put("bol_drogas",0);
        
        if(entidad.isBol_psicologicos())
        registro.put("bol_psocilogicos",1);
        else
        registro.put("bol_psocilogicos",0);
        
        int cant = bd.update(NOMBRE_TABLA, registro, "int_id_paciente="+entidad.getInt_id_paciente(), null);
        bd.close();
        if(cant==1)
            return true;
        else
            return false;
       
    }  
     
     public static  clsPaciente Buscar(Context context,int id)
     {
        clsPaciente  entidad=null;
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
            String query="select int_id_paciente,str_nombres,str_apellido_paterno,"
                    + "str_apellido_materno,dat_fecha_nacimiento,int_estatura,bol_sexo,"
                    + "bol_tipo,int_estado,bol_cardiovasculares,bol_musculares,bol_digestivos,"
                    + "bol_alergicos,bol_alcohol,bol_tabaquismo,bol_drogas,bol_psocilogicos,id_usuario,"
                    + "str_dni,int_id_persona from "+NOMBRE_TABLA+" where int_id_paciente="+id;

            Cursor fila=bd.rawQuery(query,null);
            if (fila.moveToFirst())
            {                    
                entidad= new clsPaciente();            
                entidad.setInt_id_paciente(fila.getInt(0));
                entidad.setStr_nombres(fila.getString(1));
                entidad.setStr_apellido_paterno(fila.getString(2));
                entidad.setStr_apellido_materno(fila.getString(3));
                entidad.setDat_fecha_nacimiento(new Date(fila.getLong(4)));
                entidad.setInt_estatura(fila.getInt(5));
                
                if(fila.getInt(6)==1)                
                entidad.setBol_sexo(true);
                else
                entidad.setBol_sexo(false);
                
                if(fila.getInt(7)==1)   
                entidad.setBol_tipo(true);
                else
                entidad.setBol_tipo(false);
                
                entidad.setInt_estado(fila.getInt(8));

                if(fila.getInt(9)==1)                
                entidad.setBol_cardiovasculares(true);
                else
                entidad.setBol_cardiovasculares(false);

                if(fila.getInt(10)==1)                
                entidad.setBol_musculares(true);
                else
                entidad.setBol_musculares(false);

                if(fila.getInt(11)==1)                
                entidad.setBol_digestivos(true);
                else
                entidad.setBol_digestivos(false);

                if(fila.getInt(12)==1)                
                entidad.setBol_alergicos(true);
                else
                entidad.setBol_alergicos(false);

                if(fila.getInt(13)==1)                
                entidad.setBol_alcohol(true);
                else
                entidad.setBol_alcohol(false);

                if(fila.getInt(14)==1)                
                entidad.setBol_tabaquismo(true);
                else
                entidad.setBol_tabaquismo(false);

                if(fila.getInt(15)==1)                
                entidad.setBol_drogas(true);
                else
                entidad.setBol_drogas(false);

                if(fila.getInt(16)==1)                
                entidad.setBol_psicologicos(true);
                else
                entidad.setBol_psicologicos(false);
                
                entidad.setObjUsuario(new clsUsuario(fila.getInt(17)));
                entidad.setStr_dni(fila.getString(18));
                entidad.setInt_id_persona(fila.getInt(19));

            }
        }
        bd.close();   
        return entidad;
     }
     
     public static  List<clsPaciente> Listar(Context context)
     {
        List<clsPaciente> list=new ArrayList<clsPaciente>();
        bdSQLite admin=new bdSQLite(context,null);
        SQLiteDatabase bd=admin.getWritableDatabase();
         if(bd!=null)
         {
        String query="select int_id_paciente,str_nombres,str_apellido_paterno,"
                    + "str_apellido_materno,dat_fecha_nacimiento,int_estatura,bol_sexo,"
                    + "bol_tipo,int_estado,bol_cardiovasculares,bol_musculares,bol_digestivos,"
                    + "bol_alergicos,bol_alcohol,bol_tabaquismo,bol_drogas,bol_psocilogicos,id_usuario,"
                    + "str_dni,int_id_persona from "+NOMBRE_TABLA;
        
        Cursor fila=bd.rawQuery(query,null);
        int numRows = fila.getCount();   
        fila.moveToFirst();   
                for (int i = 0; i < numRows; ++i) 
                {   
                    clsPaciente entidad= new clsPaciente();            
                    entidad.setInt_id_paciente(fila.getInt(0));
                    entidad.setStr_nombres(fila.getString(1));
                    entidad.setStr_apellido_paterno(fila.getString(2));
                    entidad.setStr_apellido_materno(fila.getString(3));
                    entidad.setDat_fecha_nacimiento(new Date(fila.getLong(4)));
                    entidad.setInt_estatura(fila.getInt(5));

                    if(fila.getInt(6)==1)                
                    entidad.setBol_sexo(true);
                    else
                    entidad.setBol_sexo(false);

                    if(fila.getInt(7)==1)   
                    entidad.setBol_tipo(true);
                    else
                    entidad.setBol_tipo(false);

                    entidad.setInt_estado(fila.getInt(8));

                    if(fila.getInt(9)==1)                
                    entidad.setBol_cardiovasculares(true);
                    else
                    entidad.setBol_cardiovasculares(false);

                    if(fila.getInt(10)==1)                
                    entidad.setBol_musculares(true);
                    else
                    entidad.setBol_musculares(false);

                    if(fila.getInt(11)==1)                
                    entidad.setBol_digestivos(true);
                    else
                    entidad.setBol_digestivos(false);

                    if(fila.getInt(12)==1)                
                    entidad.setBol_alergicos(true);
                    else
                    entidad.setBol_alergicos(false);

                    if(fila.getInt(13)==1)                
                    entidad.setBol_alcohol(true);
                    else
                    entidad.setBol_alcohol(false);

                    if(fila.getInt(14)==1)                
                    entidad.setBol_tabaquismo(true);
                    else
                    entidad.setBol_tabaquismo(false);

                    if(fila.getInt(15)==1)                
                    entidad.setBol_drogas(true);
                    else
                    entidad.setBol_drogas(false);

                    if(fila.getInt(16)==1)                
                    entidad.setBol_psicologicos(true);
                    else
                    entidad.setBol_psicologicos(false);

                    entidad.setObjUsuario(new clsUsuario(fila.getInt(17)));
                    entidad.setStr_dni(fila.getString(18));
                    entidad.setInt_id_persona(fila.getInt(19));
                    
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
