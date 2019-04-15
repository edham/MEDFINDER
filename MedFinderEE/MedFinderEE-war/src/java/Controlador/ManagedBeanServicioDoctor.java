package Controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Utilidades.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;
import servicio.WsDetalle;
import servicio.WsDoctor;
import servicio.WsEspecialidad;

/**
 *
 * @author EdHam
 */
@ManagedBean
@SessionScoped
public class ManagedBeanServicioDoctor implements Serializable {

    private List<WsDoctor> listDoctor;
    private WsDoctor objetoDoctor;
    private WsDetalle objetoDetalle;
    private List<WsEspecialidad> listEspecialidad;
    private String cmp;
    private String nombres;
    private String paterno;
    private String materno;

    public ManagedBeanServicioDoctor() {
        limpiar();

    }

    public void limpiarTodo() {
        cmp = "";
        nombres = "";
        paterno = "";
        materno = "";
        limpiar();
    }

    public void limpiar() {

        objetoDoctor = new WsDoctor();
        listDoctor = new ArrayList<WsDoctor>();
        objetoDetalle = new WsDetalle();
        listEspecialidad = new ArrayList<WsEspecialidad>();
    }

    public void buscarNombres() {
        limpiar();
        System.out.println(paterno + " - " + materno + " - " + nombres);
        if (paterno.length() > 0 || materno.length() > 0 || nombres.length() > 0) {
            try {
                listDoctor = Utilidades.consultaDoctor("", paterno, materno, nombres);
            } catch (Exception e) {

            }
        } else {
            Utilidades.Error("DEBE INGRESAR ALGUN DATO");
        }
    }

    public void buscarCodigo() {
        System.out.println("buscarCodigo " + cmp);
        if (cmp.length() == 6) {
            limpiar();
            try {
                listDoctor = Utilidades.consultaDoctor(cmp, "", "", "");
                System.out.println("listDoctor " + listDoctor.size());
                if (listDoctor != null && listDoctor.size() > 0) {
                    objetoDoctor = listDoctor.get(0);
                    listDoctor = new ArrayList<WsDoctor>();
                    try {
                        objetoDetalle = Utilidades.consultaDetalle(cmp);
                        listEspecialidad=objetoDetalle.getEspecialidad();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Utilidades.Error("DOCTOR NO ENCONTRADO");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Utilidades.Error("CMP DEBE TENER 6 DIGITOS");
        }
    }

    public List<WsDoctor> getListDoctor() {
        return listDoctor;
    }

    public void setListDoctor(List<WsDoctor> listDoctor) {
        this.listDoctor = listDoctor;
    }

    public WsDetalle getObjetoDetalle() {
        return objetoDetalle;
    }

    public void setObjetoDetalle(WsDetalle objetoDetalle) {
        this.objetoDetalle = objetoDetalle;
    }

    public List<WsEspecialidad> getListEspecialidad() {
        return listEspecialidad;
    }

    public void setListEspecialidad(List<WsEspecialidad> listEspecialidad) {
        this.listEspecialidad = listEspecialidad;
    }

    public WsDoctor getObjetoDoctor() {
        return objetoDoctor;
    }

    public void setObjetoDoctor(WsDoctor objetoDoctor) {
        this.objetoDoctor = objetoDoctor;
    }

    public String getCmp() {
        return cmp;
    }

    public void setCmp(String cmp) {
        this.cmp = cmp;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public void onRowSelect(SelectEvent event) {
        try {
            objetoDoctor = ((WsDoctor) event.getObject());

            try {
                objetoDetalle = Utilidades.consultaDetalle(objetoDoctor.getCmp());
                listEspecialidad=objetoDetalle.getEspecialidad();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
