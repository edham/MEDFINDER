package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.CasoSaludPuntaje;
import modelo.CasosSalud;
import modelo.Doctor;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-07T15:52:33")
@StaticMetamodel(RespuestaCasoSalud.class)
public class RespuestaCasoSalud_ { 

    public static volatile SingularAttribute<RespuestaCasoSalud, String> descripcion;
    public static volatile SingularAttribute<RespuestaCasoSalud, Doctor> doctor;
    public static volatile SingularAttribute<RespuestaCasoSalud, Integer> puntajeTotal;
    public static volatile SingularAttribute<RespuestaCasoSalud, Integer> pKId;
    public static volatile SingularAttribute<RespuestaCasoSalud, Integer> estado;
    public static volatile SingularAttribute<RespuestaCasoSalud, Date> fechaModificacion;
    public static volatile SingularAttribute<RespuestaCasoSalud, Integer> totalCalificaciones;
    public static volatile SingularAttribute<RespuestaCasoSalud, Date> fechaRegistro;
    public static volatile SingularAttribute<RespuestaCasoSalud, CasosSalud> casosSalud;
    public static volatile ListAttribute<RespuestaCasoSalud, CasoSaludPuntaje> casoSaludPuntajeList;

}