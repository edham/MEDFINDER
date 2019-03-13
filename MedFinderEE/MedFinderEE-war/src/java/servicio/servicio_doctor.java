/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import Utilidades.Utilidades;
import fachada.CasoSaludPuntajeFacadeLocal;
import fachada.CasosSaludFacadeLocal;
import fachada.CitaPacienteFacadeLocal;
import fachada.EspecialidadFacadeLocal;
import fachada.FavoritosFacadeLocal;
import fachada.PacienteFacadeLocal;
import fachada.PersonaFacadeLocal;
import fachada.PreguntaPacienteFacadeLocal;
import fachada.RespuestaPreguntaPacienteFacadeLocal;
import fachada.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CasoSaludPuntaje;
import modelo.CasosSalud;
import modelo.CitaPaciente;
import modelo.Clinica;
import modelo.DetalleClinicaEspecialidad;
import modelo.DetalleClinicaSeguro;
import modelo.Distrito;
import modelo.Favoritos;
import modelo.Paciente;
import modelo.Persona;
import modelo.PreguntaPaciente;
import modelo.RespuestaCasoSalud;
import modelo.RespuestaPreguntaPaciente;
import modelo.Seguro;
import modelo.Usuario;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author EdHam
 */
@WebServlet(name = "servicio_doctor", urlPatterns = {"/servicio_doctor"})
public class servicio_doctor extends HttpServlet {
 @EJB
    private CasosSaludFacadeLocal casosSaludFacade;
    @EJB
    private PacienteFacadeLocal pacienteFacade;    
    @EJB
    private PersonaFacadeLocal personaFacade;    
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    @EJB
    private EspecialidadFacadeLocal especialidadFacade;
    @EJB
    private PreguntaPacienteFacadeLocal preguntaPacienteFacade;    
    @EJB
    private CasoSaludPuntajeFacadeLocal casoSaludPuntajeFacade; 
    @EJB
    private FavoritosFacadeLocal favoritosFacade;
    @EJB
    private CitaPacienteFacadeLocal citaPacienteFacade;
    @EJB
    private RespuestaPreguntaPacienteFacadeLocal respuestaPreguntaPacienteFacade;
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
     JSONObject obj=new JSONObject();        
    if(request.getParameter("IdServicio") != null && request.getParameter("IdServicio") != "" )
    {
        int idServicio=Integer.parseInt(request.getParameter("IdServicio"));

       if(idServicio==0 && request.getParameter("usuario") != null && request.getParameter("usuario") != ""
                         && request.getParameter("clave") != null && request.getParameter("clave") != "")
                {
                    Usuario objUsuario=usuarioFacade.login(request.getParameter("usuario"), request.getParameter("clave"));
                    if(objUsuario!=null)
                    {
                        obj.put("rpta", 1); 
                        obj.put("usuarioId", objUsuario.getPKId());
                        obj.put("personaId", objUsuario.getPersona().getPKId());   
                        obj.put("personaNombre", objUsuario.getPersona().getNombre()); 
                        obj.put("personaApellidoPaterno", objUsuario.getPersona().getApellidoPaterno()); 
                        obj.put("personaApellidoMaterno", objUsuario.getPersona().getApellidoMaterno());
                        obj.put("personaEmail", objUsuario.getPersona().getEmail());
                        obj.put("personaDni", objUsuario.getPersona().getDni());
                        obj.put("personaSexo", objUsuario.getPersona().getSexo());
                        obj.put("personaDireccion", objUsuario.getPersona().getDireccion());
                        obj.put("personaTelefono", objUsuario.getPersona().getTelefono());
                                if(objUsuario.getPersona().getFoto()==null)
                                    obj.put("personaFoto", "");
                                else
                                      obj.put("personaFoto", Utilidades.getEncodeBase64(objUsuario.getPersona().getFoto()));
                                
                               
                                List<CitaPaciente> listCitaPaciente=new ArrayList<CitaPaciente>();
                                List<PreguntaPaciente> listPreguntaPaciente=new ArrayList<PreguntaPaciente>();
                                JSONArray listPacienteJSON = new JSONArray();
                                if(objUsuario.getPacienteList().size()>0)
                                {
                                    for(Paciente objPaciente : objUsuario.getPacienteList())
                                    {
                                        listCitaPaciente.addAll(objPaciente.getCitaPacienteList());
                                        listPreguntaPaciente.addAll(objPaciente.getPreguntaPacienteList());
                                        JSONObject entidadJSON=new JSONObject();
                                        entidadJSON.put("pacienteId", objPaciente.getPKId());
                                        entidadJSON.put("personaId", objPaciente.getPersona().getPKId());   
                                        entidadJSON.put("personaNombre", objPaciente.getPersona().getNombre()); 
                                        entidadJSON.put("personaApellidoPaterno", objPaciente.getPersona().getApellidoPaterno()); 
                                        entidadJSON.put("personaApellidoMaterno", objPaciente.getPersona().getApellidoMaterno());
                                        entidadJSON.put("personaDni", objPaciente.getPersona().getDni());
                                        entidadJSON.put("personaFechaNacimiento", objPaciente.getPersona().getFechaNacimiento().getTime());
                                        entidadJSON.put("personaSexo", objPaciente.getPersona().getSexo());
                                        entidadJSON.put("pacienteTipo", objPaciente.getTipo());
                                        entidadJSON.put("pacienteEstatura", objPaciente.getEstatura());
                                        entidadJSON.put("pacienteCardiovascular", objPaciente.getCardiovascular());
                                        entidadJSON.put("pacienteMusculares", objPaciente.getMusculares());
                                        entidadJSON.put("pacienteDigestivos", objPaciente.getDigestivos());
                                        entidadJSON.put("pacienteAlergicos", objPaciente.getAlergicos());
                                        entidadJSON.put("pacienteAlcohol", objPaciente.getAlcohol());
                                        entidadJSON.put("pacienteTabaquismo", objPaciente.getTabaquismo());
                                        entidadJSON.put("pacienteDrogas", objPaciente.getDrogas());
                                        entidadJSON.put("pacientePsicologicos", objPaciente.getPsicologicos());
                                        entidadJSON.put("pacienteEstado", objPaciente.getEstado());
                                        entidadJSON.put("usuarioId",objUsuario.getPKId());
                                        listPacienteJSON.put(entidadJSON);
                                    }                                        
                                }
                                obj.put("listPacienteJSON", listPacienteJSON);
                                
                                listCitaPaciente=Utilidades.clearListCitaPaciente(listCitaPaciente);
                                JSONArray listCitaPacienteJSON = new JSONArray();
                               if(listCitaPaciente.size()>0)
                               {
                                   for(CitaPaciente objCitaPaciente : listCitaPaciente)
                                   {
                                       JSONObject entidadJSON=new JSONObject();
                                       entidadJSON.put("citaPacienteId", objCitaPaciente.getPKId());
                                       entidadJSON.put("doctorId", objCitaPaciente.getDoctor().getPKId());
                                       entidadJSON.put("pacienteId", objCitaPaciente.getPaciente().getPKId());
                                       entidadJSON.put("citaPacienteDetalle", objCitaPaciente.getDetalle());
                                       entidadJSON.put("citaPacienteFechaRegistro", objCitaPaciente.getFechaRegistro().getTime());
                                       entidadJSON.put("citaPacienteAtencion", objCitaPaciente.getAtencion().getTime());
                                       entidadJSON.put("citaPacienteEstado", objCitaPaciente.getEstado());
                                       listCitaPacienteJSON.put(entidadJSON);
                                   }    
                               }
                               obj.put("listCitaPacienteJSON", listCitaPacienteJSON);
                                
                               List<RespuestaPreguntaPaciente> listRespuestaPreguntaPaciente=new ArrayList<RespuestaPreguntaPaciente>();
//                               
                               listPreguntaPaciente=Utilidades.clearListPreguntaPaciente(listPreguntaPaciente);
                                JSONArray listPreguntaPacienteJSON = new JSONArray();
                               if(listPreguntaPaciente.size()>0)
                               {
                                   for(PreguntaPaciente objPreguntaPaciente : listPreguntaPaciente)
                                   {
                                       listRespuestaPreguntaPaciente.addAll(objPreguntaPaciente.getRespuestaPreguntaPacienteList());
                                       JSONObject entidadJSON=new JSONObject();
                                       entidadJSON.put("preguntaPacienteId", objPreguntaPaciente.getPKId());
                                       entidadJSON.put("pacienteId", objPreguntaPaciente.getPaciente().getPKId());
                                       entidadJSON.put("especialidadId", objPreguntaPaciente.getEspecialidad().getPKId());
                                       entidadJSON.put("preguntaPacienteAsunto", objPreguntaPaciente.getAsunto());                                       
                                       entidadJSON.put("preguntaPacienteDetalle", objPreguntaPaciente.getDetalle());
                                       entidadJSON.put("preguntaPacienteEstado", objPreguntaPaciente.getEstado());
                                       entidadJSON.put("preguntaPacienteFechaInicio", objPreguntaPaciente.getFechaInicio().getTime());  
                                        listPreguntaPacienteJSON.put(entidadJSON); 
                                   }    
                               }
                              obj.put("listPreguntaPacienteJSON", listPreguntaPacienteJSON);  
                                
                               List<Favoritos> listFavoritos=objUsuario.getFavoritosList();
                               JSONArray listFavoritosJSON = new JSONArray();
                                if(listFavoritos.size()>0)
                                {
                                    for(Favoritos objFavoritos : listFavoritos)
                                    if(objFavoritos.getEstado()==1)
                                    {    
                                        JSONObject entidadJSON=new JSONObject();
                                        entidadJSON.put("doctorId", objFavoritos.getDoctor().getPKId());  
                                        listFavoritosJSON.put(entidadJSON); 
                                    }     
                                }
                                obj.put("listFavoritosJSON", listFavoritosJSON);  
                              
                                List<CasoSaludPuntaje> listRespuestaCasosSaludVotos=objUsuario.getCasoSaludPuntajeList();
                                JSONArray listRespuestaCasosSaludVotosJSON = new JSONArray();
                               if(listRespuestaCasosSaludVotos.size()>0)
                               {
                                   for(CasoSaludPuntaje objRespuestaCasosSalud : listRespuestaCasosSaludVotos)
                                   {
                                       JSONObject entidadJSON=new JSONObject();
                                        entidadJSON.put("respuestaCasoSaludId",objRespuestaCasosSalud.getRespuestaCasoSalud().getPKId());  
                                        listRespuestaCasosSaludVotosJSON.put(entidadJSON); 
                                   }   
                               }
                               obj.put("listRespuestaCasosSaludVotosJSON", listRespuestaCasosSaludVotosJSON);  
//                              
//                               
                             listRespuestaPreguntaPaciente=Utilidades.clearListRespuestaPreguntaPaciente(listRespuestaPreguntaPaciente);
                            JSONArray listRespuestaPreguntaPacienteJSON = new JSONArray();
                             if(listRespuestaPreguntaPaciente.size()>0)
                               {
                                   for(RespuestaPreguntaPaciente objRespuestaPreguntaPaciente : listRespuestaPreguntaPaciente)
                                   {
                                       JSONObject entidadJSON=new JSONObject();
                                       entidadJSON.put("respuestaPreguntaPacienteId", objRespuestaPreguntaPaciente.getPKId()); 
                                       entidadJSON.put("preguntaPacienteId", objRespuestaPreguntaPaciente.getPreguntaPaciente().getPKId()); 
                                       entidadJSON.put("doctorId", objRespuestaPreguntaPaciente.getDoctor().getPKId()); 
                                       entidadJSON.put("respuestaPreguntaPacienteDetalle", objRespuestaPreguntaPaciente.getDetalle()); 
                                       entidadJSON.put("respuestaPreguntaPacientePuntaje", objRespuestaPreguntaPaciente.getPuntaje()); 
                                       entidadJSON.put("respuestaPreguntaPacienteFechaRegistro", objRespuestaPreguntaPaciente.getFechaRegistro().getTime());                                                  
                                        listRespuestaPreguntaPacienteJSON.put(entidadJSON); 
                                   }
                               }
                              obj.put("listRespuestaPreguntaPacienteJSON", listRespuestaPreguntaPacienteJSON);  
                              //xd
//                           
                               
                    }
                    else
                       obj.put("rpta", 0); 
                }
                else if(idServicio==4 && request.getParameter("idPaciente") != null && request.getParameter("idPaciente") != ""
                    && request.getParameter("idEspecialidad") != null && request.getParameter("idEspecialidad") != ""
                    && request.getParameter("detalle") != null && request.getParameter("detalle") != ""
                    && request.getParameter("asunto") != null && request.getParameter("asunto") != "")
                {
                   PreguntaPaciente entdiad = new PreguntaPaciente();
                   entdiad.setEspecialidad(new modelo.Especialidad(Integer.parseInt(request.getParameter("idEspecialidad"))));
                   entdiad.setPaciente(new Paciente(Integer.parseInt(request.getParameter("idPaciente"))));        
                   entdiad.setAsunto(request.getParameter("asunto"));
                   entdiad.setDetalle(request.getParameter("detalle"));
                   entdiad.setFechaFin(new Date());
                   entdiad.setFechaInicio(new Date());
                   entdiad.setEstado(1);
                   if(request.getParameter("imagen") != null && request.getParameter("imagen") != "")
                   entdiad.setImagen(Utilidades.getDecodeBase64(request.getParameter("imagen")));
                    preguntaPacienteFacade.create(entdiad);
                    obj.put("preguntaPacienteId", entdiad.getPKId());    
                    obj.put("rpta", 1); 

                }
                 else if(idServicio==5 && request.getParameter("idUsuario") != null && request.getParameter("idUsuario") != ""
                            && request.getParameter("idRespuestaCasosSalud") != null && request.getParameter("idRespuestaCasosSalud") != ""
                            && request.getParameter("puntaje") != null && request.getParameter("puntaje") != "")
                   {
                       CasoSaludPuntaje entidad = new CasoSaludPuntaje();
                       entidad.setEstado(1);
                       entidad.setFechaRegistro(new Date());
                       entidad.setPuntajeTotal((short)Integer.parseInt(request.getParameter("puntaje")));
                       entidad.setRespuestaCasoSalud(new RespuestaCasoSalud(Integer.parseInt(request.getParameter("idRespuestaCasosSalud"))));
                       entidad.setUsuario(new Usuario(Integer.parseInt(request.getParameter("idUsuario"))));
                       casoSaludPuntajeFacade.create(entidad);                       
                       obj.put("casoSaludPuntajeId", entidad.getPKId());    
                       obj.put("rpta", 1); 
                   }
                  else if(idServicio==6 && request.getParameter("idUsuario") != null && request.getParameter("idUsuario") != ""
                            && request.getParameter("idDoctor") != null && request.getParameter("idDoctor") != ""
                            && request.getParameter("idFavoritos") != null && request.getParameter("idFavoritos") != "")
                   {
                       Favoritos entidad = new Favoritos();
                     
                      if(Integer.parseInt(request.getParameter("idFavoritos"))>0) 
                      {  
                            entidad.setUsuario(new Usuario(Integer.parseInt(request.getParameter("idUsuario"))));
                            entidad.setDoctor(new modelo.Doctor(Integer.parseInt(request.getParameter("idDoctor"))));
                            entidad.setFechaModificacion(new Date());
                            entidad.setFechaRegistro(new Date());
                            entidad.setEstado(1);
                            favoritosFacade.create(entidad);
                            obj.put("favoritosId", entidad.getPKId());    
                            obj.put("rpta", 1); 
                      }
                      else
                      { 
                            entidad=favoritosFacade.find(new Favoritos(Integer.parseInt(request.getParameter("idFavoritos"))));
                            entidad.setFechaModificacion(new Date());
                            entidad.setEstado(0);
                            favoritosFacade.edit(entidad);
                            obj.put("rpta", 1); 
                      }
                   }
                   else if(idServicio==7 && request.getParameter("idPaciente") != null && request.getParameter("idPaciente") != ""
                    && request.getParameter("idDoctor") != null && request.getParameter("idDoctor") != ""
                    && request.getParameter("detalle") != null && request.getParameter("detalle") != "")
                    {
                        CitaPaciente entidad = new CitaPaciente();
                        entidad.setDoctor(new modelo.Doctor(Integer.parseInt(request.getParameter("idDoctor"))));
                        entidad.setPaciente(new Paciente(Integer.parseInt(request.getParameter("idPaciente"))));
                        entidad.setDetalle(request.getParameter("detalle"));
                        entidad.setFechaModificacion(new Date());
                        entidad.setFechaRegistro(new Date());
                        entidad.setEstado(1);
                        citaPacienteFacade.create(entidad);
                        obj.put("citaPacienteId", entidad.getPKId());    
                        obj.put("rpta", 1); 
                    }
                   else if(idServicio==8 && request.getParameter("idCita") != null && request.getParameter("idCita") != "")
                    {
                        CitaPaciente entidad =citaPacienteFacade.find(new CitaPaciente(Integer.parseInt(request.getParameter("idCita"))));
                        entidad.setEstado(3);
                        entidad.setFechaRegistro(new Date());
                        citaPacienteFacade.edit(entidad);   
                        obj.put("rpta", 1); 
                    }  
                   else if(idServicio==9 && request.getParameter("idRespuesta") != null && request.getParameter("idRespuesta") != ""
                            && request.getParameter("puntaje") != null && request.getParameter("puntaje") != "")
                    {
                        RespuestaPreguntaPaciente entidad=respuestaPreguntaPacienteFacade.find(new RespuestaPreguntaPaciente(Integer.parseInt(request.getParameter("idRespuesta"))));
                        entidad.setFechaModificacion(new Date());
                        entidad.setEstado(1);
                        entidad.setPuntaje(Integer.parseInt(request.getParameter("puntaje")));
                        obj.put("rpta", 1); 
                    }
                   else if(idServicio==10 && request.getParameter("idUsuario") != null && request.getParameter("idUsuario") != "")
                    {
                                List<DetalleClinicaEspecialidad> listDetalleClinicaEspecialidad=new ArrayList<DetalleClinicaEspecialidad>();    
                                List<modelo.Doctor> listDoctor=new  ArrayList<modelo.Doctor>();
                               List<modelo.Especialidad> listEspecialidad=especialidadFacade.lista_activos();
                               JSONArray listEspecialidadJSON = new JSONArray();
                               if(listEspecialidad.size()>0)
                               {
                                   for(modelo.Especialidad objEspecialidad : listEspecialidad)
                                   {
                                       listDoctor.addAll(objEspecialidad.getDoctorList());
                                       listDetalleClinicaEspecialidad.addAll(objEspecialidad.getDetalleClinicaEspecialidadList());
                                       JSONObject entidadJSON=new JSONObject();
                                       entidadJSON.put("especialidadId", objEspecialidad.getPKId());
                                       entidadJSON.put("especialidadNombre", objEspecialidad.getNombre());
                                       entidadJSON.put("especialidadDescripcion", objEspecialidad.getDescripcion());
                                       entidadJSON.put("especialidadEstado", objEspecialidad.getEstado());
                                       listEspecialidadJSON.put(entidadJSON); 
                                   }   
                               }
                               obj.put("listEspecialidadJSON", listEspecialidadJSON);  
                               List<Clinica> listClinica=new ArrayList<Clinica>();
                               listDetalleClinicaEspecialidad=Utilidades.clearListDetalleClinicaEspecialidad(listDetalleClinicaEspecialidad);
                               JSONArray listDetalleClinicaEspecialidadJSON = new JSONArray();
                               if(listDetalleClinicaEspecialidad.size()>0)
                               {
                                   for(DetalleClinicaEspecialidad objDetalleClinicaEspecialidad : listDetalleClinicaEspecialidad){
                                       listClinica.add(objDetalleClinicaEspecialidad.getClinica());
                                       JSONObject entidadJSON=new JSONObject();
                                       entidadJSON.put("detalleClinicaEspecialidadId", objDetalleClinicaEspecialidad.getPKId());
                                       entidadJSON.put("clinicaId", objDetalleClinicaEspecialidad.getClinica().getPKId());
                                       entidadJSON.put("EspecialidadId", objDetalleClinicaEspecialidad.getPKId());
                                       entidadJSON.put("detalleClinicaEspecialidadHorarioInicio", objDetalleClinicaEspecialidad.getHorarioInicio().getTime());
                                       entidadJSON.put("detalleClinicaEspecialidadHorarioFin", objDetalleClinicaEspecialidad.getHorarioFin().getTime());                                       
                                       entidadJSON.put("detalleClinicaEspecialidadDetalleHorario", objDetalleClinicaEspecialidad.getDetalleHorario());
                                       entidadJSON.put("detalleClinicaEspecialidadEstado", objDetalleClinicaEspecialidad.getEstado());
                                       listDetalleClinicaEspecialidadJSON.put(entidadJSON); 
                                   }   
                               }
                              obj.put("listDetalleClinicaEspecialidadJSON", listDetalleClinicaEspecialidadJSON);

                              List<DetalleClinicaSeguro> listDetalleClinicaSeguro=new ArrayList<DetalleClinicaSeguro>();
                             
                               listClinica=Utilidades.clearListClinica(listClinica);
                               JSONArray listClinicaJSON = new JSONArray();
                               if(listClinica.size()>0)
                               {
                                   for(Clinica objClinica : listClinica)
                                   {
                                       listDetalleClinicaSeguro.addAll(objClinica.getDetalleClinicaSeguroList());
                                       JSONObject entidadJSON=new JSONObject();
                                       entidadJSON.put("clinicaId", objClinica.getPKId());
                                       entidadJSON.put("clinicaNombre", objClinica.getNombre());
                                       entidadJSON.put("clinicaSlogan", objClinica.getSlogan());
                                       entidadJSON.put("clinicaDireccion", objClinica.getDireccion());
                                       entidadJSON.put("clinicaDescripcion", objClinica.getDescripcion());
                                       entidadJSON.put("clinicaHorarioInicio", objClinica.getHorarioInicio().getTime());
                                       entidadJSON.put("clinicaHorarioFin", objClinica.getHorarioFin().getTime());
                                       entidadJSON.put("clinicaDetalleAtencion", objClinica.getDetalleAtencion());                                              
                                        if(objClinica.getLogo()==null)
                                            entidadJSON.put("clinicaLogo","");
                                        else
                                            entidadJSON.put("clinicaLogo",Utilidades.getEncodeBase64(objClinica.getLogo()));
                                        entidadJSON.put("clinicaTelefono", objClinica.getTelefono());
                                        entidadJSON.put("distritoId", objClinica.getDistrito().getPKId());
                                        entidadJSON.put("clinicaLongitud", objClinica.getLongitud());
                                        entidadJSON.put("clinicaLatitud", objClinica.getLatitud());
                                        entidadJSON.put("clinicaEstado", objClinica.getEstado());
                                        listClinicaJSON.put(entidadJSON); 
                                   }   
                               }
                              obj.put("listClinicaJSON", listClinicaJSON);

                             List<Seguro> listSeguro=new ArrayList<Seguro>(); 
                              listDetalleClinicaSeguro=Utilidades.clearListDetalleClinicaSeguro(listDetalleClinicaSeguro);
                              JSONArray listDetalleClinicaSeguroJSON = new JSONArray(); 
                              if(listDetalleClinicaSeguro.size()>0)
                               {
                                   for(DetalleClinicaSeguro objDetalleClinicaSeguro : listDetalleClinicaSeguro)
                                   {
                                       listSeguro.add(objDetalleClinicaSeguro.getSeguro());
                                       JSONObject entidadJSON=new JSONObject();
                                       entidadJSON.put("detalleClinicaSeguroId", objDetalleClinicaSeguro.getPKId());
                                       entidadJSON.put("clinicaId", objDetalleClinicaSeguro.getClinica().getPKId());
                                       entidadJSON.put("seguroId", objDetalleClinicaSeguro.getSeguro().getPKId());
                                       entidadJSON.put("detalleClinicaSeguroEstado", objDetalleClinicaSeguro.getEstado());
                                       listDetalleClinicaSeguroJSON.put(entidadJSON); 
                                   }   
                               }
                                obj.put("listDetalleClinicaSeguroJSON", listDetalleClinicaSeguroJSON);
                            
                                listDoctor = Utilidades.clearListDoctor(listDoctor);
                                JSONArray listDoctorJSON = new JSONArray(); 
                               if(listDoctor.size()>0)
                               {
                                   for(modelo.Doctor objDoctor : listDoctor)
                                   {
                                       JSONObject entidadJSON=new JSONObject();
                                       entidadJSON.put("doctorId",objDoctor.getPKId()); 
                                        entidadJSON.put("personaNombre", objDoctor.getPersona().getNombre()); 
                                        entidadJSON.put("personaApellidoPaterno", objDoctor.getPersona().getApellidoPaterno()); 
                                        entidadJSON.put("personaApellidoMaterno", objDoctor.getPersona().getApellidoMaterno());
                                        entidadJSON.put("personaDni", objDoctor.getPersona().getDni());
                                        entidadJSON.put("doctorCodigoColegiatura",objDoctor.getCodigoColegiatura()); 
                                        entidadJSON.put("doctorDireccion",objDoctor.getDireccion()); 
                                        entidadJSON.put("doctorDireccionDetalle",objDoctor.getDireccionDetalle());                                         
                                        entidadJSON.put("doctorTelefono",objDoctor.getTelefono()); 
                                        entidadJSON.put("doctorLongitud",objDoctor.getLongitud()); 
                                        entidadJSON.put("doctorLatitud",objDoctor.getLatitud()); 
                                        entidadJSON.put("doctorEspecialidadId",objDoctor.getEspecialidad().getPKId()); 
                                        entidadJSON.put("doctorPuntaje",objDoctor.getPuntaje()); 
                                        if(objDoctor.getPersona().getFoto()==null)
                                            entidadJSON.put("personaFoto", "");
                                        else
                                            entidadJSON.put("personaFoto", Utilidades.getEncodeBase64(objDoctor.getPersona().getFoto()));
                                        listDoctorJSON.put(entidadJSON); 
                                   }   
                               }
                                obj.put("listDoctorJSON", listDoctorJSON);

                               listSeguro=Utilidades.clearListSeguro(listSeguro);
                                JSONArray listSeguroJSON = new JSONArray(); 
                               if(listSeguro.size()>0)
                               {
                                   for(Seguro objSeguro : listSeguro)
                                   {
                                        JSONObject entidadJSON=new JSONObject();
                                        entidadJSON.put("seguroId",objSeguro.getPKId()); 
                                        entidadJSON.put("seguroNombre",objSeguro.getNombre()); 
                                        entidadJSON.put("seguroEstado",objSeguro.getEstado()); 
                                        if(objSeguro.getLogo()==null)
                                            entidadJSON.put("seguroLogo", "");
                                        else
                                            entidadJSON.put("seguroLogo",Utilidades.getEncodeBase64(objSeguro.getLogo()));  
                                        listSeguroJSON.put(entidadJSON); 
                                   }   
                               }
                                obj.put("listSeguroJSON", listSeguroJSON);
                                
                               List<RespuestaCasoSalud> listRespuestaCasosSalud=new ArrayList<RespuestaCasoSalud>();
                               List<CasosSalud> listCasosSalud=casosSaludFacade.lista_activos();
                               JSONArray listCasosSaludJSON = new JSONArray();    
                               if(listCasosSalud.size()>0)
                               {
                                   for(CasosSalud objCasosSalud : listCasosSalud)
                                   {
                                        listRespuestaCasosSalud.addAll(objCasosSalud.getRespuestaCasoSaludList());
                                        JSONObject entidadJSON=new JSONObject();
                                        entidadJSON.put("casosSaludId",objCasosSalud.getPKId());
                                        entidadJSON.put("casosSaludTema",objCasosSalud.getTema());
                                        entidadJSON.put("casosSaludFechaInicio",objCasosSalud.getFechaInicio().getTime());
                                        entidadJSON.put("casosSaludFechaFin",objCasosSalud.getFechaFin().getTime());  
                                        listCasosSaludJSON.put(entidadJSON); 
                                   }   
                               }
                                obj.put("listCasosSaludJSON", listCasosSaludJSON);
                                
                                listRespuestaCasosSalud=Utilidades.clearListRespuestaCasoSalud(listRespuestaCasosSalud);
                               JSONArray listRespuestaCasosSaludJSON = new JSONArray();    
                                if(listRespuestaCasosSalud.size()>0)
                                {
                                   for(RespuestaCasoSalud objRespuestaCasosSalud : listRespuestaCasosSalud)
                                   {
                                       JSONObject entidadJSON=new JSONObject();
                                       entidadJSON.put("respuestaCasosSaludId",objRespuestaCasosSalud.getPKId());
                                       entidadJSON.put("doctorId",objRespuestaCasosSalud.getDoctor().getPKId());
                                       entidadJSON.put("casosSaludId",objRespuestaCasosSalud.getCasosSalud().getPKId());
                                       entidadJSON.put("respuestaCasosSaludDescripcion",objRespuestaCasosSalud.getDescripcion());
                                       entidadJSON.put("respuestaCasosSaludPuntajeTotal",objRespuestaCasosSalud.getPuntajeTotal());       
                                       listRespuestaCasosSaludJSON.put(entidadJSON); 
                                   }   
                               }
                                obj.put("listRespuestaCasosSaludJSON", listRespuestaCasosSaludJSON);
                                obj.put("rpta", 1);
       
                        }
                   
                             else
                       obj.put("rpta", 0); 
            }
            out.println(obj);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
