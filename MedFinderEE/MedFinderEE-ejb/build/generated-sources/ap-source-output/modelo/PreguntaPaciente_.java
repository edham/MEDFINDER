package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Especialidad;
import modelo.Paciente;
import modelo.RespuestaPreguntaPaciente;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-10T01:57:44")
@StaticMetamodel(PreguntaPaciente.class)
public class PreguntaPaciente_ { 

    public static volatile SingularAttribute<PreguntaPaciente, Integer> pKId;
    public static volatile SingularAttribute<PreguntaPaciente, Integer> estado;
    public static volatile SingularAttribute<PreguntaPaciente, Integer> tipo;
    public static volatile ListAttribute<PreguntaPaciente, RespuestaPreguntaPaciente> respuestaPreguntaPacienteList;
    public static volatile SingularAttribute<PreguntaPaciente, Date> fechaInicio;
    public static volatile SingularAttribute<PreguntaPaciente, Paciente> paciente;
    public static volatile SingularAttribute<PreguntaPaciente, String> asunto;
    public static volatile SingularAttribute<PreguntaPaciente, byte[]> imagen;
    public static volatile SingularAttribute<PreguntaPaciente, Date> fechaFin;
    public static volatile SingularAttribute<PreguntaPaciente, Especialidad> especialidad;
    public static volatile SingularAttribute<PreguntaPaciente, String> detalle;

}