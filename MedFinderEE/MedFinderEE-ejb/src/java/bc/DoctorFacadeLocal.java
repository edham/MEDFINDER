/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc;

import be.Doctor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface DoctorFacadeLocal {

    void create(Doctor doctor);

    void edit(Doctor doctor);

    void remove(Doctor doctor);

    Doctor find(Object id);

    List<Doctor> findAll();

    List<Doctor> findRange(int[] range);
    
    Doctor login(String usuario,String clave);
    List<Doctor> lista_activos();
    int count();
    
}
