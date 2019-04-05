/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import modelo.RespuestaCasoSalud;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface RespuestaCasoSaludFacadeLocal {

    void create(RespuestaCasoSalud respuestaCasoSalud);

    void edit(RespuestaCasoSalud respuestaCasoSalud);

    void remove(RespuestaCasoSalud respuestaCasoSalud);

    RespuestaCasoSalud find(Object id);

    List<RespuestaCasoSalud> findAll();

    List<RespuestaCasoSalud> findRange(int[] range);
    List<RespuestaCasoSalud> lista_activos();
    List<RespuestaCasoSalud> listarXObjeto(String atributo, Object obejto, boolean orden, String atributoOrden, boolean todos);

    int count();
    
}
