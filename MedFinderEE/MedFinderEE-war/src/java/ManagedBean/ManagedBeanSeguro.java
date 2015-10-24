package ManagedBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Utilidades.Utilidades;
import bc.SeguroFacadeLocal;
import be.Seguro;
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
public class ManagedBeanSeguro implements Serializable {
    @EJB
    private SeguroFacadeLocal seguroFacade;
    private List<Seguro> listaobjSeguro;
    private List<SelectItem> objSeguroItems;
    private Seguro objSeguro;
    private Seguro objSeguroSelecionado;
    private Seguro objSeguroVacio;
    private String nuevoTitulo;
    private boolean nuevo;
    private StreamedContent imagen;
    public ManagedBeanSeguro() {
       
        limpiar();
        objSeguroVacio=new Seguro();       
        objSeguroVacio.setSeguroNombre("SELECCIONE UNA OPCIÓN");
        objSeguroItems = new LinkedList<SelectItem>();
        listaobjSeguro = new LinkedList<Seguro>();
    }
    public void limpiar()
    {
        nuevo=true;
        nuevoTitulo="AGREGAR NUEVO";
        objSeguro=new Seguro();
        objSeguro.setSeguroEstado((short)1);
        objSeguroSelecionado=new Seguro();
        imagen=null;
    }
    public Seguro getObjSeguroSelecionado() {
        return objSeguroSelecionado;
    }

    public void setObjSeguroSelecionado(Seguro objSeguroSelecionado) {
       
        this.objSeguroSelecionado = objSeguroSelecionado;
    }

    public Seguro getObjSeguroVacio() {
        return objSeguroVacio;
    }

    public void setObjSeguroVacio(Seguro objSeguroVacio) {
        this.objSeguroVacio = objSeguroVacio;
    }

    public String getNuevoTitulo() {
        return nuevoTitulo;
    }

    public void setNuevoTitulo(String nuevoTitulo) {
        this.nuevoTitulo = nuevoTitulo;
    }

    public List<Seguro> getListaobjSeguro() {
        listaobjSeguro = new LinkedList<Seguro>();
        try
        {
            listaobjSeguro=seguroFacade.findAll();       
        }
        catch (Exception e) {
        }
        return listaobjSeguro;
    }

    public void setListaobjSeguro(List<Seguro> listaobjSeguro) {
        this.listaobjSeguro = listaobjSeguro;
    }

    public List<SelectItem> getObjSeguroItems() {
        objSeguroItems = new LinkedList<SelectItem>();
        try
        {
            listaobjSeguro=seguroFacade.Seguro_lista();         
            for(Seguro p:listaobjSeguro){
                objSeguroItems.add(new SelectItem(p, p.getSeguroNombre()));
            }
        }
        catch (Exception e) {
        }
         
        return objSeguroItems;
    }

    public void setObjSeguroItems(List<SelectItem> objSeguroItems) {
        this.objSeguroItems = objSeguroItems;
    }

    public Seguro getObjSeguro() {
        return objSeguro;
    }

    public void setObjSeguro(Seguro objSeguro) {
      
        this.objSeguro = objSeguro;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

  
    
     public void crear()
    {
        System.out.println("inicial: "+objSeguro.getSeguroId());
        if(nuevo)
        {
         System.out.println(""+objSeguro.getSeguroId());
            objSeguro.setSeguroFecha(new Date());
            seguroFacade.create(objSeguro);
             System.out.println(""+objSeguro.getSeguroId());
        }
        else
        {
            System.out.println("edito: "+objSeguro.getSeguroId());
            seguroFacade.edit(objSeguro);
        }
        limpiar();
    }
     public void actualizar(Seguro obejto)
    {
        limpiar();
        imagen=null;
        this.nuevoTitulo="EDITAR ID : "+obejto.getSeguroId();        
        objSeguro=obejto;
        nuevo=false;
        InputStream is = new ByteArrayInputStream(objSeguro.getSeguroLogo());
        imagen = new DefaultStreamedContent(is);
    
    }
     public void subirImagen(FileUploadEvent event) {
         
       byte[] file = event.getFile().getContents();
       objSeguro.setSeguroLogo(Utilidades.Redimensionar(file, 80,80));
        InputStream is = new ByteArrayInputStream(objSeguro.getSeguroLogo());
        imagen = new DefaultStreamedContent(is);
           
    }

    public StreamedContent getImagen() {
      
        return imagen;
    }

    public void setImagen(StreamedContent imagen) {
        this.imagen = imagen;
    }
     
   
      public Seguro traerObjeto(String id) {
        Seguro objBuscar = seguroFacade.find(Integer.parseInt(id));
        return objBuscar;
    }
      
      
}
