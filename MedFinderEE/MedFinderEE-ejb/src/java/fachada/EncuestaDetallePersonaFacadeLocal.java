/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import java.util.List;
import javax.ejb.Local;
import modelo.EncuestaDetallePersona;

/**
 *
 * @author hp
 */
@Local
public interface EncuestaDetallePersonaFacadeLocal {

    void create(EncuestaDetallePersona encuestaDetallePersona);

    void edit(EncuestaDetallePersona encuestaDetallePersona);

    void remove(EncuestaDetallePersona encuestaDetallePersona);

    EncuestaDetallePersona find(Object id);

    List<EncuestaDetallePersona> findAll();

    List<EncuestaDetallePersona> findRange(int[] range);

    int count();
    
    List<EncuestaDetallePersona> lista_activos();
    List<EncuestaDetallePersona> listarXObjeto(String atributo, Object obejto, boolean orden, String atributoOrden, boolean todos);

}
