/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc;

import be.RespuestaPreguntaPaciente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author EdHam
 */
@Stateless
public class RespuestaPreguntaPacienteFacade extends AbstractFacade<RespuestaPreguntaPaciente> implements RespuestaPreguntaPacienteFacadeLocal {
    @PersistenceContext(unitName = "MedFinderEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RespuestaPreguntaPacienteFacade() {
        super(RespuestaPreguntaPaciente.class);
    }
    
}
