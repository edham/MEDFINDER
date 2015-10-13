/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc;

import be.CitaPaciente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface CitaPacienteFacadeLocal {

    void create(CitaPaciente citaPaciente);

    void edit(CitaPaciente citaPaciente);

    void remove(CitaPaciente citaPaciente);

    CitaPaciente find(Object id);

    List<CitaPaciente> findAll();

    List<CitaPaciente> findRange(int[] range);

    int count();
    
}
