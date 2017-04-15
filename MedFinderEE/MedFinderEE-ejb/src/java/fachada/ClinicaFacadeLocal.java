/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import modelo.Clinica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface ClinicaFacadeLocal {

    void create(Clinica clinica);

    void edit(Clinica clinica);

    void remove(Clinica clinica);

    Clinica find(Object id);

    List<Clinica> findAll();

    List<Clinica> findRange(int[] range);
    
    List<Clinica> lista_activos();

    int count();
    
}
