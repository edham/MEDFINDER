/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.med.finder.doctor.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.med.finder.doctor.entidades.clsInicio;
import com.med.finder.doctor.entidades.clsPaciente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Toditos
 */
public class clsInicioDAO {

    public static List<clsInicio> Listar(Context context,boolean consultas,boolean citas)
    {
        List<clsInicio> list=new ArrayList<clsInicio>();
        if(consultas || citas) {
            bdSQLite admin = new bdSQLite(context, null);
            SQLiteDatabase bd = admin.getWritableDatabase();
            if (bd != null) {
                String query = "select ini.id,ini.tipo,ini.dat_ini,ini.id_paciente from (" +
                        "select int_id_cita_paciente as id,1 as tipo,dat_creacion as dat_ini,int_id_paciente as id_paciente from CITA_PACIENTE where int_estado=0 " +
                        "union " +
                        "select int_id_pregunta_paciente as id,2 as tipo,dat_inicio as dat_ini,int_id_paciente as id_paciente from PREGUNTA_PACIENTE where int_estado=1) " +
                        "ini";
                if (consultas && !citas) {
                    query += " where ini.tipo=2";
                }
                if (citas && !consultas) {
                    query += " where ini.tipo=1";
                }
                query += " order by ini.dat_ini desc";
                Cursor fila = bd.rawQuery(query, null);
                int numRows = fila.getCount();
                fila.moveToFirst();
                for (int i = 0; i < numRows; ++i) {
                    clsInicio entidad = new clsInicio();
                    entidad.setInt_id(fila.getInt(0));
                    entidad.setInt_tipo(fila.getInt(1));
                    entidad.setDat_ini(new Date(fila.getLong(2)));
                    entidad.setObjPaciente(new clsPaciente(fila.getInt(3)));
                    list.add(entidad);

                    fila.moveToNext();
                }
            }
            bd.close();
        }
        return list;
    }
}
