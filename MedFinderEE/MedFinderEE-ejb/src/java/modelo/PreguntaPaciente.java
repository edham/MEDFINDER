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
import javax.persistence.Lob;
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
@Table(name = "pregunta_paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreguntaPaciente.findAll", query = "SELECT p FROM PreguntaPaciente p"),
    @NamedQuery(name = "PreguntaPaciente.findByPKId", query = "SELECT p FROM PreguntaPaciente p WHERE p.pKId = :pKId"),
    @NamedQuery(name = "PreguntaPaciente.findByAsunto", query = "SELECT p FROM PreguntaPaciente p WHERE p.asunto = :asunto"),
    @NamedQuery(name = "PreguntaPaciente.findByDetalle", query = "SELECT p FROM PreguntaPaciente p WHERE p.detalle = :detalle"),
    @NamedQuery(name = "PreguntaPaciente.findByEstado", query = "SELECT p FROM PreguntaPaciente p WHERE p.estado = :estado"),
    @NamedQuery(name = "PreguntaPaciente.findByFechaInicio", query = "SELECT p FROM PreguntaPaciente p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "PreguntaPaciente.findByFechaFin", query = "SELECT p FROM PreguntaPaciente p WHERE p.fechaFin = :fechaFin")})
public class PreguntaPaciente implements Serializable {
    @Lob
    @Column(name = "Imagen")
    private byte[] imagen;
    @Column(name = "Tipo")
    private Integer tipo;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_Id")
    private Integer pKId;
    @Size(max = 45)
    @Column(name = "Asunto")
    private String asunto;
    @Size(max = 200)
    @Column(name = "Detalle")
    private String detalle;
    @Column(name = "Estado")
    private Integer estado;
    @Column(name = "FechaInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FechaFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @OneToMany(mappedBy = "preguntaPaciente", fetch = FetchType.LAZY)
    private List<RespuestaPreguntaPaciente> respuestaPreguntaPacienteList;
    @JoinColumn(name = "FK_Especialidad", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Especialidad especialidad;
    @JoinColumn(name = "FK_Paciente", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;

    public PreguntaPaciente() {
    }

    public PreguntaPaciente(Integer pKId) {
        this.pKId = pKId;
    }

    public Integer getPKId() {
        return pKId;
    }

    public void setPKId(Integer pKId) {
        this.pKId = pKId;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }


    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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

    @XmlTransient
    public List<RespuestaPreguntaPaciente> getRespuestaPreguntaPacienteList() {
        return respuestaPreguntaPacienteList;
    }

    public void setRespuestaPreguntaPacienteList(List<RespuestaPreguntaPaciente> respuestaPreguntaPacienteList) {
        this.respuestaPreguntaPacienteList = respuestaPreguntaPacienteList;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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
        if (!(object instanceof PreguntaPaciente)) {
            return false;
        }
        PreguntaPaciente other = (PreguntaPaciente) object;
        if ((this.pKId == null && other.pKId != null) || (this.pKId != null && !this.pKId.equals(other.pKId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.PreguntaPaciente[ pKId=" + pKId + " ]";
    }


    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
}
