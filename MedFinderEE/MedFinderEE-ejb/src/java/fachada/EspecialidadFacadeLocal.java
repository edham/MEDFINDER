/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import modelo.Clinica;
import modelo.Especialidad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface EspecialidadFacadeLocal {

    void create(Especialidad especialidad);

    void edit(Especialidad especialidad);

    void remove(Especialidad especialidad);

    Especialidad find(Object id);

    List<Especialidad> findAll();

    List<Especialidad> findRange(int[] range);

    List<Especialidad> lista_Distinct_Clinica(Clinica objClinica);
    List<Especialidad> lista_activos();
    int count();
    
}
