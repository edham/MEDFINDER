/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import bc.CasosSaludFacadeLocal;
import bc.RespuestaCasoSaludFacadeLocal;
import be.CasosSalud;
import be.RespuestaCasoSalud;
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
public class ManagedBeanCasosSalud implements Serializable {
    @EJB
    private RespuestaCasoSaludFacadeLocal respuestaCasoSaludFacade;
    @EJB
    private CasosSaludFacadeLocal casosSaludFacade;
    
    private List<CasosSalud> listaobjCasosSalud;
    private List<SelectItem> objCasosSaludItems;
    private CasosSalud objCasosSalud;
    private CasosSalud objCasosSaludVacio;
    private RespuestaCasoSalud objRespuestaCasoSalud;
    private String nuevoTitulo;
    private String editarRespuesta;
    public ManagedBeanCasosSalud() {
       
        limpiar();
        objCasosSaludVacio=new CasosSalud();       
        objCasosSaludVacio.setTema("SELECCIONE UNA OPCIÓN");
        objCasosSaludItems = new LinkedList<SelectItem>();
        listaobjCasosSalud = new LinkedList<CasosSalud>();
    }
    public void limpiar()
    {
        nuevoTitulo="NUEVO";
        objRespuestaCasoSalud = new RespuestaCasoSalud();
        objCasosSalud=new CasosSalud();
        objCasosSalud.setEstado(1);
    }

    public CasosSalud getObjCasosSaludVacio() {
        return objCasosSaludVacio;
    }

    public void setObjCasosSaludVacio(CasosSalud objCasosSaludVacio) {
        this.objCasosSaludVacio = objCasosSaludVacio;
    }

    public String getNuevoTitulo() {
        return nuevoTitulo;
    }

    public void setNuevoTitulo(String nuevoTitulo) {
        this.nuevoTitulo = nuevoTitulo;
    }

    public List<CasosSalud> getListaobjCasosSalud() {
        listaobjCasosSalud = new LinkedList<CasosSalud>();
        try
        {
            listaobjCasosSalud=casosSaludFacade.findAll();       
        }
        catch (Exception e) {
        }
        return listaobjCasosSalud;
    }

    public void setListaobjCasosSalud(List<CasosSalud> listaobjCasosSalud) {
        this.listaobjCasosSalud = listaobjCasosSalud;
    }

    public List<SelectItem> getObjCasosSaludItems() {
        objCasosSaludItems = new LinkedList<SelectItem>();
        try
        {     
            for(CasosSalud p:casosSaludFacade.lista_activos()){
                objCasosSaludItems.add(new SelectItem(p, p.getTema()));
            }
        }
        catch (Exception e) {
        }
         
        return objCasosSaludItems;
    }

    public void setObjCasosSaludItems(List<SelectItem> objCasosSaludItems) {
        this.objCasosSaludItems = objCasosSaludItems;
    }

    public CasosSalud getObjCasosSalud() {
        return objCasosSalud;
    }

    public void setObjCasosSalud(CasosSalud objCasosSalud) {
      
        this.objCasosSalud = objCasosSalud;
    }

  
    
     public void crear()
    {
        try
        {
           objCasosSalud.setFechaModificacion(new Date());
           casosSaludFacade.edit(objCasosSalud);
         
            limpiar();
        }
         catch (Exception e) {
        }
        limpiar();
    }
     public void actualizar(CasosSalud obejto)
    {
        this.nuevoTitulo="EDITAR ID : "+obejto.getPKId();        
        objCasosSalud=obejto;
    
    }
     
      public CasosSalud traerObjeto(String id) {
        CasosSalud objBuscar = casosSaludFacade.find(Integer.parseInt(id));
        return objBuscar;
    }
      
    public int contar(List<RespuestaCasoSalud> lista)
    {
        int contador=0;
        try{
            if(lista!=null)
            {
                for(RespuestaCasoSalud seguro:lista)
                {
                    if(seguro.getEstado()==1)
                        contador++;
                }
            }
        }
        catch (Exception e) {
        }
        return contador;
    }
    public void selecionarRespuesta(RespuestaCasoSalud objeto)
      {
        
        try
        {
            objRespuestaCasoSalud=objeto;
            if(objRespuestaCasoSalud!=null)
            {
                if(objRespuestaCasoSalud.getEstado()==0)
                    editarRespuesta="DESEA RESTAURAR";
                else
                      editarRespuesta="DESEA ELIMINAR";
            }
        }
         catch (Exception e) {
        }   
      } 

    public void editarRespuesta()
    {
         try
        {
            if(objRespuestaCasoSalud!=null)
            {
                objRespuestaCasoSalud.setFechaModificacion(new Date());
                objRespuestaCasoSalud.setEstado((objRespuestaCasoSalud.getEstado()==0)?1:0);
               respuestaCasoSaludFacade.edit(objRespuestaCasoSalud);
            }
        }
         catch (Exception e) {
        }   
    }
    public String getEditarRespuesta() {
        return editarRespuesta;
    }

    public void setEditarRespuesta(String editarRespuesta) {
        this.editarRespuesta = editarRespuesta;
    }
    
}
