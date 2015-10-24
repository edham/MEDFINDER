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
@Table(name = "detalle_clinica_especialidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleClinicaEspecialidad.findAll", query = "SELECT d FROM DetalleClinicaEspecialidad d"),
    @NamedQuery(name = "DetalleClinicaEspecialidad.findByPKId", query = "SELECT d FROM DetalleClinicaEspecialidad d WHERE d.pKId = :pKId"),
    @NamedQuery(name = "DetalleClinicaEspecialidad.findByHorarioInicio", query = "SELECT d FROM DetalleClinicaEspecialidad d WHERE d.horarioInicio = :horarioInicio"),
    @NamedQuery(name = "DetalleClinicaEspecialidad.findByHorarioFin", query = "SELECT d FROM DetalleClinicaEspecialidad d WHERE d.horarioFin = :horarioFin"),
    @NamedQuery(name = "DetalleClinicaEspecialidad.findByDetalleHorario", query = "SELECT d FROM DetalleClinicaEspecialidad d WHERE d.detalleHorario = :detalleHorario"),
    @NamedQuery(name = "DetalleClinicaEspecialidad.findByFechaRegistro", query = "SELECT d FROM DetalleClinicaEspecialidad d WHERE d.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "DetalleClinicaEspecialidad.findByFechaModificacion", query = "SELECT d FROM DetalleClinicaEspecialidad d WHERE d.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "DetalleClinicaEspecialidad.findByEstado", query = "SELECT d FROM DetalleClinicaEspecialidad d WHERE d.estado = :estado")})
public class DetalleClinicaEspecialidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_Id")
    private Integer pKId;
    @Column(name = "HorarioInicio")
    @Temporal(TemporalType.TIME)
    private Date horarioInicio;
    @Column(name = "HorarioFin")
    @Temporal(TemporalType.TIME)
    private Date horarioFin;
    @Size(max = 45)
    @Column(name = "DetalleHorario")
    private String detalleHorario;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Estado")
    private Short estado;
    @JoinColumn(name = "FK_Clinica", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clinica clinica;
    @JoinColumn(name = "FK_Especialidad", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Especialidad especialidad;

    public DetalleClinicaEspecialidad() {
    }

    public DetalleClinicaEspecialidad(Integer pKId) {
        this.pKId = pKId;
    }

    public Integer getPKId() {
        return pKId;
    }

    public void setPKId(Integer pKId) {
        this.pKId = pKId;
    }

    public Date getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Date getHorarioFin() {
        return horarioFin;
    }

    public void setHorarioFin(Date horarioFin) {
        this.horarioFin = horarioFin;
    }

    public String getDetalleHorario() {
        return detalleHorario;
    }

    public void setDetalleHorario(String detalleHorario) {
        this.detalleHorario = detalleHorario;
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

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
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
        if (!(object instanceof DetalleClinicaEspecialidad)) {
            return false;
        }
        DetalleClinicaEspecialidad other = (DetalleClinicaEspecialidad) object;
        if ((this.pKId == null && other.pKId != null) || (this.pKId != null && !this.pKId.equals(other.pKId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.DetalleClinicaEspecialidad[ pKId=" + pKId + " ]";
    }
    
}
