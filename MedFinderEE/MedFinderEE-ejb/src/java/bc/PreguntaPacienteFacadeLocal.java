/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc;

import be.PreguntaPaciente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface PreguntaPacienteFacadeLocal {

    void create(PreguntaPaciente preguntaPaciente);

    void edit(PreguntaPaciente preguntaPaciente);

    void remove(PreguntaPaciente preguntaPaciente);

    PreguntaPaciente find(Object id);

    List<PreguntaPaciente> findAll();

    List<PreguntaPaciente> findRange(int[] range);

    int count();
    
}
