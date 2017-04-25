package com.sinergia.negocios.entidades;

/**
 * Created by EdHam on 11/03/2016.
 */
public class clsPersonal {
    private int int_id;
    private String str_nombres;
    private String str_ape_paterno;
    private String str_usuario;
    private String str_clave;
    private String str_ape_materno;
    private String str_telefono;
    private String str_cargo;
    private boolean bool_principal;

    public clsPersonal()
    {
        this.bool_principal=false;
    }

    public int getInt_id() {
        return int_id;
    }

    public void setInt_id(int int_id) {
        this.int_id = int_id;
    }

    public String getStr_nombres() {
        return str_nombres;
    }

    public void setStr_nombres(String str_nombres) {
        this.str_nombres = str_nombres;
    }

    public String getStr_ape_paterno() {
        return str_ape_paterno;
    }

    public void setStr_ape_paterno(String str_ape_paterno) {
        this.str_ape_paterno = str_ape_paterno;
    }

    public String getStr_ape_materno() {
        return str_ape_materno;
    }

    public void setStr_ape_materno(String str_ape_materno) {
        this.str_ape_materno = str_ape_materno;
    }

    public String getStr_telefono() {
        return str_telefono;
    }

    public void setStr_telefono(String str_telefono) {
        this.str_telefono = str_telefono;
    }

    public String getStr_cargo() {
        return str_cargo;
    }

    public void setStr_cargo(String str_cargo) {
        this.str_cargo = str_cargo;
    }

    public boolean isBool_principal() {
        return bool_principal;
    }

    public void setBool_principal(boolean bool_principal) {
        this.bool_principal = bool_principal;
    }

    public String getStr_usuario() {
        return str_usuario;
    }

    public void setStr_usuario(String str_usuario) {
        this.str_usuario = str_usuario;
    }

    public String getStr_clave() {
        return str_clave;
    }

    public void setStr_clave(String str_clave) {
        this.str_clave = str_clave;
    }
}
