package ManagedBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Utilidades.Utilidades;
import bc.ClinicaFacadeLocal;
import bc.DetalleClinicaEspecialidadFacadeLocal;
import bc.DetalleClinicaSeguroFacadeLocal;
import bc.EspecialidadFacadeLocal;
import bc.SeguroFacadeLocal;
import be.Clinica;
import be.DetalleClinicaEspecialidad;
import be.DetalleClinicaSeguro;
import be.Especialidad;
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
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
/**
 *
 * @author EdHam
 */
@ManagedBean
@SessionScoped
public class ManagedBeanClinica implements Serializable {
    @EJB
    private SeguroFacadeLocal seguroFacade;
    @EJB
    private EspecialidadFacadeLocal especialidadFacade;
    @EJB
    private DetalleClinicaSeguroFacadeLocal detalleClinicaSeguroFacade;
    @EJB
    private DetalleClinicaEspecialidadFacadeLocal detalleClinicaEspecialidadFacade;
    @EJB
    private ClinicaFacadeLocal clinicaFacade;
    
    
    private List<Clinica> listaobjClinica;
    private List<SelectItem> objClinicaItems;
    private List<SelectItem> objEspecialidadItems;
    private List<SelectItem> objSeguroItems;
    private List<DetalleClinicaEspecialidad> listaobjDetalleClinicaEspecialidad;
    private List<DetalleClinicaSeguro> listaobjDetalleClinicaSeguro;
    private Clinica objClinica;
    private Clinica objClinicaVacio;
    private DetalleClinicaEspecialidad objDetalleClinicaEspecialidad;
    private DetalleClinicaSeguro objDetalleClinicaSeguro;
    private String nuevoTitulo;
    private boolean nuevo;
    private StreamedContent imagen;
    private MapModel geoModel;
    private String centerGeoMap;
    private Marker marker;
    public ManagedBeanClinica() {
       
        limpiar();
        objClinicaVacio=new Clinica();       
        objClinicaVacio.setNombre("SELECCIONE UNA OPCIÓN");
        objClinicaItems = new LinkedList<SelectItem>();
        listaobjClinica = new LinkedList<Clinica>();
        objDetalleClinicaEspecialidad = new DetalleClinicaEspecialidad();
        objDetalleClinicaSeguro = new DetalleClinicaSeguro();
    }
    public void limpiar()
    {
        centerGeoMap = "0.0, 0.0";
        geoModel = new DefaultMapModel();
        nuevo=true;
        nuevoTitulo="AGREGAR NUEVO";
        objClinica=new Clinica();
        objClinica.setEstado(1);
        imagen=null;
    }

    public MapModel getGeoModel() {
        return geoModel;
    }

