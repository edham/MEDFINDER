/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc;

import be.Distrito;
import be.Distrito_;
import be.Provincia;
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
public class DistritoFacade extends AbstractFacade<Distrito> implements DistritoFacadeLocal {
    @PersistenceContext(unitName = "MedFinderEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DistritoFacade() {
        super(Distrito.class);
    }
    
    public List<Distrito> lista_Provincia(Provincia objProvincia,boolean activos) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Distrito> cq = getEntityManager().getCriteriaBuilder().createQuery(Distrito.class);
        Root<Distrito> registro = cq.from(Distrito.class);
        cq.orderBy(cb.desc(registro.get("nombre")));
        if(activos)
        {
            cq.where(cb.and(cb.equal(registro.get(Distrito_.provincia), objProvincia)));
        }else
        {
            cq.where(cb.and(cb.equal(registro.get(Distrito_.provincia), objProvincia),
                    cb.equal(registro.get(Distrito_.estado), 1)));
        }

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }
    
}
