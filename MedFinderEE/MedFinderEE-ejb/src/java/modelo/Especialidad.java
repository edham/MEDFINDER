/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
    @NamedQuery(name = "Especialidad.findByPKId", query = "SELECT e FROM Especialidad e WHERE e.pKId = :pKId"),
    @NamedQuery(name = "Especialidad.findByNombre", query = "SELECT e FROM Especialidad e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Especialidad.findByDescripcion", query = "SELECT e FROM Especialidad e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Especialidad.findByFechaRegistro", query = "SELECT e FROM Especialidad e WHERE e.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Especialidad.findByFechaModificacion", query = "SELECT e FROM Especialidad e WHERE e.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Especialidad.findByEstado", query = "SELECT e FROM Especialidad e WHERE e.estado = :estado")})
public class Especialidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_Id")
    private Integer pKId;
    @Size(max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @Size(max = 256)
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Estado")
    private Integer estado;
    @OneToMany(mappedBy = "especialidad", fetch = FetchType.LAZY)
    private List<DetalleClinicaEspecialidad> detalleClinicaEspecialidadList;
    @OneToMany(mappedBy = "especialidad", fetch = FetchType.LAZY)
    private List<PreguntaPaciente> preguntaPacienteList;
    @OneToMany(mappedBy = "especialidad", fetch = FetchType.LAZY)
    private List<Doctor> doctorList;

    public Especialidad() {
    }

    public Especialidad(Integer pKId) {
        this.pKId = pKId;
    }

    public Integer getPKId() {
        return pKId;
    }

    public void setPKId(Integer pKId) {
        this.pKId = pKId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (pKId != null ? pKId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especialidad)) {
            return false;
        }
        Especialidad other = (Especialidad) object;
        if ((this.pKId == null && other.pKId != null) || (this.pKId != null && !this.pKId.equals(other.pKId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.Especialidad[ pKId=" + pKId + " ]";
    }
    
}
