/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import modelo.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
import modelo.Roles;
import modelo.UsuarioRol;
import modelo.UsuarioRol_;
import modelo.Usuario_;

/**
 *
 * @author EdHam
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "MedFinderEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario login(String usuario, String clave) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> registro = cq.from(Usuario.class);
        ListJoin<Usuario, UsuarioRol> joinUsuarioRol = registro.join(Usuario_.usuarioRolList);
        cq.where(
                cb.and(
                        cb.equal(registro.get(Usuario_.estado), 1),
                        cb.equal(registro.get(Usuario_.usuario), usuario),
                        cb.equal(registro.get(Usuario_.clave), clave),
                        cb.equal(joinUsuarioRol.get(UsuarioRol_.estado), 1),
                        cb.equal(joinUsuarioRol.get(UsuarioRol_.roles), new Roles(2))
                ));
        try {
            javax.persistence.Query q = getEntityManager().createQuery(cq);
            return (Usuario) q.setMaxResults(1).getSingleResult();
        } catch (PersistenceException e) {}
        return null;
    }
}
