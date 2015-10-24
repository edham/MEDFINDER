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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EdHam
 */
@Entity
@Table(name = "paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findByPacienteId", query = "SELECT p FROM Paciente p WHERE p.pacienteId = :pacienteId"),
    @NamedQuery(name = "Paciente.findByPacienteEstatura", query = "SELECT p FROM Paciente p WHERE p.pacienteEstatura = :pacienteEstatura"),
    @NamedQuery(name = "Paciente.findByPacienteTipo", query = "SELECT p FROM Paciente p WHERE p.pacienteTipo = :pacienteTipo"),
    @NamedQuery(name = "Paciente.findByPacienteCardiovascular", query = "SELECT p FROM Paciente p WHERE p.pacienteCardiovascular = :pacienteCardiovascular"),
    @NamedQuery(name = "Paciente.findByPacienteMusculares", query = "SELECT p FROM Paciente p WHERE p.pacienteMusculares = :pacienteMusculares"),
    @NamedQuery(name = "Paciente.findByPacienteDigestivos", query = "SELECT p FROM Paciente p WHERE p.pacienteDigestivos = :pacienteDigestivos"),
    @NamedQuery(name = "Paciente.findByPacienteAlergicos", query = "SELECT p FROM Paciente p WHERE p.pacienteAlergicos = :pacienteAlergicos"),
    @NamedQuery(name = "Paciente.findByPacienteAlcohol", query = "SELECT p FROM Paciente p WHERE p.pacienteAlcohol = :pacienteAlcohol"),
    @NamedQuery(name = "Paciente.findByPacienteTabaquismo", query = "SELECT p FROM Paciente p WHERE p.pacienteTabaquismo = :pacienteTabaquismo"),
    @NamedQuery(name = "Paciente.findByPacienteDrogas", query = "SELECT p FROM Paciente p WHERE p.pacienteDrogas = :pacienteDrogas"),
    @NamedQuery(name = "Paciente.findByPacientePsicologicos", query = "SELECT p FROM Paciente p WHERE p.pacientePsicologicos = :pacientePsicologicos"),
    @NamedQuery(name = "Paciente.findByPacienteEstado", query = "SELECT p FROM Paciente p WHERE p.pacienteEstado = :pacienteEstado"),
    @NamedQuery(name = "Paciente.findByPacienteFechaRegistro", query = "SELECT p FROM Paciente p WHERE p.pacienteFechaRegistro = :pacienteFechaRegistro")})
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Paciente_Id")
    private Integer pacienteId;
    @Column(name = "Paciente_Estatura")
    private Integer pacienteEstatura;
    @Column(name = "Paciente_Tipo")
    private Short pacienteTipo;
    @Column(name = "Paciente_Cardiovascular")
    private Boolean pacienteCardiovascular;
    @Column(name = "Paciente_Musculares")
    private Boolean pacienteMusculares;
    @Column(name = "Paciente_Digestivos")
    private Boolean pacienteDigestivos;
    @Column(name = "Paciente_Alergicos")
    private Boolean pacienteAlergicos;
    @Column(name = "Paciente_Alcohol")
    private Boolean pacienteAlcohol;
    @Column(name = "Paciente_Tabaquismo")
    private Boolean pacienteTabaquismo;
    @Column(name = "Paciente_Drogas")
    private Boolean pacienteDrogas;
    @Column(name = "Paciente_Psicologicos")
    private Boolean pacientePsicologicos;
    @Column(name = "Paciente_Estado")
    private Short pacienteEstado;
    @Column(name = "Paciente_FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pacienteFechaRegistro;
    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private List<PreguntaPaciente> preguntaPacienteList;
    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private List<CitaPaciente> citaPacienteList;
    @JoinColumn(name = "Persona_Id", referencedColumnName = "Persona_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;
    @JoinColumn(name = "Usuario_Id", referencedColumnName = "Usuario_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    public Paciente() {
    }

    public Paciente(Integer pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Integer getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Integer pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Integer getPacienteEstatura() {
        return pacienteEstatura;
    }

    public void setPacienteEstatura(Integer pacienteEstatura) {
        this.pacienteEstatura = pacienteEstatura;
    }

    public Short getPacienteTipo() {
        return pacienteTipo;
    }

    public void setPacienteTipo(Short pacienteTipo) {
        this.pacienteTipo = pacienteTipo;
    }

    public Boolean getPacienteCardiovascular() {
        return pacienteCardiovascular;
    }

    public void setPacienteCardiovascular(Boolean pacienteCardiovascular) {
        this.pacienteCardiovascular = pacienteCardiovascular;
    }

    public Boolean getPacienteMusculares() {
        return pacienteMusculares;
    }

    public void setPacienteMusculares(Boolean pacienteMusculares) {
        this.pacienteMusculares = pacienteMusculares;
    }

    public Boolean getPacienteDigestivos() {
        return pacienteDigestivos;
    }

    public void setPacienteDigestivos(Boolean pacienteDigestivos) {
        this.pacienteDigestivos = pacienteDigestivos;
    }

    public Boolean getPacienteAlergicos() {
        return pacienteAlergicos;
    }

    public void setPacienteAlergicos(Boolean pacienteAlergicos) {
        this.pacienteAlergicos = pacienteAlergicos;
    }

    public Boolean getPacienteAlcohol() {
        return pacienteAlcohol;
    }

    public void setPacienteAlcohol(Boolean pacienteAlcohol) {
        this.pacienteAlcohol = pacienteAlcohol;
    }

    public Boolean getPacienteTabaquismo() {
        return pacienteTabaquismo;
    }

    public void setPacienteTabaquismo(Boolean pacienteTabaquismo) {
        this.pacienteTabaquismo = pacienteTabaquismo;
    }

    public Boolean getPacienteDrogas() {
        return pacienteDrogas;
    }

    public void setPacienteDrogas(Boolean pacienteDrogas) {
        this.pacienteDrogas = pacienteDrogas;
    }

    public Boolean getPacientePsicologicos() {
        return pacientePsicologicos;
    }

    public void setPacientePsicologicos(Boolean pacientePsicologicos) {
        this.pacientePsicologicos = pacientePsicologicos;
    }

    public Short getPacienteEstado() {
        return pacienteEstado;
    }

    public void setPacienteEstado(Short pacienteEstado) {
        this.pacienteEstado = pacienteEstado;
    }

    public Date getPacienteFechaRegistro() {
        return pacienteFechaRegistro;
    }

    public void setPacienteFechaRegistro(Date pacienteFechaRegistro) {
        this.pacienteFechaRegistro = pacienteFechaRegistro;
    }

    @XmlTransient
    public List<PreguntaPaciente> getPreguntaPacienteList() {
        return preguntaPacienteList;
    }

    public void setPreguntaPacienteList(List<PreguntaPaciente> preguntaPacienteList) {
        this.preguntaPacienteList = preguntaPacienteList;
    }

    @XmlTransient
    public List<CitaPaciente> getCitaPacienteList() {
        return citaPacienteList;
    }

    public void setCitaPacienteList(List<CitaPaciente> citaPacienteList) {
        this.citaPacienteList = citaPacienteList;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
        hash += (pacienteId != null ? pacienteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.pacienteId == null && other.pacienteId != null) || (this.pacienteId != null && !this.pacienteId.equals(other.pacienteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.Paciente[ pacienteId=" + pacienteId + " ]";
    }
    
}
