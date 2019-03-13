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
@Table(name = "caso_salud_puntaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CasoSaludPuntaje.findAll", query = "SELECT c FROM CasoSaludPuntaje c"),
    @NamedQuery(name = "CasoSaludPuntaje.findByPKId", query = "SELECT c FROM CasoSaludPuntaje c WHERE c.pKId = :pKId"),
    @NamedQuery(name = "CasoSaludPuntaje.findByPuntajeTotal", query = "SELECT c FROM CasoSaludPuntaje c WHERE c.puntajeTotal = :puntajeTotal"),
    @NamedQuery(name = "CasoSaludPuntaje.findByFechaRegistro", query = "SELECT c FROM CasoSaludPuntaje c WHERE c.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "CasoSaludPuntaje.findByEstado", query = "SELECT c FROM CasoSaludPuntaje c WHERE c.estado = :estado")})
public class CasoSaludPuntaje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_Id")
    private Integer pKId;
    @Column(name = "PuntajeTotal")
    private Short puntajeTotal;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "Estado")
    private Integer estado;
    @JoinColumn(name = "FK_RespuestaCasoSalud", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RespuestaCasoSalud respuestaCasoSalud;
    @JoinColumn(name = "FK_Usuario", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    public CasoSaludPuntaje() {
    }

    public CasoSaludPuntaje(Integer pKId) {
        this.pKId = pKId;
    }

    public Integer getPKId() {
        return pKId;
    }

    public void setPKId(Integer pKId) {
        this.pKId = pKId;
    }

    public Short getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(Short puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public RespuestaCasoSalud getRespuestaCasoSalud() {
        return respuestaCasoSalud;
    }

    public void setRespuestaCasoSalud(RespuestaCasoSalud respuestaCasoSalud) {
        this.respuestaCasoSalud = respuestaCasoSalud;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof CasoSaludPuntaje)) {
            return false;
        }
        CasoSaludPuntaje other = (CasoSaludPuntaje) object;
        if ((this.pKId == null && other.pKId != null) || (this.pKId != null && !this.pKId.equals(other.pKId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CasoSaludPuntaje[ pKId=" + pKId + " ]";
    }
    
}
