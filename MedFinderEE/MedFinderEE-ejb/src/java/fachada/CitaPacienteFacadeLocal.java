/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import modelo.CitaPaciente;
import modelo.Usuario;
import java.util.List;
import javax.ejb.Local;
import modelo.Doctor;

/**
 *
 * @author EdHam
 */
@Local
public interface CitaPacienteFacadeLocal {

    void create(CitaPaciente citaPaciente);

    void edit(CitaPaciente citaPaciente);

    void remove(CitaPaciente citaPaciente);

    CitaPaciente find(Object id);

    List<CitaPaciente> findAll();

    List<CitaPaciente> findRange(int[] range);
    List<CitaPaciente> listaXUsuarios(Usuario obejto);
    
    List<CitaPaciente> listarXObjeto(String atributo, Object obejto, boolean orden, String atributoOrden, boolean todos);

    List<CitaPaciente> listaXDoctorTipo(Doctor doctor,int tipo);
    int count();
    
}
