package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Departamento;
import modelo.Distrito;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-14T14:31:13")
@StaticMetamodel(Provincia.class)
public class Provincia_ { 

    public static volatile SingularAttribute<Provincia, Integer> pKId;
    public static volatile SingularAttribute<Provincia, Integer> estado;
    public static volatile SingularAttribute<Provincia, Date> fechaModificacion;
    public static volatile SingularAttribute<Provincia, Date> fechaRegistro;
    public static volatile SingularAttribute<Provincia, Departamento> departamento;
    public static volatile SingularAttribute<Provincia, String> nombre;
    public static volatile ListAttribute<Provincia, Distrito> distritoList;

}