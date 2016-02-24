package ManagedBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Utilidades.Utilidades;
import bc.ClinicaFacadeLocal;
import be.Clinica;
import be.Departamento;
import be.Provincia;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
public class ManagedBeanClinica implements Serializable {
    @EJB
    private ClinicaFacadeLocal clinicaFacade;
    private List<Clinica> listaobjClinica;
    private List<SelectItem> objClinicaItems;
    private Clinica objClinica;
    private Clinica objClinicaVacio;
    private String nuevoTitulo;
    private boolean nuevo;
    private StreamedContent imagen;
    public ManagedBeanClinica() {
       
        limpiar();
        objClinicaVacio=new Clinica();       
        objClinicaVacio.setNombre("SELECCIONE UNA OPCIÓN");
        objClinicaItems = new LinkedList<SelectItem>();
        listaobjClinica = new LinkedList<Clinica>();
    }
    public void limpiar()
    {

        nuevo=true;
        nuevoTitulo="AGREGAR NUEVO";
        objClinica=new Clinica();
        objClinica.setEstado(1);
        imagen=null;
    }



    public Clinica getObjClinicaVacio() {
        return objClinicaVacio;
    }

    public void setObjClinicaVacio(Clinica objClinicaVacio) {
        this.objClinicaVacio = objClinicaVacio;
    }

    public String getNuevoTitulo() {
        return nuevoTitulo;
    }

    public void setNuevoTitulo(String nuevoTitulo) {
        this.nuevoTitulo = nuevoTitulo;
    }

    public List<Clinica> getListaobjClinica() {
        listaobjClinica = new LinkedList<Clinica>();
        try
        {
            listaobjClinica=clinicaFacade.findAll();       
        }
        catch (Exception e) {
        }
        return listaobjClinica;
    }

    public void setListaobjClinica(List<Clinica> listaobjClinica) {
        this.listaobjClinica = listaobjClinica;
    }

    public List<SelectItem> getObjClinicaItems() {
        objClinicaItems = new LinkedList<SelectItem>();
        try
        {
            listaobjClinica=clinicaFacade.lista_activos();         
            for(Clinica p:listaobjClinica){
                objClinicaItems.add(new SelectItem(p, p.getNombre()));
            }
        }
        catch (Exception e) {
        }
         
        return objClinicaItems;
    }

    public void setObjClinicaItems(List<SelectItem> objClinicaItems) {
        this.objClinicaItems = objClinicaItems;
    }

    public Clinica getObjClinica() {
        return objClinica;
    }

    public void setObjClinica(Clinica objClinica) {
      
        this.objClinica = objClinica;
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
                objClinica.setFechaRegistro(new Date());
                objClinica.setFechaModificacion(new Date());
                clinicaFacade.create(objClinica);
                 System.out.println(""+objClinica.getPKId());
            }
            else
            {
                objClinica.setFechaModificacion(new Date());
                clinicaFacade.edit(objClinica);
            }
            limpiar();
        }
         catch (Exception e) {
        }
       
    }
     public void actualizar(Clinica obejto)
    {
        
        limpiar();
        imagen=null;
        this.nuevoTitulo="EDITAR ID : "+obejto.getPKId();        
        objClinica=obejto;
        nuevo=false;
        InputStream is = new ByteArrayInputStream(objClinica.getLogo());
        imagen = new DefaultStreamedContent(is);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ManagedBeanDistrito managedBeanDistrito = (ManagedBeanDistrito)facesContext.getApplication().createValueBinding("#{managedBeanDistrito}").getValue(facesContext);
        managedBeanDistrito.setObjDepartamento(obejto.getDistrito().getProvincia().getDepartamento());
        managedBeanDistrito.setObjProvincia(obejto.getDistrito().getProvincia());
        managedBeanDistrito.setObjDistrito(obejto.getDistrito());
    
       
    }
     public void subirImagen(FileUploadEvent event) {
         
       byte[] file = event.getFile().getContents();
       objClinica.setLogo(Utilidades.Redimensionar(file, 80,80));
        InputStream is = new ByteArrayInputStream(objClinica.getLogo());
        imagen = new DefaultStreamedContent(is);
           
    }

    public StreamedContent getImagen() {
      
        return imagen;
    }

    public void setImagen(StreamedContent imagen) {
        this.imagen = imagen;
    }
     
   
      public Clinica traerObjeto(String id) {
        Clinica objBuscar = clinicaFacade.find(Integer.parseInt(id));
        return objBuscar;
    }
      
      
}
