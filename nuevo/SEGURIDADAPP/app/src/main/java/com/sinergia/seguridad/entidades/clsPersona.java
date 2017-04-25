package com.sinergia.seguridad.entidades;

import java.util.Date;

/**
 * Created by EdHam on 27/04/2016.
 */
public class clsPersona {

    private int int_id;
    private String str_nombres;
    private String str_paterno;
    private String str_materno;
    private String str_dni;
    private Date dat_naciomiento;
    private boolean bool_sexo;
    private boolean bool_identificado;

    public clsPersona() {
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

    public String getStr_paterno() {
        return str_paterno;
    }

    public void setStr_paterno(String str_paterno) {
        this.str_paterno = str_paterno;
    }

    public String getStr_materno() {
        return str_materno;
    }

    public void setStr_materno(String str_materno) {
        this.str_materno = str_materno;
    }

    public String getStr_dni() {
        return str_dni;
    }

    public void setStr_dni(String str_dni) {
        this.str_dni = str_dni;
    }

    public Date getDat_naciomiento() {
        return dat_naciomiento;
    }

    public void setDat_naciomiento(Date dat_naciomiento) {
        this.dat_naciomiento = dat_naciomiento;
    }

    public boolean isBool_sexo() {
        return bool_sexo;
    }

    public void setBool_sexo(boolean bool_sexo) {
        this.bool_sexo = bool_sexo;
    }

    public boolean isBool_identificado() {
        return bool_identificado;
    }

    public void setBool_identificado(boolean bool_identificado) {
        this.bool_identificado = bool_identificado;
    }
}
