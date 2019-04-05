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
public class clsInicio {
    private int int_id;
    private int int_tipo;
    private Date dat_ini;
    private clsPaciente objPaciente;



    public clsInicio() {
    }

    public int getInt_id() {
        return int_id;
    }

    public void setInt_id(int int_id) {
        this.int_id = int_id;
    }

    public int getInt_tipo() {
        return int_tipo;
    }

    public void setInt_tipo(int int_tipo) {
        this.int_tipo = int_tipo;
    }

    public Date getDat_ini() {
        return dat_ini;
    }

    public void setDat_ini(Date dat_ini) {
        this.dat_ini = dat_ini;
    }

    public clsPaciente getObjPaciente() {
        return objPaciente;
    }

    public void setObjPaciente(clsPaciente objPaciente) {
        this.objPaciente = objPaciente;
    }
}
