/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.EncuestaDetalle;

/**
 *
 * @author hp
 */
@Stateless
public class EncuestaDetalleFacade extends AbstractFacade<EncuestaDetalle> implements EncuestaDetalleFacadeLocal {

    @PersistenceContext(unitName = "MedFinderEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncuestaDetalleFacade() {
        super(EncuestaDetalle.class);
    }
    
}
