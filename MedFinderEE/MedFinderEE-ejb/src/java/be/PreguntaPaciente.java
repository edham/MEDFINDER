/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EdHam
 */
@Entity
@Table(name = "pregunta_paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreguntaPaciente.findAll", query = "SELECT p FROM PreguntaPaciente p"),
    @NamedQuery(name = "PreguntaPaciente.findByPreguntaPacienteId", query = "SELECT p FROM PreguntaPaciente p WHERE p.preguntaPacienteId = :preguntaPacienteId"),
    @NamedQuery(name = "PreguntaPaciente.findByPreguntaPacienteAsunto", query = "SELECT p FROM PreguntaPaciente p WHERE p.preguntaPacienteAsunto = :preguntaPacienteAsunto"),
    @NamedQuery(name = "PreguntaPaciente.findByPreguntaPacienteDetalle", query = "SELECT p FROM PreguntaPaciente p WHERE p.preguntaPacienteDetalle = :preguntaPacienteDetalle"),
    @NamedQuery(name = "PreguntaPaciente.findByPreguntaPacienteEstado", query = "SELECT p FROM PreguntaPaciente p WHERE p.preguntaPacienteEstado = :preguntaPacienteEstado"),
    @NamedQuery(name = "PreguntaPaciente.findByPreguntaPacienteFechaInicio", query = "SELECT p FROM PreguntaPaciente p WHERE p.preguntaPacienteFechaInicio = :preguntaPacienteFechaInicio"),
    @NamedQuery(name = "PreguntaPaciente.findByPreguntaPacienteFechaFin", query = "SELECT p FROM PreguntaPaciente p WHERE p.preguntaPacienteFechaFin = :preguntaPacienteFechaFin")})
public class PreguntaPaciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Pregunta_Paciente_Id")
    private Integer preguntaPacienteId;
    @Size(max = 45)
    @Column(name = "Pregunta_Paciente_Asunto")
    private String preguntaPacienteAsunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Pregunta_Paciente_Detalle")
    private String preguntaPacienteDetalle;
    @Lob
    @Column(name = "Pregunta_Paciente_Imagen")
    private byte[] preguntaPacienteImagen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Pregunta_Paciente_Estado")
    private short preguntaPacienteEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Pregunta_Paciente_FechaInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date preguntaPacienteFechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Pregunta_Paciente_FechaFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date preguntaPacienteFechaFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preguntaPacienteId", fetch = FetchType.LAZY)
    private List<RespuestaPreguntaPaciente> respuestaPreguntaPacienteList;
    @JoinColumn(name = "Especialidad_Id", referencedColumnName = "Especialidad_Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Especialidad especialidadId;
    @JoinColumn(name = "Paciente_Id", referencedColumnName = "Paciente_Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paciente pacienteId;

    public PreguntaPaciente() {
    }

    public PreguntaPaciente(Integer preguntaPacienteId) {
        this.preguntaPacienteId = preguntaPacienteId;
    }

    public PreguntaPaciente(Integer preguntaPacienteId, String preguntaPacienteDetalle, short preguntaPacienteEstado, Date preguntaPacienteFechaInicio, Date preguntaPacienteFechaFin) {
        this.preguntaPacienteId = preguntaPacienteId;
        this.preguntaPacienteDetalle = preguntaPacienteDetalle;
        this.preguntaPacienteEstado = preguntaPacienteEstado;
        this.preguntaPacienteFechaInicio = preguntaPacienteFechaInicio;
        this.preguntaPacienteFechaFin = preguntaPacienteFechaFin;
    }

    public Integer getPreguntaPacienteId() {
        return preguntaPacienteId;
    }

    public void setPreguntaPacienteId(Integer preguntaPacienteId) {
        this.preguntaPacienteId = preguntaPacienteId;
    }

    public String getPreguntaPacienteAsunto() {
        return preguntaPacienteAsunto;
    }

    public void setPreguntaPacienteAsunto(String preguntaPacienteAsunto) {
        this.preguntaPacienteAsunto = preguntaPacienteAsunto;
    }

    public String getPreguntaPacienteDetalle() {
        return preguntaPacienteDetalle;
    }

    public void setPreguntaPacienteDetalle(String preguntaPacienteDetalle) {
        this.preguntaPacienteDetalle = preguntaPacienteDetalle;
    }

    public byte[] getPreguntaPacienteImagen() {
        return preguntaPacienteImagen;
    }

    public void setPreguntaPacienteImagen(byte[] preguntaPacienteImagen) {
        this.preguntaPacienteImagen = preguntaPacienteImagen;
    }

    public short getPreguntaPacienteEstado() {
        return preguntaPacienteEstado;
    }

    public void setPreguntaPacienteEstado(short preguntaPacienteEstado) {
        this.preguntaPacienteEstado = preguntaPacienteEstado;
    }

    public Date getPreguntaPacienteFechaInicio() {
        return preguntaPacienteFechaInicio;
    }

    public void setPreguntaPacienteFechaInicio(Date preguntaPacienteFechaInicio) {
        this.preguntaPacienteFechaInicio = preguntaPacienteFechaInicio;
    }

    public Date getPreguntaPacienteFechaFin() {
        return preguntaPacienteFechaFin;
    }

    public void setPreguntaPacienteFechaFin(Date preguntaPacienteFechaFin) {
        this.preguntaPacienteFechaFin = preguntaPacienteFechaFin;
    }

    @XmlTransient
    public List<RespuestaPreguntaPaciente> getRespuestaPreguntaPacienteList() {
        return respuestaPreguntaPacienteList;
    }

    public void setRespuestaPreguntaPacienteList(List<RespuestaPreguntaPaciente> respuestaPreguntaPacienteList) {
        this.respuestaPreguntaPacienteList = respuestaPreguntaPacienteList;
    }

    public Especialidad getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(Especialidad especialidadId) {
        this.especialidadId = especialidadId;
    }

    public Paciente getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Paciente pacienteId) {
        this.pacienteId = pacienteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preguntaPacienteId != null ? preguntaPacienteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntaPaciente)) {
            return false;
        }
        PreguntaPaciente other = (PreguntaPaciente) object;
        if ((this.preguntaPacienteId == null && other.preguntaPacienteId != null) || (this.preguntaPacienteId != null && !this.preguntaPacienteId.equals(other.preguntaPacienteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.PreguntaPaciente[ preguntaPacienteId=" + preguntaPacienteId + " ]";
    }
    
}
