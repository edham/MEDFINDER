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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EdHam
 */
@Entity
@Table(name = "especialidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Especialidad.findAll", query = "SELECT e FROM Especialidad e"),
    @NamedQuery(name = "Especialidad.findByEspecialidadId", query = "SELECT e FROM Especialidad e WHERE e.especialidadId = :especialidadId"),
    @NamedQuery(name = "Especialidad.findByEspecialidadNombre", query = "SELECT e FROM Especialidad e WHERE e.especialidadNombre = :especialidadNombre"),
    @NamedQuery(name = "Especialidad.findByEspecialidadDescripcion", query = "SELECT e FROM Especialidad e WHERE e.especialidadDescripcion = :especialidadDescripcion"),
    @NamedQuery(name = "Especialidad.findByEspecialidadEstado", query = "SELECT e FROM Especialidad e WHERE e.especialidadEstado = :especialidadEstado"),
    @NamedQuery(name = "Especialidad.findByEspecialidadFecha", query = "SELECT e FROM Especialidad e WHERE e.especialidadFecha = :especialidadFecha")})
public class Especialidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Especialidad_Id")
    private Integer especialidadId;
    @Size(max = 45)
    @Column(name = "Especialidad_Nombre")
    private String especialidadNombre;
    @Size(max = 256)
    @Column(name = "Especialidad_Descripcion")
    private String especialidadDescripcion;
    @Column(name = "Especialidad_Estado")
    private Short especialidadEstado;
    @Column(name = "Especialidad_Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date especialidadFecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "especialidad", fetch = FetchType.LAZY)
    private List<DetalleClinicaEspecialidad> detalleClinicaEspecialidadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "especialidad", fetch = FetchType.LAZY)
    private List<PreguntaPaciente> preguntaPacienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "especialidad", fetch = FetchType.LAZY)
    private List<Doctor> doctorList;

    public Especialidad() {
    }

    public Especialidad(Integer especialidadId) {
        this.especialidadId = especialidadId;
    }

    public Integer getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(Integer especialidadId) {
        this.especialidadId = especialidadId;
    }

    public String getEspecialidadNombre() {
        return especialidadNombre;
    }

    public void setEspecialidadNombre(String especialidadNombre) {
        this.especialidadNombre = especialidadNombre;
    }

    public String getEspecialidadDescripcion() {
        return especialidadDescripcion;
    }

    public void setEspecialidadDescripcion(String especialidadDescripcion) {
        this.especialidadDescripcion = especialidadDescripcion;
    }

    public Short getEspecialidadEstado() {
        return especialidadEstado;
    }

    public void setEspecialidadEstado(Short especialidadEstado) {
        this.especialidadEstado = especialidadEstado;
    }

    public Date getEspecialidadFecha() {
        return especialidadFecha;
    }

    public void setEspecialidadFecha(Date especialidadFecha) {
        this.especialidadFecha = especialidadFecha;
    }

    @XmlTransient
    public List<DetalleClinicaEspecialidad> getDetalleClinicaEspecialidadList() {
        return detalleClinicaEspecialidadList;
    }

    public void setDetalleClinicaEspecialidadList(List<DetalleClinicaEspecialidad> detalleClinicaEspecialidadList) {
        this.detalleClinicaEspecialidadList = detalleClinicaEspecialidadList;
    }

    @XmlTransient
    public List<PreguntaPaciente> getPreguntaPacienteList() {
        return preguntaPacienteList;
    }

    public void setPreguntaPacienteList(List<PreguntaPaciente> preguntaPacienteList) {
        this.preguntaPacienteList = preguntaPacienteList;
    }

    @XmlTransient
    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (especialidadId != null ? especialidadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especialidad)) {
            return false;
        }
        Especialidad other = (Especialidad) object;
        if ((this.especialidadId == null && other.especialidadId != null) || (this.especialidadId != null && !this.especialidadId.equals(other.especialidadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.Especialidad[ especialidadId=" + especialidadId + " ]";
    }
    
}
