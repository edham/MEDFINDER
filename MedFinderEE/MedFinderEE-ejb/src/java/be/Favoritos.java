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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EdHam
 */
@Entity
@Table(name = "favoritos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Favoritos.findAll", query = "SELECT f FROM Favoritos f"),
    @NamedQuery(name = "Favoritos.findByFavoritosId", query = "SELECT f FROM Favoritos f WHERE f.favoritosId = :favoritosId")})
public class Favoritos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Favoritos_Id")
    private Integer favoritosId;
    @JoinColumn(name = "Doctor_Id", referencedColumnName = "Doctor_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctorId;
    @JoinColumn(name = "Usuario_Id", referencedColumnName = "Usuario_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarioId;

    public Favoritos() {
    }

    public Favoritos(Integer favoritosId) {
        this.favoritosId = favoritosId;
    }

    public Integer getFavoritosId() {
        return favoritosId;
    }

    public void setFavoritosId(Integer favoritosId) {
        this.favoritosId = favoritosId;
    }

    public Doctor getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Doctor doctorId) {
        this.doctorId = doctorId;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (favoritosId != null ? favoritosId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Favoritos)) {
            return false;
        }
        Favoritos other = (Favoritos) object;
        if ((this.favoritosId == null && other.favoritosId != null) || (this.favoritosId != null && !this.favoritosId.equals(other.favoritosId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.Favoritos[ favoritosId=" + favoritosId + " ]";
    }
    
}
