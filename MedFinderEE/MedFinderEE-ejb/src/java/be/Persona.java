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
import javax.persistence.Lob;
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
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByPersonaId", query = "SELECT p FROM Persona p WHERE p.personaId = :personaId"),
    @NamedQuery(name = "Persona.findByPersonaNombre", query = "SELECT p FROM Persona p WHERE p.personaNombre = :personaNombre"),
    @NamedQuery(name = "Persona.findByPersonaApellidoPaterno", query = "SELECT p FROM Persona p WHERE p.personaApellidoPaterno = :personaApellidoPaterno"),
    @NamedQuery(name = "Persona.findByPersonaApellidoMaterno", query = "SELECT p FROM Persona p WHERE p.personaApellidoMaterno = :personaApellidoMaterno"),
    @NamedQuery(name = "Persona.findByPersonaDNI", query = "SELECT p FROM Persona p WHERE p.personaDNI = :personaDNI"),
    @NamedQuery(name = "Persona.findByPersonaFechaRegistro", query = "SELECT p FROM Persona p WHERE p.personaFechaRegistro = :personaFechaRegistro"),
    @NamedQuery(name = "Persona.findByPersonaSexo", query = "SELECT p FROM Persona p WHERE p.personaSexo = :personaSexo"),
    @NamedQuery(name = "Persona.findByPersonaFechaNacimiento", query = "SELECT p FROM Persona p WHERE p.personaFechaNacimiento = :personaFechaNacimiento"),
    @NamedQuery(name = "Persona.findByPersonaTelefono", query = "SELECT p FROM Persona p WHERE p.personaTelefono = :personaTelefono"),
    @NamedQuery(name = "Persona.findByPersonaDireccion", query = "SELECT p FROM Persona p WHERE p.personaDireccion = :personaDireccion"),
    @NamedQuery(name = "Persona.findByPersonaEmail", query = "SELECT p FROM Persona p WHERE p.personaEmail = :personaEmail"),
    @NamedQuery(name = "Persona.findByPersonaEstado", query = "SELECT p FROM Persona p WHERE p.personaEstado = :personaEstado")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Persona_Id")
    private Integer personaId;
    @Size(max = 45)
    @Column(name = "Persona_Nombre")
    private String personaNombre;
    @Size(max = 45)
    @Column(name = "Persona_ApellidoPaterno")
    private String personaApellidoPaterno;
    @Size(max = 45)
    @Column(name = "Persona_ApellidoMaterno")
    private String personaApellidoMaterno;
    @Size(max = 8)
    @Column(name = "Persona_DNI")
    private String personaDNI;
    @Lob
    @Column(name = "Persona_Foto")
    private byte[] personaFoto;
    @Column(name = "Persona_FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date personaFechaRegistro;
    @Column(name = "Persona_Sexo")
    private Boolean personaSexo;
    @Column(name = "Persona_FechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date personaFechaNacimiento;
    @Size(max = 9)
    @Column(name = "Persona_Telefono")
    private String personaTelefono;
    @Size(max = 100)
    @Column(name = "Persona_Direccion")
    private String personaDireccion;
    @Size(max = 45)
    @Column(name = "Persona_Email")
    private String personaEmail;
    @Column(name = "Persona_Estado")
    private Short personaEstado;
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    private List<Doctor> doctorList;
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    private List<Paciente> pacienteList;

    public Persona() {
    }

    public Persona(Integer personaId) {
        this.personaId = personaId;
    }

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public String getPersonaNombre() {
        return personaNombre;
    }

    public void setPersonaNombre(String personaNombre) {
        this.personaNombre = personaNombre;
    }

    public String getPersonaApellidoPaterno() {
        return personaApellidoPaterno;
    }

    public void setPersonaApellidoPaterno(String personaApellidoPaterno) {
        this.personaApellidoPaterno = personaApellidoPaterno;
    }

    public String getPersonaApellidoMaterno() {
        return personaApellidoMaterno;
    }

    public void setPersonaApellidoMaterno(String personaApellidoMaterno) {
        this.personaApellidoMaterno = personaApellidoMaterno;
    }

    public String getPersonaDNI() {
        return personaDNI;
    }

    public void setPersonaDNI(String personaDNI) {
        this.personaDNI = personaDNI;
    }

    public byte[] getPersonaFoto() {
        return personaFoto;
    }

    public void setPersonaFoto(byte[] personaFoto) {
        this.personaFoto = personaFoto;
    }

    public Date getPersonaFechaRegistro() {
        return personaFechaRegistro;
    }

    public void setPersonaFechaRegistro(Date personaFechaRegistro) {
        this.personaFechaRegistro = personaFechaRegistro;
    }

    public Boolean getPersonaSexo() {
        return personaSexo;
    }

    public void setPersonaSexo(Boolean personaSexo) {
        this.personaSexo = personaSexo;
    }

    public Date getPersonaFechaNacimiento() {
        return personaFechaNacimiento;
    }

    public void setPersonaFechaNacimiento(Date personaFechaNacimiento) {
        this.personaFechaNacimiento = personaFechaNacimiento;
    }

    public String getPersonaTelefono() {
        return personaTelefono;
    }

    public void setPersonaTelefono(String personaTelefono) {
        this.personaTelefono = personaTelefono;
    }

    public String getPersonaDireccion() {
        return personaDireccion;
    }

    public void setPersonaDireccion(String personaDireccion) {
        this.personaDireccion = personaDireccion;
    }

    public String getPersonaEmail() {
        return personaEmail;
    }

    public void setPersonaEmail(String personaEmail) {
        this.personaEmail = personaEmail;
    }

    public Short getPersonaEstado() {
        return personaEstado;
    }

    public void setPersonaEstado(Short personaEstado) {
        this.personaEstado = personaEstado;
    }

    @XmlTransient
    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    @XmlTransient
    public List<Paciente> getPacienteList() {
        return pacienteList;
    }

    public void setPacienteList(List<Paciente> pacienteList) {
        this.pacienteList = pacienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaId != null ? personaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.personaId == null && other.personaId != null) || (this.personaId != null && !this.personaId.equals(other.personaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.Persona[ personaId=" + personaId + " ]";
    }
    
}
