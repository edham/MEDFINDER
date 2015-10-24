/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc;

import be.DetalleClinicaEspecialidad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface DetalleClinicaEspecialidadFacadeLocal {

    void create(DetalleClinicaEspecialidad detalleClinicaEspecialidad);

    void edit(DetalleClinicaEspecialidad detalleClinicaEspecialidad);

    void remove(DetalleClinicaEspecialidad detalleClinicaEspecialidad);

    DetalleClinicaEspecialidad find(Object id);

    List<DetalleClinicaEspecialidad> findAll();

    List<DetalleClinicaEspecialidad> findRange(int[] range);

    int count();
    
}
