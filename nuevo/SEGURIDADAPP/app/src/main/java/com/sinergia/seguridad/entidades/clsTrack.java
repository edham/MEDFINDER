package com.sinergia.seguridad.entidades;

import java.util.Date;

/**
 * Created by EdHam on 08/03/2016.
 */
public class clsTrack {
    private int int_id;
    private double dou_latitud;
    private double dou_longitud;
    private String str_direccion;
    private boolean bool_encuandrante;
    private Date dat_fec_reg;

    public clsTrack() {
        this.dat_fec_reg= new Date();
    }

    public int getInt_id() {
        return int_id;
    }

    public void setInt_id(int int_id) {
        this.int_id = int_id;
    }

    public double getDou_latitud() {
        return dou_latitud;
    }

    public void setDou_latitud(double dou_latitud) {
        this.dou_latitud = dou_latitud;
    }

    public double getDou_longitud() {
        return dou_longitud;
    }

    public void setDou_longitud(double dou_longitud) {
        this.dou_longitud = dou_longitud;
    }

    public Date getDat_fec_reg() {
        return dat_fec_reg;
    }

    public void setDat_fec_reg(Date dat_fec_reg) {
        this.dat_fec_reg = dat_fec_reg;
    }

    public String getStr_direccion() {
        return str_direccion;
    }

    public void setStr_direccion(String str_direccion) {
        this.str_direccion = str_direccion;
    }

    public boolean isBool_encuandrante() {
        return bool_encuandrante;
    }

    public void setBool_encuandrante(boolean bool_encuandrante) {
        this.bool_encuandrante = bool_encuandrante;
    }
}
