/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.med.finder.cliente.entidades;

import android.util.Base64;

/**
 *
 * @author Toditos
 */
public class clsDoctor {
 
    private int int_id_doctor;
    private String str_nombres;
    private String str_apellido_paterno;
    private String str_apellido_materno;    
    private String str_dni;
    private String str_codigo_colegiatura;
    private String str_direccion;
    private String str_direccion_detalle;
    private String str_telefono;
    private double dou_longitud;
    private double dou_latitud;
    private clsEspecialidad objEspecialidad;
    private int int_puntuje;
    private byte[] byte_foto;
    private boolean bol_favorito;

    public clsDoctor() {
        this.bol_favorito = false;
    }

    public clsDoctor(String entidad) {
        String [] parametro = entidad.split("\\<+atributo+>");   
        this.int_id_doctor = Integer.parseInt(parametro[0].trim());
        this.str_nombres = parametro[1].trim();
        this.str_apellido_paterno = parametro[2].trim();
        this.str_apellido_materno = parametro[3].trim();
        this.str_dni = parametro[4].trim();
        this.str_codigo_colegiatura = parametro[5].trim();
        this.str_direccion = parametro[6].trim();
        this.str_direccion_detalle = parametro[7].trim();
        this.str_telefono = parametro[8].trim();
        this.dou_longitud = Double.parseDouble(parametro[9].trim());
        this.dou_latitud = Double.parseDouble(parametro[10].trim());
        this.int_puntuje = Integer.parseInt(parametro[11].trim());
        this.objEspecialidad = new clsEspecialidad(Integer.parseInt(parametro[12].trim()));
        if(parametro[13].trim().equals("0"))
            this.byte_foto = null;
        else
            this.byte_foto = Base64.decode(parametro[13].trim(),Base64.NO_WRAP|Base64.URL_SAFE);        
        this.bol_favorito = false;
    }

    
    public clsDoctor(int int_id_doctor) {
        this.int_id_doctor = int_id_doctor;
    }

    public int getInt_id_doctor() {
        return int_id_doctor;
    }

    public void setInt_id_doctor(int int_id_doctor) {
        this.int_id_doctor = int_id_doctor;
    }

    public String getStr_nombres() {
        return str_nombres;
    }

    public void setStr_nombres(String str_nombres) {
        this.str_nombres = str_nombres;
    }

    public String getStr_apellido_paterno() {
        return str_apellido_paterno;
    }

    public void setStr_apellido_paterno(String str_apellido_paterno) {
        this.str_apellido_paterno = str_apellido_paterno;
    }

    public String getStr_apellido_materno() {
        return str_apellido_materno;
    }

    public void setStr_apellido_materno(String str_apellido_materno) {
        this.str_apellido_materno = str_apellido_materno;
    }

   

    public String getStr_dni() {
        return str_dni;
    }

    public void setStr_dni(String str_dni) {
        this.str_dni = str_dni;
    }

    public String getStr_codigo_colegiatura() {
        return str_codigo_colegiatura;
    }

    public void setStr_codigo_colegiatura(String str_codigo_colegiatura) {
        this.str_codigo_colegiatura = str_codigo_colegiatura;
    }

    public String getStr_direccion() {
        return str_direccion;
    }

    public void setStr_direccion(String str_direccion) {
        this.str_direccion = str_direccion;
    }

    public String getStr_direccion_detalle() {
        return str_direccion_detalle;
    }

    public void setStr_direccion_detalle(String str_direccion_detalle) {
        this.str_direccion_detalle = str_direccion_detalle;
    }

    public double getDou_longitud() {
        return dou_longitud;
    }

    public void setDou_longitud(double dou_longitud) {
        this.dou_longitud = dou_longitud;
    }

    public double getDou_latitud() {
        return dou_latitud;
    }

    public void setDou_latitud(double dou_latitud) {
        this.dou_latitud = dou_latitud;
    }

    public int getInt_puntuje() {
        return int_puntuje;
    }

    public void setInt_puntuje(int int_puntuje) {
        this.int_puntuje = int_puntuje;
    }

    public byte[] getByte_foto() {
        return byte_foto;
    }

    public void setByte_foto(byte[] byte_foto) {
        this.byte_foto = byte_foto;
    }

    public String getStr_telefono() {
        return str_telefono;
    }

    public void setStr_telefono(String str_telefono) {
        this.str_telefono = str_telefono;
    }

    public boolean isBol_favorito() {
        return bol_favorito;
    }

    public void setBol_favorito(boolean bol_favorito) {
        this.bol_favorito = bol_favorito;
    }

    public clsEspecialidad getObjEspecialidad() {
        return objEspecialidad;
    }

    public void setObjEspecialidad(clsEspecialidad objEspecialidad) {
        this.objEspecialidad = objEspecialidad;
    }

   

}
