/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.med.finder.entidades;

import android.util.Base64;

/**
 *
 * @author Toditos
 */
public class clsSeguro {
    private int int_id_seguro;
    private String str_nombre;
    private int int_estado;
    private byte[] byte_logo;

    public clsSeguro() {
    }

    public clsSeguro(String entidad) {
        String [] parametro = entidad.split("\\<+atributo+>");   
        this.int_id_seguro = Integer.parseInt(parametro[0].trim());
        this.str_nombre = parametro[1].trim();
        this.int_estado = Integer.parseInt(parametro[2].trim());
        if(parametro[3].trim().equals("0"))
            this.byte_logo = null;
        else
            this.byte_logo = Base64.decode(parametro[3].trim(),Base64.NO_WRAP|Base64.URL_SAFE);
    }

    public int getInt_id_seguro() {
        return int_id_seguro;
    }

    public void setInt_id_seguro(int int_id_seguro) {
        this.int_id_seguro = int_id_seguro;
    }

    public String getStr_nombre() {
        return str_nombre;
    }

    public void setStr_nombre(String str_nombre) {
        this.str_nombre = str_nombre;
    }

    public int getInt_estado() {
        return int_estado;
    }

    public void setInt_estado(int int_estado) {
        this.int_estado = int_estado;
    }

    public byte[] getByte_logo() {
        return byte_logo;
    }

    public void setByte_logo(byte[] byte_logo) {
        this.byte_logo = byte_logo;
    }
    
    
}
