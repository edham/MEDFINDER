/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.med.finder.cliente.entidades;

/**
 *
 * @author Toditos
 */
public class clsRespuestaCasosSalud {
    private int int_id_respuesta_casos_salud;
    private int int_id_doctor;
    private int int_id_casos_salud;
    private String str_descripcion;
    private int int_puntaje;
    private boolean bol_puntaje;

    public clsRespuestaCasosSalud(String entidad) {
        String [] parametro = entidad.split("\\<+atributo+>");   
        this.int_id_respuesta_casos_salud = Integer.parseInt(parametro[0].trim());
        this.int_id_doctor = Integer.parseInt(parametro[1].trim());
        this.int_id_casos_salud = Integer.parseInt(parametro[2].trim());
        this.str_descripcion = parametro[3].trim();
        this.int_puntaje = Integer.parseInt(parametro[4].trim());
        this.bol_puntaje = false;
    }

    public clsRespuestaCasosSalud() {

    }

    public int getInt_id_respuesta_casos_salud() {
        return int_id_respuesta_casos_salud;
    }

    public void setInt_id_respuesta_casos_salud(int int_id_respuesta_casos_salud) {
        this.int_id_respuesta_casos_salud = int_id_respuesta_casos_salud;
    }

    public int getInt_id_doctor() {
        return int_id_doctor;
    }

    public void setInt_id_doctor(int int_id_doctor) {
        this.int_id_doctor = int_id_doctor;
    }

    public int getInt_id_casos_salud() {
        return int_id_casos_salud;
    }

    public void setInt_id_casos_salud(int int_id_casos_salud) {
        this.int_id_casos_salud = int_id_casos_salud;
    }

 

    public String getStr_descripcion() {
        return str_descripcion;
    }

    public void setStr_descripcion(String str_descripcion) {
        this.str_descripcion = str_descripcion;
    }

    public int getInt_puntaje() {
        return int_puntaje;
    }

    public void setInt_puntaje(int int_puntaje) {
        this.int_puntaje = int_puntaje;
    }

    public boolean isBol_puntaje() {
        return bol_puntaje;
    }

    public void setBol_puntaje(boolean bol_puntaje) {
        this.bol_puntaje = bol_puntaje;
    }

    
}
