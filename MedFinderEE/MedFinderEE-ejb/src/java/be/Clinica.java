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
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Clinica.findByClinicaId", query = "SELECT c FROM Clinica c WHERE c.clinicaId = :clinicaId"),
    @NamedQuery(name = "Clinica.findByClinicaNombre", query = "SELECT c FROM Clinica c WHERE c.clinicaNombre = :clinicaNombre"),
    @NamedQuery(name = "Clinica.findByClinicaSlogan", query = "SELECT c FROM Clinica c WHERE c.clinicaSlogan = :clinicaSlogan"),
    @NamedQuery(name = "Clinica.findByClinicaDireccion", query = "SELECT c FROM Clinica c WHERE c.clinicaDireccion = :clinicaDireccion"),
    @NamedQuery(name = "Clinica.findByClinicaDescripcion", query = "SELECT c FROM Clinica c WHERE c.clinicaDescripcion = :clinicaDescripcion"),
    @NamedQuery(name = "Clinica.findByClinicaHorarioInicio", query = "SELECT c FROM Clinica c WHERE c.clinicaHorarioInicio = :clinicaHorarioInicio"),
    @NamedQuery(name = "Clinica.findByClinicaHorarioFin", query = "SELECT c FROM Clinica c WHERE c.clinicaHorarioFin = :clinicaHorarioFin"),
    @NamedQuery(name = "Clinica.findByClinicaDetalleAtencion", query = "SELECT c FROM Clinica c WHERE c.clinicaDetalleAtencion = :clinicaDetalleAtencion"),
    @NamedQuery(name = "Clinica.findByClinicaTelefono", query = "SELECT c FROM Clinica c WHERE c.clinicaTelefono = :clinicaTelefono"),
    @NamedQuery(name = "Clinica.findByClinicaLongitud", query = "SELECT c FROM Clinica c WHERE c.clinicaLongitud = :clinicaLongitud"),
    @NamedQuery(name = "Clinica.findByClinicaLatitud", query = "SELECT c FROM Clinica c WHERE c.clinicaLatitud = :clinicaLatitud"),
    @NamedQuery(name = "Clinica.findByClinicaFechaRegistro", query = "SELECT c FROM Clinica c WHERE c.clinicaFechaRegistro = :clinicaFechaRegistro"),
    @NamedQuery(name = "Clinica.findByClinicaEstado", query = "SELECT c FROM Clinica c WHERE c.clinicaEstado = :clinicaEstado")})
