/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import bc.EspecialidadFacadeLocal;
import be.Especialidad;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author EdHam
 */
@ManagedBean
@SessionScoped
public class ManagedBeanEspecialidad implements Serializable {
    @EJB
    private EspecialidadFacadeLocal instalacionFacade;
    
    private Especialidad objSeleccionado;
    private List<Especialidad> listaobjSeleccionado;

    private List<SelectItem> objetoItems;
    private Especialidad objEspecialidad;
    
    public ManagedBeanEspecialidad() {
        objSeleccionado=new Especialidad();
        objEspecialidad=new Especialidad();
        objetoItems = new LinkedList<SelectItem>();
        listaobjSeleccionado = new LinkedList<Especialidad>();
    }

    public Especialidad getObjSeleccionado() {
        return objSeleccionado;
    }

    public void setObjSeleccionado(Especialidad objSeleccionado) {
        this.objSeleccionado = objSeleccionado;
    }

    public List<SelectItem> getObjetoItems() {
          objetoItems = new LinkedList<SelectItem>();
          listaobjSeleccionado= new LinkedList<Especialidad>();
          listaobjSeleccionado=instalacionFacade.Especialidad_lista();
         
         for(Especialidad p:listaobjSeleccionado){
          objetoItems.add(new SelectItem(p, p.getEspecialidadNombre()));
              }
        return objetoItems;
    }

    public void setObjetoItems(List<SelectItem> objetoItems) {
        this.objetoItems = objetoItems;
    }
    
    
     public Especialidad traerObjeto(String id) {
        Especialidad objetoBuscar = new Especialidad();
        objetoBuscar = instalacionFacade.find(Integer.parseInt(id));
        return objetoBuscar;
    }

    public List<Especialidad> getListaobjSeleccionado() {
        return listaobjSeleccionado;
    }

    public void setListaobjSeleccionado(List<Especialidad> listaobjSeleccionado) {
        this.listaobjSeleccionado = listaobjSeleccionado;
    }

    public Especialidad getObjEspecialidad() {
        return objEspecialidad;
    }

    public void setObjEspecialidad(Especialidad objEspecialidad) {
        this.objEspecialidad = objEspecialidad;
    }
    
    public void Update()
    {
        System.out.println("paso");
        instalacionFacade.edit(objSeleccionado);
        objSeleccionado = new Especialidad();
    }
     
}
