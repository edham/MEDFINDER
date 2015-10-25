/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import bc.DistritoFacadeLocal;
import be.Distrito;
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
public class ManagedBeanDistrito implements Serializable {
    @EJB
    private DistritoFacadeLocal distritoFacade;
    
    private List<Distrito> listaobjDistrito;
    private List<SelectItem> objDistritoItems;
    private Distrito objDistrito;
    private Distrito objDistritoSelecionado;
    private Distrito objDistritoVacio;
    private String nuevoTitulo;
    public ManagedBeanDistrito() {
       
        limpiar();
        objDistritoVacio=new Distrito();       
        objDistritoVacio.setNombre("SELECCIONE UNA OPCIÓN");
        objDistritoItems = new LinkedList<SelectItem>();
        listaobjDistrito = new LinkedList<Distrito>();
    }
    public void limpiar()
    {
        nuevoTitulo="SELECCIONE";
        objDistrito=new Distrito();
        objDistrito.setEstado((short)1);
        objDistritoSelecionado=new Distrito();
    }
    public Distrito getObjDistritoSelecionado() {
        return objDistritoSelecionado;
    }

    public void setObjDistritoSelecionado(Distrito objDistritoSelecionado) {
       
        this.objDistritoSelecionado = objDistritoSelecionado;
    }

    public Distrito getObjDistritoVacio() {
        return objDistritoVacio;
    }

    public void setObjDistritoVacio(Distrito objDistritoVacio) {
        this.objDistritoVacio = objDistritoVacio;
    }

    public String getNuevoTitulo() {
        return nuevoTitulo;
    }

    public void setNuevoTitulo(String nuevoTitulo) {
        this.nuevoTitulo = nuevoTitulo;
    }

    public List<Distrito> getListaobjDistrito() {
        listaobjDistrito = new LinkedList<Distrito>();
        try
        {
            listaobjDistrito=distritoFacade.findAll();       
        }
        catch (Exception e) {
        }
        return listaobjDistrito;
    }

    public void setListaobjDistrito(List<Distrito> listaobjDistrito) {
        this.listaobjDistrito = listaobjDistrito;
    }

    public List<SelectItem> getObjDistritoItems() {
        objDistritoItems = new LinkedList<SelectItem>();
        try
        {
            listaobjDistrito=distritoFacade.lista_activos();         
            for(Distrito p:listaobjDistrito){
                objDistritoItems.add(new SelectItem(p, p.getNombre()));
            }
        }
        catch (Exception e) {
        }
         
        return objDistritoItems;
    }

    public void setObjDistritoItems(List<SelectItem> objDistritoItems) {
        this.objDistritoItems = objDistritoItems;
    }

    public Distrito getObjDistrito() {
        return objDistrito;
    }

    public void setObjDistrito(Distrito objDistrito) {
      
        this.objDistrito = objDistrito;
    }

  
    
     public void crear()
    {
        try
        {
           objDistrito.setFechaModificacion(new Date());
           distritoFacade.edit(objDistrito);
         
            limpiar();
        }
         catch (Exception e) {
        }
        limpiar();
    }
     public void actualizar(Distrito obejto)
    {
        this.nuevoTitulo="EDITAR ID : "+obejto.getPKId();        
        objDistrito=obejto;
    
    }
     
      public Distrito traerObjeto(String id) {
        Distrito objBuscar = distritoFacade.find(Integer.parseInt(id));
        return objBuscar;
    }
}
