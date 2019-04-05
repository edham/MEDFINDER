package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Clinica;
import modelo.Especialidad;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-04T22:06:34")
@StaticMetamodel(DetalleClinicaEspecialidad.class)
public class DetalleClinicaEspecialidad_ { 

    public static volatile SingularAttribute<DetalleClinicaEspecialidad, String> detalleHorario;
    public static volatile SingularAttribute<DetalleClinicaEspecialidad, Date> horarioInicio;
    public static volatile SingularAttribute<DetalleClinicaEspecialidad, Date> horarioFin;
    public static volatile SingularAttribute<DetalleClinicaEspecialidad, Integer> pKId;
    public static volatile SingularAttribute<DetalleClinicaEspecialidad, Integer> estado;
    public static volatile SingularAttribute<DetalleClinicaEspecialidad, Date> fechaModificacion;
    public static volatile SingularAttribute<DetalleClinicaEspecialidad, Date> fechaRegistro;
    public static volatile SingularAttribute<DetalleClinicaEspecialidad, Clinica> clinica;
    public static volatile SingularAttribute<DetalleClinicaEspecialidad, Especialidad> especialidad;

}