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
public class clsPaciente {
    private int int_id_paciente;
    private String str_nombres;
    private String str_apellido_paterno;
    private String str_apellido_materno;
    private String str_dni;
    private Date dat_fecha_nacimiento;
    private boolean bol_sexo;
    private int int_estatura;
    private boolean bol_cardiovasculares;
    private boolean bol_musculares;
    private boolean bol_digestivos;
    private boolean bol_alergicos;
    private boolean bol_alcohol;
    private boolean bol_tabaquismo;
    private boolean bol_drogas;
    private boolean bol_psicologicos;
    private int int_id_persona;


    public clsPaciente() {
    }

    public clsPaciente(int int_id_paciente, String str_nombres) {
        this.int_id_paciente = int_id_paciente;
        this.str_nombres = str_nombres;
    }

    public clsPaciente(int int_id_paciente) {
        this.int_id_paciente = int_id_paciente;
    }

    public int getInt_id_paciente() {
        return int_id_paciente;
    }

    public void setInt_id_paciente(int int_id_paciente) {
        this.int_id_paciente = int_id_paciente;
    }

    public String getStr_nombres() {
        return str_nombres;
    }

    public void setStr_nombres(String str_nombres) {
        this.str_nombres = str_nombres;
    }

    public Date getDat_fecha_nacimiento() {
        return dat_fecha_nacimiento;
    }

    public void setDat_fecha_nacimiento(Date dat_fecha_nacimiento) {
        this.dat_fecha_nacimiento = dat_fecha_nacimiento;
    }

    public boolean isBol_sexo() {
        return bol_sexo;
    }

    public void setBol_sexo(boolean bol_sexo) {
        this.bol_sexo = bol_sexo;
    }



    public boolean isBol_cardiovasculares() {
        return bol_cardiovasculares;
    }

    public void setBol_cardiovasculares(boolean bol_cardiovasculares) {
        this.bol_cardiovasculares = bol_cardiovasculares;
    }

    public boolean isBol_musculares() {
        return bol_musculares;
    }

    public void setBol_musculares(boolean bol_musculares) {
        this.bol_musculares = bol_musculares;
    }

    public boolean isBol_digestivos() {
        return bol_digestivos;
    }

    public void setBol_digestivos(boolean bol_digestivos) {
        this.bol_digestivos = bol_digestivos;
    }

    public boolean isBol_alergicos() {
        return bol_alergicos;
    }

    public void setBol_alergicos(boolean bol_alergicos) {
        this.bol_alergicos = bol_alergicos;
    }

    public boolean isBol_alcohol() {
        return bol_alcohol;
    }

    public void setBol_alcohol(boolean bol_alcohol) {
        this.bol_alcohol = bol_alcohol;
    }

    public boolean isBol_tabaquismo() {
        return bol_tabaquismo;
    }

    public void setBol_tabaquismo(boolean bol_tabaquismo) {
        this.bol_tabaquismo = bol_tabaquismo;
    }

    public boolean isBol_drogas() {
        return bol_drogas;
    }

    public void setBol_drogas(boolean bol_drogas) {
        this.bol_drogas = bol_drogas;
    }

    public boolean isBol_psicologicos() {
        return bol_psicologicos;
    }

    public void setBol_psicologicos(boolean bol_psicologicos) {
        this.bol_psicologicos = bol_psicologicos;
    }

    public String getStr_apellido_paterno() {
        return str_apellido_paterno;
    }

    public void setStr_apellido_paterno(String str_apellido_paterno) {
        this.str_apellido_paterno = str_apellido_paterno;
    }

    public String getStr_apellido_materno() {
        return str_apellido_materno;
    }

    public void setStr_apellido_materno(String str_apellido_materno) {
        this.str_apellido_materno = str_apellido_materno;
    }

    public int getInt_estatura() {
        return int_estatura;
    }

    public void setInt_estatura(int int_estatura) {
        this.int_estatura = int_estatura;
    }

    @Override
    public String toString() {
        return ((str_apellido_paterno!=null)?str_apellido_paterno + " " :"")+ ((str_apellido_materno!=null)?str_apellido_materno + ", " :"")+((str_nombres!=null)?str_nombres:"");

    }

    public String getStr_dni() {
        return str_dni;
    }

    public void setStr_dni(String str_dni) {
        this.str_dni = str_dni;
    }

    public int getInt_id_persona() {
        return int_id_persona;
    }

    public void setInt_id_persona(int int_id_persona) {
        this.int_id_persona = int_id_persona;
    }

}
