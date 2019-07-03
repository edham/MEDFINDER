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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "seguro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seguro.findAll", query = "SELECT s FROM Seguro s")
    , @NamedQuery(name = "Seguro.findByPKId", query = "SELECT s FROM Seguro s WHERE s.pKId = :pKId")
    , @NamedQuery(name = "Seguro.findByNombre", query = "SELECT s FROM Seguro s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "Seguro.findByFechaRegistro", query = "SELECT s FROM Seguro s WHERE s.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Seguro.findByFechaModificacion", query = "SELECT s FROM Seguro s WHERE s.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Seguro.findByEstado", query = "SELECT s FROM Seguro s WHERE s.estado = :estado")})
public class Seguro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_Id")
    private Integer pKId;
    @Column(name = "Nombre")
    private String nombre;
    @Lob
    @Column(name = "Logo")
    private byte[] logo;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Estado")
    private Integer estado;
    @OneToMany(mappedBy = "seguro", fetch = FetchType.LAZY)
    private List<DetalleClinicaSeguro> detalleClinicaSeguroList;

    public Seguro() {
    }

    public Seguro(Integer pKId) {
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

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
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
    public List<DetalleClinicaSeguro> getDetalleClinicaSeguroList() {
        return detalleClinicaSeguroList;
    }

    public void setDetalleClinicaSeguroList(List<DetalleClinicaSeguro> detalleClinicaSeguroList) {
        this.detalleClinicaSeguroList = detalleClinicaSeguroList;
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
        if (!(object instanceof Seguro)) {
            return false;
        }
        Seguro other = (Seguro) object;
        if ((this.pKId == null && other.pKId != null) || (this.pKId != null && !this.pKId.equals(other.pKId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Seguro[ pKId=" + pKId + " ]";
    }
    
}
