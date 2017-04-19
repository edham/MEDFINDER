/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import fachada.DistritoFacadeLocal;
import fachada.ProvinciaFacadeLocal;
import modelo.Departamento;
import modelo.Distrito;
import modelo.Provincia;
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
    @EJB
    private ProvinciaFacadeLocal provinciaFacade;
    
    
    
    private List<Distrito> listaobjDistrito;
    private List<SelectItem> objDistritoItems;
     private List<SelectItem> objProvinciaItems;
    private Distrito objDistrito;
    private Departamento objDepartamento;
    private Provincia objProvincia;
    private Distrito objDistritoVacio;
    private String nuevoTitulo;
    public ManagedBeanDistrito() {
       
        limpiar();
        objDistritoVacio=new Distrito();       
        objDistritoVacio.setNombre("SELECCIONE");
        objDistritoItems = new LinkedList<SelectItem>();
        listaobjDistrito = new LinkedList<Distrito>();
        objProvinciaItems = new LinkedList<SelectItem>();
    }
    public void limpiar()
    {
        nuevoTitulo="SELECCIONE";
        objDistrito=new Distrito();
        objDistrito.setEstado(1);
    }

    public Departamento getObjDepartamento() {
        listaobjDistrito = new LinkedList<Distrito>();
        return objDepartamento;
    }

    public void setObjDepartamento(Departamento objDepartamento) {
        
        this.objDepartamento = objDepartamento;
    }

    public Provincia getObjProvincia() {
        return objProvincia;
    }

    public void setObjProvincia(Provincia objProvincia) {
         
        this.objProvincia = objProvincia;
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

    public List<SelectItem> getObjProvinciaItems() {
        
        objProvinciaItems = new LinkedList<SelectItem>();
        try
        {         
            for(Provincia p:provinciaFacade.lista_Departamento(objDepartamento, false)){
                objProvinciaItems.add(new SelectItem(p, p.getNombre()));
            }
        }
        catch (Exception e) {
        }
        return objProvinciaItems;
    }

    public void setObjProvinciaItems(List<SelectItem> objProvinciaItems) {
        this.objProvinciaItems = objProvinciaItems;
    }

    public List<Distrito> getListaobjDistrito() {
        listaobjDistrito = new LinkedList<Distrito>();
        try
        {
            if(objProvincia!=null)
            {
                for(Distrito objDistrito:distritoFacade.lista_Provincia(objProvincia, true))
                {
                        listaobjDistrito.add(objDistrito);
                }
                
            }
            
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
              if(objProvincia!=null)
            {
                for(Distrito p:distritoFacade.lista_Provincia(objProvincia, true))
                {
                        objDistritoItems.add(new SelectItem(p, p.getNombre()));
                }
                
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
