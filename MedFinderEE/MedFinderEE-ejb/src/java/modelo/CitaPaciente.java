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
@Table(name = "cita_paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CitaPaciente.findAll", query = "SELECT c FROM CitaPaciente c")
    , @NamedQuery(name = "CitaPaciente.findByPKId", query = "SELECT c FROM CitaPaciente c WHERE c.pKId = :pKId")
    , @NamedQuery(name = "CitaPaciente.findByDetalle", query = "SELECT c FROM CitaPaciente c WHERE c.detalle = :detalle")
    , @NamedQuery(name = "CitaPaciente.findByAtencion", query = "SELECT c FROM CitaPaciente c WHERE c.atencion = :atencion")
    , @NamedQuery(name = "CitaPaciente.findByFechaRegistro", query = "SELECT c FROM CitaPaciente c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "CitaPaciente.findByFechaModificacion", query = "SELECT c FROM CitaPaciente c WHERE c.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "CitaPaciente.findByEstado", query = "SELECT c FROM CitaPaciente c WHERE c.estado = :estado")
    , @NamedQuery(name = "CitaPaciente.findByTipo", query = "SELECT c FROM CitaPaciente c WHERE c.tipo = :tipo")
    , @NamedQuery(name = "CitaPaciente.findByRespuestaDoctor", query = "SELECT c FROM CitaPaciente c WHERE c.respuestaDoctor = :respuestaDoctor")})
public class CitaPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_Id")
    private Integer pKId;
    @Column(name = "Detalle")
    private String detalle;
    @Column(name = "Atencion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date atencion;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Estado")
    private Integer estado;
    @Column(name = "Tipo")
    private Integer tipo;
    @Column(name = "respuesta_doctor")
    private String respuestaDoctor;
    @JoinColumn(name = "FK_Doctor", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;
    @JoinColumn(name = "FK_Paciente", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;

    public CitaPaciente() {
    }

    public CitaPaciente(Integer pKId) {
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

    public Date getAtencion() {
        return atencion;
    }

    public void setAtencion(Date atencion) {
        this.atencion = atencion;
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

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getRespuestaDoctor() {
        return respuestaDoctor;
    }

    public void setRespuestaDoctor(String respuestaDoctor) {
        this.respuestaDoctor = respuestaDoctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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
        if (!(object instanceof CitaPaciente)) {
            return false;
        }
        CitaPaciente other = (CitaPaciente) object;
        if ((this.pKId == null && other.pKId != null) || (this.pKId != null && !this.pKId.equals(other.pKId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CitaPaciente[ pKId=" + pKId + " ]";
    }
    
}
