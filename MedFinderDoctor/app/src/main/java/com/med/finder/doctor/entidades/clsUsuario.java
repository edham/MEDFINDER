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
public class clsUsuario {
    private int int_id_usuario;
    private String str_usuario;
    private String str_clave;
    private String str_nombres;
    private String str_apellido_paterno;
    private String str_apellido_materno;    
    private String str_email;
    private String str_dni;    
    private String str_telefono;
    private String str_direccion;
    private boolean bol_sexo;
    private int int_id_persona;   
    private byte[] byte_foto;

    public clsUsuario() {
        this.byte_foto = null;
    }

    public clsUsuario(int int_id_usuario) {
        this.int_id_usuario = int_id_usuario;
    }

    public int getInt_id_usuario() {
        return int_id_usuario;
    }

    public void setInt_id_usuario(int int_id_usuario) {
        this.int_id_usuario = int_id_usuario;
    }

    public String getStr_usuario() {
        return str_usuario;
    }

    public void setStr_usuario(String str_usuario) {
        this.str_usuario = str_usuario;
    }

    public String getStr_clave() {
        return str_clave;
    }

    public void setStr_clave(String str_clave) {
        this.str_clave = str_clave;
    }

    public String getStr_nombres() {
        return str_nombres;
    }

    public void setStr_nombres(String str_nombres) {
        this.str_nombres = str_nombres;
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

    public String getStr_email() {
        return str_email;
    }

    public void setStr_email(String str_email) {
        this.str_email = str_email;
    }

    public String getStr_dni() {
        return str_dni;
    }

    public void setStr_dni(String str_dni) {
        this.str_dni = str_dni;
    }

    public String getStr_telefono() {
        return str_telefono;
    }

    public void setStr_telefono(String str_telefono) {
        this.str_telefono = str_telefono;
    }

    public String getStr_direccion() {
        return str_direccion;
    }

    public void setStr_direccion(String str_direccion) {
        this.str_direccion = str_direccion;
    }

    public boolean isBol_sexo() {
        return bol_sexo;
    }

    public void setBol_sexo(boolean bol_sexo) {
        this.bol_sexo = bol_sexo;
    }

 
    public byte[] getByte_foto() {
        return byte_foto;
    }

    public void setByte_foto(byte[] byte_foto) {
        this.byte_foto = byte_foto;
    }

    public int getInt_id_persona() {
        return int_id_persona;
    }

    public void setInt_id_persona(int int_id_persona) {
        this.int_id_persona = int_id_persona;
    }

    @Override
    public String toString() {
        return str_apellido_paterno+ " "+str_apellido_materno+ " "+str_nombres;
    }
}
