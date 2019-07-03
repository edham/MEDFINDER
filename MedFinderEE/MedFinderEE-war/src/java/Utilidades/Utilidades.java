/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import modelo.CitaPaciente;
import modelo.Clinica;
import modelo.DetalleClinicaEspecialidad;
import modelo.DetalleClinicaSeguro;
import modelo.Doctor;
import modelo.PreguntaPaciente;
import modelo.RespuestaCasoSalud;
import modelo.RespuestaPreguntaPaciente;
import modelo.Seguro;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import modelo.Especialidad;
import modelo.Paciente;
import wsdl.Medico;
import wsdl.Medico_Service;
import wsdl.WsDetalle;
import wsdl.WsDoctor;

/**
 *
 * @author EdHam
 */
public class Utilidades {
     public static void Info(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFORMACIÓN", msg));
    }
     
    public static void Warn(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "PRECAUCIÓN!", msg));
    }
     
    public static void Error(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR!", msg));
    }
     
    public static void Fatal(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR FATAL!", msg));
    }
    public static byte[] getDecodeBase64(String input)
    {    
       return Base64.decode(input,Base64.NO_WRAP|Base64.URL_SAFE);
    }

    public static String getEncodeBase64(byte[] input)
    {
        return Base64.encodeToString(input,Base64.NO_WRAP|Base64.URL_SAFE);
    }
    public static byte[] Redimensionar(byte[] bytes, int w, int h)
    {
        byte[] imageBytes=null;
        
        try {
            InputStream in = new ByteArrayInputStream(bytes);
            BufferedImage bufferedImage = ImageIO.read(in);
            int finalw = w;
            int finalh = h;
            double factor = 1.0d;
            if(bufferedImage.getWidth() > bufferedImage.getHeight()){
                factor = ((double)bufferedImage.getHeight()/(double)bufferedImage.getWidth());
                finalh = (int)(finalw * factor);                
            }else{
                factor = ((double)bufferedImage.getWidth()/(double)bufferedImage.getHeight());
                finalw = (int)(finalh * factor);
            }   
            BufferedImage resizedImg = new BufferedImage(finalw, finalh, BufferedImage.TRANSLUCENT);
            Graphics2D g2 = resizedImg.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(bufferedImage, 0, 0, finalw, finalh, null);
            g2.dispose();
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImg, "png", baos);
            baos.flush();
            imageBytes = baos.toByteArray();
            baos.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imageBytes;
    }

   public static List<RespuestaPreguntaPaciente> clearListRespuestaPreguntaPaciente (List<RespuestaPreguntaPaciente> ListRespuestaPreguntaPaciente)
   {
       
        Set<RespuestaPreguntaPaciente> linkedHashSet = new LinkedHashSet<RespuestaPreguntaPaciente>();
        linkedHashSet.addAll(ListRespuestaPreguntaPaciente);                
        ListRespuestaPreguntaPaciente.clear();
        ListRespuestaPreguntaPaciente.addAll(linkedHashSet);
        return ListRespuestaPreguntaPaciente;
   }
   
   public static List<Paciente> clearListPaciente (List<Paciente> ListPaciente)
   {
       
        Set<Paciente> linkedHashSet = new LinkedHashSet<Paciente>();
        linkedHashSet.addAll(ListPaciente);                
        ListPaciente.clear();
        ListPaciente.addAll(linkedHashSet);
        return ListPaciente;
   }
   
   public static List<CitaPaciente> clearListCitaPaciente (List<CitaPaciente> ListCitaPaciente)
   {
       
        Set<CitaPaciente> linkedHashSet = new LinkedHashSet<CitaPaciente>();
        linkedHashSet.addAll(ListCitaPaciente);                
        ListCitaPaciente.clear();
        ListCitaPaciente.addAll(linkedHashSet);
        return ListCitaPaciente;
   }
   public static List<PreguntaPaciente> clearListPreguntaPaciente (List<PreguntaPaciente> ListPreguntaPaciente)
   {
       
        Set<PreguntaPaciente> linkedHashSet = new LinkedHashSet<PreguntaPaciente>();
        linkedHashSet.addAll(ListPreguntaPaciente);                
        ListPreguntaPaciente.clear();
        ListPreguntaPaciente.addAll(linkedHashSet);
        return ListPreguntaPaciente;
   }
   public static List<DetalleClinicaEspecialidad> clearListDetalleClinicaEspecialidad (List<DetalleClinicaEspecialidad> ListDetalleClinicaEspecialidad)
   {
       
        Set<DetalleClinicaEspecialidad> linkedHashSet = new LinkedHashSet<DetalleClinicaEspecialidad>();
        linkedHashSet.addAll(ListDetalleClinicaEspecialidad);                
        ListDetalleClinicaEspecialidad.clear();
        ListDetalleClinicaEspecialidad.addAll(linkedHashSet);
        return ListDetalleClinicaEspecialidad;
   }
   public static List<Clinica> clearListClinica (List<Clinica> ListClinica)
   {
       
        Set<Clinica> linkedHashSet = new LinkedHashSet<Clinica>();
        linkedHashSet.addAll(ListClinica);                
        ListClinica.clear();
        ListClinica.addAll(linkedHashSet);
        return ListClinica;
   }
   public static List<Doctor> clearListDoctor (List<Doctor> ListDoctor)
   {
       
        Set<Doctor> linkedHashSet = new LinkedHashSet<Doctor>();
        linkedHashSet.addAll(ListDoctor);                
        ListDoctor.clear();
        ListDoctor.addAll(linkedHashSet);
        return ListDoctor;
   }
   public static List<DetalleClinicaSeguro> clearListDetalleClinicaSeguro (List<DetalleClinicaSeguro> ListDetalleClinicaSeguro)
   {
       
        Set<DetalleClinicaSeguro> linkedHashSet = new LinkedHashSet<DetalleClinicaSeguro>();
        linkedHashSet.addAll(ListDetalleClinicaSeguro);                
        ListDetalleClinicaSeguro.clear();
        ListDetalleClinicaSeguro.addAll(linkedHashSet);
        return ListDetalleClinicaSeguro;
   }
   public static List<Seguro> clearListSeguro (List<Seguro> ListSeguro)
   {
       
        Set<Seguro> linkedHashSet = new LinkedHashSet<Seguro>();
        linkedHashSet.addAll(ListSeguro);                
        ListSeguro.clear();
        ListSeguro.addAll(linkedHashSet);
        return ListSeguro;
   }
   public static List<RespuestaCasoSalud> clearListRespuestaCasoSalud (List<RespuestaCasoSalud> ListRespuestaCasoSalud)
   {
       
        Set<RespuestaCasoSalud> linkedHashSet = new LinkedHashSet<RespuestaCasoSalud>();
        linkedHashSet.addAll(ListRespuestaCasoSalud);                
        ListRespuestaCasoSalud.clear();
        ListRespuestaCasoSalud.addAll(linkedHashSet);
        return ListRespuestaCasoSalud;
   }
   
   public static List<Especialidad> clearListEspecialidad(List<Especialidad> ListEspecialidad)
   {
        Set<Especialidad> linkedHashSet = new LinkedHashSet<Especialidad>();
        linkedHashSet.addAll(ListEspecialidad);                
        ListEspecialidad.clear();
        ListEspecialidad.addAll(linkedHashSet);
        return ListEspecialidad;
   }

    public static WsDetalle consultaDetalle(String cmp) {
        Medico_Service service = new Medico_Service();
        Medico port = service.getMedicoPort();
        return port.consultaDetalle(cmp);
    }

    public static List<WsDoctor> consultaDoctor(String cmp,String paterno,String materno,String nombres) {
        Medico_Service service = new Medico_Service();
        Medico port = service.getMedicoPort();
        return port.consultaDoctor(cmp, paterno, materno, nombres);
    }
   
   
}
