/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Edham
 */
public class wsEspecialidad {
    private String especialidad;
    private String rne;
    private String certificacion;

    public wsEspecialidad(String especialidad, String rne, String certificacion) {
        this.especialidad = especialidad;
        this.rne = rne;
        this.certificacion = certificacion;
    }

    public wsEspecialidad() {
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getRne() {
        return rne;
    }

    public void setRne(String rne) {
        this.rne = rne;
    }

    public String getCertificacion() {
        return certificacion;
    }

    public void setCertificacion(String certificacion) {
        this.certificacion = certificacion;
    }
    
}
