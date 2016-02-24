/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import bc.ProvinciaFacadeLocal;
import be.Departamento;
import be.Provincia;
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
public class ManagedBeanProvincia implements Serializable {
    @EJB
    private ProvinciaFacadeLocal provinciaFacade;
    
    private List<Provincia> listaobjProvincia;
    private List<SelectItem> objProvinciaItems;
    private Provincia objProvincia;
    private Departamento objDepartamento;
    private Provincia objProvinciaVacio;
    private String nuevoTitulo;
    public ManagedBeanProvincia() {
       
        limpiar();
        objProvinciaVacio=new Provincia();       
        objDepartamento= new Departamento();
        objProvinciaVacio.setNombre("SELECCIONE UNA OPCIÓN");
        objProvinciaItems = new LinkedList<SelectItem>();
        listaobjProvincia = new LinkedList<Provincia>();
    }
    public void limpiar()
    {
        nuevoTitulo="SELECCIONE";
        objProvincia=new Provincia();
        objProvincia.setEstado(1);
    }

    public Departamento getObjDepartamento() {
        return objDepartamento;
    }

    public void setObjDepartamento(Departamento objDepartamento) {
       
        this.objDepartamento = objDepartamento;
    }

    public Provincia getObjProvinciaVacio() {
        return objProvinciaVacio;
    }

    public void setObjProvinciaVacio(Provincia objProvinciaVacio) {
        this.objProvinciaVacio = objProvinciaVacio;
    }

    public String getNuevoTitulo() {
        return nuevoTitulo;
    }

    public void setNuevoTitulo(String nuevoTitulo) {
        this.nuevoTitulo = nuevoTitulo;
    }

    public List<Provincia> getListaobjProvincia() {
         listaobjProvincia = new LinkedList<Provincia>();
        try
        {
            if(objDepartamento!=null)
            {
                for(Provincia objProvincia:objDepartamento.getProvinciaList())
                {
                        listaobjProvincia.add(objProvincia);
                }
                
            }
            
        }
        catch (Exception e) {
        }
        return listaobjProvincia;
    }

    public void setListaobjProvincia(List<Provincia> listaobjProvincia) {
        this.listaobjProvincia = listaobjProvincia;
    }

    public List<SelectItem> getObjProvinciaItems() {
        objProvinciaItems = new LinkedList<SelectItem>();
        try
        {
            listaobjProvincia=provinciaFacade.lista_activos();         
            for(Provincia p:listaobjProvincia){
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

    public Provincia getObjProvincia() {
        return objProvincia;
    }

    public void setObjProvincia(Provincia objProvincia) {
      
        this.objProvincia = objProvincia;
    }

  
    
     public void crear()
    {
        try
        {
           objProvincia.setFechaModificacion(new Date());
           provinciaFacade.edit(objProvincia);
         
            limpiar();
        }
         catch (Exception e) {
        }
        limpiar();
    }
     public void actualizar(Provincia obejto)
    {
        this.nuevoTitulo="EDITAR ID : "+obejto.getPKId();        
        objProvincia=obejto;
    
    }
     
      public Provincia traerObjeto(String id) {
        Provincia objBuscar = provinciaFacade.find(Integer.parseInt(id));
        return objBuscar;
    }
}
