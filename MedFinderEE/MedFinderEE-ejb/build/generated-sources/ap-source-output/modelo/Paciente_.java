package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.CitaPaciente;
import modelo.Persona;
import modelo.PreguntaPaciente;
import modelo.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-07T15:52:33")
@StaticMetamodel(Paciente.class)
public class Paciente_ { 

    public static volatile SingularAttribute<Paciente, Boolean> alcohol;
    public static volatile ListAttribute<Paciente, PreguntaPaciente> preguntaPacienteList;
    public static volatile SingularAttribute<Paciente, Integer> pKId;
    public static volatile SingularAttribute<Paciente, Boolean> tipo;
    public static volatile SingularAttribute<Paciente, Integer> estado;
    public static volatile SingularAttribute<Paciente, Date> fechaModificacion;
    public static volatile SingularAttribute<Paciente, Persona> persona;
    public static volatile SingularAttribute<Paciente, Boolean> psicologicos;
    public static volatile SingularAttribute<Paciente, Boolean> tabaquismo;
    public static volatile SingularAttribute<Paciente, Date> fechaRegistro;
    public static volatile SingularAttribute<Paciente, Boolean> drogas;
    public static volatile SingularAttribute<Paciente, Integer> estatura;
    public static volatile SingularAttribute<Paciente, Boolean> alergicos;
    public static volatile SingularAttribute<Paciente, Boolean> cardiovascular;
    public static volatile SingularAttribute<Paciente, Boolean> musculares;
    public static volatile SingularAttribute<Paciente, Boolean> digestivos;
    public static volatile SingularAttribute<Paciente, Usuario> usuario;
    public static volatile ListAttribute<Paciente, CitaPaciente> citaPacienteList;

}