public class Clinica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Clinica_Id")
    private Integer clinicaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Clinica_Nombre")
    private String clinicaNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Clinica_Slogan")
    private String clinicaSlogan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Clinica_Direccion")
    private String clinicaDireccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Clinica_Descripcion")
    private String clinicaDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Clinica_Horario_Inicio")
    @Temporal(TemporalType.TIME)
    private Date clinicaHorarioInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Clinica_Horario_Fin")
    @Temporal(TemporalType.TIME)
    private Date clinicaHorarioFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Clinica_Detalle_Atencion")
    private String clinicaDetalleAtencion;
    @Lob
    @Column(name = "Clinica_Logo")
    private byte[] clinicaLogo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Clinica_Telefono")
    private String clinicaTelefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Clinica_Longitud")
    private double clinicaLongitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Clinica_Latitud")
    private double clinicaLatitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Clinica_FechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date clinicaFechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Clinica_Estado")
    private short clinicaEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clinicaId", fetch = FetchType.LAZY)
    private List<DetalleClinicaSeguro> detalleClinicaSeguroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clinicaId", fetch = FetchType.LAZY)
    private List<DetalleClinicaEspecialidad> detalleClinicaEspecialidadList;

    public Clinica() {
    }

    public Clinica(Integer clinicaId) {
        this.clinicaId = clinicaId;
    }

    public Clinica(Integer clinicaId, String clinicaNombre, String clinicaSlogan, String clinicaDireccion, String clinicaDescripcion, Date clinicaHorarioInicio, Date clinicaHorarioFin, String clinicaDetalleAtencion, String clinicaTelefono, double clinicaLongitud, double clinicaLatitud, Date clinicaFechaRegistro, short clinicaEstado) {
        this.clinicaId = clinicaId;
        this.clinicaNombre = clinicaNombre;
        this.clinicaSlogan = clinicaSlogan;
        this.clinicaDireccion = clinicaDireccion;
        this.clinicaDescripcion = clinicaDescripcion;
        this.clinicaHorarioInicio = clinicaHorarioInicio;
        this.clinicaHorarioFin = clinicaHorarioFin;
        this.clinicaDetalleAtencion = clinicaDetalleAtencion;
        this.clinicaTelefono = clinicaTelefono;
        this.clinicaLongitud = clinicaLongitud;
        this.clinicaLatitud = clinicaLatitud;
        this.clinicaFechaRegistro = clinicaFechaRegistro;
        this.clinicaEstado = clinicaEstado;
    }

    public Integer getClinicaId() {
        return clinicaId;
    }

    public void setClinicaId(Integer clinicaId) {
        this.clinicaId = clinicaId;
    }

    public String getClinicaNombre() {
        return clinicaNombre;
    }

    public void setClinicaNombre(String clinicaNombre) {
        this.clinicaNombre = clinicaNombre;
    }

    public String getClinicaSlogan() {
        return clinicaSlogan;
    }

    public void setClinicaSlogan(String clinicaSlogan) {
        this.clinicaSlogan = clinicaSlogan;
    }

    public String getClinicaDireccion() {
        return clinicaDireccion;
    }

    public void setClinicaDireccion(String clinicaDireccion) {
        this.clinicaDireccion = clinicaDireccion;
    }

    public String getClinicaDescripcion() {
        return clinicaDescripcion;
    }

    public void setClinicaDescripcion(String clinicaDescripcion) {
        this.clinicaDescripcion = clinicaDescripcion;
    }

    public Date getClinicaHorarioInicio() {
        return clinicaHorarioInicio;
    }

    public void setClinicaHorarioInicio(Date clinicaHorarioInicio) {
        this.clinicaHorarioInicio = clinicaHorarioInicio;
    }

    public Date getClinicaHorarioFin() {
        return clinicaHorarioFin;
    }

    public void setClinicaHorarioFin(Date clinicaHorarioFin) {
        this.clinicaHorarioFin = clinicaHorarioFin;
    }

    public String getClinicaDetalleAtencion() {
        return clinicaDetalleAtencion;
    }

    public void setClinicaDetalleAtencion(String clinicaDetalleAtencion) {
        this.clinicaDetalleAtencion = clinicaDetalleAtencion;
    }

    public byte[] getClinicaLogo() {
        return clinicaLogo;
    }

    public void setClinicaLogo(byte[] clinicaLogo) {
        this.clinicaLogo = clinicaLogo;
    }

    public String getClinicaTelefono() {
        return clinicaTelefono;
    }

    public void setClinicaTelefono(String clinicaTelefono) {
        this.clinicaTelefono = clinicaTelefono;
    }

    public double getClinicaLongitud() {
        return clinicaLongitud;
    }

    public void setClinicaLongitud(double clinicaLongitud) {
        this.clinicaLongitud = clinicaLongitud;
    }

    public double getClinicaLatitud() {
        return clinicaLatitud;
    }

    public void setClinicaLatitud(double clinicaLatitud) {
        this.clinicaLatitud = clinicaLatitud;
    }

    public Date getClinicaFechaRegistro() {
        return clinicaFechaRegistro;
    }

    public void setClinicaFechaRegistro(Date clinicaFechaRegistro) {
        this.clinicaFechaRegistro = clinicaFechaRegistro;
    }

    public short getClinicaEstado() {
        return clinicaEstado;
    }

    public void setClinicaEstado(short clinicaEstado) {
        this.clinicaEstado = clinicaEstado;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clinicaId != null ? clinicaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clinica)) {
            return false;
        }
        Clinica other = (Clinica) object;
        if ((this.clinicaId == null && other.clinicaId != null) || (this.clinicaId != null && !this.clinicaId.equals(other.clinicaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.Clinica[ clinicaId=" + clinicaId + " ]";
    }
    
}
