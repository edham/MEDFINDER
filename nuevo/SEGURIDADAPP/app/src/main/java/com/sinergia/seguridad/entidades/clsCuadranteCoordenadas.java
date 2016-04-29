package com.sinergia.seguridad.entidades;

import java.util.Date;

/**
 * Created by EdHam on 19/03/2016.
 */
public class clsCuadranteCoordenadas {

    private int int_id;
    private double dou_latitud;
    private double dou_longitud;
    private int  int_id_cuadrante;

    public clsCuadranteCoordenadas() {
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

    public int getInt_id_cuadrante() {
        return int_id_cuadrante;
    }

    public void setInt_id_cuadrante(int int_id_cuadrante) {
        this.int_id_cuadrante = int_id_cuadrante;
    }
}
