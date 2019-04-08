package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Clinica;
import modelo.Seguro;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-07T15:52:33")
@StaticMetamodel(DetalleClinicaSeguro.class)
public class DetalleClinicaSeguro_ { 

    public static volatile SingularAttribute<DetalleClinicaSeguro, Seguro> seguro;
    public static volatile SingularAttribute<DetalleClinicaSeguro, Integer> pKId;
    public static volatile SingularAttribute<DetalleClinicaSeguro, Integer> estado;
    public static volatile SingularAttribute<DetalleClinicaSeguro, Date> fechaModificacion;
    public static volatile SingularAttribute<DetalleClinicaSeguro, Date> fechaRegistro;
    public static volatile SingularAttribute<DetalleClinicaSeguro, Clinica> clinica;

}