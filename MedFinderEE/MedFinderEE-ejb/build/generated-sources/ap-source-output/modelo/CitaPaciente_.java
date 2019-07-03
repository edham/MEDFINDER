package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Doctor;
import modelo.Paciente;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-03T00:46:05")
@StaticMetamodel(CitaPaciente.class)
public class CitaPaciente_ { 

    public static volatile SingularAttribute<CitaPaciente, Doctor> doctor;
    public static volatile SingularAttribute<CitaPaciente, Integer> pKId;
    public static volatile SingularAttribute<CitaPaciente, Integer> estado;
    public static volatile SingularAttribute<CitaPaciente, Integer> tipo;
    public static volatile SingularAttribute<CitaPaciente, Date> fechaModificacion;
    public static volatile SingularAttribute<CitaPaciente, Date> fechaRegistro;
    public static volatile SingularAttribute<CitaPaciente, Paciente> paciente;
    public static volatile SingularAttribute<CitaPaciente, Date> atencion;
    public static volatile SingularAttribute<CitaPaciente, String> detalle;
    public static volatile SingularAttribute<CitaPaciente, String> respuestaDoctor;

}