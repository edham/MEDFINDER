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
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EdHam
 */
@Entity
@Table(name = "respuesta_caso_salud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespuestaCasoSalud.findAll", query = "SELECT r FROM RespuestaCasoSalud r"),
    @NamedQuery(name = "RespuestaCasoSalud.findByRespuestaCasoSaludId", query = "SELECT r FROM RespuestaCasoSalud r WHERE r.respuestaCasoSaludId = :respuestaCasoSaludId"),
    @NamedQuery(name = "RespuestaCasoSalud.findByRespuestaCasoSaludDescripcion", query = "SELECT r FROM RespuestaCasoSalud r WHERE r.respuestaCasoSaludDescripcion = :respuestaCasoSaludDescripcion"),
    @NamedQuery(name = "RespuestaCasoSalud.findByRespuestaCasoSaludPuntajeTotal", query = "SELECT r FROM RespuestaCasoSalud r WHERE r.respuestaCasoSaludPuntajeTotal = :respuestaCasoSaludPuntajeTotal"),
    @NamedQuery(name = "RespuestaCasoSalud.findByRespuestaCasoSaludFecha", query = "SELECT r FROM RespuestaCasoSalud r WHERE r.respuestaCasoSaludFecha = :respuestaCasoSaludFecha"),
    @NamedQuery(name = "RespuestaCasoSalud.findByRespuestaCasoSaludEstado", query = "SELECT r FROM RespuestaCasoSalud r WHERE r.respuestaCasoSaludEstado = :respuestaCasoSaludEstado")})
public class RespuestaCasoSalud implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Respuesta_Caso_Salud_Id")
    private Integer respuestaCasoSaludId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Respuesta_Caso_Salud_Descripcion")
    private String respuestaCasoSaludDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Respuesta_Caso_Salud_Puntaje_Total")
    private int respuestaCasoSaludPuntajeTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Respuesta_Caso_Salud_Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date respuestaCasoSaludFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Respuesta_Caso_Salud_Estado")
    private short respuestaCasoSaludEstado;
    @JoinColumn(name = "Casos_Salud_Id", referencedColumnName = "Casos_Salud_Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CasosSalud casosSalud;
    @JoinColumn(name = "Doctor_Id", referencedColumnName = "Doctor_Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Doctor doctor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "respuestaCasoSalud", fetch = FetchType.LAZY)
    private List<CasoSaludPuntaje> casoSaludPuntajeList;

    public RespuestaCasoSalud() {
    }

    public RespuestaCasoSalud(Integer respuestaCasoSaludId) {
        this.respuestaCasoSaludId = respuestaCasoSaludId;
    }

    public RespuestaCasoSalud(Integer respuestaCasoSaludId, String respuestaCasoSaludDescripcion, int respuestaCasoSaludPuntajeTotal, Date respuestaCasoSaludFecha, short respuestaCasoSaludEstado) {
        this.respuestaCasoSaludId = respuestaCasoSaludId;
        this.respuestaCasoSaludDescripcion = respuestaCasoSaludDescripcion;
        this.respuestaCasoSaludPuntajeTotal = respuestaCasoSaludPuntajeTotal;
        this.respuestaCasoSaludFecha = respuestaCasoSaludFecha;
        this.respuestaCasoSaludEstado = respuestaCasoSaludEstado;
    }

    public Integer getRespuestaCasoSaludId() {
        return respuestaCasoSaludId;
    }

    public void setRespuestaCasoSaludId(Integer respuestaCasoSaludId) {
        this.respuestaCasoSaludId = respuestaCasoSaludId;
    }

    public String getRespuestaCasoSaludDescripcion() {
        return respuestaCasoSaludDescripcion;
    }

    public void setRespuestaCasoSaludDescripcion(String respuestaCasoSaludDescripcion) {
        this.respuestaCasoSaludDescripcion = respuestaCasoSaludDescripcion;
    }

    public int getRespuestaCasoSaludPuntajeTotal() {
        return respuestaCasoSaludPuntajeTotal;
    }

    public void setRespuestaCasoSaludPuntajeTotal(int respuestaCasoSaludPuntajeTotal) {
        this.respuestaCasoSaludPuntajeTotal = respuestaCasoSaludPuntajeTotal;
    }

    public Date getRespuestaCasoSaludFecha() {
        return respuestaCasoSaludFecha;
    }

    public void setRespuestaCasoSaludFecha(Date respuestaCasoSaludFecha) {
        this.respuestaCasoSaludFecha = respuestaCasoSaludFecha;
    }

    public short getRespuestaCasoSaludEstado() {
        return respuestaCasoSaludEstado;
    }

    public void setRespuestaCasoSaludEstado(short respuestaCasoSaludEstado) {
        this.respuestaCasoSaludEstado = respuestaCasoSaludEstado;
    }

    public CasosSalud getCasosSalud() {
        return casosSalud;
    }

    public void setCasosSalud(CasosSalud casosSalud) {
        this.casosSalud = casosSalud;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @XmlTransient
    public List<CasoSaludPuntaje> getCasoSaludPuntajeList() {
        return casoSaludPuntajeList;
    }

    public void setCasoSaludPuntajeList(List<CasoSaludPuntaje> casoSaludPuntajeList) {
        this.casoSaludPuntajeList = casoSaludPuntajeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (respuestaCasoSaludId != null ? respuestaCasoSaludId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaCasoSalud)) {
            return false;
        }
        RespuestaCasoSalud other = (RespuestaCasoSalud) object;
        if ((this.respuestaCasoSaludId == null && other.respuestaCasoSaludId != null) || (this.respuestaCasoSaludId != null && !this.respuestaCasoSaludId.equals(other.respuestaCasoSaludId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.RespuestaCasoSalud[ respuestaCasoSaludId=" + respuestaCasoSaludId + " ]";
    }
    
}
