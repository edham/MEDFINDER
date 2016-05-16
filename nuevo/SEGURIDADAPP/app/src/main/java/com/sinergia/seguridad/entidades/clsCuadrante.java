package com.sinergia.seguridad.entidades;

/**
 * Created by EdHam on 19/03/2016.
 */
public class clsCuadrante {


    private int int_id;
    private String str_nombre;
    private String str_descripcion;

    public clsCuadrante() {
    }

    public clsCuadrante(int int_id) {
        this.int_id = int_id;
    }

    public int getInt_id() {
        return int_id;
    }

    public void setInt_id(int int_id) {
        this.int_id = int_id;
    }

    public String getStr_nombre() {
        return str_nombre;
    }

    public void setStr_nombre(String str_nombre) {
        this.str_nombre = str_nombre;
    }

    public String getStr_descripcion() {
        return str_descripcion;
    }

    public void setStr_descripcion(String str_descripcion) {
        this.str_descripcion = str_descripcion;
    }

    @Override
    public String toString() {
        return str_nombre ;
    }
}
