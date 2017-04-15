/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import modelo.Departamento;
import modelo.Provincia;
import modelo.Provincia_;
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
public class ProvinciaFacade extends AbstractFacade<Provincia> implements ProvinciaFacadeLocal {
    @PersistenceContext(unitName = "MedFinderEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProvinciaFacade() {
        super(Provincia.class);
    }
    public List<Provincia> lista_Departamento(Departamento objDepartamento,boolean activos) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Provincia> cq = getEntityManager().getCriteriaBuilder().createQuery(Provincia.class);
        Root<Provincia> registro = cq.from(Provincia.class);
        cq.orderBy(cb.desc(registro.get("nombre")));
        if(activos)
        {
            cq.where(cb.and(cb.equal(registro.get(Provincia_.departamento), objDepartamento)));
        }else
        {
            cq.where(cb.and(cb.equal(registro.get(Provincia_.departamento), objDepartamento),
                    cb.equal(registro.get(Provincia_.estado), 1)));
        }

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }
}
