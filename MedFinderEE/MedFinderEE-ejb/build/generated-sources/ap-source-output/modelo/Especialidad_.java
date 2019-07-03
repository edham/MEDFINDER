package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.DetalleClinicaEspecialidad;
import modelo.Doctor;
import modelo.PreguntaPaciente;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-03T00:46:05")
@StaticMetamodel(Especialidad.class)
public class Especialidad_ { 

    public static volatile SingularAttribute<Especialidad, String> descripcion;
    public static volatile ListAttribute<Especialidad, PreguntaPaciente> preguntaPacienteList;
    public static volatile SingularAttribute<Especialidad, Integer> pKId;
    public static volatile SingularAttribute<Especialidad, Integer> estado;
    public static volatile SingularAttribute<Especialidad, Date> fechaModificacion;
    public static volatile ListAttribute<Especialidad, Doctor> doctorList;
    public static volatile SingularAttribute<Especialidad, Date> fechaRegistro;
    public static volatile ListAttribute<Especialidad, DetalleClinicaEspecialidad> detalleClinicaEspecialidadList;
    public static volatile SingularAttribute<Especialidad, String> nombre;

}