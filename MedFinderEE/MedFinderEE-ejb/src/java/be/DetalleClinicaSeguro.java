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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EdHam
 */
@Entity
@Table(name = "detalle_clinica_seguro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleClinicaSeguro.findAll", query = "SELECT d FROM DetalleClinicaSeguro d"),
    @NamedQuery(name = "DetalleClinicaSeguro.findByDetalleClinicaSeguroId", query = "SELECT d FROM DetalleClinicaSeguro d WHERE d.detalleClinicaSeguroId = :detalleClinicaSeguroId"),
    @NamedQuery(name = "DetalleClinicaSeguro.findByDetalleClinicaSeguroFecha", query = "SELECT d FROM DetalleClinicaSeguro d WHERE d.detalleClinicaSeguroFecha = :detalleClinicaSeguroFecha"),
    @NamedQuery(name = "DetalleClinicaSeguro.findByDetalleClinicaSeguroEstado", query = "SELECT d FROM DetalleClinicaSeguro d WHERE d.detalleClinicaSeguroEstado = :detalleClinicaSeguroEstado")})
public class DetalleClinicaSeguro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Detalle_Clinica_Seguro_Id")
    private Integer detalleClinicaSeguroId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Detalle_Clinica_Seguro_Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date detalleClinicaSeguroFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Detalle_Clinica_Seguro_Estado")
    private short detalleClinicaSeguroEstado;
    @JoinColumn(name = "Clinica_Id", referencedColumnName = "Clinica_Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Clinica clinicaId;
    @JoinColumn(name = "Seguro_Id", referencedColumnName = "Seguro_Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Seguro seguroId;

    public DetalleClinicaSeguro() {
    }

    public DetalleClinicaSeguro(Integer detalleClinicaSeguroId) {
        this.detalleClinicaSeguroId = detalleClinicaSeguroId;
    }

    public DetalleClinicaSeguro(Integer detalleClinicaSeguroId, Date detalleClinicaSeguroFecha, short detalleClinicaSeguroEstado) {
        this.detalleClinicaSeguroId = detalleClinicaSeguroId;
        this.detalleClinicaSeguroFecha = detalleClinicaSeguroFecha;
        this.detalleClinicaSeguroEstado = detalleClinicaSeguroEstado;
    }

    public Integer getDetalleClinicaSeguroId() {
        return detalleClinicaSeguroId;
    }

    public void setDetalleClinicaSeguroId(Integer detalleClinicaSeguroId) {
        this.detalleClinicaSeguroId = detalleClinicaSeguroId;
    }

    public Date getDetalleClinicaSeguroFecha() {
        return detalleClinicaSeguroFecha;
    }

    public void setDetalleClinicaSeguroFecha(Date detalleClinicaSeguroFecha) {
        this.detalleClinicaSeguroFecha = detalleClinicaSeguroFecha;
    }

    public short getDetalleClinicaSeguroEstado() {
        return detalleClinicaSeguroEstado;
    }

    public void setDetalleClinicaSeguroEstado(short detalleClinicaSeguroEstado) {
        this.detalleClinicaSeguroEstado = detalleClinicaSeguroEstado;
    }

    public Clinica getClinicaId() {
        return clinicaId;
    }

    public void setClinicaId(Clinica clinicaId) {
        this.clinicaId = clinicaId;
    }

    public Seguro getSeguroId() {
        return seguroId;
    }

    public void setSeguroId(Seguro seguroId) {
        this.seguroId = seguroId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleClinicaSeguroId != null ? detalleClinicaSeguroId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleClinicaSeguro)) {
            return false;
        }
        DetalleClinicaSeguro other = (DetalleClinicaSeguro) object;
        if ((this.detalleClinicaSeguroId == null && other.detalleClinicaSeguroId != null) || (this.detalleClinicaSeguroId != null && !this.detalleClinicaSeguroId.equals(other.detalleClinicaSeguroId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.DetalleClinicaSeguro[ detalleClinicaSeguroId=" + detalleClinicaSeguroId + " ]";
    }
    
}
