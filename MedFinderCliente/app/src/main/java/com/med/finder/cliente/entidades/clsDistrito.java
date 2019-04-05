package com.med.finder.cliente.entidades;


public class clsDistrito {
    private int id;
    private String nombre;
    private clsProvincia objProvincia;

    public clsDistrito(int id, String nombre, clsProvincia objProvincia) {
        this.id = id;
        this.nombre = nombre;
        this.objProvincia = objProvincia;
    }

    public clsDistrito(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public clsDistrito(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public clsProvincia getObjProvincia() {
        return objProvincia;
    }

    public void setObjProvincia(clsProvincia objProvincia) {
        this.objProvincia = objProvincia;
    }

    @Override
    public String toString() {
        return nombre ;
    }
}