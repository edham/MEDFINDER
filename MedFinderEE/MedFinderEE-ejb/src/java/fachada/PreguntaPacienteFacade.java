/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import java.util.List;
import modelo.PreguntaPaciente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import modelo.Especialidad;
import modelo.PreguntaPaciente_;
import modelo.RespuestaPreguntaPaciente;
import modelo.RespuestaPreguntaPaciente_;

/**
 *
 * @author EdHam
 */
@Stateless
public class PreguntaPacienteFacade extends AbstractFacade<PreguntaPaciente> implements PreguntaPacienteFacadeLocal {
    @PersistenceContext(unitName = "MedFinderEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreguntaPacienteFacade() {
        super(PreguntaPaciente.class);
    }
    
    public List<PreguntaPaciente> listaXEspecialidadTipo(Especialidad especialidad,int tipo) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<PreguntaPaciente> cq = getEntityManager().getCriteriaBuilder().createQuery(PreguntaPaciente.class);
        Root<PreguntaPaciente> registro = cq.from(PreguntaPaciente.class);
        Join<PreguntaPaciente, RespuestaPreguntaPaciente> join = registro.join(PreguntaPaciente_.respuestaPreguntaPacienteList, JoinType.LEFT);
        
        cq.orderBy(cb.asc(registro.get(PreguntaPaciente_.pKId)));        
            cq.where(cb.and(cb.equal(registro.get(PreguntaPaciente_.especialidad),especialidad),
                    cb.equal(registro.get(PreguntaPaciente_.tipo), tipo),
                    cb.isNull(join.get(RespuestaPreguntaPaciente_.pKId)),
                    cb.equal(registro.get(PreguntaPaciente_.estado), 1)));
       

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }
}
