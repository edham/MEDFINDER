package Controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Utilidades.Utilidades;
import fachada.PersonaFacadeLocal;
import fachada.UsuarioFacadeLocal;
import modelo.Persona;
import modelo.Usuario;
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
public class ManagedBeanUsuario implements Serializable {
    @EJB
    private PersonaFacadeLocal personaFacade;
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private List<Usuario> listaobjUsuario;
    private List<SelectItem> objUsuarioItems;
    private Usuario objUsuario;
    private Usuario objUsuarioVacio;
    private Persona objPersona;
    private String nuevoTitulo;
    private boolean nuevo;
    private StreamedContent imagen;
    
    public ManagedBeanUsuario() {
       
        limpiar(true);
        objUsuarioVacio=new Usuario();       
        objUsuarioVacio.setUsuario("SELECCIONE UNA OPCIÓN");
        objUsuarioItems = new LinkedList<SelectItem>();
        listaobjUsuario = new LinkedList<Usuario>();
    }
    public void limpiar(boolean nuevoPer)
    {
        nuevo=true;
        nuevoTitulo="AGREGAR NUEVO";
        objUsuario=new Usuario();
        objUsuario.setEstado(1);
        imagen=null;
         if(nuevoPer){
            objPersona= new Persona();
            objPersona.setEstado(1);
            objPersona.setFechaNacimiento(new Date());
        }
    }

    public Usuario getObjUsuarioVacio() {
        return objUsuarioVacio;
    }

    public void setObjUsuarioVacio(Usuario objUsuarioVacio) {
        this.objUsuarioVacio = objUsuarioVacio;
    }

    public String getNuevoTitulo() {
        return nuevoTitulo;
    }

    public void setNuevoTitulo(String nuevoTitulo) {
        this.nuevoTitulo = nuevoTitulo;
    }

    public List<Usuario> getListaobjUsuario() {
        listaobjUsuario = new LinkedList<Usuario>();
        try
        {
            listaobjUsuario=usuarioFacade.findAll();       
        }
        catch (Exception e) {
        }
        return listaobjUsuario;
    }

    public void setListaobjUsuario(List<Usuario> listaobjUsuario) {
        this.listaobjUsuario = listaobjUsuario;
    }

    public List<SelectItem> getObjUsuarioItems() {
        objUsuarioItems = new LinkedList<SelectItem>();
        try
        {    
            for(Usuario p:usuarioFacade.lista_activos()){
                objUsuarioItems.add(new SelectItem(p, p.getUsuario()));
            }
        }
        catch (Exception e) {
        }
         
        return objUsuarioItems;
    }

    public void setObjUsuarioItems(List<SelectItem> objUsuarioItems) {
        this.objUsuarioItems = objUsuarioItems;
    }

    public Usuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuario objUsuario) {
      
        this.objUsuario = objUsuario;
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
            objUsuario.setFechaModificacion(new Date());
            if(nuevo)
            {
                objUsuario.setFechaRegistro(new Date());                
                usuarioFacade.create(objUsuario);
                 System.out.println(""+objUsuario.getPKId());
            }
            else
            {
                personaFacade.edit(objPersona);
                if(objUsuario.getPKId()!=null)
                {   objUsuario.setUsuario(objPersona.getDni());
                    usuarioFacade.edit(objUsuario);
                }
                else
                {
                    objUsuario.setPersona(objPersona);
                    objUsuario.setEstado(1);
                    objUsuario.setFechaRegistro(new Date());
                    objUsuario.setFechaModificacion(new Date());
                    objUsuario.setFechaUltimoAcceso(new Date());
                    objUsuario.setPersona(objPersona);
                    usuarioFacade.create(objUsuario);
                }
            }
            limpiar(true);
        }
         catch (Exception e) {
        }
       
    }
     public void actualizar(Usuario obejto)
    {
        limpiar(true);
        imagen=null;
        this.nuevoTitulo="EDITAR ID : "+obejto.getPKId();        
        objUsuario=obejto;
        objPersona=obejto.getPersona();
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
     
   
      public Usuario traerObjeto(String id) {
        Usuario objBuscar = usuarioFacade.find(Integer.parseInt(id));
        return objBuscar;
    }
      
   
    public Persona getObjPersona() {
        return objPersona;
    }

    public void setObjPersona(Persona objPersona) {
        this.objPersona = objPersona;
    }
     
     
}
