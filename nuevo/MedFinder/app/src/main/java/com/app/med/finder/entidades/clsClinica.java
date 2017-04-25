/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.med.finder.entidades;

import android.util.Base64;

import java.util.Date;

/**
 *
 * @author Toditos
 */
public class clsClinica {
    private int int_id_clinica;
    private String str_nombre;
    private String str_slogan;
    private String str_direccion;
    private String str_descripcion;
    private Date dat_hora_inicio;
    private Date dat_hora_fin;
    private String str_clinica_atencion;
    private byte[] byte_logo;
    private String str_telefono;
    private String str_detalle_atencion;
    private double dou_longitud;
    private double dou_latitud;
    private int int_estado;

    public clsClinica() {
    }

    public clsClinica(String entidad) {
        String [] parametro = entidad.split("\\<+atributo+>");   
        this.int_id_clinica = Integer.parseInt(parametro[0].trim());
        this.str_nombre = parametro[1].trim();
        this.str_slogan = parametro[2].trim();
        this.str_direccion = parametro[3].trim();
        this.str_descripcion = parametro[4].trim();
        this.dat_hora_inicio = new Date(Long.parseLong(parametro[5].trim()));
        this.dat_hora_fin = new Date(Long.parseLong(parametro[6].trim()));
        this.str_clinica_atencion = parametro[7].trim();
        if(parametro[8].trim().equals("0"))
            this.byte_logo = null;
        else
            this.byte_logo = Base64.decode(parametro[8].trim(),Base64.NO_WRAP|Base64.URL_SAFE);
        this.str_telefono = parametro[9].trim();
        this.str_detalle_atencion = parametro[10].trim();
        this.dou_longitud = Double.parseDouble(parametro[11].trim());
        this.dou_latitud = Double.parseDouble(parametro[12].trim());
        this.int_estado = Integer.parseInt(parametro[13].trim());
    }

    
    public int getInt_id_clinica() {
        return int_id_clinica;
    }

    public void setInt_id_clinica(int int_id_clinica) {
        this.int_id_clinica = int_id_clinica;
    }

    public String getStr_nombre() {
        return str_nombre;
    }

    public void setStr_nombre(String str_nombre) {
        this.str_nombre = str_nombre;
    }

    public String getStr_slogan() {
        return str_slogan;
    }

    public void setStr_slogan(String str_slogan) {
        this.str_slogan = str_slogan;
    }

    public String getStr_direccion() {
        return str_direccion;
    }

    public void setStr_direccion(String str_direccion) {
        this.str_direccion = str_direccion;
    }

    public String getStr_descripcion() {
        return str_descripcion;
    }

    public void setStr_descripcion(String str_descripcion) {
        this.str_descripcion = str_descripcion;
    }

    public Date getDat_hora_inicio() {
        return dat_hora_inicio;
    }

    public void setDat_hora_inicio(Date dat_hora_inicio) {
        this.dat_hora_inicio = dat_hora_inicio;
    }

    public Date getDat_hora_fin() {
        return dat_hora_fin;
    }

    public void setDat_hora_fin(Date dat_hora_fin) {
        this.dat_hora_fin = dat_hora_fin;
    }

    public String getStr_clinica_atencion() {
        return str_clinica_atencion;
    }

    public void setStr_clinica_atencion(String str_clinica_atencion) {
        this.str_clinica_atencion = str_clinica_atencion;
    }

    public byte[] getByte_logo() {
        return byte_logo;
    }

    public void setByte_logo(byte[] byte_logo) {
        this.byte_logo = byte_logo;
    }

    public String getStr_detalle_atencion() {
        return str_detalle_atencion;
    }

    public void setStr_detalle_atencion(String str_detalle_atencion) {
        this.str_detalle_atencion = str_detalle_atencion;
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

    public int getInt_estado() {
        return int_estado;
    }

    public void setInt_estado(int int_estado) {
        this.int_estado = int_estado;
    }

    public String getStr_telefono() {
        return str_telefono;
    }

    public void setStr_telefono(String str_telefono) {
        this.str_telefono = str_telefono;
    }
    
    
}
