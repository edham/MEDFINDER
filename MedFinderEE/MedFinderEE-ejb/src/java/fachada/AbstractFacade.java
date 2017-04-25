/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import modelo.Favoritos;
import modelo.Favoritos_;
import modelo.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author EdHam
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;
 

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    public List<T> lista_activos() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Class<T>> registro = cq.from(entityClass);
        cq.orderBy(cb.asc(registro.get("pKId")));
        cq.where(
                cb.and(
                        cb.equal(registro.get("estado"), 1)
                ));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }  
    
      public T buscarXString(String dato,String columna) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Class<T>> registro = cq.from(entityClass);
        cq.where(
                cb.and(
                        cb.equal(registro.get("estado"), 1),
                        cb.equal(registro.get(columna), dato)
                ));
        try {
            javax.persistence.Query q = getEntityManager().createQuery(cq);
            return (T) q.getSingleResult();
        }
        catch (PersistenceException e) {}
        return null;
    }
    
    
    
    
    
     public T login(String usuario,String clave) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Class<T>> registro = cq.from(entityClass);
        cq.where(
                cb.and(
                        cb.equal(registro.get("estado"), 1),
                        cb.equal(registro.get("usuario"), usuario),
                        cb.equal(registro.get("clave"), clave)
                ));
        try {
            javax.persistence.Query q = getEntityManager().createQuery(cq);
            return (T) q.getSingleResult();
        }
        catch (PersistenceException e) {}
        return null;
    }
     
     
     public List<T> listaXUsuarios(Usuario obejto) {

        // agregar aqui
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Class<T>> registro = cq.from(entityClass);
        cq.orderBy(cb.asc(registro.get("pKId")));
            cq.where(
                    cb.and(
                            cb.equal(registro.get("estado"), 1),
                            cb.equal(registro.get("usuario"), obejto)
                ));

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }
     
     

}