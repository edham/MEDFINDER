package com.med.finder.doctor.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class bdSQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MedFinderDoc";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_DOCTOR = "CREATE TABLE DOCTOR ("
            + "int_id_doctor integer NOT NULL PRIMARY KEY,"
            + "str_usuario text NOT NULL,"
            + "str_clave text NOT NULL,"
            + "str_nombres text NOT NULL,"
            + "str_apellido_paterno text NOT NULL,"
            + "str_apellido_materno text NOT NULL,"
            + "str_dni text NOT NULL,"
            + "str_codigo_colegiatura text NOT NULL,"
            + "str_direccion text NOT NULL,"
            + "str_direccion_detalle text NOT NULL,"
            + "str_telefono text NOT NULL,"
            + "dou_longitud numeric NOT NULL,"
            + "dou_latitud numeric NOT NULL,"
            + "int_puntuje integer NOT NULL,"
            + "int_id_especialidad integer NOT NULL,"
            + "byte_foto blob)";

    private static final String CREATE_PACIENTE = "CREATE TABLE PACIENTE ("
            + "int_id_paciente integer NOT NULL PRIMARY KEY,"
            + "str_nombres text NOT NULL,"
            + "str_apellido_paterno text NOT NULL,"
            + "str_apellido_materno text NOT NULL,"
            + "str_dni text NOT NULL,"
            + "dat_fecha_nacimiento numeric NOT NULL,"
            + "int_estatura integer NOT NULL,"
            + "bol_sexo integer NOT NULL,"
            + "bol_tipo integer NOT NULL,"
            + "int_estado integer NOT NULL,"
            + "bol_cardiovasculares integer NOT NULL,"
            + "bol_musculares integer NOT NULL,"
            + "bol_digestivos integer NOT NULL,"
            + "bol_alergicos integer NOT NULL,"
            + "bol_alcohol integer NOT NULL,"
            + "bol_tabaquismo integer NOT NULL,"
            + "bol_drogas integer NOT NULL,"
            + "bol_psocilogicos integer NOT NULL,"
            + "int_id_persona integer NOT NULL,"
            + "id_usuario integer NOT NULL)";

    private static final String CREATE_ESPECIALIDAD = "CREATE TABLE ESPECIALIDAD ("
            + "int_id_especialidad integer NOT NULL PRIMARY KEY,"
            + "str_nombre text NOT NULL,"
            + "str_descripcion text NOT NULL,"
            + "int_estado integer NOT NULL)";

    private static final String CREATE_CASOS_SALUD = "CREATE TABLE CASOS_SALUD ("
            + "int_id_casos_salud integer NOT NULL PRIMARY KEY,"
            + "str_tema text NOT NULL,"
            + "dat_inicio numeric NOT NULL,"
            + "dat_fin numeric NOT NULL)";


	public bdSQLite(Context context, CursorFactory factory) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
                db.execSQL(CREATE_PACIENTE);
                db.execSQL(CREATE_ESPECIALIDAD);
                db.execSQL(CREATE_DOCTOR);
                db.execSQL(CREATE_CASOS_SALUD);
                
        }   
         
	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {

        db.execSQL("drop table if exists PACIENTE");
        db.execSQL(CREATE_PACIENTE);
        db.execSQL("drop table if exists ESPECIALIDAD");
        db.execSQL(CREATE_ESPECIALIDAD);
        db.execSQL("drop table if exists DOCTOR");
        db.execSQL(CREATE_DOCTOR);
        db.execSQL("drop table if exists CASOS_SALUD");
        db.execSQL(CREATE_CASOS_SALUD);
    }
}