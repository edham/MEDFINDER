package com.sinergia.negocios.entidades;

/**
 * Created by EdHam on 11/03/2016.
 */
public class clsEntidad {
    private int int_id;
    private String str_nombre;
    private String str_descripcion;
    private String str_tipo;

    public clsEntidad() {
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

    public String getStr_tipo() {
        return str_tipo;
    }

    public void setStr_tipo(String str_tipo) {
        this.str_tipo = str_tipo;
    }
}
