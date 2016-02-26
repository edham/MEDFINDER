/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc;

import be.Clinica;
import be.DetalleClinicaSeguro;
import be.DetalleClinicaSeguro_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author EdHam
 */
@Stateless
public class DetalleClinicaSeguroFacade extends AbstractFacade<DetalleClinicaSeguro> implements DetalleClinicaSeguroFacadeLocal {
    @PersistenceContext(unitName = "MedFinderEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleClinicaSeguroFacade() {
        super(DetalleClinicaSeguro.class);
    }
      public List<DetalleClinicaSeguro> lista_Clinica(Clinica objClinica,boolean activos) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<DetalleClinicaSeguro> cq = getEntityManager().getCriteriaBuilder().createQuery(DetalleClinicaSeguro.class);
        Root<DetalleClinicaSeguro> registro = cq.from(DetalleClinicaSeguro.class);
        cq.orderBy(cb.desc(registro.get("pKId")));
        if(activos)
        {
            cq.where(cb.and(cb.equal(registro.get(DetalleClinicaSeguro_.clinica), objClinica)));
        }else
        {
            cq.where(cb.and(cb.equal(registro.get(DetalleClinicaSeguro_.clinica), objClinica),
                    cb.equal(registro.get(DetalleClinicaSeguro_.estado), 1)));
        }

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }
    
}
