/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EdHam
 */
@Entity
@Table(name = "caso_salud_puntaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CasoSaludPuntaje.findAll", query = "SELECT c FROM CasoSaludPuntaje c"),
    @NamedQuery(name = "CasoSaludPuntaje.findByCasoSaludPuntajeId", query = "SELECT c FROM CasoSaludPuntaje c WHERE c.casoSaludPuntajeId = :casoSaludPuntajeId"),
    @NamedQuery(name = "CasoSaludPuntaje.findByCasoSsaludPuntajeTotal", query = "SELECT c FROM CasoSaludPuntaje c WHERE c.casoSsaludPuntajeTotal = :casoSsaludPuntajeTotal")})
public class CasoSaludPuntaje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Caso_Salud_Puntaje_Id")
    private Integer casoSaludPuntajeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Caso_Ssalud_Puntaje_Total")
    private short casoSsaludPuntajeTotal;
    @JoinColumn(name = "Respuesta_Caso_Salud_Id", referencedColumnName = "Respuesta_Caso_Salud_Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RespuestaCasoSalud respuestaCasoSalud;
    @JoinColumn(name = "Usuario_Id", referencedColumnName = "Usuario_Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public CasoSaludPuntaje() {
    }

    public CasoSaludPuntaje(Integer casoSaludPuntajeId) {
        this.casoSaludPuntajeId = casoSaludPuntajeId;
    }

    public CasoSaludPuntaje(Integer casoSaludPuntajeId, short casoSsaludPuntajeTotal) {
        this.casoSaludPuntajeId = casoSaludPuntajeId;
        this.casoSsaludPuntajeTotal = casoSsaludPuntajeTotal;
    }

    public Integer getCasoSaludPuntajeId() {
        return casoSaludPuntajeId;
    }

    public void setCasoSaludPuntajeId(Integer casoSaludPuntajeId) {
        this.casoSaludPuntajeId = casoSaludPuntajeId;
    }

    public short getCasoSsaludPuntajeTotal() {
        return casoSsaludPuntajeTotal;
    }

    public void setCasoSsaludPuntajeTotal(short casoSsaludPuntajeTotal) {
        this.casoSsaludPuntajeTotal = casoSsaludPuntajeTotal;
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
        hash += (casoSaludPuntajeId != null ? casoSaludPuntajeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CasoSaludPuntaje)) {
            return false;
        }
        CasoSaludPuntaje other = (CasoSaludPuntaje) object;
        if ((this.casoSaludPuntajeId == null && other.casoSaludPuntajeId != null) || (this.casoSaludPuntajeId != null && !this.casoSaludPuntajeId.equals(other.casoSaludPuntajeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.CasoSaludPuntaje[ casoSaludPuntajeId=" + casoSaludPuntajeId + " ]";
    }
    
}
