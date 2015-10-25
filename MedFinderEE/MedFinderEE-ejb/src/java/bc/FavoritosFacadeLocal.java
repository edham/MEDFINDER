/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc;

import be.Favoritos;
import be.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface FavoritosFacadeLocal {

    void create(Favoritos favoritos);

    void edit(Favoritos favoritos);

    void remove(Favoritos favoritos);

    Favoritos find(Object id);

    List<Favoritos> findAll();

    List<Favoritos> findRange(int[] range);
    List<Favoritos> lista_activos();
    List<Favoritos> listaXUsuarios(Usuario obejto);
    int count();
    
}
