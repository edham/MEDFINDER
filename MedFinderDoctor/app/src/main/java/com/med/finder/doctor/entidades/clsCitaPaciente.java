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
public class clsCitaPaciente {
    private int int_id_cita_paciente;
    private clsDoctor objDoctor;
    private clsPaciente objPaciente;
    private String str_detalle;
    private String str_respuesta;
    private Date dat_creacion;
    private Date dat_atencion;
    private int int_estado;

    public clsCitaPaciente() {
        this.dat_atencion=new Date();
        this.dat_creacion=new Date();
        this.int_estado=0;
    }


    
    public int getInt_id_cita_paciente() {
        return int_id_cita_paciente;
    }

    public void setInt_id_cita_paciente(int int_id_cita_paciente) {
        this.int_id_cita_paciente = int_id_cita_paciente;
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

    public clsPaciente getObjPaciente() {
        return objPaciente;
    }

    public void setObjPaciente(clsPaciente objPaciente) {
        this.objPaciente = objPaciente;
    }

    public Date getDat_creacion() {
        return dat_creacion;
    }

    public void setDat_creacion(Date dat_creacion) {
        this.dat_creacion = dat_creacion;
    }

    public Date getDat_atencion() {
        return dat_atencion;
    }

    public void setDat_atencion(Date dat_atencion) {
        this.dat_atencion = dat_atencion;
    }

    public int getInt_estado() {
        return int_estado;
    }

    public void setInt_estado(int int_estado) {
        this.int_estado = int_estado;
    }

    public String getStr_respuesta() {
        return str_respuesta;
    }

    public void setStr_respuesta(String str_respuesta) {
        this.str_respuesta = str_respuesta;
    }
}
