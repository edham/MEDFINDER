/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc;

import be.CasosSalud;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface CasosSaludFacadeLocal {

    void create(CasosSalud casosSalud);

    void edit(CasosSalud casosSalud);

    void remove(CasosSalud casosSalud);

    CasosSalud find(Object id);

    List<CasosSalud> findAll();

    List<CasosSalud> findRange(int[] range);

    int count();
    
}
