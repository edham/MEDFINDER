package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Clinica;
import modelo.Persona;
import modelo.Provincia;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-14T20:08:32")
@StaticMetamodel(Distrito.class)
public class Distrito_ { 

    public static volatile SingularAttribute<Distrito, Integer> pKId;
    public static volatile SingularAttribute<Distrito, Integer> estado;
    public static volatile SingularAttribute<Distrito, Date> fechaModificacion;
    public static volatile SingularAttribute<Distrito, Date> fechaRegistro;
    public static volatile ListAttribute<Distrito, Persona> personaList;
    public static volatile SingularAttribute<Distrito, Provincia> provincia;
    public static volatile SingularAttribute<Distrito, String> nombre;
    public static volatile ListAttribute<Distrito, Clinica> clinicaList;

}