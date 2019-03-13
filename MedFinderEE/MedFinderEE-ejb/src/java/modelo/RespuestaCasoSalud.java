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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Edham
 */
@Entity
@Table(name = "respuesta_caso_salud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespuestaCasoSalud.findAll", query = "SELECT r FROM RespuestaCasoSalud r"),
    @NamedQuery(name = "RespuestaCasoSalud.findByPKId", query = "SELECT r FROM RespuestaCasoSalud r WHERE r.pKId = :pKId"),
    @NamedQuery(name = "RespuestaCasoSalud.findByDescripcion", query = "SELECT r FROM RespuestaCasoSalud r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "RespuestaCasoSalud.findByPuntajeTotal", query = "SELECT r FROM RespuestaCasoSalud r WHERE r.puntajeTotal = :puntajeTotal"),
    @NamedQuery(name = "RespuestaCasoSalud.findByFechaRegistro", query = "SELECT r FROM RespuestaCasoSalud r WHERE r.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "RespuestaCasoSalud.findByFechaModificacion", query = "SELECT r FROM RespuestaCasoSalud r WHERE r.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "RespuestaCasoSalud.findByEstado", query = "SELECT r FROM RespuestaCasoSalud r WHERE r.estado = :estado")})
public class RespuestaCasoSalud implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_Id")
    private Integer pKId;
    @Size(max = 255)
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "PuntajeTotal")
    private Integer puntajeTotal;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Estado")
    private Integer estado;
    @JoinColumn(name = "FK_CasosSalud", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CasosSalud casosSalud;
    @JoinColumn(name = "FK_Doctor", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;
    @OneToMany(mappedBy = "respuestaCasoSalud", fetch = FetchType.LAZY)
    private List<CasoSaludPuntaje> casoSaludPuntajeList;

    public RespuestaCasoSalud() {
    }

    public RespuestaCasoSalud(Integer pKId) {
        this.pKId = pKId;
    }

    public Integer getPKId() {
        return pKId;
    }

    public void setPKId(Integer pKId) {
        this.pKId = pKId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(Integer puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
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
        hash += (pKId != null ? pKId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaCasoSalud)) {
            return false;
        }
        RespuestaCasoSalud other = (RespuestaCasoSalud) object;
        if ((this.pKId == null && other.pKId != null) || (this.pKId != null && !this.pKId.equals(other.pKId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.RespuestaCasoSalud[ pKId=" + pKId + " ]";
    }
    
}
