package Controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Utilidades.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.ws.WebServiceRef;
import org.primefaces.event.SelectEvent;
import servicio.Detalle;
import servicio.Doctor;
import servicio.Especialidad;
import servicio.Medico;
import servicio.Medico_Service;

/**
 *
 * @author EdHam
 */
@ManagedBean
@SessionScoped
public class ManagedBeanServicioDoctor implements Serializable {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/170.239.86.92/MedSevis/medico.wsdl")
    private Medico_Service service;
    private List<Doctor> listDoctor;
    private Doctor objetoDoctor;
    private Detalle objetoDetalle;
    private List<Especialidad> listEspecialidad;
    private String cmp;
    private String nombres;
    private String apellidos;
    
    public ManagedBeanServicioDoctor() {
        limpiar();   
        
    }
     public void limpiarTodo()
    {
        cmp="";
        nombres="";
        apellidos="";
        limpiar();   
    }
    
    public void limpiar()
    {
        
        objetoDoctor = new Doctor();
        listDoctor = new ArrayList<Doctor>();
        objetoDetalle = new Detalle();
        listEspecialidad = new ArrayList<Especialidad>();
    }

    public void buscarNombres(String paterno, String materno)
    {
        limpiar();
        try
        {
            Medico port = service.getMedicoPort();
            listDoctor= port.consultaDoctor("", paterno, materno);
        }
        catch (Exception e) {
            
        }
    }
    
    
    public void buscarCodigo(String cmp)
    {
        if(cmp.length()==6)
        {
            limpiar();
            Medico port = service.getMedicoPort();
            try{
                listDoctor= port.consultaDoctor(cmp, "", "");
                System.out.println("listDoctor "+listDoctor.size());
                if(listDoctor!=null && listDoctor.size()>0)
                {
                    objetoDoctor=listDoctor.get(0);
                    listDoctor = new ArrayList<Doctor>();
                      try{
                        objetoDetalle= port.consultaDetalle(cmp);
                    }catch (Exception e) {e.printStackTrace();}

                   try{
                       listEspecialidad= port.consultaEspecialidad(cmp);
                    }catch (Exception e) {e.printStackTrace();}
                }else
                {
                    Utilidades.Error("DOCTOR NO ENCONTRADO");
                }
            }catch (Exception e) {e.printStackTrace();}
        }
        else
        {
            Utilidades.Error("CMP DEBE TENER 6 DIGITOS");
        }
    }
    
    


    public List<Doctor> getListDoctor() {
        return listDoctor;
    }

    public void setListDoctor(List<Doctor> listDoctor) {
        this.listDoctor = listDoctor;
    }

    public Detalle getObjetoDetalle() {
        return objetoDetalle;
    }

    public void setObjetoDetalle(Detalle objetoDetalle) {
        this.objetoDetalle = objetoDetalle;
    }

    public List<Especialidad> getListEspecialidad() {
        return listEspecialidad;
    }

    public void setListEspecialidad(List<Especialidad> listEspecialidad) {
        this.listEspecialidad = listEspecialidad;
    }


    public Doctor getObjetoDoctor() {
        return objetoDoctor;
    }

    public void setObjetoDoctor(Doctor objetoDoctor) {
        this.objetoDoctor = objetoDoctor;
    }

    public String getCmp() {
        return cmp;
    }

    public void setCmp(String cmp) {
        this.cmp = cmp;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public void onRowSelect(SelectEvent event) {
        Medico port = service.getMedicoPort();
            try{ 
                objetoDoctor=((Doctor) event.getObject());
                      
                try{
                    objetoDetalle= port.consultaDetalle(objetoDoctor.getCmp());
                }catch (Exception e) {e.printStackTrace();}

                try{
                    listEspecialidad= port.consultaEspecialidad(objetoDoctor.getCmp());
                }catch (Exception e) {e.printStackTrace();}
            }catch (Exception e) {e.printStackTrace();}
    }

    
    
      
}
