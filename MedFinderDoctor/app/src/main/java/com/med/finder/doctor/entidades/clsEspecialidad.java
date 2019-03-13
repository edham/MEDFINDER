/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.med.finder.doctor.entidades;

/**
 *
 * @author Toditos
 */
public class clsEspecialidad {
    private int int_id_especialidad;
    private String str_nombre;
    private String str_descripcion;
    private int int_estado;

    public clsEspecialidad() {
    }

    public clsEspecialidad(String entidad) {
        String [] parametro = entidad.split("\\<+atributo+>");   
        this.int_id_especialidad = Integer.parseInt(parametro[0].trim());
        this.str_nombre = parametro[1].trim();;
        this.str_descripcion = parametro[2].trim();
        this.int_estado = Integer.parseInt(parametro[3].trim());
    }

    public clsEspecialidad(int int_id_especialidad, String str_nombre) {
        this.int_id_especialidad = int_id_especialidad;
        this.str_nombre = str_nombre;
    }

    public clsEspecialidad(int int_id_especialidad) {
        this.int_id_especialidad = int_id_especialidad;
    }

    public int getInt_id_especialidad() {
        return int_id_especialidad;
    }

    public String getStr_nombre() {
        return str_nombre;
    }   

    public void setInt_id_especialidad(int int_id_especialidad) {
        this.int_id_especialidad = int_id_especialidad;
    }

    public void setStr_nombre(String str_nombre) {
        this.str_nombre = str_nombre;
    }

    public String getStr_descripcion() {
        return str_descripcion;
    }

    public void setStr_descripcion(String str_descripcion) {
        this.str_descripcion = str_descripcion;
    }

    public int getInt_estado() {
        return int_estado;
    }

    public void setInt_estado(int int_estado) {
        this.int_estado = int_estado;
    }
    
    @Override
    public String toString() {
        return str_nombre ;
    }
   
}
