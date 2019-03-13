package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.CitaPaciente;
import modelo.Especialidad;
import modelo.Favoritos;
import modelo.Persona;
import modelo.RespuestaCasoSalud;
import modelo.RespuestaPreguntaPaciente;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-14T14:31:13")
@StaticMetamodel(Doctor.class)
public class Doctor_ { 

    public static volatile SingularAttribute<Doctor, Integer> pKId;
    public static volatile SingularAttribute<Doctor, Double> latitud;
    public static volatile SingularAttribute<Doctor, Integer> estado;
    public static volatile SingularAttribute<Doctor, Date> fechaModificacion;
    public static volatile SingularAttribute<Doctor, Persona> persona;
    public static volatile SingularAttribute<Doctor, Date> fechaRegistro;
    public static volatile SingularAttribute<Doctor, String> direccion;
    public static volatile SingularAttribute<Doctor, Especialidad> especialidad;
    public static volatile SingularAttribute<Doctor, Date> fechaUltimoAcceso;
    public static volatile SingularAttribute<Doctor, String> codigoColegiatura;
    public static volatile SingularAttribute<Doctor, Double> longitud;
    public static volatile SingularAttribute<Doctor, Integer> puntaje;
    public static volatile ListAttribute<Doctor, RespuestaPreguntaPaciente> respuestaPreguntaPacienteList;
    public static volatile SingularAttribute<Doctor, String> direccionDetalle;
    public static volatile ListAttribute<Doctor, Favoritos> favoritosList;
    public static volatile ListAttribute<Doctor, CitaPaciente> citaPacienteList;
    public static volatile SingularAttribute<Doctor, String> telefono;
    public static volatile ListAttribute<Doctor, RespuestaCasoSalud> respuestaCasoSaludList;

}