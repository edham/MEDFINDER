/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "respuesta_pregunta_paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespuestaPreguntaPaciente.findAll", query = "SELECT r FROM RespuestaPreguntaPaciente r")
    , @NamedQuery(name = "RespuestaPreguntaPaciente.findByPKId", query = "SELECT r FROM RespuestaPreguntaPaciente r WHERE r.pKId = :pKId")
    , @NamedQuery(name = "RespuestaPreguntaPaciente.findByDetalle", query = "SELECT r FROM RespuestaPreguntaPaciente r WHERE r.detalle = :detalle")
    , @NamedQuery(name = "RespuestaPreguntaPaciente.findByPuntaje", query = "SELECT r FROM RespuestaPreguntaPaciente r WHERE r.puntaje = :puntaje")
    , @NamedQuery(name = "RespuestaPreguntaPaciente.findByFechaRegistro", query = "SELECT r FROM RespuestaPreguntaPaciente r WHERE r.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "RespuestaPreguntaPaciente.findByFechaModificacion", query = "SELECT r FROM RespuestaPreguntaPaciente r WHERE r.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "RespuestaPreguntaPaciente.findByEstado", query = "SELECT r FROM RespuestaPreguntaPaciente r WHERE r.estado = :estado")})
public class RespuestaPreguntaPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_Id")
    private Integer pKId;
    @Column(name = "Detalle")
    private String detalle;
    @Column(name = "Puntaje")
    private Integer puntaje;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Estado")
    private Integer estado;
    @JoinColumn(name = "FK_Doctor", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;
    @JoinColumn(name = "FK_PreguntaPaciente", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PreguntaPaciente preguntaPaciente;

    public RespuestaPreguntaPaciente() {
    }

    public RespuestaPreguntaPaciente(Integer pKId) {
        this.pKId = pKId;
    }

    public Integer getPKId() {
        return pKId;
    }

    public void setPKId(Integer pKId) {
        this.pKId = pKId;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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
        hash += (pKId != null ? pKId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaPreguntaPaciente)) {
            return false;
        }
        RespuestaPreguntaPaciente other = (RespuestaPreguntaPaciente) object;
        if ((this.pKId == null && other.pKId != null) || (this.pKId != null && !this.pKId.equals(other.pKId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.RespuestaPreguntaPaciente[ pKId=" + pKId + " ]";
    }
    
}
