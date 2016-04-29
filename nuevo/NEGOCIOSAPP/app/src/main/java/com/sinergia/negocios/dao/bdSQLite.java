package com.sinergia.negocios.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class bdSQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Negocio";
    private static final int DATABASE_VERSION = 1;        

    private static final String CREATE_PERSONAL = "CREATE TABLE PERSONAL ("
                                + "int_id integer NOT NULL PRIMARY KEY,"
                                + "str_usuario text NOT NULL,"
                                + "str_clave text NOT NULL,"
                                + "str_nombres text NOT NULL,"
                                + "str_ape_paterno text NOT NULL,"
                                + "str_ape_materno text NOT NULL,"
                                + "str_telefono text NOT NULL,"
                                + "str_cargo text NOT NULL,"
                                + "bool_principal integer NOT NULL)";

    private static final String CREATE_VEHICULO = "CREATE TABLE VEHICULO ("
                            + "int_id integer NOT NULL PRIMARY KEY,"
                            + "int_numero integer NOT NULL,"
                            + "str_placa text NOT NULL,"
                            + "str_tipo text NOT NULL,"
                            + "str_marca text NOT NULL,"
                            + "str_clase text NOT NULL)";

    private static final String CREATE_SESION_VIGILANCIA= "CREATE TABLE SESION_VIGILANCIA ("
                        + "int_id integer NOT NULL PRIMARY KEY,"
                        + "int_id_cuadrante integer NOT NULL,"
                        + "dat_fec_ini integer NOT NULL)";

    private static final String CREATE_ENTIDAD= "CREATE TABLE ENTIDAD ("
                                + "int_id integer NOT NULL PRIMARY KEY,"
                                + "str_nombre text NOT NULL,"
                                + "str_descripcion text NOT NULL,"
                                + "str_tipo text NOT NULL)";

    private static final String CREATE_TRACK = "CREATE TABLE TRACK ("
                                + "int_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                                + "dou_latitud numeric NOT NULL,"
                                + "dou_longitud numeric NOT NULL,"
                                + "dat_fec_reg integer NOT NULL)";

    private static final String CREATE_CUADRANTE = "CREATE TABLE CUADRANTE ("
            + "int_id integer NOT NULL PRIMARY KEY,"
            + "str_nombre text NOT NULL,"
            + "str_descripcion text NOT NULL)";

    private static final String CREATE_CUADRANTE_COORDENADAS = "CREATE TABLE CUADRANTE_COORDENADAS ("
            + "int_id integer NOT NULL PRIMARY KEY,"
            + "dou_latitud numeric NOT NULL,"
            + "dou_longitud numeric NOT NULL,"
            + "int_id_cuadrante integer NOT NULL)";

	public bdSQLite(Context context, CursorFactory factory) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
                db.execSQL(CREATE_PERSONAL);
                db.execSQL(CREATE_ENTIDAD);
                db.execSQL(CREATE_TRACK);
                db.execSQL(CREATE_VEHICULO);
                db.execSQL(CREATE_CUADRANTE);
                db.execSQL(CREATE_CUADRANTE_COORDENADAS);
                db.execSQL(CREATE_SESION_VIGILANCIA);



        }   
         
	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {

                db.execSQL("drop table if exists PERSONAL");
                db.execSQL(CREATE_PERSONAL);
                db.execSQL("drop table if exists ENTIDAD");
                db.execSQL(CREATE_ENTIDAD);
                db.execSQL("drop table if exists TRACK");
                db.execSQL(CREATE_TRACK);
                db.execSQL("drop table if exists VEHICULO");
                db.execSQL(CREATE_VEHICULO);
                db.execSQL("drop table if exists CUADRANTE");
                db.execSQL(CREATE_CUADRANTE);
                db.execSQL("drop table if exists CUADRANTE_COORDENADAS");
                db.execSQL(CREATE_CUADRANTE_COORDENADAS);
                db.execSQL("drop table if exists SESION_VIGILANCIA");
                db.execSQL(CREATE_SESION_VIGILANCIA);
	}	
        
}