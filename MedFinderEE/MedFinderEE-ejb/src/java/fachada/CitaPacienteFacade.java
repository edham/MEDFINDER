/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import java.util.List;
import modelo.CitaPaciente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.CitaPaciente_;
import modelo.Doctor;

/**
 *
 * @author EdHam
 */
@Stateless
public class CitaPacienteFacade extends AbstractFacade<CitaPaciente> implements CitaPacienteFacadeLocal {
    @PersistenceContext(unitName = "MedFinderEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitaPacienteFacade() {
        super(CitaPaciente.class);
    }
    
    public List<CitaPaciente> listaXDoctorTipo(Doctor doctor,int tipo) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CitaPaciente> cq = getEntityManager().getCriteriaBuilder().createQuery(CitaPaciente.class);
        Root<CitaPaciente> registro = cq.from(CitaPaciente.class);
        cq.orderBy(cb.asc(registro.get(CitaPaciente_.pKId)));
    
            cq.where(cb.and(cb.equal(registro.get(CitaPaciente_.doctor),doctor),
                    cb.equal(registro.get(CitaPaciente_.tipo), tipo),
                     cb.equal(registro.get(CitaPaciente_.estado), 1)));
       

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }
}
