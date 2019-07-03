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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "casos_salud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CasosSalud.findAll", query = "SELECT c FROM CasosSalud c")
    , @NamedQuery(name = "CasosSalud.findByPKId", query = "SELECT c FROM CasosSalud c WHERE c.pKId = :pKId")
    , @NamedQuery(name = "CasosSalud.findByTema", query = "SELECT c FROM CasosSalud c WHERE c.tema = :tema")
    , @NamedQuery(name = "CasosSalud.findByFechaInicio", query = "SELECT c FROM CasosSalud c WHERE c.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "CasosSalud.findByFechaFin", query = "SELECT c FROM CasosSalud c WHERE c.fechaFin = :fechaFin")
    , @NamedQuery(name = "CasosSalud.findByFechaRegistro", query = "SELECT c FROM CasosSalud c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "CasosSalud.findByFechaModificacion", query = "SELECT c FROM CasosSalud c WHERE c.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "CasosSalud.findByEstado", query = "SELECT c FROM CasosSalud c WHERE c.estado = :estado")})
public class CasosSalud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_Id")
    private Integer pKId;
    @Column(name = "Tema")
    private String tema;
    @Column(name = "FechaInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FechaFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Estado")
    private Integer estado;
    @OneToMany(mappedBy = "casosSalud", fetch = FetchType.LAZY)
    private List<RespuestaCasoSalud> respuestaCasoSaludList;

    public CasosSalud() {
    }

    public CasosSalud(Integer pKId) {
        this.pKId = pKId;
    }

    public Integer getPKId() {
        return pKId;
    }

    public void setPKId(Integer pKId) {
        this.pKId = pKId;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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
    public List<RespuestaCasoSalud> getRespuestaCasoSaludList() {
        return respuestaCasoSaludList;
    }

    public void setRespuestaCasoSaludList(List<RespuestaCasoSalud> respuestaCasoSaludList) {
        this.respuestaCasoSaludList = respuestaCasoSaludList;
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
        if (!(object instanceof CasosSalud)) {
            return false;
        }
        CasosSalud other = (CasosSalud) object;
        if ((this.pKId == null && other.pKId != null) || (this.pKId != null && !this.pKId.equals(other.pKId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CasosSalud[ pKId=" + pKId + " ]";
    }
    
}
