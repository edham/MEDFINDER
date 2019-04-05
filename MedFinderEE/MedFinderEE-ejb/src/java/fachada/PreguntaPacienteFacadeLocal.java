/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import modelo.PreguntaPaciente;
import modelo.Usuario;
import java.util.List;
import javax.ejb.Local;
import modelo.Especialidad;
import util.ParametroNodo;

/**
 *
 * @author EdHam
 */
@Local
public interface PreguntaPacienteFacadeLocal {

    void create(PreguntaPaciente preguntaPaciente);

    void edit(PreguntaPaciente preguntaPaciente);

    void remove(PreguntaPaciente preguntaPaciente);

    PreguntaPaciente find(Object id);

    List<PreguntaPaciente> findAll();

    List<PreguntaPaciente> findRange(int[] range);
    List<PreguntaPaciente> listaXUsuarios(Usuario obejto);
    List<PreguntaPaciente> listarXObjeto(String atributo, Object obejto, boolean orden, String atributoOrden, boolean todos);
    List<PreguntaPaciente> obtenerObjetosPorParametros(ParametroNodo parametros, boolean orden, String atributoOrden, boolean todos, boolean operador);    
    List<PreguntaPaciente> listaXEspecialidadTipo(Especialidad especialidad,int tipo);
    int count();
    
}
