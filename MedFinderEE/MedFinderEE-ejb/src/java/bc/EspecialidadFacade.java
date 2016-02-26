/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc;

import be.Clinica;
import be.DetalleClinicaEspecialidad;
import be.DetalleClinicaEspecialidad_;
import be.Especialidad;
import be.Especialidad_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 *
 * @author EdHam
 */
@Stateless
public class EspecialidadFacade extends AbstractFacade<Especialidad> implements EspecialidadFacadeLocal {
    @PersistenceContext(unitName = "MedFinderEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EspecialidadFacade() {
        super(Especialidad.class);
    }
    public List<Especialidad> lista_Distinct_Clinica(Clinica objClinica) {
      
         TypedQuery q = getEntityManager().createQuery("SELECT e FROM Especialidad e WHERE e.pKId not in (SELECT es.pKId FROM Especialidad es INNER JOIN es.detalleClinicaEspecialidadList d where d.clinica = :clinica) and e.estado=1", Especialidad.class);
         q.setParameter("clinica", objClinica);
//        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<Especialidad> query = cb.createQuery(Especialidad.class);
//        Root<Especialidad> registro = query.from(Especialidad.class);
//
//        Subquery<Integer> subquery = query.subquery(Integer.class);
//        Root<DetalleClinicaEspecialidad> subregistro = subquery.from(DetalleClinicaEspecialidad.class);
//        Join<DetalleClinicaEspecialidad, Especialidad> sqEmp = subregistro.join(DetalleClinicaEspecialidad_.especialidad, JoinType.LEFT);
//        subquery.distinct(true);
//        subquery.select(sqEmp.get(Especialidad_.pKId)).where(
//        cb.notEqual(subregistro.get(DetalleClinicaEspecialidad_.clinica),objClinica ));
//
//        query.orderBy(cb.desc(registro.get("nombre")));
//        query.select(registro).where(
//                cb.in(registro.get(Especialidad_.pKId)).value(subquery));
//        
//        javax.persistence.Query q = getEntityManager().createQuery(query);

        return q.getResultList();
    }
}
