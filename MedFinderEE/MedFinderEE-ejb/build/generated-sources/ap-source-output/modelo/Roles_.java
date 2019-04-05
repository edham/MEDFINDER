package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.UsuarioRol;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-04T22:06:34")
@StaticMetamodel(Roles.class)
public class Roles_ { 

    public static volatile SingularAttribute<Roles, String> descripcion;
    public static volatile SingularAttribute<Roles, Integer> pkId;
    public static volatile SingularAttribute<Roles, Integer> estado;
    public static volatile SingularAttribute<Roles, Date> fechaModificacion;
    public static volatile SingularAttribute<Roles, Date> fechaRegistro;
    public static volatile ListAttribute<Roles, UsuarioRol> usuarioRolList;
    public static volatile SingularAttribute<Roles, String> nombre;

}