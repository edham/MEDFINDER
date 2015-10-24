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
@Table(name = "casos_salud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CasosSalud.findAll", query = "SELECT c FROM CasosSalud c"),
    @NamedQuery(name = "CasosSalud.findByCasosSaludId", query = "SELECT c FROM CasosSalud c WHERE c.casosSaludId = :casosSaludId"),
    @NamedQuery(name = "CasosSalud.findByCasosSaludTema", query = "SELECT c FROM CasosSalud c WHERE c.casosSaludTema = :casosSaludTema"),
    @NamedQuery(name = "CasosSalud.findByCasosSaludFechaInicio", query = "SELECT c FROM CasosSalud c WHERE c.casosSaludFechaInicio = :casosSaludFechaInicio"),
    @NamedQuery(name = "CasosSalud.findByCasosSaludFechaFin", query = "SELECT c FROM CasosSalud c WHERE c.casosSaludFechaFin = :casosSaludFechaFin"),
    @NamedQuery(name = "CasosSalud.findByCasosSaludFecha", query = "SELECT c FROM CasosSalud c WHERE c.casosSaludFecha = :casosSaludFecha"),
    @NamedQuery(name = "CasosSalud.findByCasosSaludEstado", query = "SELECT c FROM CasosSalud c WHERE c.casosSaludEstado = :casosSaludEstado")})
public class CasosSalud implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Casos_Salud_Id")
    private Integer casosSaludId;
    @Size(max = 45)
    @Column(name = "Casos_Salud_Tema")
    private String casosSaludTema;
    @Column(name = "Casos_Salud_FechaInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date casosSaludFechaInicio;
    @Column(name = "Casos_Salud_FechaFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date casosSaludFechaFin;
    @Column(name = "Casos_Salud_Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date casosSaludFecha;
    @Column(name = "Casos_Salud_Estado")
    private Short casosSaludEstado;
    @OneToMany(mappedBy = "casosSalud", fetch = FetchType.LAZY)
    private List<RespuestaCasoSalud> respuestaCasoSaludList;

    public CasosSalud() {
    }

    public CasosSalud(Integer casosSaludId) {
        this.casosSaludId = casosSaludId;
    }

    public Integer getCasosSaludId() {
        return casosSaludId;
    }

    public void setCasosSaludId(Integer casosSaludId) {
        this.casosSaludId = casosSaludId;
    }

    public String getCasosSaludTema() {
        return casosSaludTema;
    }

    public void setCasosSaludTema(String casosSaludTema) {
        this.casosSaludTema = casosSaludTema;
    }

    public Date getCasosSaludFechaInicio() {
        return casosSaludFechaInicio;
    }

    public void setCasosSaludFechaInicio(Date casosSaludFechaInicio) {
        this.casosSaludFechaInicio = casosSaludFechaInicio;
    }

    public Date getCasosSaludFechaFin() {
        return casosSaludFechaFin;
    }

    public void setCasosSaludFechaFin(Date casosSaludFechaFin) {
        this.casosSaludFechaFin = casosSaludFechaFin;
    }

    public Date getCasosSaludFecha() {
        return casosSaludFecha;
    }

    public void setCasosSaludFecha(Date casosSaludFecha) {
        this.casosSaludFecha = casosSaludFecha;
    }

    public Short getCasosSaludEstado() {
        return casosSaludEstado;
    }

    public void setCasosSaludEstado(Short casosSaludEstado) {
        this.casosSaludEstado = casosSaludEstado;
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
        hash += (casosSaludId != null ? casosSaludId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CasosSalud)) {
            return false;
        }
        CasosSalud other = (CasosSalud) object;
        if ((this.casosSaludId == null && other.casosSaludId != null) || (this.casosSaludId != null && !this.casosSaludId.equals(other.casosSaludId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.CasosSalud[ casosSaludId=" + casosSaludId + " ]";
    }
    
}
