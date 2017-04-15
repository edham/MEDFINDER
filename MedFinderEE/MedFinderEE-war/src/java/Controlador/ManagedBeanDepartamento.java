/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import bc.DepartamentoFacadeLocal;
import be.Departamento;
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
public class ManagedBeanDepartamento implements Serializable {
    @EJB
    private DepartamentoFacadeLocal departamentoFacade;
    
    private List<Departamento> listaobjDepartamento;
    private List<SelectItem> objDepartamentoItems;
    private Departamento objDepartamento;
    private Departamento objDepartamentoVacio;
    private String nuevoTitulo;
    public ManagedBeanDepartamento() {
       
        limpiar();
        objDepartamentoVacio=new Departamento();       
        objDepartamentoVacio.setNombre("SELECCIONE");
        objDepartamentoItems = new LinkedList<SelectItem>();
        listaobjDepartamento = new LinkedList<Departamento>();
    }
    public void limpiar()
    {
        nuevoTitulo="SELECCIONE";
        objDepartamento=new Departamento();
        objDepartamento.setEstado(1);
    }

    public Departamento getObjDepartamentoVacio() {
        return objDepartamentoVacio;
    }

    public void setObjDepartamentoVacio(Departamento objDepartamentoVacio) {
        this.objDepartamentoVacio = objDepartamentoVacio;
    }

    public String getNuevoTitulo() {
        return nuevoTitulo;
    }

    public void setNuevoTitulo(String nuevoTitulo) {
        this.nuevoTitulo = nuevoTitulo;
    }

    public List<Departamento> getListaobjDepartamento() {
        listaobjDepartamento = new LinkedList<Departamento>();
        try
        {
            listaobjDepartamento=departamentoFacade.findAll();       
        }
        catch (Exception e) {
        }
        return listaobjDepartamento;
    }

    public void setListaobjDepartamento(List<Departamento> listaobjDepartamento) {
        this.listaobjDepartamento = listaobjDepartamento;
    }

    public List<SelectItem> getObjDepartamentoItems() {
        objDepartamentoItems = new LinkedList<SelectItem>();
        try
        {     
            for(Departamento p:departamentoFacade.lista_activos()){
                objDepartamentoItems.add(new SelectItem(p, p.getNombre()));
            }
        }
        catch (Exception e) {
        }
         
        return objDepartamentoItems;
    }

    public void setObjDepartamentoItems(List<SelectItem> objDepartamentoItems) {
        this.objDepartamentoItems = objDepartamentoItems;
    }

    public Departamento getObjDepartamento() {
        return objDepartamento;
    }

    public void setObjDepartamento(Departamento objDepartamento) {
      
        this.objDepartamento = objDepartamento;
    }

  
    
     public void crear()
    {
        try
        {
           objDepartamento.setFechaModificacion(new Date());
           departamentoFacade.edit(objDepartamento);
         
            limpiar();
        }
         catch (Exception e) {
        }
        limpiar();
    }
     public void actualizar(Departamento obejto)
    {
        this.nuevoTitulo="EDITAR ID : "+obejto.getPKId();        
        objDepartamento=obejto;
    
    }
     
      public Departamento traerObjeto(String id) {
        Departamento objBuscar = departamentoFacade.find(Integer.parseInt(id));
        return objBuscar;
    }
}
