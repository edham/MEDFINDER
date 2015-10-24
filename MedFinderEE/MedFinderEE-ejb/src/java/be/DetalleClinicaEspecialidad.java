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
    @NamedQuery(name = "DetalleClinicaEspecialidad.findByDetalleClinicaEspecialidadId", query = "SELECT d FROM DetalleClinicaEspecialidad d WHERE d.detalleClinicaEspecialidadId = :detalleClinicaEspecialidadId"),
    @NamedQuery(name = "DetalleClinicaEspecialidad.findByDetalleClinicaEspecialidadHorarioInicio", query = "SELECT d FROM DetalleClinicaEspecialidad d WHERE d.detalleClinicaEspecialidadHorarioInicio = :detalleClinicaEspecialidadHorarioInicio"),
    @NamedQuery(name = "DetalleClinicaEspecialidad.findByDetalleClinicaEspecialidadHorarioFin", query = "SELECT d FROM DetalleClinicaEspecialidad d WHERE d.detalleClinicaEspecialidadHorarioFin = :detalleClinicaEspecialidadHorarioFin"),
    @NamedQuery(name = "DetalleClinicaEspecialidad.findByDetalleClinicaEspecialidadDetalleHorario", query = "SELECT d FROM DetalleClinicaEspecialidad d WHERE d.detalleClinicaEspecialidadDetalleHorario = :detalleClinicaEspecialidadDetalleHorario"),
    @NamedQuery(name = "DetalleClinicaEspecialidad.findByDetalleClinicaEspecialidadEstado", query = "SELECT d FROM DetalleClinicaEspecialidad d WHERE d.detalleClinicaEspecialidadEstado = :detalleClinicaEspecialidadEstado"),
    @NamedQuery(name = "DetalleClinicaEspecialidad.findByDetalleClinicaEspecialidadFecha", query = "SELECT d FROM DetalleClinicaEspecialidad d WHERE d.detalleClinicaEspecialidadFecha = :detalleClinicaEspecialidadFecha")})
public class DetalleClinicaEspecialidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Detalle_Clinica_Especialidad_Id")
    private Integer detalleClinicaEspecialidadId;
    @Column(name = "Detalle_Clinica_Especialidad_HorarioInicio")
    @Temporal(TemporalType.TIME)
    private Date detalleClinicaEspecialidadHorarioInicio;
    @Column(name = "Detalle_Clinica_Especialidad_HorarioFin")
    @Temporal(TemporalType.TIME)
    private Date detalleClinicaEspecialidadHorarioFin;
    @Size(max = 45)
    @Column(name = "Detalle_Clinica_Especialidad_Detalle_Horario")
    private String detalleClinicaEspecialidadDetalleHorario;
    @Column(name = "Detalle_Clinica_Especialidad_Estado")
    private Short detalleClinicaEspecialidadEstado;
    @Column(name = "Detalle_Clinica_Especialidad_Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date detalleClinicaEspecialidadFecha;
    @JoinColumn(name = "Clinica_Id", referencedColumnName = "Clinica_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clinica clinica;
    @JoinColumn(name = "Especialidad_Id", referencedColumnName = "Especialidad_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Especialidad especialidad;

    public DetalleClinicaEspecialidad() {
    }

    public DetalleClinicaEspecialidad(Integer detalleClinicaEspecialidadId) {
        this.detalleClinicaEspecialidadId = detalleClinicaEspecialidadId;
    }

    public Integer getDetalleClinicaEspecialidadId() {
        return detalleClinicaEspecialidadId;
    }

    public void setDetalleClinicaEspecialidadId(Integer detalleClinicaEspecialidadId) {
        this.detalleClinicaEspecialidadId = detalleClinicaEspecialidadId;
    }

    public Date getDetalleClinicaEspecialidadHorarioInicio() {
        return detalleClinicaEspecialidadHorarioInicio;
    }

    public void setDetalleClinicaEspecialidadHorarioInicio(Date detalleClinicaEspecialidadHorarioInicio) {
        this.detalleClinicaEspecialidadHorarioInicio = detalleClinicaEspecialidadHorarioInicio;
    }

    public Date getDetalleClinicaEspecialidadHorarioFin() {
        return detalleClinicaEspecialidadHorarioFin;
    }

    public void setDetalleClinicaEspecialidadHorarioFin(Date detalleClinicaEspecialidadHorarioFin) {
        this.detalleClinicaEspecialidadHorarioFin = detalleClinicaEspecialidadHorarioFin;
    }

    public String getDetalleClinicaEspecialidadDetalleHorario() {
        return detalleClinicaEspecialidadDetalleHorario;
    }

    public void setDetalleClinicaEspecialidadDetalleHorario(String detalleClinicaEspecialidadDetalleHorario) {
        this.detalleClinicaEspecialidadDetalleHorario = detalleClinicaEspecialidadDetalleHorario;
    }

    public Short getDetalleClinicaEspecialidadEstado() {
        return detalleClinicaEspecialidadEstado;
    }

    public void setDetalleClinicaEspecialidadEstado(Short detalleClinicaEspecialidadEstado) {
        this.detalleClinicaEspecialidadEstado = detalleClinicaEspecialidadEstado;
    }

    public Date getDetalleClinicaEspecialidadFecha() {
        return detalleClinicaEspecialidadFecha;
    }

    public void setDetalleClinicaEspecialidadFecha(Date detalleClinicaEspecialidadFecha) {
        this.detalleClinicaEspecialidadFecha = detalleClinicaEspecialidadFecha;
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
        hash += (detalleClinicaEspecialidadId != null ? detalleClinicaEspecialidadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleClinicaEspecialidad)) {
            return false;
        }
        DetalleClinicaEspecialidad other = (DetalleClinicaEspecialidad) object;
        if ((this.detalleClinicaEspecialidadId == null && other.detalleClinicaEspecialidadId != null) || (this.detalleClinicaEspecialidadId != null && !this.detalleClinicaEspecialidadId.equals(other.detalleClinicaEspecialidadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.DetalleClinicaEspecialidad[ detalleClinicaEspecialidadId=" + detalleClinicaEspecialidadId + " ]";
    }
    
}
