/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import modelo.Usuario;
import util.ParametroNodo;

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

    public List<T> lista_estado(String ordenParametro, boolean orden, boolean todos) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Class<T>> registro = cq.from(entityClass);
        if (orden) {
            cq.orderBy(cb.asc(registro.get(ordenParametro)));
        } else {
            cq.orderBy(cb.desc(registro.get(ordenParametro)));
        }
        if (!todos) {
            cq.where(
                    cb.and(
                            cb.equal(registro.get("estado"), 1)
                    ));
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    public T buscarXString(String dato, String columna) {
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
        } catch (PersistenceException e) {
        }
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

    public List<T> listarXObjeto(String atributo, Object obejto, boolean orden, String atributoOrden, boolean todos) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Class<T>> registro = cq.from(entityClass);
        if (orden) {
            cq.orderBy(cb.asc(registro.get(atributoOrden)));
        } else {
            cq.orderBy(cb.desc(registro.get(atributoOrden)));
        }
        if (todos) {
            cq.where(
                    cb.and(
                            cb.equal(registro.get(atributo), obejto)
                    ));
        } else {
            cq.where(
                    cb.and(
                            cb.equal(registro.get("estado"), 1),
                            cb.equal(registro.get(atributo), obejto)
                    ));

        }

        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    public List<T> obtenerObjetosPorParametros(ParametroNodo parametros, boolean orden, String atributoOrden, boolean todos, boolean operador) {
        javax.persistence.criteria.CriteriaQuery<T> cb = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        List<Predicate> predicados = new ArrayList<Predicate>();
        Root<T> entitie = cb.from(entityClass);

        for (ParametroNodo.parametro entidad : parametros.getParametros()) {
            predicados.add(criteriaBuilder.equal(entitie.get(entidad.getCampo()), entidad.getValor()));
        }

        if (!todos) {
            predicados.add(criteriaBuilder.equal(entitie.get("estado"), 1));
        }
        if (operador) {
            cb.select(entitie).where(
                    getEntityManager().getCriteriaBuilder().and(predicados.toArray(new Predicate[]{})));
        } else {
            cb.select(entitie).where(
                    getEntityManager().getCriteriaBuilder().or(predicados.toArray(new Predicate[]{})));
        }
        if (orden) {
            cb.orderBy(criteriaBuilder.asc(entitie.get(atributoOrden)));
        } else {
            cb.orderBy(criteriaBuilder.desc(entitie.get(atributoOrden)));
        }

        return getEntityManager().createQuery(cb).getResultList();
    }

    public T buscar_XPorParametros(ParametroNodo parametros, boolean todos, boolean operador, boolean pkId) {
        javax.persistence.criteria.CriteriaQuery<T> cb = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        List<Predicate> predicados = new ArrayList<Predicate>();
        Root<T> entitie = cb.from(entityClass);
        if (pkId) {
            cb.orderBy(criteriaBuilder.desc(entitie.get("pkId")));
        }
        for (ParametroNodo.parametro entidad : parametros.getParametros()) {
            predicados.add(criteriaBuilder.equal(entitie.get(entidad.getCampo()), entidad.getValor()));
        }

        if (!todos) {
            predicados.add(criteriaBuilder.equal(entitie.get("estado"), 1));
        }

        if (operador) {
            cb.select(entitie).where(
                    getEntityManager().getCriteriaBuilder().and(predicados.toArray(new Predicate[]{})));
        } else {
            cb.select(entitie).where(
                    getEntityManager().getCriteriaBuilder().or(predicados.toArray(new Predicate[]{})));
        }

        try {
            javax.persistence.Query q = getEntityManager().createQuery(cb);
            return (T) q.setMaxResults(1).getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }
}
