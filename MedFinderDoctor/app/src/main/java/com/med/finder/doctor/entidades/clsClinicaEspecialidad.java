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
public class clsClinicaEspecialidad {
    private int int_id_clinica_especialidad;
    private int int_id_clinica;
    private clsEspecialidad objEspecialidad;
    private Date dat_hora_inicio;
    private Date dat_hora_fin;
    private String str_detalle_horario;
    private int int_estado;
    

    public clsClinicaEspecialidad() {
    }

    public clsClinicaEspecialidad(String entidad) {
        String [] parametro = entidad.split("\\<+atributo+>");   
        this.int_id_clinica_especialidad = Integer.parseInt(parametro[0].trim());
        this.int_id_clinica = Integer.parseInt(parametro[1].trim());
        this.objEspecialidad = new clsEspecialidad(Integer.parseInt(parametro[2].trim()));
        this.dat_hora_inicio = new Date(Long.parseLong(parametro[3].trim()));
        this.dat_hora_fin = new Date(Long.parseLong(parametro[4].trim()));
        this.str_detalle_horario = parametro[5].trim();
        this.int_estado = Integer.parseInt(parametro[6].trim());
    }

    public int getInt_id_clinica_especialidad() {
        return int_id_clinica_especialidad;
    }

    public void setInt_id_clinica_especialidad(int int_id_clinica_especialidad) {
        this.int_id_clinica_especialidad = int_id_clinica_especialidad;
    }

 
    public clsEspecialidad getObjEspecialidad() {
        return objEspecialidad;
    }

    public void setObjEspecialidad(clsEspecialidad objEspecialidad) {
        this.objEspecialidad = objEspecialidad;
    }

    public int getInt_id_clinica() {
        return int_id_clinica;
    }

    public void setInt_id_clinica(int int_id_clinica) {
        this.int_id_clinica = int_id_clinica;
    }

    public int getInt_estado() {
        return int_estado;
    }

    public void setInt_estado(int int_estado) {
        this.int_estado = int_estado;
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

    public String getStr_detalle_horario() {
        return str_detalle_horario;
    }

    public void setStr_detalle_horario(String str_detalle_horario) {
        this.str_detalle_horario = str_detalle_horario;
    }
  
    
}
