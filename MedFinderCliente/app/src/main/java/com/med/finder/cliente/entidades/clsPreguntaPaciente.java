/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.med.finder.cliente.entidades;

import java.util.Date;

/**
 *
 * @author Toditos
 */
public class clsPreguntaPaciente {
    private int int_id_pregunta_paciente;
    private clsPaciente objPaciente;
    private clsEspecialidad objEspecialidad;
    private String str_asunto;
    private String str_paciente_detalle;
    private byte[] byte_imagen;
    private int int_estado;
    private Date dat_inicio;
    private Date dat_fin;

    public clsPreguntaPaciente() {
       this.byte_imagen=null;
       this.int_estado=0;
       this.dat_inicio=new Date();
    }

    public clsPreguntaPaciente(String entidad) {
        String [] parametro = entidad.split("\\<+atributo+>");  
        this.int_id_pregunta_paciente = Integer.parseInt(parametro[0].trim());
        this.objPaciente = new clsPaciente(Integer.parseInt(parametro[1].trim()));
        this.objEspecialidad = new clsEspecialidad(Integer.parseInt(parametro[2].trim()));
        this.str_asunto = parametro[3].trim();
        this.str_paciente_detalle = parametro[4].trim();
        this.int_estado = Integer.parseInt(parametro[5].trim());
        this.dat_inicio = new Date(Long.parseLong(parametro[6].trim()));
    }

    
    
    
    
    public int getInt_id_pregunta_paciente() {
        return int_id_pregunta_paciente;
    }

    public void setInt_id_pregunta_paciente(int int_id_pregunta_paciente) {
        this.int_id_pregunta_paciente = int_id_pregunta_paciente;
    }

    public clsPaciente getObjPaciente() {
        return objPaciente;
    }

    public void setObjPaciente(clsPaciente objPaciente) {
        this.objPaciente = objPaciente;
    }

    public clsEspecialidad getObjEspecialidad() {
        return objEspecialidad;
    }

    public void setObjEspecialidad(clsEspecialidad objEspecialidad) {
        this.objEspecialidad = objEspecialidad;
    }

    public String getStr_asunto() {
        return str_asunto;
    }

    public void setStr_asunto(String str_asunto) {
        this.str_asunto = str_asunto;
    }

    public String getStr_paciente_detalle() {
        return str_paciente_detalle;
    }

    public void setStr_paciente_detalle(String str_paciente_detalle) {
        this.str_paciente_detalle = str_paciente_detalle;
    }

    public byte[] getByte_imagen() {
        return byte_imagen;
    }

    public void setByte_imagen(byte[] byte_imagen) {
        this.byte_imagen = byte_imagen;
    }

    public int getInt_estado() {
        return int_estado;
    }

    public void setInt_estado(int int_estado) {
        this.int_estado = int_estado;
    }

    public Date getDat_inicio() {
        return dat_inicio;
    }

    public void setDat_inicio(Date dat_inicio) {
        this.dat_inicio = dat_inicio;
    }

    public Date getDat_fin() {
        return dat_fin;
    }

    public void setDat_fin(Date dat_fin) {
        this.dat_fin = dat_fin;
    }
    
    
}
