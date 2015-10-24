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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "seguro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seguro.findAll", query = "SELECT s FROM Seguro s"),
    @NamedQuery(name = "Seguro.findBySeguroId", query = "SELECT s FROM Seguro s WHERE s.seguroId = :seguroId"),
    @NamedQuery(name = "Seguro.findBySeguroNombre", query = "SELECT s FROM Seguro s WHERE s.seguroNombre = :seguroNombre"),
    @NamedQuery(name = "Seguro.findBySeguroFecha", query = "SELECT s FROM Seguro s WHERE s.seguroFecha = :seguroFecha"),
    @NamedQuery(name = "Seguro.findBySeguroEstado", query = "SELECT s FROM Seguro s WHERE s.seguroEstado = :seguroEstado")})
public class Seguro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Seguro_Id")
    private Integer seguroId;
    @Size(max = 45)
    @Column(name = "Seguro_Nombre")
    private String seguroNombre;
    @Lob
    @Column(name = "Seguro_Logo")
    private byte[] seguroLogo;
    @Column(name = "Seguro_Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date seguroFecha;
    @Column(name = "Seguro_Estado")
    private Short seguroEstado;
    @OneToMany(mappedBy = "seguro", fetch = FetchType.LAZY)
    private List<DetalleClinicaSeguro> detalleClinicaSeguroList;

    public Seguro() {
    }

    public Seguro(Integer seguroId) {
        this.seguroId = seguroId;
    }

    public Integer getSeguroId() {
        return seguroId;
    }

    public void setSeguroId(Integer seguroId) {
        this.seguroId = seguroId;
    }

    public String getSeguroNombre() {
        return seguroNombre;
    }

    public void setSeguroNombre(String seguroNombre) {
        this.seguroNombre = seguroNombre;
    }

    public byte[] getSeguroLogo() {
        return seguroLogo;
    }

    public void setSeguroLogo(byte[] seguroLogo) {
        this.seguroLogo = seguroLogo;
    }

    public Date getSeguroFecha() {
        return seguroFecha;
    }

    public void setSeguroFecha(Date seguroFecha) {
        this.seguroFecha = seguroFecha;
    }

    public Short getSeguroEstado() {
        return seguroEstado;
    }

    public void setSeguroEstado(Short seguroEstado) {
        this.seguroEstado = seguroEstado;
    }

    @XmlTransient
    public List<DetalleClinicaSeguro> getDetalleClinicaSeguroList() {
        return detalleClinicaSeguroList;
    }

    public void setDetalleClinicaSeguroList(List<DetalleClinicaSeguro> detalleClinicaSeguroList) {
        this.detalleClinicaSeguroList = detalleClinicaSeguroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seguroId != null ? seguroId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seguro)) {
            return false;
        }
        Seguro other = (Seguro) object;
        if ((this.seguroId == null && other.seguroId != null) || (this.seguroId != null && !this.seguroId.equals(other.seguroId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.Seguro[ seguroId=" + seguroId + " ]";
    }
    
}
