package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.CasoSaludPuntaje;
import modelo.Favoritos;
import modelo.Paciente;
import modelo.Persona;
import modelo.UsuarioRol;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-03T00:46:05")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Integer> pKId;
    public static volatile SingularAttribute<Usuario, String> clave;
    public static volatile SingularAttribute<Usuario, Integer> estado;
    public static volatile SingularAttribute<Usuario, Date> fechaModificacion;
    public static volatile SingularAttribute<Usuario, Persona> persona;
    public static volatile SingularAttribute<Usuario, Date> fechaRegistro;
    public static volatile ListAttribute<Usuario, UsuarioRol> usuarioRolList;
    public static volatile ListAttribute<Usuario, CasoSaludPuntaje> casoSaludPuntajeList;
    public static volatile SingularAttribute<Usuario, String> usuario;
    public static volatile ListAttribute<Usuario, Favoritos> favoritosList;
    public static volatile ListAttribute<Usuario, Paciente> pacienteList;
    public static volatile SingularAttribute<Usuario, Date> fechaUltimoAcceso;

}