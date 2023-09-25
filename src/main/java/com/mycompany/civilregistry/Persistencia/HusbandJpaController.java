/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.civilregistry.Persistencia;

import com.mycompany.civilregistry.Logica.Husband;
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
public class HusbandJpaController implements Serializable {

    public HusbandJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public HusbandJpaController(){
        emf = Persistence.createEntityManagerFactory("CivilRegistry");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Husband husband) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(husband);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Husband husband) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            husband = em.merge(husband);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = husband.getIdHusband();
                if (findHusband(id) == null) {
                    throw new NonexistentEntityException("The husband with id " + id + " no longer exists.");
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
            Husband husband;
            try {
                husband = em.getReference(Husband.class, id);
                husband.getIdHusband();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The husband with id " + id + " no longer exists.", enfe);
            }
            em.remove(husband);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Husband> findHusbandEntities() {
        return findHusbandEntities(true, -1, -1);
    }

    public List<Husband> findHusbandEntities(int maxResults, int firstResult) {
        return findHusbandEntities(false, maxResults, firstResult);
    }

    private List<Husband> findHusbandEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Husband.class));
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

    public Husband findHusband(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Husband.class, id);
        } finally {
            em.close();
        }
    }

    public int getHusbandCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Husband> rt = cq.from(Husband.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
