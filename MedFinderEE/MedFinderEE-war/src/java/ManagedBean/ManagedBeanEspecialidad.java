/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import bc.EspecialidadFacadeLocal;
import be.Especialidad;
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
    private Especialidad objEspecialidadSelecionado;
    private Especialidad objEspecialidadVacio;
    private String nuevoTitulo;
    private boolean nuevo;
    public ManagedBeanEspecialidad() {
       
        limpiar();
        objEspecialidadVacio=new Especialidad();       
        objEspecialidadVacio.setEspecialidadNombre("SELECCIONE UNA OPCIÓN");
        objEspecialidadItems = new LinkedList<SelectItem>();
        listaobjEspecialidad = new LinkedList<Especialidad>();
    }
    public void limpiar()
    {
        nuevo=true;
        nuevoTitulo="AGREGAR NUEVO";
        objEspecialidad=new Especialidad();
        objEspecialidad.setEspecialidadEstado((short)1);
        objEspecialidadSelecionado=new Especialidad();
    }
    public Especialidad getObjEspecialidadSelecionado() {
        return objEspecialidadSelecionado;
    }

    public void setObjEspecialidadSelecionado(Especialidad objEspecialidadSelecionado) {
       
        this.objEspecialidadSelecionado = objEspecialidadSelecionado;
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
            listaobjEspecialidad=especialidadFacade.Especialidad_lista();         
            for(Especialidad p:listaobjEspecialidad){
                objEspecialidadItems.add(new SelectItem(p, p.getEspecialidadNombre()));
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
        System.out.println("inicial: "+objEspecialidad.getEspecialidadId());
        if(nuevo)
        {
         System.out.println(""+objEspecialidad.getEspecialidadId());
            objEspecialidad.setEspecialidadFecha(new Date());
            especialidadFacade.create(objEspecialidad);
             System.out.println(""+objEspecialidad.getEspecialidadId());
        }
        else
        {
            System.out.println("edito: "+objEspecialidad.getEspecialidadId());
            especialidadFacade.edit(objEspecialidad);
        }
        limpiar();
    }
     public void actualizar(Especialidad obejto)
    {
        this.nuevoTitulo="EDITAR ID : "+obejto.getEspecialidadId();
        objEspecialidad=obejto;
        nuevo=false;
    
    }
     
      public Especialidad traerObjeto(String id) {
        Especialidad objBuscar = especialidadFacade.find(Integer.parseInt(id));
        return objBuscar;
    }
}
