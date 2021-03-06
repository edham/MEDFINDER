/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import modelo.Clinica;
import modelo.Seguro;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface SeguroFacadeLocal {

    void create(Seguro seguro);

    void edit(Seguro seguro);

    void remove(Seguro seguro);

    Seguro find(Object id);

    List<Seguro> findAll();

    List<Seguro> findRange(int[] range);
    List<Seguro> lista_activos();
    List<Seguro> lista_Distinct_Clinica(Clinica objClinica);
    int count();
    
}
