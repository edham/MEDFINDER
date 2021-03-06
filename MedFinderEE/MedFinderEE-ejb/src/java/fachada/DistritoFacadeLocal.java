/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import modelo.Distrito;
import modelo.Provincia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author EdHam
 */
@Local
public interface DistritoFacadeLocal {

    void create(Distrito distrito);

    void edit(Distrito distrito);

    void remove(Distrito distrito);

    Distrito find(Object id);

    List<Distrito> findAll();

    List<Distrito> findRange(int[] range);

    List<Distrito> lista_activos();
    List<Distrito> lista_Provincia(Provincia objProvincia,boolean activos);
    int count();
    
}
