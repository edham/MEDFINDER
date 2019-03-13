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
 * @author Edham
 */
@Entity
@Table(name = "detalle_clinica_seguro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleClinicaSeguro.findAll", query = "SELECT d FROM DetalleClinicaSeguro d"),
    @NamedQuery(name = "DetalleClinicaSeguro.findByPKId", query = "SELECT d FROM DetalleClinicaSeguro d WHERE d.pKId = :pKId"),
    @NamedQuery(name = "DetalleClinicaSeguro.findByFechaRegistro", query = "SELECT d FROM DetalleClinicaSeguro d WHERE d.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "DetalleClinicaSeguro.findByFechaModificacion", query = "SELECT d FROM DetalleClinicaSeguro d WHERE d.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "DetalleClinicaSeguro.findByEstado", query = "SELECT d FROM DetalleClinicaSeguro d WHERE d.estado = :estado")})
public class DetalleClinicaSeguro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_Id")
    private Integer pKId;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Estado")
    private Integer estado;
    @JoinColumn(name = "FK_Clinica", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Clinica clinica;
    @JoinColumn(name = "FK_Seguro", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Seguro seguro;

    public DetalleClinicaSeguro() {
    }

    public DetalleClinicaSeguro(Integer pKId) {
        this.pKId = pKId;
    }

    public Integer getPKId() {
        return pKId;
    }

    public void setPKId(Integer pKId) {
        this.pKId = pKId;
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

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
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
        if (!(object instanceof DetalleClinicaSeguro)) {
            return false;
        }
        DetalleClinicaSeguro other = (DetalleClinicaSeguro) object;
        if ((this.pKId == null && other.pKId != null) || (this.pKId != null && !this.pKId.equals(other.pKId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleClinicaSeguro[ pKId=" + pKId + " ]";
    }
    
}
