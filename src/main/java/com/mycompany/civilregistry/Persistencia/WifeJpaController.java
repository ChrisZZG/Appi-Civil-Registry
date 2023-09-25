/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.civilregistry.Persistencia;

import com.mycompany.civilregistry.Logica.Wife;
import com.mycompany.civilregistry.Persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author crist
 */
public class WifeJpaController implements Serializable {

    public WifeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public WifeJpaController(){
        emf = Persistence.createEntityManagerFactory("CivilRegistry");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Wife wife) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(wife);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Wife wife) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            wife = em.merge(wife);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = wife.getIdWife();
                if (findWife(id) == null) {
                    throw new NonexistentEntityException("The wife with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Wife wife;
            try {
                wife = em.getReference(Wife.class, id);
                wife.getIdWife();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The wife with id " + id + " no longer exists.", enfe);
            }
            em.remove(wife);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Wife> findWifeEntities() {
        return findWifeEntities(true, -1, -1);
    }

    public List<Wife> findWifeEntities(int maxResults, int firstResult) {
        return findWifeEntities(false, maxResults, firstResult);
    }

    private List<Wife> findWifeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Wife.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Wife findWife(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Wife.class, id);
        } finally {
            em.close();
        }
    }

    public int getWifeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Wife> rt = cq.from(Wife.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
