/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import Utilidades.Utilidades;
import fachada.CasosSaludFacadeLocal;
import fachada.CitaPacienteFacadeLocal;
import fachada.DoctorFacadeLocal;
import fachada.PreguntaPacienteFacadeLocal;
import fachada.RespuestaCasoSaludFacadeLocal;
import fachada.RespuestaPreguntaPacienteFacadeLocal;
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
import modelo.CasosSalud;
import modelo.CitaPaciente;
import modelo.Paciente;
import modelo.PreguntaPaciente;
import modelo.RespuestaCasoSalud;
import modelo.RespuestaPreguntaPaciente;
import org.json.JSONArray;
import org.json.JSONObject;
import util.ParametroNodo;

/**
 *
 * @author Edham
 */
@WebServlet(name = "servicio_doctor", urlPatterns = {"/servicio_doctor"})
public class servicio_doctor extends HttpServlet {

    @EJB
    private CasosSaludFacadeLocal casosSaludFacade;
    @EJB
    private DoctorFacadeLocal doctorFacade;
    @EJB
    private CitaPacienteFacadeLocal citaPacienteFacade;
    @EJB
    private RespuestaPreguntaPacienteFacadeLocal respuestaPreguntaPacienteFacade;
    @EJB
    private PreguntaPacienteFacadeLocal preguntaPacienteFacade;
    @EJB
    private RespuestaCasoSaludFacadeLocal respuestaCasoSaludFacade;

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
        PrintWriter out = response.getWriter();
        try {
            JSONObject obj = new JSONObject();
            if (request.getParameter("IdServicio") != null && request.getParameter("IdServicio") != "") {
                int idServicio = Integer.parseInt(request.getParameter("IdServicio"));

                if (idServicio == 0 && request.getParameter("usuario") != null && request.getParameter("usuario") != ""
                        && request.getParameter("clave") != null && request.getParameter("clave") != "") {

                    modelo.Doctor objDoctor = doctorFacade.login(request.getParameter("usuario"), request.getParameter("clave"));
                    if (objDoctor != null) {
                        List<modelo.Especialidad> listaEspecialidad = new ArrayList<modelo.Especialidad>();
                        listaEspecialidad.add(objDoctor.getEspecialidad());
                        JSONObject doctorJSON = new JSONObject();
                        doctorJSON.put("doctorId", objDoctor.getPKId());
                        doctorJSON.put("personaNombre", objDoctor.getPersona().getNombre());
                        doctorJSON.put("personaApellidoPaterno", objDoctor.getPersona().getApellidoPaterno());
                        doctorJSON.put("personaApellidoMaterno", objDoctor.getPersona().getApellidoMaterno());
                        doctorJSON.put("personaDni", objDoctor.getPersona().getDni());
                        doctorJSON.put("doctorCodigoColegiatura", objDoctor.getCodigoColegiatura());
                        doctorJSON.put("doctorDireccion", objDoctor.getDireccion());
                        doctorJSON.put("doctorDireccionDetalle", objDoctor.getDireccionDetalle());
                        doctorJSON.put("doctorTelefono", objDoctor.getTelefono());
                        doctorJSON.put("doctorLongitud", objDoctor.getLongitud());
                        doctorJSON.put("doctorLatitud", objDoctor.getLatitud());
                        doctorJSON.put("doctorEspecialidadId", objDoctor.getEspecialidad().getPKId());
                        doctorJSON.put("doctorPuntaje", objDoctor.getPuntaje());
                        if (objDoctor.getPersona().getFoto() == null) {
                            doctorJSON.put("personaFoto", "");
                        } else {
                            doctorJSON.put("personaFoto", Utilidades.getEncodeBase64(objDoctor.getPersona().getFoto()));
                        }
                        obj.put("doctorJSON", doctorJSON);

                        List<PreguntaPaciente> listPreguntaPaciente = new ArrayList<PreguntaPaciente>();
                        List<Paciente> listaPaciente = new ArrayList<Paciente>();

                        JSONArray listCitaPacienteJSON = new JSONArray();
                        for (CitaPaciente objCitaPaciente : citaPacienteFacade.listarXObjeto("doctor", objDoctor, true, "pKId", true)) {
                            listaPaciente.add(objCitaPaciente.getPaciente());
                            JSONObject entidadJSON = new JSONObject();
                            entidadJSON.put("citaPacienteId", objCitaPaciente.getPKId());
                            entidadJSON.put("doctorId", objCitaPaciente.getDoctor().getPKId());
                            entidadJSON.put("pacienteId", objCitaPaciente.getPaciente().getPKId());
                            entidadJSON.put("citaPacienteDetalle", objCitaPaciente.getDetalle());
                            entidadJSON.put("citaPacienteFechaRegistro", objCitaPaciente.getFechaRegistro().getTime());
                            entidadJSON.put("citaPacienteAtencion", objCitaPaciente.getAtencion().getTime());
                            entidadJSON.put("citaPacienteEstado", objCitaPaciente.getTipo());
                            entidadJSON.put("citaPacienteRespuesta", objCitaPaciente.getRespuestaDoctor());
                            listCitaPacienteJSON.put(entidadJSON);
                        }
                        obj.put("listCitaPacienteJSON", listCitaPacienteJSON);

                        JSONArray listRespuestaPreguntaPacienteJSON = new JSONArray();
                        for (RespuestaPreguntaPaciente objRespuestaPreguntaPaciente : respuestaPreguntaPacienteFacade.listarXObjeto("doctor", objDoctor, true, "pKId", true)) {
                            listaPaciente.add(objRespuestaPreguntaPaciente.getPreguntaPaciente().getPaciente());
                            listPreguntaPaciente.add(objRespuestaPreguntaPaciente.getPreguntaPaciente());
                            JSONObject entidadJSON = new JSONObject();
                            entidadJSON.put("respuestaPreguntaPacienteId", objRespuestaPreguntaPaciente.getPKId());
                            entidadJSON.put("preguntaPacienteId", objRespuestaPreguntaPaciente.getPreguntaPaciente().getPKId());
                            entidadJSON.put("doctorId", objRespuestaPreguntaPaciente.getDoctor().getPKId());
                            entidadJSON.put("respuestaPreguntaPacienteDetalle", objRespuestaPreguntaPaciente.getDetalle());
                            entidadJSON.put("respuestaPreguntaPacientePuntaje", objRespuestaPreguntaPaciente.getPuntaje());
                            entidadJSON.put("respuestaPreguntaPacienteFechaRegistro", objRespuestaPreguntaPaciente.getFechaRegistro().getTime());
                            listRespuestaPreguntaPacienteJSON.put(entidadJSON);
                        }
                        obj.put("listRespuestaPreguntaPacienteJSON", listRespuestaPreguntaPacienteJSON);

                        listPreguntaPaciente = Utilidades.clearListPreguntaPaciente(listPreguntaPaciente);
                        JSONArray listPreguntaPacienteJSON = new JSONArray();
                        for (PreguntaPaciente objPreguntaPaciente : listPreguntaPaciente) {
                            listaEspecialidad.add(objPreguntaPaciente.getEspecialidad());
                            JSONObject entidadJSON = new JSONObject();
                            entidadJSON.put("preguntaPacienteId", objPreguntaPaciente.getPKId());
                            entidadJSON.put("pacienteId", objPreguntaPaciente.getPaciente().getPKId());
                            entidadJSON.put("especialidadId", objPreguntaPaciente.getEspecialidad().getPKId());
                            entidadJSON.put("preguntaPacienteAsunto", objPreguntaPaciente.getAsunto());
                            entidadJSON.put("preguntaPacienteDetalle", objPreguntaPaciente.getDetalle());
                            entidadJSON.put("preguntaPacienteEstado", objPreguntaPaciente.getTipo());
                            entidadJSON.put("preguntaPacienteFechaInicio", objPreguntaPaciente.getFechaInicio().getTime());
                            listPreguntaPacienteJSON.put(entidadJSON);
                        }
                        obj.put("listPreguntaPacienteJSON", listPreguntaPacienteJSON);

                        List<RespuestaCasoSalud> listRespuestaCasosSalud = new ArrayList<RespuestaCasoSalud>();
                        JSONArray listCasosSaludJSON = new JSONArray();
                        for (CasosSalud objCasosSalud : casosSaludFacade.lista_activos()) {
                            listRespuestaCasosSalud.addAll(respuestaCasoSaludFacade.listarXObjeto("doctor", objDoctor, true, "pKId", false));
                            JSONObject entidadJSON = new JSONObject();
                            entidadJSON.put("casosSaludId", objCasosSalud.getPKId());
                            entidadJSON.put("casosSaludTema", objCasosSalud.getTema());
                            entidadJSON.put("casosSaludFechaInicio", objCasosSalud.getFechaInicio().getTime());
                            entidadJSON.put("casosSaludFechaFin", objCasosSalud.getFechaFin().getTime());
                            listCasosSaludJSON.put(entidadJSON);
                        }
                        obj.put("listCasosSaludJSON", listCasosSaludJSON);

                        listRespuestaCasosSalud = Utilidades.clearListRespuestaCasoSalud(listRespuestaCasosSalud);
                        JSONArray listRespuestaCasosSaludJSON = new JSONArray();
                        for (RespuestaCasoSalud objRespuestaCasosSalud : listRespuestaCasosSalud) {
                            JSONObject entidadJSON = new JSONObject();
                            entidadJSON.put("respuestaCasosSaludId", objRespuestaCasosSalud.getPKId());
                            entidadJSON.put("doctorId", objRespuestaCasosSalud.getDoctor().getPKId());
                            entidadJSON.put("casosSaludId", objRespuestaCasosSalud.getCasosSalud().getPKId());
                            entidadJSON.put("respuestaCasosSaludDescripcion", objRespuestaCasosSalud.getDescripcion());
                            entidadJSON.put("respuestaCasosSaludPuntajeTotal", objRespuestaCasosSalud.getPuntajeTotal());
                            listRespuestaCasosSaludJSON.put(entidadJSON);
                        }
                        obj.put("listRespuestaCasosSaludJSON", listRespuestaCasosSaludJSON);

                        listaPaciente = Utilidades.clearListPaciente(listaPaciente);
                        JSONArray listPacienteJSON = new JSONArray();
                        for (Paciente objPaciente : listaPaciente) {
                            JSONObject entidadJSON = new JSONObject();
                            entidadJSON.put("pacienteId", objPaciente.getPKId());
                            entidadJSON.put("personaId", objPaciente.getPersona().getPKId());
                            entidadJSON.put("personaNombre", objPaciente.getPersona().getNombre());
                            entidadJSON.put("personaApellidoPaterno", objPaciente.getPersona().getApellidoPaterno());
                            entidadJSON.put("personaApellidoMaterno", objPaciente.getPersona().getApellidoMaterno());
                            entidadJSON.put("personaDni", objPaciente.getPersona().getDni());
                            entidadJSON.put("personaFechaNacimiento", objPaciente.getPersona().getFechaNacimiento().getTime());
                            entidadJSON.put("personaSexo", objPaciente.getPersona().getSexo());
                            // entidadJSON.put("pacienteTipo", objPaciente.getTipo());
                            entidadJSON.put("pacienteEstatura", objPaciente.getEstatura());
                            entidadJSON.put("pacienteCardiovascular", objPaciente.getCardiovascular());
                            entidadJSON.put("pacienteMusculares", objPaciente.getMusculares());
                            entidadJSON.put("pacienteDigestivos", objPaciente.getDigestivos());
                            entidadJSON.put("pacienteAlergicos", objPaciente.getAlergicos());
                            entidadJSON.put("pacienteAlcohol", objPaciente.getAlcohol());
                            entidadJSON.put("pacienteTabaquismo", objPaciente.getTabaquismo());
                            entidadJSON.put("pacienteDrogas", objPaciente.getDrogas());
                            entidadJSON.put("pacientePsicologicos", objPaciente.getPsicologicos());
                            // entidadJSON.put("pacienteEstado", objPaciente.getEstado());
                            entidadJSON.put("usuarioId", objPaciente.getUsuario().getPKId());
                            listPacienteJSON.put(entidadJSON);
                        }
                        obj.put("listPacienteJSON", listPacienteJSON);

                        listaEspecialidad = Utilidades.clearListEspecialidad(listaEspecialidad);
                        JSONArray listEspecialidadJSON = new JSONArray();
                        for (modelo.Especialidad objEspecialidad : listaEspecialidad) {
                            JSONObject entidadJSON = new JSONObject();
                            entidadJSON.put("especialidadId", objEspecialidad.getPKId());
                            entidadJSON.put("especialidadNombre", objEspecialidad.getNombre());
                            entidadJSON.put("especialidadDescripcion", objEspecialidad.getDescripcion());
                            listEspecialidadJSON.put(entidadJSON);
                        }
                        obj.put("listEspecialidadJSON", listEspecialidadJSON);

                        obj.put("rpta", 1);

                    } else {
                        obj.put("rpta", 0);
                    }

                } else if (idServicio == 1 && request.getParameter("IdDoctor") != null && request.getParameter("IdDoctor") != "") {
                    modelo.Doctor doctor = doctorFacade.find(Integer.parseInt(request.getParameter("IdDoctor")));
                    if (doctor != null) {
                        List<Paciente> listaPaciente = new ArrayList<Paciente>();

                        JSONArray listPreguntaPacienteJSON = new JSONArray();
                        for (PreguntaPaciente objPreguntaPaciente : preguntaPacienteFacade.listaXEspecialidadTipo(doctor.getEspecialidad(), 1)) {
                            JSONObject entidadJSON = new JSONObject();
                            listaPaciente.add(objPreguntaPaciente.getPaciente());
                            entidadJSON.put("preguntaPacienteId", objPreguntaPaciente.getPKId());
                            entidadJSON.put("pacienteId", objPreguntaPaciente.getPaciente().getPKId());
                            entidadJSON.put("especialidadId", objPreguntaPaciente.getEspecialidad().getPKId());
                            entidadJSON.put("preguntaPacienteAsunto", objPreguntaPaciente.getAsunto());
                            entidadJSON.put("preguntaPacienteDetalle", objPreguntaPaciente.getDetalle());
                            entidadJSON.put("preguntaPacienteEstado", objPreguntaPaciente.getTipo());
                            entidadJSON.put("preguntaPacienteFechaInicio", objPreguntaPaciente.getFechaInicio().getTime());
                            if (objPreguntaPaciente.getImagen() == null) {
                                entidadJSON.put("preguntaPacienteImagen", "");
                            } else {
                                entidadJSON.put("preguntaPacienteImagen", Utilidades.getEncodeBase64(objPreguntaPaciente.getImagen()));
                            }
                            listPreguntaPacienteJSON.put(entidadJSON);
                        }
                        obj.put("listPreguntaPacienteJSON", listPreguntaPacienteJSON);

                        JSONArray listCitaPacienteJSON = new JSONArray();
                        for (CitaPaciente objCitaPaciente : citaPacienteFacade.listaXDoctorTipo(doctor, 0)) {
                            listaPaciente.add(objCitaPaciente.getPaciente());
                            JSONObject entidadJSON = new JSONObject();
                            entidadJSON.put("citaPacienteId", objCitaPaciente.getPKId());
                            entidadJSON.put("doctorId", objCitaPaciente.getDoctor().getPKId());
                            entidadJSON.put("pacienteId", objCitaPaciente.getPaciente().getPKId());
                            entidadJSON.put("citaPacienteDetalle", objCitaPaciente.getDetalle());
                            entidadJSON.put("citaPacienteFechaRegistro", objCitaPaciente.getFechaRegistro().getTime());
                            entidadJSON.put("citaPacienteAtencion", objCitaPaciente.getAtencion().getTime());
                            entidadJSON.put("citaPacienteEstado", objCitaPaciente.getTipo());
                            entidadJSON.put("citaPacienteRespuesta", objCitaPaciente.getRespuestaDoctor());
                            listCitaPacienteJSON.put(entidadJSON);
                        }
                        obj.put("listCitaPacienteJSON", listCitaPacienteJSON);

                        listaPaciente = Utilidades.clearListPaciente(listaPaciente);
                        JSONArray listPacienteJSON = new JSONArray();
                        for (Paciente objPaciente : listaPaciente) {
                            JSONObject entidadJSON = new JSONObject();
                            entidadJSON.put("pacienteId", objPaciente.getPKId());
                            entidadJSON.put("personaId", objPaciente.getPersona().getPKId());
                            entidadJSON.put("personaNombre", objPaciente.getPersona().getNombre());
                            entidadJSON.put("personaApellidoPaterno", objPaciente.getPersona().getApellidoPaterno());
                            entidadJSON.put("personaApellidoMaterno", objPaciente.getPersona().getApellidoMaterno());
                            entidadJSON.put("personaDni", objPaciente.getPersona().getDni());
                            entidadJSON.put("personaFechaNacimiento", objPaciente.getPersona().getFechaNacimiento().getTime());
                            entidadJSON.put("personaSexo", objPaciente.getPersona().getSexo());
                            // entidadJSON.put("pacienteTipo", objPaciente.getTipo());
                            entidadJSON.put("pacienteEstatura", objPaciente.getEstatura());
                            entidadJSON.put("pacienteCardiovascular", objPaciente.getCardiovascular());
                            entidadJSON.put("pacienteMusculares", objPaciente.getMusculares());
                            entidadJSON.put("pacienteDigestivos", objPaciente.getDigestivos());
                            entidadJSON.put("pacienteAlergicos", objPaciente.getAlergicos());
                            entidadJSON.put("pacienteAlcohol", objPaciente.getAlcohol());
                            entidadJSON.put("pacienteTabaquismo", objPaciente.getTabaquismo());
                            entidadJSON.put("pacienteDrogas", objPaciente.getDrogas());
                            entidadJSON.put("pacientePsicologicos", objPaciente.getPsicologicos());
                            // entidadJSON.put("pacienteEstado", objPaciente.getEstado());
                            entidadJSON.put("usuarioId", objPaciente.getUsuario().getPKId());
                            listPacienteJSON.put(entidadJSON);
                        }
                        obj.put("listPacienteJSON", listPacienteJSON);
                        JSONArray listCasosSaludJSON = new JSONArray();
                        for (CasosSalud objCasosSalud : casosSaludFacade.lista_activos()) {
                            JSONObject entidadJSON = new JSONObject();
                            entidadJSON.put("casosSaludId", objCasosSalud.getPKId());
                            entidadJSON.put("casosSaludTema", objCasosSalud.getTema());
                            entidadJSON.put("casosSaludFechaInicio", objCasosSalud.getFechaInicio().getTime());
                            entidadJSON.put("casosSaludFechaFin", objCasosSalud.getFechaFin().getTime());
                            listCasosSaludJSON.put(entidadJSON);
                        }
                        obj.put("listCasosSaludJSON", listCasosSaludJSON);

                        obj.put("rpta", 1);
                    } else {
                        obj.put("rpta", 0);
                    }

                } else if (idServicio == 2 && request.getParameter("IdDoctor") != null && request.getParameter("IdDoctor") != ""
                        && request.getParameter("IdPregunta") != null && request.getParameter("IdPregunta") != ""
                        && request.getParameter("respuesta") != null && request.getParameter("respuesta") != "") {

                    PreguntaPaciente preguntaPaciente = preguntaPacienteFacade.find(Integer.parseInt(request.getParameter("IdPregunta")));
                    if (preguntaPaciente != null) {
                        int total = preguntaPaciente.getRespuestaPreguntaPacienteList().size();
//                        if(total<3)
//                        {
                        RespuestaPreguntaPaciente respuestaPreguntaPaciente = new RespuestaPreguntaPaciente();
                        respuestaPreguntaPaciente.setPreguntaPaciente(preguntaPaciente);
                        respuestaPreguntaPaciente.setDoctor(new modelo.Doctor(Integer.parseInt(request.getParameter("IdDoctor"))));
                        respuestaPreguntaPaciente.setEstado(1);
                        respuestaPreguntaPaciente.setFechaRegistro(new Date());
                        respuestaPreguntaPaciente.setFechaModificacion(new Date());
                        respuestaPreguntaPaciente.setPuntaje(0);
                        respuestaPreguntaPaciente.setDetalle(request.getParameter("respuesta"));
                        respuestaPreguntaPacienteFacade.create(respuestaPreguntaPaciente);
                        if (total == 2) {
                            preguntaPaciente.setFechaFin(new Date());
                            preguntaPaciente.setTipo(2);
                            preguntaPacienteFacade.edit(preguntaPaciente);
                        }
                        obj.put("rpta", 1);
                        obj.put("respuestaPreguntaPacienteId", respuestaPreguntaPaciente.getPKId());
                    } else {
                        obj.put("rpta", 0);
                    }
//                    }else
//                    {
//                        obj.put("rpta", 2);
//                    }
                } else if (idServicio == 3 && request.getParameter("IdCita") != null && request.getParameter("IdCita") != ""
                        && request.getParameter("tipo") != null && request.getParameter("tipo") != "") {

                    CitaPaciente citaPaciente = citaPacienteFacade.find(Integer.parseInt(request.getParameter("IdCita")));
                    if (citaPaciente != null) {
                        citaPaciente.setTipo(Integer.parseInt(request.getParameter("tipo")));
                        citaPaciente.setFechaModificacion(new Date());
                        
                        if(citaPaciente.getTipo()==1 && request.getParameter("fecha") != null && request.getParameter("fecha") != "")                        
                            citaPaciente.setAtencion(new Date(Long.parseLong(request.getParameter("fecha"))));
                        if(request.getParameter("comentario") != null && request.getParameter("comentario") != "")
                            citaPaciente.setRespuestaDoctor(request.getParameter("comentario"));
                        citaPacienteFacade.edit(citaPaciente);
                        obj.put("rpta", 1);
                    } else {
                        obj.put("rpta", 0);
                    }
                } else {
                    obj.put("rpta", 0);
                }

            } else {
                obj.put("rpta", -1);
            }
            out.println(obj);

        } finally {
            out.close();
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
