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
 * @author EdHam
 */
@Entity
@Table(name = "clinica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clinica.findAll", query = "SELECT c FROM Clinica c"),
    @NamedQuery(name = "Clinica.findByPKId", query = "SELECT c FROM Clinica c WHERE c.pKId = :pKId"),
    @NamedQuery(name = "Clinica.findByNombre", query = "SELECT c FROM Clinica c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Clinica.findBySlogan", query = "SELECT c FROM Clinica c WHERE c.slogan = :slogan"),
    @NamedQuery(name = "Clinica.findByDireccion", query = "SELECT c FROM Clinica c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Clinica.findByDescripcion", query = "SELECT c FROM Clinica c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Clinica.findByHorarioInicio", query = "SELECT c FROM Clinica c WHERE c.horarioInicio = :horarioInicio"),
    @NamedQuery(name = "Clinica.findByHorarioFin", query = "SELECT c FROM Clinica c WHERE c.horarioFin = :horarioFin"),
    @NamedQuery(name = "Clinica.findByDetalleAtencion", query = "SELECT c FROM Clinica c WHERE c.detalleAtencion = :detalleAtencion"),
    @NamedQuery(name = "Clinica.findByTelefono", query = "SELECT c FROM Clinica c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Clinica.findByLongitud", query = "SELECT c FROM Clinica c WHERE c.longitud = :longitud"),
    @NamedQuery(name = "Clinica.findByLatitud", query = "SELECT c FROM Clinica c WHERE c.latitud = :latitud"),
    @NamedQuery(name = "Clinica.findByFechaRegistro", query = "SELECT c FROM Clinica c WHERE c.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Clinica.findByFechaModificacion", query = "SELECT c FROM Clinica c WHERE c.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "Clinica.findByEstado", query = "SELECT c FROM Clinica c WHERE c.estado = :estado")})
public class Clinica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_Id")
    private Integer pKId;
    @Size(max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @Size(max = 100)
    @Column(name = "Slogan")
    private String slogan;
    @Size(max = 100)
    @Column(name = "Direccion")
    private String direccion;
    @Size(max = 200)
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "HorarioInicio")
    @Temporal(TemporalType.TIME)
    private Date horarioInicio;
    @Column(name = "HorarioFin")
    @Temporal(TemporalType.TIME)
    private Date horarioFin;
    @Size(max = 45)
    @Column(name = "DetalleAtencion")
    private String detalleAtencion;
    @Lob
    @Column(name = "Logo")
    private byte[] logo;
    @Size(max = 20)
    @Column(name = "Telefono")
    private String telefono;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Longitud")
    private Double longitud;
    @Column(name = "Latitud")
    private Double latitud;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "FechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "Estado")
    private Integer estado;
    @OneToMany(mappedBy = "clinica", fetch = FetchType.LAZY)
    private List<DetalleClinicaSeguro> detalleClinicaSeguroList;
    @OneToMany(mappedBy = "clinica", fetch = FetchType.LAZY)
    private List<DetalleClinicaEspecialidad> detalleClinicaEspecialidadList;
    @JoinColumn(name = "FK_Distrito", referencedColumnName = "PK_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Distrito distrito;

    public Clinica() {
    }

    public Clinica(Integer pKId) {
        this.pKId = pKId;
    }

    public Integer getPKId() {
        return pKId;
    }

    public void setPKId(Integer pKId) {
        this.pKId = pKId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Date getHorarioFin() {
        return horarioFin;
    }

    public void setHorarioFin(Date horarioFin) {
        this.horarioFin = horarioFin;
    }

    public String getDetalleAtencion() {
        return detalleAtencion;
    }

    public void setDetalleAtencion(String detalleAtencion) {
        this.detalleAtencion = detalleAtencion;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
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
    public List<DetalleClinicaSeguro> getDetalleClinicaSeguroList() {
        return detalleClinicaSeguroList;
    }

    public void setDetalleClinicaSeguroList(List<DetalleClinicaSeguro> detalleClinicaSeguroList) {
        this.detalleClinicaSeguroList = detalleClinicaSeguroList;
    }

    @XmlTransient
    public List<DetalleClinicaEspecialidad> getDetalleClinicaEspecialidadList() {
        return detalleClinicaEspecialidadList;
    }

    public void setDetalleClinicaEspecialidadList(List<DetalleClinicaEspecialidad> detalleClinicaEspecialidadList) {
        this.detalleClinicaEspecialidadList = detalleClinicaEspecialidadList;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
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
        if (!(object instanceof Clinica)) {
            return false;
        }
        Clinica other = (Clinica) object;
        if ((this.pKId == null && other.pKId != null) || (this.pKId != null && !this.pKId.equals(other.pKId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.Clinica[ pKId=" + pKId + " ]";
    }
    
}
