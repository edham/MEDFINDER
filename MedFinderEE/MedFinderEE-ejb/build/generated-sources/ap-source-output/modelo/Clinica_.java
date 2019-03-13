package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.DetalleClinicaEspecialidad;
import modelo.DetalleClinicaSeguro;
import modelo.Distrito;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-14T14:31:13")
@StaticMetamodel(Clinica.class)
public class Clinica_ { 

    public static volatile SingularAttribute<Clinica, String> descripcion;
    public static volatile ListAttribute<Clinica, DetalleClinicaSeguro> detalleClinicaSeguroList;
    public static volatile SingularAttribute<Clinica, Date> horarioInicio;
    public static volatile SingularAttribute<Clinica, Date> horarioFin;
    public static volatile SingularAttribute<Clinica, Distrito> distrito;
    public static volatile SingularAttribute<Clinica, Integer> pKId;
    public static volatile SingularAttribute<Clinica, Double> latitud;
    public static volatile SingularAttribute<Clinica, Integer> estado;
    public static volatile SingularAttribute<Clinica, Date> fechaModificacion;
    public static volatile SingularAttribute<Clinica, Date> fechaRegistro;
    public static volatile SingularAttribute<Clinica, String> direccion;
    public static volatile SingularAttribute<Clinica, String> detalleAtencion;
    public static volatile SingularAttribute<Clinica, String> nombre;
    public static volatile SingularAttribute<Clinica, Double> longitud;
    public static volatile SingularAttribute<Clinica, byte[]> logo;
    public static volatile SingularAttribute<Clinica, String> telefono;
    public static volatile ListAttribute<Clinica, DetalleClinicaEspecialidad> detalleClinicaEspecialidadList;
    public static volatile SingularAttribute<Clinica, String> slogan;

}