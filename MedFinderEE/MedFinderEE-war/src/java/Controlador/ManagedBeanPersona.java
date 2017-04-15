package Controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Utilidades.Utilidades;
import bc.PersonaFacadeLocal;
import be.Persona;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author EdHam
 */
@ManagedBean
@SessionScoped
public class ManagedBeanPersona implements Serializable {
    @EJB
    private PersonaFacadeLocal personaFacade;
    private List<Persona> listaobjPersona;
    private List<SelectItem> objPersonaItems;
    private Persona objPersona;
    private Persona objPersonaVacio;
    private String nuevoTitulo;
    private boolean nuevo;
    private StreamedContent imagen;
    public ManagedBeanPersona() {
       
        limpiar();
        objPersonaVacio=new Persona();       
        objPersonaVacio.setNombre("SELECCIONE UNA OPCIÓN");
        objPersonaItems = new LinkedList<SelectItem>();
        listaobjPersona = new LinkedList<Persona>();
    }
    public void limpiar()
    {
        nuevo=true;
        nuevoTitulo="AGREGAR NUEVO";
        objPersona=new Persona();
        objPersona.setEstado(1);
        imagen=null;
    }

    public Persona getObjPersonaVacio() {
        return objPersonaVacio;
    }

    public void setObjPersonaVacio(Persona objPersonaVacio) {
        this.objPersonaVacio = objPersonaVacio;
    }

    public String getNuevoTitulo() {
        return nuevoTitulo;
    }

    public void setNuevoTitulo(String nuevoTitulo) {
        this.nuevoTitulo = nuevoTitulo;
    }

    public List<Persona> getListaobjPersona() {
        listaobjPersona = new LinkedList<Persona>();
        try
        {
            listaobjPersona=personaFacade.findAll();       
        }
        catch (Exception e) {
        }
        return listaobjPersona;
    }

    public void setListaobjPersona(List<Persona> listaobjPersona) {
        this.listaobjPersona = listaobjPersona;
    }

    public List<SelectItem> getObjPersonaItems() {
        objPersonaItems = new LinkedList<SelectItem>();
        try
        {    
            for(Persona p:personaFacade.lista_activos()){
                objPersonaItems.add(new SelectItem(p, p.getNombre()));
            }
        }
        catch (Exception e) {
        }
         
        return objPersonaItems;
    }

    public void setObjPersonaItems(List<SelectItem> objPersonaItems) {
        this.objPersonaItems = objPersonaItems;
    }

    public Persona getObjPersona() {
        return objPersona;
    }

    public void setObjPersona(Persona objPersona) {
      
        this.objPersona = objPersona;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

  
    
     public void crear()
    {
        try
        {
            if(nuevo)
            {
                objPersona.setFechaRegistro(new Date());
                objPersona.setFechaModificacion(new Date());
                personaFacade.create(objPersona);
                 System.out.println(""+objPersona.getPKId());
            }
            else
            {
                objPersona.setFechaModificacion(new Date());
                personaFacade.edit(objPersona);
            }
            limpiar();
        }
         catch (Exception e) {
        }
       
    }
     public void actualizar(Persona obejto)
    {
        limpiar();
        imagen=null;
        this.nuevoTitulo="EDITAR ID : "+obejto.getPKId();        
        objPersona=obejto;
        nuevo=false;
        InputStream is = new ByteArrayInputStream(objPersona.getFoto());
        imagen = new DefaultStreamedContent(is);
    
    }
     public void subirImagen(FileUploadEvent event) {
         
       byte[] file = event.getFile().getContents();
       objPersona.setFoto(Utilidades.Redimensionar(file, 80,80));
        InputStream is = new ByteArrayInputStream(objPersona.getFoto());
        imagen = new DefaultStreamedContent(is);
           
    }

    public StreamedContent getImagen() {
      
        return imagen;
    }

    public void setImagen(StreamedContent imagen) {
        this.imagen = imagen;
    }
     
   
      public Persona traerObjeto(String id) {
        Persona objBuscar = personaFacade.find(Integer.parseInt(id));
        return objBuscar;
    }
      
     public List<Persona> getListaobjPersonaFilro(String dato) {
        listaobjPersona = new LinkedList<Persona>();
        try
        {
            listaobjPersona=personaFacade.lista_Distinct_DoctorFiltro(dato);    
        }
        catch (Exception e) {
        }         
        return listaobjPersona;
    }
}
