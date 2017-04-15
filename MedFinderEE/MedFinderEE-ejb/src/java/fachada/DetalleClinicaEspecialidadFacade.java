/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import modelo.Clinica;
import modelo.DetalleClinicaEspecialidad;
import modelo.DetalleClinicaEspecialidad_;
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
public class DetalleClinicaEspecialidadFacade extends AbstractFacade<DetalleClinicaEspecialidad> implements DetalleClinicaEspecialidadFacadeLocal {
    @PersistenceContext(unitName = "MedFinderEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleClinicaEspecialidadFacade() {
        super(DetalleClinicaEspecialidad.class);
    }
    
    public List<DetalleClinicaEspecialidad> lista_Clinica(Clinica objClinica,boolean activos) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<DetalleClinicaEspecialidad> cq = getEntityManager().getCriteriaBuilder().createQuery(DetalleClinicaEspecialidad.class);
        Root<DetalleClinicaEspecialidad> registro = cq.from(DetalleClinicaEspecialidad.class);
        cq.orderBy(cb.desc(registro.get("pKId")));
        if(activos)
        {
            cq.where(cb.and(cb.equal(registro.get(DetalleClinicaEspecialidad_.clinica), objClinica)));
        }else
        {
            cq.where(cb.and(cb.equal(registro.get(DetalleClinicaEspecialidad_.clinica), objClinica),
                    cb.equal(registro.get(DetalleClinicaEspecialidad_.estado), 1)));
        }

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }
    
}
