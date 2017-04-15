/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import modelo.CasoSaludPuntaje;
import modelo.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface CasoSaludPuntajeFacadeLocal {

    void create(CasoSaludPuntaje casoSaludPuntaje);

    void edit(CasoSaludPuntaje casoSaludPuntaje);

    void remove(CasoSaludPuntaje casoSaludPuntaje);

    CasoSaludPuntaje find(Object id);

    List<CasoSaludPuntaje> findAll();

    List<CasoSaludPuntaje> findRange(int[] range);
    List<CasoSaludPuntaje> listaXUsuarios(Usuario obejto);
    int count();
    
}
