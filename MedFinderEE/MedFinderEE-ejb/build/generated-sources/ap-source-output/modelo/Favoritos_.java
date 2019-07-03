package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Doctor;
import modelo.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-03T00:46:05")
@StaticMetamodel(Favoritos.class)
public class Favoritos_ { 

    public static volatile SingularAttribute<Favoritos, Doctor> doctor;
    public static volatile SingularAttribute<Favoritos, Integer> pKId;
    public static volatile SingularAttribute<Favoritos, Integer> estado;
    public static volatile SingularAttribute<Favoritos, Date> fechaModificacion;
    public static volatile SingularAttribute<Favoritos, Date> fechaRegistro;
    public static volatile SingularAttribute<Favoritos, Usuario> usuario;

}