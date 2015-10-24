/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc;

import be.RespuestaPreguntaPaciente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface RespuestaPreguntaPacienteFacadeLocal {

    void create(RespuestaPreguntaPaciente respuestaPreguntaPaciente);

    void edit(RespuestaPreguntaPaciente respuestaPreguntaPaciente);

    void remove(RespuestaPreguntaPaciente respuestaPreguntaPaciente);

    RespuestaPreguntaPaciente find(Object id);

    List<RespuestaPreguntaPaciente> findAll();

    List<RespuestaPreguntaPaciente> findRange(int[] range);

    int count();
    
}
