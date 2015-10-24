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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuarioId", query = "SELECT u FROM Usuario u WHERE u.usuarioId = :usuarioId"),
    @NamedQuery(name = "Usuario.findByUsuarioNick", query = "SELECT u FROM Usuario u WHERE u.usuarioNick = :usuarioNick"),
    @NamedQuery(name = "Usuario.findByUsuarioClave", query = "SELECT u FROM Usuario u WHERE u.usuarioClave = :usuarioClave"),
    @NamedQuery(name = "Usuario.findByUsuarioEstado", query = "SELECT u FROM Usuario u WHERE u.usuarioEstado = :usuarioEstado"),
    @NamedQuery(name = "Usuario.findByUsuarioFechaRegistro", query = "SELECT u FROM Usuario u WHERE u.usuarioFechaRegistro = :usuarioFechaRegistro"),
    @NamedQuery(name = "Usuario.findByUsuarioFechaUltimoAcceso", query = "SELECT u FROM Usuario u WHERE u.usuarioFechaUltimoAcceso = :usuarioFechaUltimoAcceso"),
    @NamedQuery(name = "Usuario.findByPersonaId", query = "SELECT u FROM Usuario u WHERE u.personaId = :personaId")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Usuario_Id")
    private Integer usuarioId;
    @Size(max = 45)
    @Column(name = "Usuario_Nick")
    private String usuarioNick;
    @Size(max = 45)
    @Column(name = "Usuario_Clave")
    private String usuarioClave;
    @Column(name = "Usuario_Estado")
    private Integer usuarioEstado;
    @Column(name = "Usuario_FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFechaRegistro;
    @Column(name = "usuario_FechaUltimoAcceso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuarioFechaUltimoAcceso;
    @Column(name = "Persona_Id")
    private Integer personaId;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Paciente> pacienteList;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Favoritos> favoritosList;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<CasoSaludPuntaje> casoSaludPuntajeList;

    public Usuario() {
    }

    public Usuario(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNick() {
        return usuarioNick;
    }

    public void setUsuarioNick(String usuarioNick) {
        this.usuarioNick = usuarioNick;
    }

    public String getUsuarioClave() {
        return usuarioClave;
    }

    public void setUsuarioClave(String usuarioClave) {
        this.usuarioClave = usuarioClave;
    }

    public Integer getUsuarioEstado() {
        return usuarioEstado;
    }

    public void setUsuarioEstado(Integer usuarioEstado) {
        this.usuarioEstado = usuarioEstado;
    }

    public Date getUsuarioFechaRegistro() {
        return usuarioFechaRegistro;
    }

    public void setUsuarioFechaRegistro(Date usuarioFechaRegistro) {
        this.usuarioFechaRegistro = usuarioFechaRegistro;
    }

    public Date getUsuarioFechaUltimoAcceso() {
        return usuarioFechaUltimoAcceso;
    }

    public void setUsuarioFechaUltimoAcceso(Date usuarioFechaUltimoAcceso) {
        this.usuarioFechaUltimoAcceso = usuarioFechaUltimoAcceso;
    }

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    @XmlTransient
    public List<Paciente> getPacienteList() {
        return pacienteList;
    }

    public void setPacienteList(List<Paciente> pacienteList) {
        this.pacienteList = pacienteList;
    }

    @XmlTransient
    public List<Favoritos> getFavoritosList() {
        return favoritosList;
    }

    public void setFavoritosList(List<Favoritos> favoritosList) {
        this.favoritosList = favoritosList;
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
        hash += (usuarioId != null ? usuarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuarioId == null && other.usuarioId != null) || (this.usuarioId != null && !this.usuarioId.equals(other.usuarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.Usuario[ usuarioId=" + usuarioId + " ]";
    }
    
}