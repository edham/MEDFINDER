/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import java.util.List;
import javax.ejb.Local;
import modelo.Encuesta;

/**
 *
 * @author hp
 */
@Local
public interface EncuestaFacadeLocal {

    void create(Encuesta encuesta);

    void edit(Encuesta encuesta);

    void remove(Encuesta encuesta);

    Encuesta find(Object id);

    List<Encuesta> findAll();

    List<Encuesta> findRange(int[] range);

    int count();
    
    List<Encuesta> lista_activos();
    List<Encuesta> listarXObjeto(String atributo, Object obejto, boolean orden, String atributoOrden, boolean todos);

}
