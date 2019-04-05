package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.RespuestaCasoSalud;
import modelo.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-04T22:06:34")
@StaticMetamodel(CasoSaludPuntaje.class)
public class CasoSaludPuntaje_ { 

    public static volatile SingularAttribute<CasoSaludPuntaje, Short> puntajeTotal;
    public static volatile SingularAttribute<CasoSaludPuntaje, RespuestaCasoSalud> respuestaCasoSalud;
    public static volatile SingularAttribute<CasoSaludPuntaje, Integer> pKId;
    public static volatile SingularAttribute<CasoSaludPuntaje, Integer> estado;
    public static volatile SingularAttribute<CasoSaludPuntaje, Date> fechaRegistro;
    public static volatile SingularAttribute<CasoSaludPuntaje, Usuario> usuario;

}