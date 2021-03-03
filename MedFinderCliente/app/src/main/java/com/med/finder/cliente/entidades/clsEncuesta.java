/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.med.finder.cliente.entidades;

/**
 *
 * @author Toditos
 */
public class clsEncuesta {
    private int int_id_encuesta;
    private String str_pregunta;
    private int int_valor;
    private int int_orden;

    public clsEncuesta() {
        this.int_valor=0;
    }

    public int getInt_id_encuesta() {
        return int_id_encuesta;
    }

    public void setInt_id_encuesta(int int_id_encuesta) {
        this.int_id_encuesta = int_id_encuesta;
    }

    public String getStr_pregunta() {
        return str_pregunta;
    }

    public void setStr_pregunta(String str_pregunta) {
        this.str_pregunta = str_pregunta;
    }

    public int getInt_orden() {
        return int_orden;
    }

    public void setInt_orden(int int_orden) {
        this.int_orden = int_orden;
    }

    public int getInt_valor() {
        return int_valor;
    }

    public void setInt_valor(int int_valor) {
        this.int_valor = int_valor;
    }

    @Override
    public String toString() {
        return str_pregunta ;
    }
   
}
