/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.EncuestaDetallePersona;

/**
 *
 * @author hp
 */
@Stateless
public class EncuestaDetallePersonaFacade extends AbstractFacade<EncuestaDetallePersona> implements EncuestaDetallePersonaFacadeLocal {

    @PersistenceContext(unitName = "MedFinderEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncuestaDetallePersonaFacade() {
        super(EncuestaDetallePersona.class);
    }
    
}
