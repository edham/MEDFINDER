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
import javax.validation.constraints.NotNull;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "Detalle_Clinica_Especialidad_HorarioInicio")
    @Temporal(TemporalType.TIME)
    private Date detalleClinicaEspecialidadHorarioInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Detalle_Clinica_Especialidad_HorarioFin")
    @Temporal(TemporalType.TIME)
    private Date detalleClinicaEspecialidadHorarioFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Detalle_Clinica_Especialidad_Detalle_Horario")
    private String detalleClinicaEspecialidadDetalleHorario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Detalle_Clinica_Especialidad_Estado")
    private short detalleClinicaEspecialidadEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Detalle_Clinica_Especialidad_Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date detalleClinicaEspecialidadFecha;
    @JoinColumn(name = "Clinica_Id", referencedColumnName = "Clinica_Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Clinica clinicaId;
    @JoinColumn(name = "Especialidad_Id", referencedColumnName = "Especialidad_Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Especialidad especialidadId;

    public DetalleClinicaEspecialidad() {
    }

    public DetalleClinicaEspecialidad(Integer detalleClinicaEspecialidadId) {
        this.detalleClinicaEspecialidadId = detalleClinicaEspecialidadId;
    }

    public DetalleClinicaEspecialidad(Integer detalleClinicaEspecialidadId, Date detalleClinicaEspecialidadHorarioInicio, Date detalleClinicaEspecialidadHorarioFin, String detalleClinicaEspecialidadDetalleHorario, short detalleClinicaEspecialidadEstado, Date detalleClinicaEspecialidadFecha) {
        this.detalleClinicaEspecialidadId = detalleClinicaEspecialidadId;
        this.detalleClinicaEspecialidadHorarioInicio = detalleClinicaEspecialidadHorarioInicio;
        this.detalleClinicaEspecialidadHorarioFin = detalleClinicaEspecialidadHorarioFin;
        this.detalleClinicaEspecialidadDetalleHorario = detalleClinicaEspecialidadDetalleHorario;
        this.detalleClinicaEspecialidadEstado = detalleClinicaEspecialidadEstado;
        this.detalleClinicaEspecialidadFecha = detalleClinicaEspecialidadFecha;
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

    public short getDetalleClinicaEspecialidadEstado() {
        return detalleClinicaEspecialidadEstado;
    }

    public void setDetalleClinicaEspecialidadEstado(short detalleClinicaEspecialidadEstado) {
        this.detalleClinicaEspecialidadEstado = detalleClinicaEspecialidadEstado;
    }

    public Date getDetalleClinicaEspecialidadFecha() {
        return detalleClinicaEspecialidadFecha;
    }

    public void setDetalleClinicaEspecialidadFecha(Date detalleClinicaEspecialidadFecha) {
        this.detalleClinicaEspecialidadFecha = detalleClinicaEspecialidadFecha;
    }

    public Clinica getClinicaId() {
        return clinicaId;
    }

    public void setClinicaId(Clinica clinicaId) {
        this.clinicaId = clinicaId;
    }

    public Especialidad getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(Especialidad especialidadId) {
        this.especialidadId = especialidadId;
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
