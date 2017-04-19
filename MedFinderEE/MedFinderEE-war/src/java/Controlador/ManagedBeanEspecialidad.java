/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import fachada.EspecialidadFacadeLocal;
import modelo.Especialidad;
import java.io.Serializable;
import java.util.Date;
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
    private EspecialidadFacadeLocal especialidadFacade;
    
    private List<Especialidad> listaobjEspecialidad;
    private List<SelectItem> objEspecialidadItems;
    private Especialidad objEspecialidad;
    private Especialidad objEspecialidadVacio;
    private String nuevoTitulo;
    private boolean nuevo;
    public ManagedBeanEspecialidad() {
       
        limpiar();
        objEspecialidadVacio=new Especialidad();       
        objEspecialidadVacio.setNombre("SELECCIONE");
        objEspecialidadItems = new LinkedList<SelectItem>();
        listaobjEspecialidad = new LinkedList<Especialidad>();
    }
    public void limpiar()
    {
        nuevo=true;
        nuevoTitulo="AGREGAR NUEVO";
        objEspecialidad=new Especialidad();
        objEspecialidad.setEstado(1);
    }

    public Especialidad getObjEspecialidadVacio() {
        return objEspecialidadVacio;
    }

    public void setObjEspecialidadVacio(Especialidad objEspecialidadVacio) {
        this.objEspecialidadVacio = objEspecialidadVacio;
    }

    public String getNuevoTitulo() {
        return nuevoTitulo;
    }

    public void setNuevoTitulo(String nuevoTitulo) {
        this.nuevoTitulo = nuevoTitulo;
    }

    public List<Especialidad> getListaobjEspecialidad() {
        listaobjEspecialidad = new LinkedList<Especialidad>();
        try
        {
            listaobjEspecialidad=especialidadFacade.findAll();       
        }
        catch (Exception e) {
        }
        return listaobjEspecialidad;
    }

    public void setListaobjEspecialidad(List<Especialidad> listaobjEspecialidad) {
        this.listaobjEspecialidad = listaobjEspecialidad;
    }

    public List<SelectItem> getObjEspecialidadItems() {
        objEspecialidadItems = new LinkedList<SelectItem>();
        try
        {
            listaobjEspecialidad=especialidadFacade.lista_activos();         
            for(Especialidad p:listaobjEspecialidad){
                objEspecialidadItems.add(new SelectItem(p, p.getNombre()));
            }
        }
        catch (Exception e) {
        }
         
        return objEspecialidadItems;
    }

    public void setObjEspecialidadItems(List<SelectItem> objEspecialidadItems) {
        this.objEspecialidadItems = objEspecialidadItems;
    }

    public Especialidad getObjEspecialidad() {
        return objEspecialidad;
    }

    public void setObjEspecialidad(Especialidad objEspecialidad) {
      
        this.objEspecialidad = objEspecialidad;
    }

  
    
     public void crear()
    {
        try
        {
            if(nuevo)
            {
             System.out.println(""+objEspecialidad.getPKId());
                objEspecialidad.setFechaRegistro(new Date());
                objEspecialidad.setFechaModificacion(new Date());
                especialidadFacade.create(objEspecialidad);
            }
            else
            {
                objEspecialidad.setFechaModificacion(new Date());
                especialidadFacade.edit(objEspecialidad);
            }
            limpiar();
        }
         catch (Exception e) {
        }
        limpiar();
    }
     public void actualizar(Especialidad obejto)
    {
        objEspecialidad=obejto;
        nuevo=false;
    
    }
     
      public Especialidad traerObjeto(String id) {
        Especialidad objBuscar = especialidadFacade.find(Integer.parseInt(id));
        return objBuscar;
    }
}
