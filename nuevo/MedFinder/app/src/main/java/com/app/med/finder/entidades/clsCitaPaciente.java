/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.med.finder.entidades;

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
    private Date dat_creacion;
    private Date dat_atencion;
    private int int_estado;

    public clsCitaPaciente() {
        this.dat_atencion=new Date();
        this.dat_creacion=new Date();
        this.int_estado=0;
    }

    public clsCitaPaciente(String entidad) {
        String [] parametro = entidad.split("\\<+atributo+>");   
        this.int_id_cita_paciente = Integer.parseInt(parametro[0].trim());
        this.objDoctor = new clsDoctor(Integer.parseInt(parametro[1].trim()));
        this.objPaciente = new clsPaciente(Integer.parseInt(parametro[2].trim()));
        this.str_detalle = parametro[3].trim();
        this.dat_creacion = new Date(Long.parseLong( parametro[4].trim()));
        this.dat_atencion = new Date(Long.parseLong( parametro[5].trim()));
        this.int_estado = Integer.parseInt(parametro[6].trim());
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

}
