/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EdHam
 */
@Entity
@Table(name = "respuesta_pregunta_paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespuestaPreguntaPaciente.findAll", query = "SELECT r FROM RespuestaPreguntaPaciente r"),
    @NamedQuery(name = "RespuestaPreguntaPaciente.findByRespuestaPreguntaPacienteId", query = "SELECT r FROM RespuestaPreguntaPaciente r WHERE r.respuestaPreguntaPacienteId = :respuestaPreguntaPacienteId"),
    @NamedQuery(name = "RespuestaPreguntaPaciente.findByRespuestaPreguntaPacienteDetalle", query = "SELECT r FROM RespuestaPreguntaPaciente r WHERE r.respuestaPreguntaPacienteDetalle = :respuestaPreguntaPacienteDetalle"),
    @NamedQuery(name = "RespuestaPreguntaPaciente.findByRespuestaPreguntaPacientePuntaje", query = "SELECT r FROM RespuestaPreguntaPaciente r WHERE r.respuestaPreguntaPacientePuntaje = :respuestaPreguntaPacientePuntaje"),
    @NamedQuery(name = "RespuestaPreguntaPaciente.findByRespuestaPreguntaPacienteFecha", query = "SELECT r FROM RespuestaPreguntaPaciente r WHERE r.respuestaPreguntaPacienteFecha = :respuestaPreguntaPacienteFecha"),
    @NamedQuery(name = "RespuestaPreguntaPaciente.findByRespuestaPreguntaPacienteEstado", query = "SELECT r FROM RespuestaPreguntaPaciente r WHERE r.respuestaPreguntaPacienteEstado = :respuestaPreguntaPacienteEstado")})
public class RespuestaPreguntaPaciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Respuesta_Pregunta_Paciente_Id")
    private Integer respuestaPreguntaPacienteId;
    @Size(max = 200)
    @Column(name = "Respuesta_Pregunta_Paciente_Detalle")
    private String respuestaPreguntaPacienteDetalle;
    @Column(name = "Respuesta_Pregunta_Paciente_Puntaje")
    private Short respuestaPreguntaPacientePuntaje;
    @Column(name = "Respuesta_Pregunta_Paciente_Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date respuestaPreguntaPacienteFecha;
    @Column(name = "Respuesta_Pregunta_Paciente_Estado")
    private Short respuestaPreguntaPacienteEstado;
    @JoinColumn(name = "Doctor_Id", referencedColumnName = "Doctor_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;
    @JoinColumn(name = "Pregunta_Paciente_Id", referencedColumnName = "Pregunta_Paciente_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PreguntaPaciente preguntaPaciente;

    public RespuestaPreguntaPaciente() {
    }

    public RespuestaPreguntaPaciente(Integer respuestaPreguntaPacienteId) {
        this.respuestaPreguntaPacienteId = respuestaPreguntaPacienteId;
    }

    public Integer getRespuestaPreguntaPacienteId() {
        return respuestaPreguntaPacienteId;
    }

    public void setRespuestaPreguntaPacienteId(Integer respuestaPreguntaPacienteId) {
        this.respuestaPreguntaPacienteId = respuestaPreguntaPacienteId;
    }

    public String getRespuestaPreguntaPacienteDetalle() {
        return respuestaPreguntaPacienteDetalle;
    }

    public void setRespuestaPreguntaPacienteDetalle(String respuestaPreguntaPacienteDetalle) {
        this.respuestaPreguntaPacienteDetalle = respuestaPreguntaPacienteDetalle;
    }

    public Short getRespuestaPreguntaPacientePuntaje() {
        return respuestaPreguntaPacientePuntaje;
    }

    public void setRespuestaPreguntaPacientePuntaje(Short respuestaPreguntaPacientePuntaje) {
        this.respuestaPreguntaPacientePuntaje = respuestaPreguntaPacientePuntaje;
    }

    public Date getRespuestaPreguntaPacienteFecha() {
        return respuestaPreguntaPacienteFecha;
    }

    public void setRespuestaPreguntaPacienteFecha(Date respuestaPreguntaPacienteFecha) {
        this.respuestaPreguntaPacienteFecha = respuestaPreguntaPacienteFecha;
    }

    public Short getRespuestaPreguntaPacienteEstado() {
        return respuestaPreguntaPacienteEstado;
    }

    public void setRespuestaPreguntaPacienteEstado(Short respuestaPreguntaPacienteEstado) {
        this.respuestaPreguntaPacienteEstado = respuestaPreguntaPacienteEstado;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public PreguntaPaciente getPreguntaPaciente() {
        return preguntaPaciente;
    }

    public void setPreguntaPaciente(PreguntaPaciente preguntaPaciente) {
        this.preguntaPaciente = preguntaPaciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (respuestaPreguntaPacienteId != null ? respuestaPreguntaPacienteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaPreguntaPaciente)) {
            return false;
        }
        RespuestaPreguntaPaciente other = (RespuestaPreguntaPaciente) object;
        if ((this.respuestaPreguntaPacienteId == null && other.respuestaPreguntaPacienteId != null) || (this.respuestaPreguntaPacienteId != null && !this.respuestaPreguntaPacienteId.equals(other.respuestaPreguntaPacienteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.RespuestaPreguntaPaciente[ respuestaPreguntaPacienteId=" + respuestaPreguntaPacienteId + " ]";
    }
    
}
