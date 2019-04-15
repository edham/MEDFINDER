package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.RespuestaCasoSalud;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-14T20:08:32")
@StaticMetamodel(CasosSalud.class)
public class CasosSalud_ { 

    public static volatile SingularAttribute<CasosSalud, Integer> pKId;
    public static volatile SingularAttribute<CasosSalud, Integer> estado;
    public static volatile SingularAttribute<CasosSalud, String> tema;
    public static volatile SingularAttribute<CasosSalud, Date> fechaModificacion;
    public static volatile SingularAttribute<CasosSalud, Date> fechaInicio;
    public static volatile SingularAttribute<CasosSalud, Date> fechaRegistro;
    public static volatile SingularAttribute<CasosSalud, Date> fechaFin;
    public static volatile ListAttribute<CasosSalud, RespuestaCasoSalud> respuestaCasoSaludList;

}