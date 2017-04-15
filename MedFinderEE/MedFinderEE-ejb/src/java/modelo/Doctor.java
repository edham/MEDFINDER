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
 * @author EdHam
 */
@Entity
@Table(name = "doctor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Doctor.findAll", query = "SELECT d FROM Doctor d"),
    @NamedQuery(name = "Doctor.findByPKId", query = "SELECT d FROM Doctor d WHERE d.pKId = :pKId"),
    @NamedQuery(name = "Doctor.findByCodigoColegiatura", query = "SELECT d FROM Doctor d WHERE d.codigoColegiatura = :codigoColegiatura"),
    @NamedQuery(name = "Doctor.findByDireccion", query = "SELECT d FROM Doctor d WHERE d.direccion = :direccion"),
    @NamedQuery(name = "Doctor.findByDireccionDetalle", query = "SELECT d FROM Doctor d WHERE d.direccionDetalle = :direccionDetalle"),
    @NamedQuery(name = "Doctor.findByTelefono", query = "SELECT d FROM Doctor d WHERE d.telefono = :telefono"),
    @NamedQuery(name = "Doctor.findByLongitud", query = "SELECT d FROM Doctor d WHERE d.longitud = :longitud"),
    @NamedQuery(name = "Doctor.findByLatitud", query = "SELECT d FROM Doctor d WHERE d.latitud = :latitud"),
    @NamedQuery(name = "Doctor.findByPuntaje", query = "SELECT d FROM Doctor d WHERE d.puntaje = :puntaje"),
    @NamedQuery(name = "Doctor.findByFechaRegistro", query = "SELECT d FROM Doctor d WHERE d.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Doctor.findByFechaModificacion", query = "SELECT d FROM Doctor d WHERE d.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Doctor.findByFechaUltimoAcceso", query = "SELECT d FROM Doctor d WHERE d.fechaUltimoAcceso = :fechaUltimoAcceso"),
    @NamedQuery(name = "Doctor.findByEstado", query = "SELECT d FROM Doctor d WHERE d.estado = :estado")})
public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_Id")
    private Integer pKId;
    @Size(max = 45)
    @Column(name = "CodigoColegiatura")
    private String codigoColegiatura;
    @Size(max = 100)
    @Column(name = "Direccion")
    private String direccion;
    @Size(max = 100)
    @Column(name = "DireccionDetalle")
    private String direccionDetalle;
    @Size(max = 9)
    @Column(name = "Telefono")
    private String telefono;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Longitud")
    private Double longitud;
    @Column(name = "Latitud")
    private Double latitud;
    @Column(name = "Puntaje")
    private Integer puntaje;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "FechaUltimoAcceso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaUltimoAcceso;
    @Column(name = "Estado")
    private Integer estado;
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<RespuestaPreguntaPaciente> respuestaPreguntaPacienteList;
    @JoinColumn(name = "FK_Especialidad", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Especialidad especialidad;
    @JoinColumn(name = "FK_Persona", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<CitaPaciente> citaPacienteList;
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<Favoritos> favoritosList;
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<RespuestaCasoSalud> respuestaCasoSaludList;

    public Doctor() {
    }

    public Doctor(Integer pKId) {
        this.pKId = pKId;
    }

    public Integer getPKId() {
        return pKId;
    }

    public void setPKId(Integer pKId) {
        this.pKId = pKId;
    }

    public String getCodigoColegiatura() {
        return codigoColegiatura;
    }

    public void setCodigoColegiatura(String codigoColegiatura) {
        this.codigoColegiatura = codigoColegiatura;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccionDetalle() {
        return direccionDetalle;
    }

    public void setDireccionDetalle(String direccionDetalle) {
        this.direccionDetalle = direccionDetalle;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
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

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getFechaUltimoAcceso() {
        return fechaUltimoAcceso;
    }

    public void setFechaUltimoAcceso(Date fechaUltimoAcceso) {
        this.fechaUltimoAcceso = fechaUltimoAcceso;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @XmlTransient
    public List<CitaPaciente> getCitaPacienteList() {
        return citaPacienteList;
    }

    public void setCitaPacienteList(List<CitaPaciente> citaPacienteList) {
        this.citaPacienteList = citaPacienteList;
    }

    @XmlTransient
    public List<Favoritos> getFavoritosList() {
        return favoritosList;
    }

    public void setFavoritosList(List<Favoritos> favoritosList) {
        this.favoritosList = favoritosList;
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
        if (!(object instanceof Doctor)) {
            return false;
        }
        Doctor other = (Doctor) object;
        if ((this.pKId == null && other.pKId != null) || (this.pKId != null && !this.pKId.equals(other.pKId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.Doctor[ pKId=" + pKId + " ]";
    }
    
}
