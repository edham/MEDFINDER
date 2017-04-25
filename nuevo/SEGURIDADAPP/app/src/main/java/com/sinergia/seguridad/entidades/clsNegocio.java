package com.sinergia.seguridad.entidades;

import java.util.Date;

/**
 * Created by EdHam on 27/04/2016.
 */
public class clsNegocio {
    private int int_id;
    private String str_nombre;
    private String str_comercial;
    private String str_tipo;
    private String str_ruc;
    private Date dat_registro;
    private int int_activacion;
    private int int_ubicacion_id;
    private String str_ubicacion_direccion;
    private Date dat_ubicacion_registro;
    private int int_ubicacion_activacion;
    private double dou_ubicacion_latitud;
    private double dou_ubicacion_longitud;
    private String str_ubicacion_referencia;
    private String str_ubicacion_telefono;
    private clsPersona objPersona;

    public clsNegocio() {
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

    public String getStr_comercial() {
        return str_comercial;
    }

    public void setStr_comercial(String str_comercial) {
        this.str_comercial = str_comercial;
    }

    public String getStr_tipo() {
        return str_tipo;
    }

    public void setStr_tipo(String str_tipo) {
        this.str_tipo = str_tipo;
    }

    public String getStr_ruc() {
        return str_ruc;
    }

    public void setStr_ruc(String str_ruc) {
        this.str_ruc = str_ruc;
    }

    public Date getDat_registro() {
        return dat_registro;
    }

    public void setDat_registro(Date dat_registro) {
        this.dat_registro = dat_registro;
    }

    public int getInt_activacion() {
        return int_activacion;
    }

    public void setInt_activacion(int int_activacion) {
        this.int_activacion = int_activacion;
    }

    public int getInt_ubicacion_id() {
        return int_ubicacion_id;
    }

    public void setInt_ubicacion_id(int int_ubicacion_id) {
        this.int_ubicacion_id = int_ubicacion_id;
    }

    public String getStr_ubicacion_direccion() {
        return str_ubicacion_direccion;
    }

    public void setStr_ubicacion_direccion(String str_ubicacion_direccion) {
        this.str_ubicacion_direccion = str_ubicacion_direccion;
    }

    public Date getDat_ubicacion_registro() {
        return dat_ubicacion_registro;
    }

    public void setDat_ubicacion_registro(Date dat_ubicacion_registro) {
        this.dat_ubicacion_registro = dat_ubicacion_registro;
    }

    public int getInt_ubicacion_activacion() {
        return int_ubicacion_activacion;
    }

    public void setInt_ubicacion_activacion(int int_ubicacion_activacion) {
        this.int_ubicacion_activacion = int_ubicacion_activacion;
    }

    public double getDou_ubicacion_latitud() {
        return dou_ubicacion_latitud;
    }

    public void setDou_ubicacion_latitud(double dou_ubicacion_latitud) {
        this.dou_ubicacion_latitud = dou_ubicacion_latitud;
    }

    public double getDou_ubicacion_longitud() {
        return dou_ubicacion_longitud;
    }

    public void setDou_ubicacion_longitud(double dou_ubicacion_longitud) {
        this.dou_ubicacion_longitud = dou_ubicacion_longitud;
    }

    public String getStr_ubicacion_referencia() {
        return str_ubicacion_referencia;
    }

    public void setStr_ubicacion_referencia(String str_ubicacion_referencia) {
        this.str_ubicacion_referencia = str_ubicacion_referencia;
    }

    public String getStr_ubicacion_telefono() {
        return str_ubicacion_telefono;
    }

    public void setStr_ubicacion_telefono(String str_ubicacion_telefono) {
        this.str_ubicacion_telefono = str_ubicacion_telefono;
    }

    public clsPersona getObjPersona() {
        return objPersona;
    }

    public void setObjPersona(clsPersona objPersona) {
        this.objPersona = objPersona;
    }
}
