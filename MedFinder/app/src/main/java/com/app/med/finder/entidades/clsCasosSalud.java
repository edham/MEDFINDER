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
public class clsCasosSalud {
    private int int_id_casos_salud;
    private String str_tema;
    private Date dat_inicio;
    private Date dat_fin;



    
    public clsCasosSalud() {
    }

    public clsCasosSalud(String entidad) {
         String [] parametro = entidad.split("\\<+atributo+>");   
        this.int_id_casos_salud = Integer.parseInt(parametro[0].trim());
        this.str_tema = parametro[1].trim();
        this.dat_inicio = new Date(Long.parseLong(parametro[2].trim()));
        this.dat_fin = new Date(Long.parseLong(parametro[3].trim()));
    }

    
    public int getInt_id_casos_salud() {
        return int_id_casos_salud;
    }

    public void setInt_id_casos_salud(int int_id_casos_salud) {
        this.int_id_casos_salud = int_id_casos_salud;
    }

    public String getStr_tema() {
        return str_tema;
    }

    public void setStr_tema(String str_tema) {
        this.str_tema = str_tema;
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