    public void setGeoModel(MapModel geoModel) {
        this.geoModel = geoModel;
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

  
      public void crearEspecialidad()
      {
          
      }
       public void crearSeguro(boolean nuevo)
      {
        try
        {
            objDetalleClinicaSeguro.setFechaModificacion(new Date());
            if(nuevo)
            {
                objDetalleClinicaSeguro.setFechaRegistro(new Date());
                objDetalleClinicaSeguro.setEstado(1);
                objDetalleClinicaSeguro.setClinica(objClinica);
                detalleClinicaSeguroFacade.create(objDetalleClinicaSeguro);
                objDetalleClinicaSeguro = new DetalleClinicaSeguro();
            }
            else
            {
                objDetalleClinicaSeguro.setEstado(0);
                clinicaFacade.edit(objClinica);
            }
            limpiar();
        }
         catch (Exception e) {
        }
          
      }
     public void crear()
    {
        try
        {
            objClinica.setLatitud( marker.getLatlng().getLat() );
            objClinica.setLongitud(marker.getLatlng().getLng());
            objClinica.setFechaModificacion(new Date());
            if(nuevo)
            {
                objClinica.setFechaRegistro(new Date());
                clinicaFacade.create(objClinica);
            }
            else
            {
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
        
        centerGeoMap = objClinica.getLatitud()+ "," + objClinica.getLongitud();
        LatLng coord = new LatLng(objClinica.getLatitud(), objClinica.getLongitud());
        marker=new Marker(coord);
        geoModel.getMarkers().clear();
        geoModel.addOverlay(marker);
        marker.setDraggable(true);
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
      
      
    public int contarSeguro(List<DetalleClinicaSeguro> lista)
    {
        int contador=0;
        if(lista!=null)
        {
            System.out.println("contarSeguro "+lista.size());
            for(DetalleClinicaSeguro seguro:lista)
            {
                if(seguro.getSeguro().getEstado()==1)
                    contador++;
            }
        }
        return contador;
    }
    public int contarEspecialidad(List<DetalleClinicaEspecialidad> lista)
    {
        int contador=0;
        if(lista!=null)
        {
            for(DetalleClinicaEspecialidad especialidad:lista)
            {
                if(especialidad.getEspecialidad().getEstado()==1)
                    contador++;
            }
        }
        return contador;
    }
      
      public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();
         
        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();
             
            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                marker=new Marker(result.getLatLng());
                geoModel.getMarkers().clear();
                geoModel.addOverlay(marker);
                    marker.setDraggable(true);
            }
        }
    }
     
    public void onMarkerDrag(MarkerDragEvent event) {
        marker = event.getMarker();
        
        Utilidades.Info("Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng());

    }
    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    public void setCenterGeoMap(String centerGeoMap) {
        this.centerGeoMap = centerGeoMap;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public List<DetalleClinicaEspecialidad> getListaobjDetalleClinicaEspecialidad() {
        listaobjDetalleClinicaEspecialidad = new LinkedList<DetalleClinicaEspecialidad>(); 
         try
        {   if(objClinica!=null)   
            {
                listaobjDetalleClinicaEspecialidad = detalleClinicaEspecialidadFacade.lista_Clinica(objClinica,false);
              
            }
        }
        catch (Exception e) {
        }
        return listaobjDetalleClinicaEspecialidad;
    }

    public void setListaobjDetalleClinicaEspecialidad(List<DetalleClinicaEspecialidad> listaobjDetalleClinicaEspecialidad) {
        this.listaobjDetalleClinicaEspecialidad = listaobjDetalleClinicaEspecialidad;
    }

    public List<DetalleClinicaSeguro> getListaobjDetalleClinicaSeguro() {
        listaobjDetalleClinicaSeguro = new LinkedList<DetalleClinicaSeguro>(); 
         try
        {   if(objClinica!=null)   
            {
                listaobjDetalleClinicaSeguro=detalleClinicaSeguroFacade.lista_Clinica(objClinica,false);
            }
        }
        catch (Exception e) {
        }
        
        return listaobjDetalleClinicaSeguro;
    }

    public void setListaobjDetalleClinicaSeguro(List<DetalleClinicaSeguro> listaobjDetalleClinicaSeguro) {
        this.listaobjDetalleClinicaSeguro = listaobjDetalleClinicaSeguro;
    }

    public List<SelectItem> getObjEspecialidadItems() {
         objEspecialidadItems = new LinkedList<SelectItem>();
        try
        {   if(objClinica!=null)   
            {
                for(Especialidad p:especialidadFacade.lista_Distinct_Clinica(objClinica)){
                        objEspecialidadItems.add(new SelectItem(p, p.getNombre()));
                }
            }
        }
        catch (Exception e) {
        }
         
        return objEspecialidadItems;
    }

    public void setObjEspecialidadItems(List<SelectItem> objEspecialidadItems) {
        this.objEspecialidadItems = objEspecialidadItems;
    }

    public DetalleClinicaEspecialidad getObjDetalleClinicaEspecialidad() {
        return objDetalleClinicaEspecialidad;
    }

    public void setObjDetalleClinicaEspecialidad(DetalleClinicaEspecialidad objDetalleClinicaEspecialidad) {
        this.objDetalleClinicaEspecialidad = objDetalleClinicaEspecialidad;
    }

    public DetalleClinicaSeguro getObjDetalleClinicaSeguro() {
        return objDetalleClinicaSeguro;
    }

    public void setObjDetalleClinicaSeguro(DetalleClinicaSeguro objDetalleClinicaSeguro) {
        this.objDetalleClinicaSeguro = objDetalleClinicaSeguro;
    }

    public List<SelectItem> getObjSeguroItems() {
         objSeguroItems = new LinkedList<SelectItem>();
        try
        {   if(objClinica!=null)   
            {
                for(Seguro p:seguroFacade.lista_Distinct_Clinica(objClinica)){
                        objSeguroItems.add(new SelectItem(p, p.getNombre()));
                }
            }
        }
        catch (Exception e) {
        }
         
        return objSeguroItems;
    }

    public void setObjSeguroItems(List<SelectItem> objSeguroItems) {
        this.objSeguroItems = objSeguroItems;
    }
 
}
