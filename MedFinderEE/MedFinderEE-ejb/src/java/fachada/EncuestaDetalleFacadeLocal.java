/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import java.util.List;
import javax.ejb.Local;
import modelo.EncuestaDetalle;

/**
 *
 * @author hp
 */
@Local
public interface EncuestaDetalleFacadeLocal {

    void create(EncuestaDetalle encuestaDetalle);

    void edit(EncuestaDetalle encuestaDetalle);

    void remove(EncuestaDetalle encuestaDetalle);

    EncuestaDetalle find(Object id);

    List<EncuestaDetalle> findAll();

    List<EncuestaDetalle> findRange(int[] range);

    int count();
    
    List<EncuestaDetalle> lista_activos();
    List<EncuestaDetalle> listarXObjeto(String atributo, Object obejto, boolean orden, String atributoOrden, boolean todos);

}
