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
@Table(name = "cita_paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CitaPaciente.findAll", query = "SELECT c FROM CitaPaciente c"),
    @NamedQuery(name = "CitaPaciente.findByCitaPacienteId", query = "SELECT c FROM CitaPaciente c WHERE c.citaPacienteId = :citaPacienteId"),
    @NamedQuery(name = "CitaPaciente.findByCitaPacienteDetalle", query = "SELECT c FROM CitaPaciente c WHERE c.citaPacienteDetalle = :citaPacienteDetalle"),
    @NamedQuery(name = "CitaPaciente.findByCitaPacienteCreacion", query = "SELECT c FROM CitaPaciente c WHERE c.citaPacienteCreacion = :citaPacienteCreacion"),
    @NamedQuery(name = "CitaPaciente.findByCitaPacienteAtencion", query = "SELECT c FROM CitaPaciente c WHERE c.citaPacienteAtencion = :citaPacienteAtencion"),
    @NamedQuery(name = "CitaPaciente.findByCitaPacienteEstado", query = "SELECT c FROM CitaPaciente c WHERE c.citaPacienteEstado = :citaPacienteEstado")})
public class CitaPaciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Cita_Paciente_Id")
    private Integer citaPacienteId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Cita_Paciente_Detalle")
    private String citaPacienteDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cita_Paciente_Creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date citaPacienteCreacion;
    @Column(name = "Cita_Paciente_Atencion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date citaPacienteAtencion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cita_Paciente_Estado")
    private short citaPacienteEstado;
    @JoinColumn(name = "Doctor_Id", referencedColumnName = "Doctor_Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Doctor doctorId;
    @JoinColumn(name = "Paciente_Id", referencedColumnName = "Paciente_Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paciente pacienteId;

    public CitaPaciente() {
    }

    public CitaPaciente(Integer citaPacienteId) {
        this.citaPacienteId = citaPacienteId;
    }

    public CitaPaciente(Integer citaPacienteId, String citaPacienteDetalle, Date citaPacienteCreacion, short citaPacienteEstado) {
        this.citaPacienteId = citaPacienteId;
        this.citaPacienteDetalle = citaPacienteDetalle;
        this.citaPacienteCreacion = citaPacienteCreacion;
        this.citaPacienteEstado = citaPacienteEstado;
    }

    public Integer getCitaPacienteId() {
        return citaPacienteId;
    }

    public void setCitaPacienteId(Integer citaPacienteId) {
        this.citaPacienteId = citaPacienteId;
    }

    public String getCitaPacienteDetalle() {
        return citaPacienteDetalle;
    }

    public void setCitaPacienteDetalle(String citaPacienteDetalle) {
        this.citaPacienteDetalle = citaPacienteDetalle;
    }

    public Date getCitaPacienteCreacion() {
        return citaPacienteCreacion;
    }

    public void setCitaPacienteCreacion(Date citaPacienteCreacion) {
        this.citaPacienteCreacion = citaPacienteCreacion;
    }

    public Date getCitaPacienteAtencion() {
        return citaPacienteAtencion;
    }

    public void setCitaPacienteAtencion(Date citaPacienteAtencion) {
        this.citaPacienteAtencion = citaPacienteAtencion;
    }

    public short getCitaPacienteEstado() {
        return citaPacienteEstado;
    }

    public void setCitaPacienteEstado(short citaPacienteEstado) {
        this.citaPacienteEstado = citaPacienteEstado;
    }

    public Doctor getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Doctor doctorId) {
        this.doctorId = doctorId;
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
        hash += (citaPacienteId != null ? citaPacienteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CitaPaciente)) {
            return false;
        }
        CitaPaciente other = (CitaPaciente) object;
        if ((this.citaPacienteId == null && other.citaPacienteId != null) || (this.citaPacienteId != null && !this.citaPacienteId.equals(other.citaPacienteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.CitaPaciente[ citaPacienteId=" + citaPacienteId + " ]";
    }
    
}
