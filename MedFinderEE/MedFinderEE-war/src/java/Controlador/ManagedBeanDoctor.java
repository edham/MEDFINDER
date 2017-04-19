package Controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Utilidades.Utilidades;
import fachada.DoctorFacadeLocal;
import fachada.PersonaFacadeLocal;
import fachada.UsuarioFacadeLocal;
import modelo.Doctor;
import modelo.Persona;
import modelo.RespuestaCasoSalud;
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
public class ManagedBeanDoctor implements Serializable {
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    @EJB
    private PersonaFacadeLocal personaFacade;    
    @EJB
    private DoctorFacadeLocal doctorFacade;
    private List<Doctor> listaobjDoctor;   
    private List<SelectItem> objDoctorItems;
    private Doctor objDoctor;
    private Persona objPersona;
    private Usuario objUsuario;
    private String nuevoTitulo;
    private boolean nuevo;
    private StreamedContent imagen;
    private MapModel geoModel;
    private String centerGeoMap;
    private Marker marker;
    public ManagedBeanDoctor() {
       
        limpiar(true);
        objDoctorItems = new LinkedList<SelectItem>();
        listaobjDoctor = new LinkedList<Doctor>();
    }
    public void limpiar(boolean nuevoPer)
    {
        centerGeoMap = "0.0, 0.0";
        geoModel = new DefaultMapModel();
        nuevo=true;
        nuevoTitulo="AGREGAR NUEVO";
        objDoctor=new Doctor();
        objDoctor.setEstado(1);
         objPersona= new Persona();
        if(nuevoPer){
           
            objPersona.setEstado(1);
            objPersona.setFechaNacimiento(new Date());
//            ManagedBeanDistrito managedBeanDistrito = (ManagedBeanDistrito)facesContext.getApplication().createValueBinding("#{managedBeanDistrito}").getValue(facesContext);
//            managedBeanDistrito
        }
        objUsuario= new Usuario();
        objUsuario.setEstado(1);
        imagen=null;
    }


    public String getNuevoTitulo() {
        return nuevoTitulo;
    }

    public void setNuevoTitulo(String nuevoTitulo) {
        this.nuevoTitulo = nuevoTitulo;
    }

    public List<Doctor> getListaobjDoctor() {
        listaobjDoctor = new LinkedList<Doctor>();
        try
        {
            listaobjDoctor=doctorFacade.findAll();       
        }
        catch (Exception e) {
        }
        return listaobjDoctor;
    }

    public void setListaobjDoctor(List<Doctor> listaobjDoctor) {
        this.listaobjDoctor = listaobjDoctor;
    }

    public List<SelectItem> getObjDoctorItems() {
        objDoctorItems = new LinkedList<SelectItem>();
        try
        {
            listaobjDoctor=doctorFacade.lista_activos();         
            for(Doctor p:listaobjDoctor){
                objDoctorItems.add(new SelectItem(p, p.getPersona().getApellidoPaterno()+" "+p.getPersona().getApellidoMaterno()+" "+p.getPersona().getNombre()));
            }
        }
        catch (Exception e) {
        }
         
        return objDoctorItems;
    }

    public void setObjDoctorItems(List<SelectItem> objDoctorItems) {
        this.objDoctorItems = objDoctorItems;
    }

    public Doctor getObjDoctor() {
        return objDoctor;
    }

    public void setObjDoctor(Doctor objDoctor) {
      
        this.objDoctor = objDoctor;
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
             objDoctor.setFechaModificacion(new Date());
            if(nuevo)
            {
                objDoctor.setFechaRegistro(new Date());
                objDoctor.setPuntaje(0);
                personaFacade.create(objPersona);                
                objDoctor.setPersona(objPersona);
                
                doctorFacade.create(objDoctor);   
                
                objUsuario.setPersona(objPersona);
                objUsuario.setEstado(1);
                objUsuario.setFechaRegistro(new Date());
                objUsuario.setFechaModificacion(new Date());
                objUsuario.setFechaUltimoAcceso(new Date());
                objUsuario.setUsuario(objPersona.getDni());
                usuarioFacade.create(objUsuario);
                
                 System.out.println(""+objDoctor.getPKId());
            }
            else
            {
                doctorFacade.edit(objDoctor);
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
     public void actualizar(Doctor obejto)
    {
        limpiar(true);
        imagen=null;
        this.nuevoTitulo="EDITAR ID : "+obejto.getPKId();        
        objDoctor=obejto;
        objPersona=obejto.getPersona();
        for(Usuario usuario:obejto.getPersona().getUsuarioList())
        {
           objUsuario=usuario;
        }
        nuevo=false;
        InputStream is = new ByteArrayInputStream(objDoctor.getPersona().getFoto());
        imagen = new DefaultStreamedContent(is);
            FacesContext facesContext = FacesContext.getCurrentInstance();
        ManagedBeanDistrito managedBeanDistrito = (ManagedBeanDistrito)facesContext.getApplication().createValueBinding("#{managedBeanDistrito}").getValue(facesContext);
        managedBeanDistrito.setObjDepartamento(obejto.getPersona().getDistrito().getProvincia().getDepartamento());
        managedBeanDistrito.setObjProvincia(obejto.getPersona().getDistrito().getProvincia());
        managedBeanDistrito.setObjDistrito(obejto.getPersona().getDistrito());
        
        centerGeoMap = objDoctor.getLatitud()+ "," + objDoctor.getLongitud();
        LatLng coord = new LatLng(objDoctor.getLatitud(), objDoctor.getLongitud());
        marker=new Marker(coord);
        geoModel.getMarkers().clear();
        geoModel.addOverlay(marker);
        marker.setDraggable(true);
    
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
     
   
      public Doctor traerObjeto(String id) {
        Doctor objBuscar = doctorFacade.find(Integer.parseInt(id));
        return objBuscar;
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

    public MapModel getGeoModel() {
        return geoModel;
    }

    public void setGeoModel(MapModel geoModel) {
        this.geoModel = geoModel;
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

    public Persona getObjPersona() {
        return objPersona;
    }

    public void setObjPersona(Persona objPersona) {
        this.objPersona = objPersona;
    }

    public Usuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(Usuario objUsuario) {
        this.objUsuario = objUsuario;
    }
    public Date getMaxDate() {
        return new Date();
    }
    public int contarRespuestaAnulada(List<RespuestaCasoSalud> lista)
    {
        int contador=0;
        try{
            if(lista!=null)
            {
                for(RespuestaCasoSalud seguro:lista)
                {
                    if(seguro.getEstado()==0)
                        contador++;
                }
            }
        }
        catch (Exception e) {
        }
        return contador;
    }

    
}
