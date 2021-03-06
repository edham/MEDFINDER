package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Distrito;
import modelo.Doctor;
import modelo.EncuestaDetallePersona;
import modelo.Paciente;
import modelo.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-10T01:57:44")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> apellidoPaterno;
    public static volatile SingularAttribute<Persona, Distrito> distrito;
    public static volatile SingularAttribute<Persona, Integer> pKId;
    public static volatile SingularAttribute<Persona, Integer> estado;
    public static volatile SingularAttribute<Persona, Date> fechaModificacion;
    public static volatile ListAttribute<Persona, Usuario> usuarioList;
    public static volatile SingularAttribute<Persona, Date> fechaNacimiento;
    public static volatile ListAttribute<Persona, Doctor> doctorList;
    public static volatile SingularAttribute<Persona, Date> fechaRegistro;
    public static volatile SingularAttribute<Persona, String> direccion;
    public static volatile ListAttribute<Persona, EncuestaDetallePersona> encuestaDetallePersonaList;
    public static volatile SingularAttribute<Persona, String> nombre;
    public static volatile ListAttribute<Persona, Paciente> pacienteList;
    public static volatile SingularAttribute<Persona, String> apellidoMaterno;
    public static volatile SingularAttribute<Persona, byte[]> foto;
    public static volatile SingularAttribute<Persona, Boolean> sexo;
    public static volatile SingularAttribute<Persona, String> telefono;
    public static volatile SingularAttribute<Persona, String> dni;
    public static volatile SingularAttribute<Persona, String> email;

}