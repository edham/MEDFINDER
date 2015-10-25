/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import Utilidades.Utilidades;
import bc.CasoSaludPuntajeFacadeLocal;
import bc.CasosSaludFacadeLocal;
import bc.CitaPacienteFacadeLocal;
import bc.ClinicaFacadeLocal;
import bc.DetalleClinicaEspecialidadFacadeLocal;
import bc.DetalleClinicaSeguroFacadeLocal;
import bc.DoctorFacadeLocal;
import bc.EspecialidadFacadeLocal;
import bc.FavoritosFacadeLocal;
import bc.PacienteFacadeLocal;
import bc.PersonaFacadeLocal;
import bc.PreguntaPacienteFacadeLocal;
import bc.RespuestaCasoSaludFacadeLocal;
import bc.RespuestaPreguntaPacienteFacadeLocal;
import bc.SeguroFacadeLocal;
import bc.UsuarioFacadeLocal;
import be.CasoSaludPuntaje;
import be.CasosSalud;
import be.CitaPaciente;
import be.Clinica;
import be.DetalleClinicaEspecialidad;
import be.DetalleClinicaSeguro;
import be.Distrito;
import be.Doctor;
import be.Especialidad;
import be.Favoritos;
import be.Paciente;
import be.Persona;
import be.PreguntaPaciente;
import be.RespuestaCasoSalud;
import be.RespuestaPreguntaPaciente;
import be.Seguro;
import be.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author EdHam
 */
@WebServlet(name = "servicio_usuario", urlPatterns = {"/servicio_usuario"})
public class servicio_usuario extends HttpServlet {
//     @EJB
//    private RespuestaPreguntaPacienteFacadeLocal respuestaPreguntaPacienteFacade;
//    
//    @EJB
//    private PreguntaPacienteFacadeLocal preguntaPacienteFacade;
//    
//    @EJB
//    private CitaPacienteFacadeLocal citaPacienteFacade;
//    
//    @EJB
//    private CasoSaludPuntajeFacadeLocal casoSaludPuntajeFacade;
//    
//    @EJB
//    private RespuestaCasoSaludFacadeLocal respuestaCasoSaludFacade;
//    
//    @EJB
//    private CasosSaludFacadeLocal casosSaludFacade;
//    
//    @EJB
//    private FavoritosFacadeLocal favoritosFacade;
//    
//    @EJB
//    private DetalleClinicaSeguroFacadeLocal detalleClinicaSeguroFacade;
//    
//    @EJB
//    private DetalleClinicaEspecialidadFacadeLocal detalleClinicaEspecialidadFacade;

    @EJB
    private PacienteFacadeLocal pacienteFacade;
    
    @EJB
    private PersonaFacadeLocal personaFacade;
    
