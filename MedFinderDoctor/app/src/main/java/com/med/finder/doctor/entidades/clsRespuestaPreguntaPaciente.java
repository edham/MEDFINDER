/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.med.finder.doctor.entidades;

import java.util.Date;

/**
 *
 * @author Toditos
 */
public class clsRespuestaPreguntaPaciente {
    private int int_id_respuesta_pregunta_paciente;
    private int int_id_pregunta_paciente;
    private clsDoctor objDoctor;
    private String str_detalle;
    private int int_puntaje;
    private Date dat_fecha;

    public clsRespuestaPreguntaPaciente() {
    }

    public clsRespuestaPreguntaPaciente(String entidad) {
        String [] parametro = entidad.split("\\<+atributo+>"); 
        this.int_id_respuesta_pregunta_paciente = Integer.parseInt(parametro[0].trim());
        this.int_id_pregunta_paciente = Integer.parseInt(parametro[1].trim());
        this.objDoctor = new clsDoctor(Integer.parseInt(parametro[2].trim()));
        this.str_detalle =  parametro[3].trim();
        this.int_puntaje =  Integer.parseInt(parametro[4].trim());
        this.dat_fecha = new Date(Long.parseLong(parametro[5].trim()));
    }

    public int getInt_id_respuesta_pregunta_paciente() {
        return int_id_respuesta_pregunta_paciente;
    }

    public void setInt_id_respuesta_pregunta_paciente(int int_id_respuesta_pregunta_paciente) {
        this.int_id_respuesta_pregunta_paciente = int_id_respuesta_pregunta_paciente;
    }

    public int getInt_id_pregunta_paciente() {
        return int_id_pregunta_paciente;
    }

    public void setInt_id_pregunta_paciente(int int_id_pregunta_paciente) {
        this.int_id_pregunta_paciente = int_id_pregunta_paciente;
    }



    public clsDoctor getObjDoctor() {
        return objDoctor;
    }

    public void setObjDoctor(clsDoctor objDoctor) {
        this.objDoctor = objDoctor;
    }

    public String getStr_detalle() {
        return str_detalle;
    }

    public void setStr_detalle(String str_detalle) {
        this.str_detalle = str_detalle;
    }

    public int getInt_puntaje() {
        return int_puntaje;
    }

    public void setInt_puntaje(int int_puntaje) {
        this.int_puntaje = int_puntaje;
    }

    public Date getDat_fecha() {
        return dat_fecha;
    }

    public void setDat_fecha(Date dat_fecha) {
        this.dat_fecha = dat_fecha;
    }

   
    
}
