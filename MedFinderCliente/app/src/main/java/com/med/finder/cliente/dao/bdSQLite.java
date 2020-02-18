package com.med.finder.cliente.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

public class bdSQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MedFinderCliente";
    private static final int DATABASE_VERSION = 1;

        private static final String CREATE_USUARIO = "CREATE TABLE USUARIO ("
                                + "int_id_usuario integer PRIMARY KEY,"
                                + "str_usuario text,"
                                + "str_clave text,"
                                + "str_nombres text,"
                                + "str_apellido_paterno text,"
                                + "str_apellido_materno text,"
                                + "str_email text,"
                                + "str_dni text,"
                                + "str_telefono text,"
                                + "str_direccion text,"
                                + "bol_sexo integer,"
                                + "int_id_persona integer,"
                                + "int_encuesta integer,"
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
                                + "bol_tipo integer,"
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
                                + "str_descripcion text,"
                                + "int_estado integer)";
        
        private static final String CREATE_CLINICA = "CREATE TABLE CLINICA ("
                                + "int_id_clinica integer PRIMARY KEY,"
                                + "str_nombre text,"
                                + "str_slogan text,"
                                + "str_direccion text,"
                                + "str_descripcion text,"
                                + "dat_hora_inicio numeric,"
                                + "dat_hora_fin numeric,"
                                + "str_clinica_atencion text,"
                                + "str_telefono text,"
                                + "str_detalle_atencion text,"
                                + "dou_longitud numeric,"
                                + "dou_latitud numeric,"
                                + "int_estado integer,"
                                + "byte_logo blob)";
        
        private static final String CREATE_DOCTOR = "CREATE TABLE DOCTOR ("
                                + "int_id_doctor integer PRIMARY KEY,"
                                + "int_id_favorito integer,"
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
                                + "bol_favorito integer,"
                                + "byte_foto blob)";

        
        private static final String CREATE_CLINICA_ESPECIALIDAD = "CREATE TABLE CLINICA_ESPECIALIDAD ("
                                + "int_id_clinica_especialidad integer PRIMARY KEY,"
                                + "int_id_clinica integer,"
                                + "int_id_especialidad integer,"
                                + "dat_hora_inicio numeric,"
                                + "dat_hora_fin numeric,"
                                + "str_detalle_horario text,"
                                + "int_estado integer)";
        
         private static final String CREATE_SEGURO = "CREATE TABLE SEGURO ("
                                + "int_id_seguro integer PRIMARY KEY,"
                                + "str_nombre text,"
                                + "int_estado integer,"
                                + "byte_logo blob)";
         
         private static final String CREATE_CLINICA_SEGURO = "CREATE TABLE CLINICA_SEGURO ("
                                + "int_id_clinica_seguro integer PRIMARY KEY,"
                                + "int_id_clinica integer,"
                                + "int_id_seguro integer,"
                                + "int_estado integer)";
         
         private static final String CREATE_CASOS_SALUD = "CREATE TABLE CASOS_SALUD ("
                                + "int_id_casos_salud integer PRIMARY KEY,"
                                + "str_tema text,"
                                + "dat_inicio numeric,"
                                + "dat_fin numeric)";
         
         private static final String CREATE_RESPUESTA_CASOS_SALUD = "CREATE TABLE RESPUESTA_CASOS_SALUD ("
                                + "int_id_respuesta_casos_salud integer PRIMARY KEY,"
                                + "int_id_doctor integer,"
                                + "int_id_casos_salud integer,"
                                + "str_descripcion text,"
                                + "int_puntaje integer,"
                                + "bol_puntaje integer)";
         
         private static final String CREATE_CITA_PACIENTE = "CREATE TABLE CITA_PACIENTE ("
                                + "int_id_cita_paciente integer PRIMARY KEY,"
                                + "int_id_doctor integer,"
                                + "int_id_paciente integer,"
                                + "str_detalle text,"
                                + "str_respuesta text,"
                                + "dat_creacion numeric,"
                                + "dat_atencion numeric,"
                                + "int_estado integer)";
         
         private static final String CREATE_PREGUNTA_PACIENTE = "CREATE TABLE PREGUNTA_PACIENTE ("
                                + "int_id_pregunta_paciente integer PRIMARY KEY,"
                                + "int_id_paciente integer,"
                                + "int_id_especialidad integer,"
                                + "str_asunto text,"
                                + "str_paciente_detalle text,"
                                + "dat_inicio numeric,"
                                + "int_estado integer)";

         private static final String CREATE_RESPUESTA_PREGUNTA_PACIENTE = "CREATE TABLE RESPUESTA_PREGUNTA_PACIENTE ("
                                + "int_id_respuesta_pregunta_paciente integer PRIMARY KEY,"
                                + "int_id_pregunta_paciente integer,"
                                + "int_id_doctor integer,"
                                + "str_detalle text,"
                                + "int_puntaje integer,"
                                + "dat_fecha numeric)";

    private static final String CREATE_ENCUESTA= "CREATE TABLE ENCUESTA ("
            + "int_id_encuesta integer PRIMARY KEY,"
            + "str_pregunta text,"
            + "int_orden integer)";


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
                db.execSQL(CREATE_USUARIO);
                db.execSQL(CREATE_PACIENTE);
                db.execSQL(CREATE_ESPECIALIDAD);  
                db.execSQL(CREATE_CLINICA);
                db.execSQL(CREATE_DOCTOR);
                db.execSQL(CREATE_CLINICA_ESPECIALIDAD);
                db.execSQL(CREATE_SEGURO);
                db.execSQL(CREATE_CLINICA_SEGURO);
                db.execSQL(CREATE_CASOS_SALUD);
                db.execSQL(CREATE_RESPUESTA_CASOS_SALUD);
                db.execSQL(CREATE_CITA_PACIENTE);
                db.execSQL(CREATE_PREGUNTA_PACIENTE);
                db.execSQL(CREATE_RESPUESTA_PREGUNTA_PACIENTE);
                db.execSQL(CREATE_ENCUESTA);
                
        }   
         
	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {

                db.execSQL("drop table if exists USUARIO");
                db.execSQL(CREATE_USUARIO);
                db.execSQL("drop table if exists PACIENTE");
                db.execSQL(CREATE_PACIENTE);
                db.execSQL("drop table if exists ESPECIALIDAD");
                db.execSQL(CREATE_ESPECIALIDAD);
                db.execSQL("drop table if exists CLINICA");
                db.execSQL(CREATE_CLINICA);
                db.execSQL("drop table if exists DOCTOR");
                db.execSQL(CREATE_DOCTOR);
                db.execSQL("drop table if exists CLINICA_ESPECIALIDAD");
                db.execSQL(CREATE_CLINICA_ESPECIALIDAD);
                db.execSQL("drop table if exists SEGURO");
                db.execSQL(CREATE_SEGURO);
                db.execSQL("drop table if exists CLINICA_SEGURO");
                db.execSQL(CREATE_CLINICA_SEGURO);
                db.execSQL("drop table if exists CASOS_SALUD");
                db.execSQL(CREATE_CASOS_SALUD);
                db.execSQL("drop table if exists RESPUESTA_CASOS_SALUD");
                db.execSQL(CREATE_RESPUESTA_CASOS_SALUD);
                db.execSQL("drop table if exists CITA_PACIENTE");
                db.execSQL(CREATE_CITA_PACIENTE);
                db.execSQL("drop table if exists PREGUNTA_PACIENTE");
                db.execSQL(CREATE_PREGUNTA_PACIENTE);
                db.execSQL("drop table if exists RESPUESTA_PREGUNTA_PACIENTE");
                db.execSQL(CREATE_RESPUESTA_PREGUNTA_PACIENTE);
                db.execSQL("drop table if exists ENCUESTA");
                db.execSQL(CREATE_ENCUESTA);
	}	
        
}