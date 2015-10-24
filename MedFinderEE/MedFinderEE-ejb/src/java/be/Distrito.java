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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "distrito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distrito.findAll", query = "SELECT d FROM Distrito d"),
    @NamedQuery(name = "Distrito.findByPKId", query = "SELECT d FROM Distrito d WHERE d.pKId = :pKId"),
    @NamedQuery(name = "Distrito.findByNombre", query = "SELECT d FROM Distrito d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Distrito.findByFechaRegistro", query = "SELECT d FROM Distrito d WHERE d.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Distrito.findByFechaModificacion", query = "SELECT d FROM Distrito d WHERE d.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Distrito.findByEstado", query = "SELECT d FROM Distrito d WHERE d.estado = :estado")})
public class Distrito implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_Id")
    private Integer pKId;
    @Size(max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Estado")
    private Short estado;
    @JoinColumn(name = "FK_Provincia", referencedColumnName = "Provincia_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Provincia provincia;
    @OneToMany(mappedBy = "distrito", fetch = FetchType.LAZY)
    private List<Persona> personaList;
    @OneToMany(mappedBy = "distrito", fetch = FetchType.LAZY)
    private List<Clinica> clinicaList;

    public Distrito() {
    }

    public Distrito(Integer pKId) {
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

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @XmlTransient
    public List<Clinica> getClinicaList() {
        return clinicaList;
    }

    public void setClinicaList(List<Clinica> clinicaList) {
        this.clinicaList = clinicaList;
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
        if (!(object instanceof Distrito)) {
            return false;
        }
        Distrito other = (Distrito) object;
        if ((this.pKId == null && other.pKId != null) || (this.pKId != null && !this.pKId.equals(other.pKId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.Distrito[ pKId=" + pKId + " ]";
    }
    
}
