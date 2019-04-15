package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Provincia;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-14T20:08:31")
@StaticMetamodel(Departamento.class)
public class Departamento_ { 

    public static volatile SingularAttribute<Departamento, Integer> pKId;
    public static volatile SingularAttribute<Departamento, Integer> estado;
    public static volatile SingularAttribute<Departamento, Date> fechaModificacion;
    public static volatile SingularAttribute<Departamento, Date> fechaRegistro;
    public static volatile SingularAttribute<Departamento, String> nombre;
    public static volatile ListAttribute<Departamento, Provincia> provinciaList;

}