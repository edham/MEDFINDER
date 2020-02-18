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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "encuesta_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EncuestaDetalle.findAll", query = "SELECT e FROM EncuestaDetalle e")
    , @NamedQuery(name = "EncuestaDetalle.findByPKId", query = "SELECT e FROM EncuestaDetalle e WHERE e.pKId = :pKId")
    , @NamedQuery(name = "EncuestaDetalle.findByPregunta", query = "SELECT e FROM EncuestaDetalle e WHERE e.pregunta = :pregunta")
    , @NamedQuery(name = "EncuestaDetalle.findByFechaRegistro", query = "SELECT e FROM EncuestaDetalle e WHERE e.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "EncuestaDetalle.findByOrden", query = "SELECT e FROM EncuestaDetalle e WHERE e.orden = :orden")
    , @NamedQuery(name = "EncuestaDetalle.findByEstado", query = "SELECT e FROM EncuestaDetalle e WHERE e.estado = :estado")})
public class EncuestaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_Id")
    private Integer pKId;
    @Column(name = "pregunta")
    private String pregunta;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "orden")
    private Integer orden;
    @Column(name = "Estado")
    private Integer estado;
    @JoinColumn(name = "FK_Encuenta", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Encuesta encuesta;
    @OneToMany(mappedBy = "encuestaDetalle", fetch = FetchType.LAZY)
    private List<EncuestaDetallePersona> encuestaDetallePersonaList;

    public EncuestaDetalle() {
    }

    public EncuestaDetalle(Integer pKId) {
        this.pKId = pKId;
    }

    public Integer getPKId() {
        return pKId;
    }

    public void setPKId(Integer pKId) {
        this.pKId = pKId;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    @XmlTransient
    public List<EncuestaDetallePersona> getEncuestaDetallePersonaList() {
        return encuestaDetallePersonaList;
    }

    public void setEncuestaDetallePersonaList(List<EncuestaDetallePersona> encuestaDetallePersonaList) {
        this.encuestaDetallePersonaList = encuestaDetallePersonaList;
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
        if (!(object instanceof EncuestaDetalle)) {
            return false;
        }
        EncuestaDetalle other = (EncuestaDetalle) object;
        if ((this.pKId == null && other.pKId != null) || (this.pKId != null && !this.pKId.equals(other.pKId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.EncuestaDetalle[ pKId=" + pKId + " ]";
    }
    
}
