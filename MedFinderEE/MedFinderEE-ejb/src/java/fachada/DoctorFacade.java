/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import modelo.Doctor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
import modelo.Doctor_;
import modelo.Persona;
import modelo.Persona_;
import modelo.Roles;
import modelo.Usuario;
import modelo.UsuarioRol;
import modelo.UsuarioRol_;
import modelo.Usuario_;

/**
 *
 * @author EdHam
 */
@Stateless
public class DoctorFacade extends AbstractFacade<Doctor> implements DoctorFacadeLocal {
    @PersistenceContext(unitName = "MedFinderEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DoctorFacade() {
        super(Doctor.class);
    }
    
    public Doctor login(String usuario,String clave){
       try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Doctor> cq = cb.createQuery(Doctor.class);
            Root<Doctor> registro = cq.from(Doctor.class);
            Join<Doctor, Persona> joinPersona = registro.join(Doctor_.persona);
            ListJoin<Persona, Usuario> joinUsuario = joinPersona.join(Persona_.usuarioList);
            ListJoin<Usuario, UsuarioRol> joinUsuarioRol = joinUsuario.join(Usuario_.usuarioRolList);
            cq.where(
                    cb.and(
                            cb.equal(registro.get(Doctor_.estado), 1),
                            cb.equal(joinPersona.get(Persona_.estado), 1),
                            cb.equal(joinUsuario.get(Usuario_.estado), 1),
                            cb.equal(joinUsuario.get(Usuario_.usuario), usuario),
                            cb.equal(joinUsuario.get(Usuario_.clave), clave),
                            cb.equal(joinUsuarioRol.get(UsuarioRol_.estado), 1),
                            cb.equal(joinUsuarioRol.get(UsuarioRol_.roles), new Roles(3))
                    ));

            javax.persistence.Query q = getEntityManager().createQuery(cq);
            return (Doctor) q.setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
