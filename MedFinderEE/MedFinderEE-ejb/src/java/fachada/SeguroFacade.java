/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import modelo.Clinica;
import modelo.Especialidad;
import modelo.Seguro;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author EdHam
 */
@Stateless
public class SeguroFacade extends AbstractFacade<Seguro> implements SeguroFacadeLocal {
    @PersistenceContext(unitName = "MedFinderEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeguroFacade() {
        super(Seguro.class);
    }
     public List<Seguro> lista_Distinct_Clinica(Clinica objClinica) {
      
         TypedQuery q = getEntityManager().createQuery("SELECT s FROM Seguro s WHERE s.pKId not in (SELECT se.pKId FROM Seguro se INNER JOIN se.detalleClinicaSeguroList d where d.clinica = :clinica) and s.estado=1", Seguro.class);
         q.setParameter("clinica", objClinica);
        return q.getResultList();
    }
}
