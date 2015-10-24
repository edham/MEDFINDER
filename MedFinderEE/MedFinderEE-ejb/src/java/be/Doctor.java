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
    @NamedQuery(name = "Doctor.findByDoctorId", query = "SELECT d FROM Doctor d WHERE d.doctorId = :doctorId"),
    @NamedQuery(name = "Doctor.findByDoctorUsuario", query = "SELECT d FROM Doctor d WHERE d.doctorUsuario = :doctorUsuario"),
    @NamedQuery(name = "Doctor.findByDoctorClave", query = "SELECT d FROM Doctor d WHERE d.doctorClave = :doctorClave"),
    @NamedQuery(name = "Doctor.findByDoctorFechaRegistro", query = "SELECT d FROM Doctor d WHERE d.doctorFechaRegistro = :doctorFechaRegistro"),
    @NamedQuery(name = "Doctor.findByDoctorCodigoColegiatura", query = "SELECT d FROM Doctor d WHERE d.doctorCodigoColegiatura = :doctorCodigoColegiatura"),
    @NamedQuery(name = "Doctor.findByDoctorDireccion", query = "SELECT d FROM Doctor d WHERE d.doctorDireccion = :doctorDireccion"),
    @NamedQuery(name = "Doctor.findByDoctorTelefono", query = "SELECT d FROM Doctor d WHERE d.doctorTelefono = :doctorTelefono"),
    @NamedQuery(name = "Doctor.findByDoctorDireccionDetalle", query = "SELECT d FROM Doctor d WHERE d.doctorDireccionDetalle = :doctorDireccionDetalle"),
    @NamedQuery(name = "Doctor.findByDoctorLongitud", query = "SELECT d FROM Doctor d WHERE d.doctorLongitud = :doctorLongitud"),
    @NamedQuery(name = "Doctor.findByDoctorLatitud", query = "SELECT d FROM Doctor d WHERE d.doctorLatitud = :doctorLatitud"),
    @NamedQuery(name = "Doctor.findByDoctorPuntaje", query = "SELECT d FROM Doctor d WHERE d.doctorPuntaje = :doctorPuntaje"),
    @NamedQuery(name = "Doctor.findByDoctorEstado", query = "SELECT d FROM Doctor d WHERE d.doctorEstado = :doctorEstado"),
    @NamedQuery(name = "Doctor.findByDoctorFechaUltimoIngreso", query = "SELECT d FROM Doctor d WHERE d.doctorFechaUltimoIngreso = :doctorFechaUltimoIngreso")})
public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Doctor_Id")
    private Integer doctorId;
    @Size(max = 45)
    @Column(name = "Doctor_Usuario")
    private String doctorUsuario;
    @Size(max = 45)
    @Column(name = "Doctor_Clave")
    private String doctorClave;
    @Column(name = "Doctor_FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doctorFechaRegistro;
    @Size(max = 45)
    @Column(name = "Doctor_CodigoColegiatura")
    private String doctorCodigoColegiatura;
    @Size(max = 100)
    @Column(name = "Doctor_Direccion")
    private String doctorDireccion;
    @Size(max = 9)
    @Column(name = "Doctor_Telefono")
    private String doctorTelefono;
    @Size(max = 100)
    @Column(name = "Doctor_Direccion_Detalle")
    private String doctorDireccionDetalle;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Doctor_Longitud")
    private Double doctorLongitud;
    @Column(name = "Doctor_Latitud")
    private Double doctorLatitud;
    @Column(name = "Doctor_Puntaje")
    private Integer doctorPuntaje;
    @Column(name = "Doctor_Estado")
    private Short doctorEstado;
    @Column(name = "Doctor_FechaUltimoIngreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doctorFechaUltimoIngreso;
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<RespuestaPreguntaPaciente> respuestaPreguntaPacienteList;
    @JoinColumn(name = "Persona_Id", referencedColumnName = "Persona_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;
    @JoinColumn(name = "Especialidad_Id", referencedColumnName = "Especialidad_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Especialidad especialidad;
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<CitaPaciente> citaPacienteList;
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<Favoritos> favoritosList;
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private List<RespuestaCasoSalud> respuestaCasoSaludList;

    public Doctor() {
    }

    public Doctor(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorUsuario() {
        return doctorUsuario;
    }

    public void setDoctorUsuario(String doctorUsuario) {
        this.doctorUsuario = doctorUsuario;
    }

    public String getDoctorClave() {
        return doctorClave;
    }

    public void setDoctorClave(String doctorClave) {
        this.doctorClave = doctorClave;
    }

    public Date getDoctorFechaRegistro() {
        return doctorFechaRegistro;
    }

    public void setDoctorFechaRegistro(Date doctorFechaRegistro) {
        this.doctorFechaRegistro = doctorFechaRegistro;
    }

    public String getDoctorCodigoColegiatura() {
        return doctorCodigoColegiatura;
    }

    public void setDoctorCodigoColegiatura(String doctorCodigoColegiatura) {
        this.doctorCodigoColegiatura = doctorCodigoColegiatura;
    }

    public String getDoctorDireccion() {
        return doctorDireccion;
    }

    public void setDoctorDireccion(String doctorDireccion) {
        this.doctorDireccion = doctorDireccion;
    }

    public String getDoctorTelefono() {
        return doctorTelefono;
    }

    public void setDoctorTelefono(String doctorTelefono) {
        this.doctorTelefono = doctorTelefono;
    }

    public String getDoctorDireccionDetalle() {
        return doctorDireccionDetalle;
    }

    public void setDoctorDireccionDetalle(String doctorDireccionDetalle) {
        this.doctorDireccionDetalle = doctorDireccionDetalle;
    }

    public Double getDoctorLongitud() {
        return doctorLongitud;
    }

    public void setDoctorLongitud(Double doctorLongitud) {
        this.doctorLongitud = doctorLongitud;
    }

    public Double getDoctorLatitud() {
        return doctorLatitud;
    }

    public void setDoctorLatitud(Double doctorLatitud) {
        this.doctorLatitud = doctorLatitud;
    }

    public Integer getDoctorPuntaje() {
        return doctorPuntaje;
    }

    public void setDoctorPuntaje(Integer doctorPuntaje) {
        this.doctorPuntaje = doctorPuntaje;
    }

    public Short getDoctorEstado() {
        return doctorEstado;
    }

    public void setDoctorEstado(Short doctorEstado) {
        this.doctorEstado = doctorEstado;
    }

    public Date getDoctorFechaUltimoIngreso() {
        return doctorFechaUltimoIngreso;
    }

    public void setDoctorFechaUltimoIngreso(Date doctorFechaUltimoIngreso) {
        this.doctorFechaUltimoIngreso = doctorFechaUltimoIngreso;
    }

    @XmlTransient
    public List<RespuestaPreguntaPaciente> getRespuestaPreguntaPacienteList() {
        return respuestaPreguntaPacienteList;
    }

    public void setRespuestaPreguntaPacienteList(List<RespuestaPreguntaPaciente> respuestaPreguntaPacienteList) {
        this.respuestaPreguntaPacienteList = respuestaPreguntaPacienteList;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
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
        hash += (doctorId != null ? doctorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctor)) {
            return false;
        }
        Doctor other = (Doctor) object;
        if ((this.doctorId == null && other.doctorId != null) || (this.doctorId != null && !this.doctorId.equals(other.doctorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.Doctor[ doctorId=" + doctorId + " ]";
    }
    
}
