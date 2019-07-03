package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.DetalleClinicaSeguro;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-03T00:46:05")
@StaticMetamodel(Seguro.class)
public class Seguro_ { 

    public static volatile ListAttribute<Seguro, DetalleClinicaSeguro> detalleClinicaSeguroList;
    public static volatile SingularAttribute<Seguro, Integer> pKId;
    public static volatile SingularAttribute<Seguro, Integer> estado;
    public static volatile SingularAttribute<Seguro, Date> fechaModificacion;
    public static volatile SingularAttribute<Seguro, Date> fechaRegistro;
    public static volatile SingularAttribute<Seguro, byte[]> logo;
    public static volatile SingularAttribute<Seguro, String> nombre;

}