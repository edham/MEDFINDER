/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import modelo.Doctor;
import modelo.Persona;
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
public class PersonaFacade extends AbstractFacade<Persona> implements PersonaFacadeLocal {
    @PersistenceContext(unitName = "MedFinderEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }
    
    public List<Persona> lista_Distinct_DoctorFiltro(String dato) {
      
        TypedQuery q = getEntityManager().createQuery("SELECT p FROM Persona p WHERE p.pKId not in (SELECT pe.pKId FROM Persona pe INNER JOIN pe.doctorList d) and p.estado=1 and (p.apellidoPaterno LIKE :paterno or p.apellidoMaterno LIKE :materno or p.nombre LIKE :nombre or p.dni LIKE :dni)", Persona.class);
        q.setParameter("paterno","%"+dato+"%" );
        q.setParameter("materno","%"+dato+"%" );
        q.setParameter("nombre","%"+dato+"%" );
        q.setParameter("dni","%"+dato+"%" );
        return q.getResultList();
    }
}
