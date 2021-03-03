package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Doctor;
import modelo.PreguntaPaciente;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-10T01:57:44")
@StaticMetamodel(RespuestaPreguntaPaciente.class)
public class RespuestaPreguntaPaciente_ { 

    public static volatile SingularAttribute<RespuestaPreguntaPaciente, Doctor> doctor;
    public static volatile SingularAttribute<RespuestaPreguntaPaciente, PreguntaPaciente> preguntaPaciente;
    public static volatile SingularAttribute<RespuestaPreguntaPaciente, Integer> pKId;
    public static volatile SingularAttribute<RespuestaPreguntaPaciente, Integer> estado;
    public static volatile SingularAttribute<RespuestaPreguntaPaciente, Date> fechaModificacion;
    public static volatile SingularAttribute<RespuestaPreguntaPaciente, Integer> puntaje;
    public static volatile SingularAttribute<RespuestaPreguntaPaciente, Date> fechaRegistro;
    public static volatile SingularAttribute<RespuestaPreguntaPaciente, String> detalle;

}