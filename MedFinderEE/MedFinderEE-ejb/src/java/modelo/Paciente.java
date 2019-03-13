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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Edham
 */
@Entity
@Table(name = "paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findByPKId", query = "SELECT p FROM Paciente p WHERE p.pKId = :pKId"),
    @NamedQuery(name = "Paciente.findByEstatura", query = "SELECT p FROM Paciente p WHERE p.estatura = :estatura"),
    @NamedQuery(name = "Paciente.findByTipo", query = "SELECT p FROM Paciente p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Paciente.findByCardiovascular", query = "SELECT p FROM Paciente p WHERE p.cardiovascular = :cardiovascular"),
    @NamedQuery(name = "Paciente.findByMusculares", query = "SELECT p FROM Paciente p WHERE p.musculares = :musculares"),
    @NamedQuery(name = "Paciente.findByDigestivos", query = "SELECT p FROM Paciente p WHERE p.digestivos = :digestivos"),
    @NamedQuery(name = "Paciente.findByAlergicos", query = "SELECT p FROM Paciente p WHERE p.alergicos = :alergicos"),
    @NamedQuery(name = "Paciente.findByAlcohol", query = "SELECT p FROM Paciente p WHERE p.alcohol = :alcohol"),
    @NamedQuery(name = "Paciente.findByTabaquismo", query = "SELECT p FROM Paciente p WHERE p.tabaquismo = :tabaquismo"),
    @NamedQuery(name = "Paciente.findByDrogas", query = "SELECT p FROM Paciente p WHERE p.drogas = :drogas"),
    @NamedQuery(name = "Paciente.findByPsicologicos", query = "SELECT p FROM Paciente p WHERE p.psicologicos = :psicologicos"),
    @NamedQuery(name = "Paciente.findByFechaRegistro", query = "SELECT p FROM Paciente p WHERE p.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Paciente.findByFechaModificacion", query = "SELECT p FROM Paciente p WHERE p.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Paciente.findByEstado", query = "SELECT p FROM Paciente p WHERE p.estado = :estado")})
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_Id")
    private Integer pKId;
    @Column(name = "Estatura")
    private Integer estatura;
    @Column(name = "Tipo")
    private Integer tipo;
    @Column(name = "Cardiovascular")
    private Boolean cardiovascular;
    @Column(name = "Musculares")
    private Boolean musculares;
    @Column(name = "Digestivos")
    private Boolean digestivos;
    @Column(name = "Alergicos")
    private Boolean alergicos;
    @Column(name = "Alcohol")
    private Boolean alcohol;
    @Column(name = "Tabaquismo")
    private Boolean tabaquismo;
    @Column(name = "Drogas")
    private Boolean drogas;
    @Column(name = "Psicologicos")
    private Boolean psicologicos;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Estado")
    private Integer estado;
    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private List<PreguntaPaciente> preguntaPacienteList;
    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private List<CitaPaciente> citaPacienteList;
    @JoinColumn(name = "FK_Persona", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;
    @JoinColumn(name = "FK_Usuario", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    public Paciente() {
    }

    public Paciente(Integer pKId) {
        this.pKId = pKId;
    }

    public Integer getPKId() {
        return pKId;
    }

    public void setPKId(Integer pKId) {
        this.pKId = pKId;
    }

    public Integer getEstatura() {
        return estatura;
    }

    public void setEstatura(Integer estatura) {
        this.estatura = estatura;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Boolean getCardiovascular() {
        return cardiovascular;
    }

    public void setCardiovascular(Boolean cardiovascular) {
        this.cardiovascular = cardiovascular;
    }

    public Boolean getMusculares() {
        return musculares;
    }

    public void setMusculares(Boolean musculares) {
        this.musculares = musculares;
    }

    public Boolean getDigestivos() {
        return digestivos;
    }

    public void setDigestivos(Boolean digestivos) {
        this.digestivos = digestivos;
    }

    public Boolean getAlergicos() {
        return alergicos;
    }

    public void setAlergicos(Boolean alergicos) {
        this.alergicos = alergicos;
    }

    public Boolean getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Boolean alcohol) {
        this.alcohol = alcohol;
    }

    public Boolean getTabaquismo() {
        return tabaquismo;
    }

    public void setTabaquismo(Boolean tabaquismo) {
        this.tabaquismo = tabaquismo;
    }

    public Boolean getDrogas() {
        return drogas;
    }

    public void setDrogas(Boolean drogas) {
        this.drogas = drogas;
    }

    public Boolean getPsicologicos() {
        return psicologicos;
    }

    public void setPsicologicos(Boolean psicologicos) {
        this.psicologicos = psicologicos;
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
        hash += (pKId != null ? pKId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.pKId == null && other.pKId != null) || (this.pKId != null && !this.pKId.equals(other.pKId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Paciente[ pKId=" + pKId + " ]";
    }
    
}
