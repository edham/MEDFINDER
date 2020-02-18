package com.med.finder.doctor.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

public class bdSQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MedFinderDoc";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_DOCTOR = "CREATE TABLE DOCTOR ("
            + "int_id_doctor integer PRIMARY KEY,"
            + "str_usuario text,"
            + "str_clave text,"
            + "str_nombres text,"
            + "str_apellido_paterno text,"
            + "str_apellido_materno text,"
            + "str_dni text,"
            + "str_codigo_colegiatura text,"
            + "str_direccion text,"
            + "str_direccion_detalle text,"
            + "str_telefono text,"
            + "dou_longitud numeric,"
            + "dou_latitud numeric,"
            + "int_puntuje integer,"
            + "int_id_especialidad integer,"
            + "byte_foto blob)";


    private static final String CREATE_PACIENTE = "CREATE TABLE PACIENTE ("
            + "int_id_paciente integer PRIMARY KEY,"
            + "str_nombres text,"
            + "str_apellido_paterno text,"
            + "str_apellido_materno text,"
            + "str_dni text,"
            + "dat_fecha_nacimiento numeric,"
            + "int_estatura integer,"
            + "bol_sexo integer,"
            + "int_estado integer,"
            + "bol_cardiovasculares integer,"
            + "bol_musculares integer,"
            + "bol_digestivos integer,"
            + "bol_alergicos integer,"
            + "bol_alcohol integer,"
            + "bol_tabaquismo integer,"
            + "bol_drogas integer,"
            + "bol_psocilogicos integer,"
            + "int_id_persona integer,"
            + "id_usuario integer)";



    private static final String CREATE_ESPECIALIDAD = "CREATE TABLE ESPECIALIDAD ("
            + "int_id_especialidad integer PRIMARY KEY,"
            + "str_nombre text,"
            + "str_descripcion text)";

    private static final String CREATE_CASOS_SALUD = "CREATE TABLE CASOS_SALUD ("
            + "int_id_casos_salud integer PRIMARY KEY,"
            + "str_tema text,"
            + "dat_inicio numeric,"
            + "dat_fin numeric)";

    private static final String CREATE_PREGUNTA_PACIENTE = "CREATE TABLE PREGUNTA_PACIENTE ("
            + "int_id_pregunta_paciente integer PRIMARY KEY,"
            + "int_id_paciente integer,"
            + "int_id_especialidad integer,"
            + "str_asunto text,"
            + "str_paciente_detalle text,"
            + "dat_inicio numeric,"
            + "byte_imagen blod,"
            + "int_estado integer)";

    private static final String CREATE_RESPUESTA_PREGUNTA_PACIENTE = "CREATE TABLE RESPUESTA_PREGUNTA_PACIENTE ("
            + "int_id_respuesta_pregunta_paciente integer PRIMARY KEY,"
            + "int_id_pregunta_paciente integer,"
            + "int_id_doctor integer,"
            + "str_detalle text,"
            + "int_puntaje integer,"
            + "dat_fecha numeric)";

    private static final String CREATE_CITA_PACIENTE = "CREATE TABLE CITA_PACIENTE ("
            + "int_id_cita_paciente integer PRIMARY KEY,"
            + "int_id_doctor integer,"
            + "int_id_paciente integer,"
            + "str_detalle text,"
            + "str_respuesta text,"
            + "dat_creacion numeric,"
            + "dat_atencion numeric,"
            + "int_estado integer)";

    private static final String CREATE_RESPUESTA_CASOS_SALUD = "CREATE TABLE RESPUESTA_CASOS_SALUD ("
            + "int_id_respuesta_casos_salud integer PRIMARY KEY,"
            + "int_id_doctor integer,"
            + "int_id_casos_salud integer,"
            + "str_descripcion text,"
            + "int_puntaje integer,"
            + "int_total integer)";

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.setForeignKeyConstraintsEnabled(true);
        }
    }

    public bdSQLite(Context context, CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
	public void onCreate(SQLiteDatabase db) {
                db.execSQL(CREATE_PACIENTE);
                db.execSQL(CREATE_ESPECIALIDAD);
                db.execSQL(CREATE_DOCTOR);
                db.execSQL(CREATE_CASOS_SALUD);
                db.execSQL(CREATE_PREGUNTA_PACIENTE);
                db.execSQL(CREATE_RESPUESTA_PREGUNTA_PACIENTE);
                db.execSQL(CREATE_CITA_PACIENTE);
                db.execSQL(CREATE_RESPUESTA_CASOS_SALUD);


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
        db.execSQL("drop table if exists PREGUNTA_PACIENTE");
        db.execSQL(CREATE_PREGUNTA_PACIENTE);
        db.execSQL("drop table if exists RESPUESTA_PREGUNTA_PACIENTE");
        db.execSQL(CREATE_RESPUESTA_PREGUNTA_PACIENTE);
        db.execSQL("drop table if exists CITA_PACIENTE");
        db.execSQL(CREATE_CITA_PACIENTE);
        db.execSQL("drop table if exists RESPUESTA_CASOS_SALUD");
        db.execSQL(CREATE_RESPUESTA_CASOS_SALUD);
    }
}