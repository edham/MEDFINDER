/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import modelo.RespuestaCasoSalud;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author EdHam
 */
@Stateless
public class RespuestaCasoSaludFacade extends AbstractFacade<RespuestaCasoSalud> implements RespuestaCasoSaludFacadeLocal {
    @PersistenceContext(unitName = "MedFinderEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RespuestaCasoSaludFacade() {
        super(RespuestaCasoSalud.class);
    }
    
}
