/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.med.finder.entidades;

/**
 *
 * @author Toditos
 */
public class clsClinicaSeguro {
    private int int_id_clinica_seguro;
    private int int_id_clinica;
    private int int_id_seguro;
     private int int_estado;

    public clsClinicaSeguro() {
    }

    public clsClinicaSeguro(String entidad) {
        String [] parametro = entidad.split("\\<+atributo+>");   
        this.int_id_clinica_seguro = Integer.parseInt(parametro[0].trim());
        this.int_id_clinica = Integer.parseInt(parametro[1].trim());
        this.int_id_seguro = Integer.parseInt(parametro[2].trim());
        this.int_estado = Integer.parseInt(parametro[3].trim());
    }

    public int getInt_id_clinica_seguro() {
        return int_id_clinica_seguro;
    }

    public void setInt_id_clinica_seguro(int int_id_clinica_seguro) {
        this.int_id_clinica_seguro = int_id_clinica_seguro;
    }

    public int getInt_id_clinica() {
        return int_id_clinica;
    }

    public void setInt_id_clinica(int int_id_clinica) {
        this.int_id_clinica = int_id_clinica;
    }

    public int getInt_id_seguro() {
        return int_id_seguro;
    }

    public void setInt_id_seguro(int int_id_seguro) {
        this.int_id_seguro = int_id_seguro;
    }



    public int getInt_estado() {
        return int_estado;
    }

    public void setInt_estado(int int_estado) {
        this.int_estado = int_estado;
    }

    

    
    
}
