package com.med.finder.cliente.entidades;


public class clsProvincia {
    private int id;
    private String nombre;
    private clsDepartamento objDepartamento;

    public clsProvincia(int id, String nombre, clsDepartamento objDepartamento) {
        this.id = id;
        this.nombre = nombre;
        this.objDepartamento = objDepartamento;
    }

    public clsProvincia(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public clsProvincia(int id) {
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

    public clsDepartamento getObjDepartamento() {
        return objDepartamento;
    }

    public void setObjDepartamento(clsDepartamento objDepartamento) {
        this.objDepartamento = objDepartamento;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