    @EJB
    private UsuarioFacadeLocal usuarioFacade;
//    
//    @EJB
//    private EspecialidadFacadeLocal especialidadFacade;
//    
//    @EJB
//    private ClinicaFacadeLocal clinicaFacade;
//    
//    @EJB
//    private DoctorFacadeLocal doctorFacade;
//    
//    @EJB
//    private SeguroFacadeLocal seguroFacade;
    
    
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
            if(request.getParameter("IdServicio") != null && request.getParameter("IdServicio") != "" )
            {
                int idServicio=Integer.parseInt(request.getParameter("IdServicio"));

     if(idServicio==0 && request.getParameter("clave") != null && request.getParameter("clave") != ""
             && request.getParameter("direccion") != null && request.getParameter("direccion") != ""
             && request.getParameter("idDistrito") != null && request.getParameter("idDistrito") != ""
             && request.getParameter("dni") != null && request.getParameter("dni") != ""
             && request.getParameter("email") != null && request.getParameter("email") != ""
             && request.getParameter("telefono") != null && request.getParameter("telefono") != ""
             && request.getParameter("usuario") != null && request.getParameter("usuario") != ""
             && request.getParameter("alcohol") != null && request.getParameter("alcohol") != ""
             && request.getParameter("alergicos") != null && request.getParameter("alergicos") != ""
             && request.getParameter("cardio") != null && request.getParameter("cardio") != ""
             && request.getParameter("drogas") != null && request.getParameter("drogas") != ""
             && request.getParameter("musculares") != null && request.getParameter("musculares") != ""
             && request.getParameter("digestivos") != null && request.getParameter("digestivos") != ""
             && request.getParameter("psicologicos") != null && request.getParameter("psicologicos") != ""
             && request.getParameter("sexo") != null && request.getParameter("sexo") != ""
             && request.getParameter("tabaquismo") != null && request.getParameter("tabaquismo") != ""
             && request.getParameter("fnacimiento") != null && request.getParameter("fnacimiento") != ""
             && request.getParameter("estatura") != null && request.getParameter("estatura") != ""
             && request.getParameter("paterno") != null && request.getParameter("paterno") != ""
             && request.getParameter("materno") != null && request.getParameter("materno") != ""
             && request.getParameter("nombre") != null && request.getParameter("nombre") != "")
                {
                    Persona persona = new Persona();
                    persona.setDireccion(request.getParameter("direccion"));
                    persona.setDni(request.getParameter("dni"));
                    persona.setEmail(request.getParameter("email"));
                    persona.setTelefono(request.getParameter("telefono"));
                    persona.setSexo(Boolean.parseBoolean(request.getParameter("sexo")));
                    persona.setApellidoMaterno(request.getParameter("materno"));
                    persona.setApellidoPaterno(request.getParameter("paterno"));
                    persona.setNombre(request.getParameter("nombre"));
                    persona.setFechaNacimiento(new Date(Long.parseLong(request.getParameter("fnacimiento"))));
                    persona.setDistrito(new Distrito(Integer.parseInt(request.getParameter("idDistrito"))));
                    persona.setFechaModificacion(new Date());
                    persona.setFechaRegistro(new Date());
                    personaFacade.create(persona);
                   
                    Usuario usuario= new Usuario();
                    usuario.setEstado(0);
                    usuario.setClave(request.getParameter("clave"));
                    usuario.setUsuario(request.getParameter("usuario"));
                    usuario.setFechaModificacion(new Date());
                    usuario.setFechaRegistro(new Date());
                    usuarioFacade.create(usuario);
                    
                    Paciente entidad=new Paciente();
                    entidad.setAlcohol(Boolean.parseBoolean(request.getParameter("alcohol")));
                    entidad.setAlergicos(Boolean.parseBoolean(request.getParameter("alergicos")));
                    entidad.setCardiovascular(Boolean.parseBoolean(request.getParameter("cardio")));
                    entidad.setDrogas(Boolean.parseBoolean(request.getParameter("drogas")));
                    entidad.setMusculares(Boolean.parseBoolean(request.getParameter("musculares")));
                    entidad.setDigestivos(Boolean.parseBoolean(request.getParameter("digestivos")));
                    entidad.setPsicologicos(Boolean.parseBoolean(request.getParameter("psicologicos")));       
                    entidad.setTabaquismo(Boolean.parseBoolean(request.getParameter("tabaquismo")));        
                    entidad.setEstatura(Integer.parseInt(request.getParameter("estatura")));        
                    entidad.setUsuario(usuario);
                    entidad.setPersona(persona); 
                    entidad.setFechaModificacion(new Date());
                    entidad.setFechaRegistro(new Date());
                    pacienteFacade.create(entidad);
                    
                    out.print(entidad.getPKId()+"<parametro>"+usuario.getPKId()+"<parametro>"+persona.getPKId()+"<parametro>");

                }
                else if(idServicio==1 && request.getParameter("alcohol") != null && request.getParameter("alcohol") != ""
                       && request.getParameter("idDistrito") != null && request.getParameter("idDistrito") != ""
                        && request.getParameter("alergicos") != null && request.getParameter("alergicos") != ""
                       && request.getParameter("cardio") != null && request.getParameter("cardio") != ""
                       && request.getParameter("drogas") != null && request.getParameter("drogas") != ""
                       && request.getParameter("musculares") != null && request.getParameter("musculares") != ""
                       && request.getParameter("digestivos") != null && request.getParameter("digestivos") != ""
                       && request.getParameter("psicologicos") != null && request.getParameter("psicologicos") != ""
                       && request.getParameter("sexo") != null && request.getParameter("sexo") != ""
                       && request.getParameter("tabaquismo") != null && request.getParameter("tabaquismo") != ""
                       && request.getParameter("fnacimiento") != null && request.getParameter("fnacimiento") != ""
                       && request.getParameter("estatura") != null && request.getParameter("estatura") != ""
                       && request.getParameter("paterno") != null && request.getParameter("paterno") != ""
                       && request.getParameter("materno") != null && request.getParameter("materno") != ""
                       && request.getParameter("nombre") != null && request.getParameter("nombre") != ""
                       && request.getParameter("dni") != null && request.getParameter("dni") != ""
                       && request.getParameter("idUsuario") != null && request.getParameter("idUsuario") != "")
                {
                    Persona persona = new Persona();
                    persona.setDni(request.getParameter("dni"));
                    persona.setDistrito(new Distrito(Integer.parseInt(request.getParameter("idDistrito"))));
                    persona.setSexo(Boolean.parseBoolean(request.getParameter("sexo")));
                    persona.setApellidoMaterno(request.getParameter("materno"));
                    persona.setApellidoPaterno(request.getParameter("paterno"));
                    persona.setNombre(request.getParameter("nombre"));
                    persona.setFechaNacimiento(new Date(Long.parseLong(request.getParameter("fnacimiento"))));
                    persona.setFechaModificacion(new Date());
                    persona.setFechaRegistro(new Date());
                    personaFacade.create(persona);

                    Paciente entidad=new Paciente();
                    entidad.setAlcohol(Boolean.parseBoolean(request.getParameter("alcohol")));
                    entidad.setAlergicos(Boolean.parseBoolean(request.getParameter("alergicos")));
                    entidad.setCardiovascular(Boolean.parseBoolean(request.getParameter("cardio")));
                    entidad.setDrogas(Boolean.parseBoolean(request.getParameter("drogas")));
                    entidad.setMusculares(Boolean.parseBoolean(request.getParameter("musculares")));
                    entidad.setDigestivos(Boolean.parseBoolean(request.getParameter("digestivos")));
                    entidad.setPsicologicos(Boolean.parseBoolean(request.getParameter("psicologicos")));       
                    entidad.setTabaquismo(Boolean.parseBoolean(request.getParameter("tabaquismo")));        
                    entidad.setEstatura(Integer.parseInt(request.getParameter("estatura")));        
                    entidad.setUsuario(new Usuario(Integer.parseInt(request.getParameter("idUsuario"))));
                    entidad.setPersona(persona);
                    entidad.setFechaModificacion(new Date());
                    entidad.setFechaRegistro(new Date());
                    pacienteFacade.create(entidad);
                            
                    out.print(entidad.getPKId()+"<parametro>"+persona.getPKId()+"<parametro>");
                   
                }
                 else if(idServicio==2 && request.getParameter("alcohol") != null && request.getParameter("alcohol") != ""
                         && request.getParameter("idDistrito") != null && request.getParameter("idDistrito") != ""
                         && request.getParameter("alergicos") != null && request.getParameter("alergicos") != ""
                         && request.getParameter("cardio") != null && request.getParameter("cardio") != ""
                         && request.getParameter("drogas") != null && request.getParameter("drogas") != ""
                         && request.getParameter("musculares") != null && request.getParameter("musculares") != ""
                         && request.getParameter("digestivos") != null && request.getParameter("digestivos") != ""
                         && request.getParameter("psicologicos") != null && request.getParameter("psicologicos") != ""
                         && request.getParameter("sexo") != null && request.getParameter("sexo") != ""
                         && request.getParameter("tabaquismo") != null && request.getParameter("tabaquismo") != ""
                         && request.getParameter("fnacimiento") != null && request.getParameter("fnacimiento") != ""
                         && request.getParameter("estatura") != null && request.getParameter("estatura") != ""
                         && request.getParameter("paterno") != null && request.getParameter("paterno") != ""
                         && request.getParameter("materno") != null && request.getParameter("materno") != ""
                         && request.getParameter("nombre") != null && request.getParameter("nombre") != ""
                         && request.getParameter("idUsuario") != null && request.getParameter("idUsuario") != ""
                         && request.getParameter("idPaciente") != null && request.getParameter("idPaciente") != ""
                         && request.getParameter("idPersona") != null && request.getParameter("idPersona") != "")
                {
                    Paciente entidad=pacienteFacade.find(Integer.parseInt(request.getParameter("idPaciente")));
                    
                    Persona persona = entidad.getPersona();
                    persona.setDistrito(new Distrito(Integer.parseInt(request.getParameter("idDistrito"))));
                    persona.setSexo(Boolean.parseBoolean(request.getParameter("sexo")));
                    persona.setApellidoMaterno(request.getParameter("materno"));
                    persona.setApellidoPaterno(request.getParameter("paterno"));
                    persona.setNombre(request.getParameter("nombre"));
                    persona.setFechaNacimiento(new Date(Long.parseLong(request.getParameter("fnacimiento"))));
                    persona.setFechaModificacion(new Date());
                    personaFacade.edit(persona);
                    
                    entidad.setAlcohol(Boolean.parseBoolean(request.getParameter("alcohol")));
                    entidad.setAlergicos(Boolean.parseBoolean(request.getParameter("alergicos")));
                    entidad.setCardiovascular(Boolean.parseBoolean(request.getParameter("cardio")));
                    entidad.setDrogas(Boolean.parseBoolean(request.getParameter("drogas")));
                    entidad.setMusculares(Boolean.parseBoolean(request.getParameter("musculares")));
                    entidad.setDigestivos(Boolean.parseBoolean(request.getParameter("digestivos")));
                    entidad.setPsicologicos(Boolean.parseBoolean(request.getParameter("psicologicos")));        
                    entidad.setTabaquismo(Boolean.parseBoolean(request.getParameter("tabaquismo")));        
                    entidad.setEstatura(Integer.parseInt(request.getParameter("estatura")));       
                    entidad.setUsuario(new Usuario(Integer.parseInt(request.getParameter("idUsuario"))));
                    entidad.setPersona(persona);
                    entidad.setFechaModificacion(new Date());
                    pacienteFacade.edit(entidad);
                    out.print("1");
                   
                }
                 else if(idServicio==3 && request.getParameter("usuario") != null && request.getParameter("usuario") != ""
                         && request.getParameter("clave") != null && request.getParameter("clave") != "")
                {
                    Usuario objUsuario=usuarioFacade.login(request.getParameter("usuario"), request.getParameter("clave"));
                    if(objUsuario!=null)
                    {
                        String dato=""+objUsuario.getPKId()+"<parametro>"
                                +objUsuario.getPersona().getNombre()+"<parametro>"
                                +objUsuario.getPersona().getApellidoPaterno()+"<parametro>"
                                +objUsuario.getPersona().getApellidoMaterno()+"<parametro>"
                                +objUsuario.getPersona().getEmail()+"<parametro>"
                                +objUsuario.getPersona().getEmail()+"<parametro>"
                                +objUsuario.getPersona().getSexo()+"<parametro>"
                                +objUsuario.getPersona().getDireccion()+"<parametro>"
                                +objUsuario.getPersona().getTelefono()+"<parametro>"
                                +objUsuario.getPersona().getPKId()+"<parametro>";
                                if(objUsuario.getPersona().getFoto()==null)
                                dato+="0<parametro>";
                                else
                                     dato+=Utilidades.getEncodeBase64(objUsuario.getPersona().getFoto())+"<parametro>";

                                if(objUsuario.getPacienteList().size()>0)
                                {
                                    for(Paciente objPaciente : objUsuario.getPacienteList())
                                        dato+=objPaciente.getPKId()+"<atributo>"
                                                +objPaciente.getPersona().getNombre()+"<atributo>"
                                                +objPaciente.getPersona().getApellidoPaterno()+"<atributo>"
                                                +objPaciente.getPersona().getApellidoMaterno()+"<atributo>"
                                                +objPaciente.getPersona().getDni()+"<atributo>"
                                                +objPaciente.getPersona().getFechaNacimiento().getTime()+"<atributo>"
                                                +objPaciente.getPersona().getSexo()+"<atributo>"
                                                +objPaciente.getTipo()+"<atributo>"
                                                +objPaciente.getEstatura()+"<atributo>"
                                                +objPaciente.getCardiovascular()+"<atributo>"
                                                +objPaciente.getMusculares()+"<atributo>"
                                                +objPaciente.getDigestivos()+"<atributo>"
                                                +objPaciente.getAlergicos()+"<atributo>"
                                                +objPaciente.getAlcohol()+"<atributo>"
                                                +objPaciente.getTabaquismo()+"<atributo>"
                                                +objPaciente.getDrogas()+"<atributo>"
                                                +objPaciente.getPsicologicos()+"<atributo>"
                                                +objPaciente.getEstado()+"<atributo>"
                                                +objPaciente.getPersona().getPKId()+"<atributo>"
                                                +objUsuario.getPKId()+"<atributo><entidad>";

                                        dato+="<parametro>";    
                                }
                                else
                                    dato+="0<parametro>";
                                
                                 List<Favoritos> listFavoritos=objUsuario.getFavoritosList();
                                if(listFavoritos.size()>0)
                                {
                                    for(Favoritos objFavoritos : listFavoritos)
                                        dato+=objFavoritos.getDoctor().getPKId()+"<entidad>";          

                                       dato+="<parametro>";      
                                }
                                else
                                     dato+="0<parametro>";


//                                List<Especialidad> listEspecialidad=especialidadFacade.lista_activos();
//                               if(listEspecialidad.size()>0)
//                               {
//                                   for(Especialidad objEspecialidad : listEspecialidad)
//                                       dato+=objEspecialidad.getPKId()+"<atributo>"
//                                               +objEspecialidad.getNombre()+"<atributo>"
//                                               +objEspecialidad.getDescripcion()+"<atributo>"
//                                               +objEspecialidad.getEstado()+"<atributo><entidad>";
//
//                                      dato+="<parametro>";      
//                               }
//                               else
//                                    dato+="0<parametro>";                   
//
//
//                               List<Clinica> listClinica=clinicaFacade.lista_activos();
//                               if(listClinica.size()>0)
//                               {
//                                   for(Clinica objClinica : listClinica)
//                                   {
//                                       dato+=objClinica.getPKId()+"<atributo>"
//                                               +objClinica.getNombre()+"<atributo>"
//                                               +objClinica.getSlogan()+"<atributo>"
//                                               +objClinica.getDireccion()+"<atributo>"
//                                               +objClinica.getDescripcion()+"<atributo>"
//                                               +objClinica.getHorarioInicio().getTime()+"<atributo>"
//                                               +objClinica.getHorarioFin().getTime()+"<atributo>"
//                                               +objClinica.getDetalleAtencion()+"<atributo>";
//                                                if(objClinica.getLogo()==null)
//                                                dato+="0<atributo>";
//                                                else
//                                                     dato+=Utilidades.getEncodeBase64(objClinica.getLogo())+"<atributo>";
//                                               dato+=objClinica.getTelefono()+"<atributo>"
//                                               +objClinica.getDistrito().getPKId()+"<atributo>"
//                                               +objClinica.getLongitud()+"<atributo>"
//                                               +objClinica.getLatitud()+"<atributo>"
//                                               +objClinica.getEstado()+"<atributo><entidad>";
//                                   }
//
//
//                                      dato+="<parametro>";      
//                               }
//                               else
//                                    dato+="0<parametro>";
//
//
//                               List<Doctor> listDoctor=doctorFacade.lista_activos();
//                               if(listDoctor.size()>0)
//                               {
//                                   for(Doctor objDoctor : listDoctor)
//                                   {
//                                       dato+=objDoctor.getPKId()+"<atributo>"
//                                               +objDoctor.getPersona().getNombre()+"<atributo>"
//                                               +objDoctor.getPersona().getApellidoPaterno()+"<atributo>"
//                                               +objDoctor.getPersona().getApellidoMaterno()+"<atributo>"
//                                               +objDoctor.getPersona().getDni()+"<atributo>"
//                                               +objDoctor.getCodigoColegiatura()+"<atributo>"
//                                               +objDoctor.getDireccion()+"<atributo>"
//                                               +objDoctor.getDireccionDetalle()+"<atributo>"
//                                               +objDoctor.getTelefono()+"<atributo>"
//                                               +objDoctor.getLongitud()+"<atributo>"
//                                               +objDoctor.getLatitud()+"<atributo>"
//                                               +objDoctor.getPuntaje()+"<atributo>"
//                                               +objDoctor.getEspecialidad().getPKId()+"<atributo>";
//                                                if(objDoctor.getPersona().getFoto()==null)
//                                                dato+="0<atributo><entidad>";
//                                                else
//                                                     dato+=Utilidades.getEncodeBase64(objDoctor.getPersona().getFoto())+"<atributo><entidad>";          
//                                   }
//
//
//                                      dato+="<parametro>";      
//                               }
//                               else
//                                    dato+="0<parametro>";
//
//
//                               List<DetalleClinicaEspecialidad> listDetalleClinicaEspecialidad=detalleClinicaEspecialidadFacade.lista_activos();
//                               if(listDetalleClinicaEspecialidad.size()>0)
//                               {
//                                   for(DetalleClinicaEspecialidad objDetalleClinicaEspecialidad : listDetalleClinicaEspecialidad)
//                                       dato+=objDetalleClinicaEspecialidad.getPKId()+"<atributo>"       
//                                               +objDetalleClinicaEspecialidad.getClinica().getPKId()+"<atributo>"
//                                               +objDetalleClinicaEspecialidad.getEspecialidad().getPKId()+"<atributo>"
//                                               +objDetalleClinicaEspecialidad.getHorarioInicio().getTime()+"<atributo>"
//                                               +objDetalleClinicaEspecialidad.getHorarioFin().getTime()+"<atributo>"
//                                               +objDetalleClinicaEspecialidad.getDetalleHorario()+"<atributo>"
//                                               +objDetalleClinicaEspecialidad.getEstado()+"<atributo><entidad>";
//
//
//                                      dato+="<parametro>";      
//                               }
//                               else
//                                    dato+="0<parametro>";
//
//
//
//
//                               List<Seguro> listSeguro=seguroFacade.lista_activos();
//                               if(listSeguro.size()>0)
//                               {
//                                   for(Seguro objSeguro : listSeguro)
//                                     {
//                                       dato+=objSeguro.getPKId()+"<atributo>"
//                                               +objSeguro.getNombre()+"<atributo>"
//                                               +objSeguro.getEstado()+"<atributo>";
//                                                if(objSeguro.getLogo()==null)
//                                                dato+="0<atributo><entidad>";
//                                                else
//                                                     dato+=Utilidades.getEncodeBase64(objSeguro.getLogo())+"<atributo><entidad>";          
//                                   }
//
//
//
//                                      dato+="<parametro>";      
//                               }
//                               else
//                                    dato+="0<parametro>";
//
//
//                               List<DetalleClinicaSeguro> listDetalleClinicaSeguro=detalleClinicaSeguroFacade.lista_activos();
//                               if(listSeguro.size()>0)
//                               {
//                                   for(DetalleClinicaSeguro objDetalleClinicaSeguro : listDetalleClinicaSeguro)
//                                       dato+=objDetalleClinicaSeguro.getPKId()+"<atributo>"
//                                               +objDetalleClinicaSeguro.getClinica().getPKId()+"<atributo>"
//                                               +objDetalleClinicaSeguro.getSeguro().getPKId()+"<atributo>"
//                                               +objDetalleClinicaSeguro.getEstado()+"<atributo><entidad>";          
//
//                                      dato+="<parametro>";      
//                               }
//                               else
//                                    dato+="0<parametro>";
//
//                               List<Favoritos> listFavoritos=favoritosFacade.listaXUsuarios(objUsuario);
//                               if(listFavoritos.size()>0)
//                               {
//                                   for(Favoritos objFavoritos : listFavoritos)
//                                       dato+=objFavoritos.getDoctor().getPKId()+"<entidad>";          
//
//                                      dato+="<parametro>";      
//                               }
//                               else
//                                    dato+="0<parametro>";
//
//
//                               List<CasosSalud> listCasosSalud=casosSaludFacade.lista_activos();
//                               if(listCasosSalud.size()>0)
//                               {
//                                   for(CasosSalud objCasosSalud : listCasosSalud)
//                                       dato+=objCasosSalud.getPKId()+"<atributo>"
//                                               +objCasosSalud.getTema()+"<atributo>"
//                                               +objCasosSalud.getFechaInicio().getTime()+"<atributo>"
//                                               +objCasosSalud.getFechaFin().getTime()+"<atributo><entidad>";                  
//
//                                      dato+="<parametro>";      
//                               }
//                               else
//                                    dato+="0<parametro>";
//
//
//                               List<RespuestaCasoSalud> listRespuestaCasosSalud=respuestaCasoSaludFacade.lista_activos();
//                               if(listRespuestaCasosSalud.size()>0)
//                               {
//                                   for(RespuestaCasoSalud objRespuestaCasosSalud : listRespuestaCasosSalud)
//                                       dato+=objRespuestaCasosSalud.getPKId()+"<atributo>"
//                                               +objRespuestaCasosSalud.getDoctor().getPKId()+"<atributo>"
//                                               +objRespuestaCasosSalud.getCasosSalud().getPKId()+"<atributo>"
//                                               +objRespuestaCasosSalud.getDescripcion()+"<atributo>"
//                                               +objRespuestaCasosSalud.getPuntajeTotal()+"<atributo><entidad>";       
//
//                                      dato+="<parametro>";      
//                               }
//                               else
//                                    dato+="0<parametro>";

//
//                               List<CasoSaludPuntaje> listRespuestaCasosSaludVotos=casoSaludPuntajeFacade.listaXUsuarios(objUsuario);
//                               if(listRespuestaCasosSaludVotos.size()>0)
//                               {
//                                   for(CasoSaludPuntaje objRespuestaCasosSalud : listRespuestaCasosSaludVotos)
//                                       dato+=objRespuestaCasosSalud.getRespuestaCasoSalud().getPKId()+"<entidad>";          
//
//                                      dato+="<parametro>";      
//                               }
//                               else
//                                    dato+="0<parametro>";


//                               List<CitaPaciente> listCitaPaciente=citaPacienteFacade.listaXUsuarios(objUsuario);
//                               if(listCitaPaciente.size()>0)
//                               {
//                                   for(CitaPaciente objCitaPaciente : listCitaPaciente)
//                                        dato+=objCitaPaciente.getPKId()+"<atributo>"
//                                               +objCitaPaciente.getDoctor().getPKId()+"<atributo>"
//                                               +objCitaPaciente.getPaciente().getPKId()+"<atributo>"
//                                               +objCitaPaciente.getDetalle()+"<atributo>"
//                                               +objCitaPaciente.getFechaRegistro().getTime()+"<atributo>"
//                                               +objCitaPaciente.getAtencion().getTime()+"<atributo>"
//                                               +objCitaPaciente.getEstado()+"<atributo><entidad>";        
//
//                                      dato+="<parametro>";      
//                               }
//                               else
//                                    dato+="0<parametro>";
//
//                               List<PreguntaPaciente> listPreguntaPaciente=preguntaPacienteFacade.listaXUsuarios(objUsuario);
//                               if(listPreguntaPaciente.size()>0)
//                               {
//                                   for(PreguntaPaciente objPreguntaPaciente : listPreguntaPaciente)
//                                        dato+=objPreguntaPaciente.getPKId()+"<atributo>"
//                                               +objPreguntaPaciente.getPaciente().getPKId()+"<atributo>"
//                                               +objPreguntaPaciente.getEspecialidad().getPKId()+"<atributo>"
//                                               +objPreguntaPaciente.getAsunto()+"<atributo>"
//                                               +objPreguntaPaciente.getDetalle()+"<atributo>"
//                                               +objPreguntaPaciente.getEstado()+"<atributo>"
//                                               +objPreguntaPaciente.getFechaInicio().getTime()+"<atributo><entidad>";        
//
//                                      dato+="<parametro>";      
//                               }
//                               else
//                                    dato+="0<parametro>";
//
//
//                               List<RespuestaPreguntaPaciente> listRespuestaPreguntaPaciente=respuestaPreguntaPacienteFacade.listaXUsuarios(objUsuario);
//                               if(listRespuestaPreguntaPaciente.size()>0)
//                               {
//                                   for(RespuestaPreguntaPaciente objRespuestaPreguntaPaciente : listRespuestaPreguntaPaciente)
//                                        dato+=objRespuestaPreguntaPaciente.getPKId()+"<atributo>"
//                                               +objRespuestaPreguntaPaciente.getPreguntaPaciente().getPKId()+"<atributo>"
//                                               +objRespuestaPreguntaPaciente.getDoctor().getPKId()+"<atributo>"
//                                               +objRespuestaPreguntaPaciente.getDetalle()+"<atributo>"
//                                               +objRespuestaPreguntaPaciente.getPuntaje()+"<atributo>"
//                                               +objRespuestaPreguntaPaciente.getFechaRegistro().getTime()+"<atributo><entidad>";        
//
//                                      dato+="<parametro>";      
//                               }
//                               else
//                                    dato+="0<parametro>";
//





                                    out.print(dato);
                    }
                    else
                        out.print("0");
                }

            }
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
