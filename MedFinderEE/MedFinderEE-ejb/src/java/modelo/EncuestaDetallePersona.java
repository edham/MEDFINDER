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
 * @author hp
 */
@Entity
@Table(name = "encuesta_detalle_persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EncuestaDetallePersona.findAll", query = "SELECT e FROM EncuestaDetallePersona e")
    , @NamedQuery(name = "EncuestaDetallePersona.findByPKId", query = "SELECT e FROM EncuestaDetallePersona e WHERE e.pKId = :pKId")
    , @NamedQuery(name = "EncuestaDetallePersona.findByPuntaje", query = "SELECT e FROM EncuestaDetallePersona e WHERE e.puntaje = :puntaje")
    , @NamedQuery(name = "EncuestaDetallePersona.findByFechaRegistro", query = "SELECT e FROM EncuestaDetallePersona e WHERE e.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "EncuestaDetallePersona.findByEstado", query = "SELECT e FROM EncuestaDetallePersona e WHERE e.estado = :estado")})
public class EncuestaDetallePersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_Id")
    private Integer pKId;
    @Column(name = "puntaje")
    private Integer puntaje;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "Estado")
    private Integer estado;
    @JoinColumn(name = "FK_Encuenta_Detalle", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private EncuestaDetalle encuestaDetalle;
    @JoinColumn(name = "FK_Persona", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;

    public EncuestaDetallePersona() {
    }

    public EncuestaDetallePersona(Integer pKId) {
        this.pKId = pKId;
    }

    public Integer getPKId() {
        return pKId;
    }

    public void setPKId(Integer pKId) {
        this.pKId = pKId;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
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

    public EncuestaDetalle getEncuestaDetalle() {
        return encuestaDetalle;
    }

    public void setEncuestaDetalle(EncuestaDetalle encuestaDetalle) {
        this.encuestaDetalle = encuestaDetalle;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
        if (!(object instanceof EncuestaDetallePersona)) {
            return false;
        }
        EncuestaDetallePersona other = (EncuestaDetallePersona) object;
        if ((this.pKId == null && other.pKId != null) || (this.pKId != null && !this.pKId.equals(other.pKId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.EncuestaDetallePersona[ pKId=" + pKId + " ]";
    }
    
}
