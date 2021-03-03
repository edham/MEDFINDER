package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Roles;
import modelo.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-03-10T01:57:40")
@StaticMetamodel(UsuarioRol.class)
public class UsuarioRol_ { 

    public static volatile SingularAttribute<UsuarioRol, Integer> pkId;
    public static volatile SingularAttribute<UsuarioRol, Integer> estado;
    public static volatile SingularAttribute<UsuarioRol, Date> fechaModificacion;
    public static volatile SingularAttribute<UsuarioRol, Date> fechaRegistro;
    public static volatile SingularAttribute<UsuarioRol, Roles> roles;
    public static volatile SingularAttribute<UsuarioRol, Usuario> usuario;

}