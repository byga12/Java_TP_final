/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logic.Sale;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logic.Customer;
import persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author Micaela
 */
public class CustomerJpaController implements Serializable {

    public CustomerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CustomerJpaController() {
        emf = Persistence.createEntityManagerFactory("Shimabuku_Gabriel_tpo_finalPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Customer customer) {
        if (customer.getSalesAssociated() == null) {
            customer.setSalesAssociated(new ArrayList<Sale>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Sale> attachedSalesAssociated = new ArrayList<Sale>();
            for (Sale salesAssociatedSaleToAttach : customer.getSalesAssociated()) {
                salesAssociatedSaleToAttach = em.getReference(salesAssociatedSaleToAttach.getClass(), salesAssociatedSaleToAttach.getSaleId());
                attachedSalesAssociated.add(salesAssociatedSaleToAttach);
            }
            customer.setSalesAssociated(attachedSalesAssociated);
            em.persist(customer);
            for (Sale salesAssociatedSale : customer.getSalesAssociated()) {
                Customer oldCustomerOfSalesAssociatedSale = salesAssociatedSale.getCustomer();
                salesAssociatedSale.setCustomer(customer);
                salesAssociatedSale = em.merge(salesAssociatedSale);
                if (oldCustomerOfSalesAssociatedSale != null) {
                    oldCustomerOfSalesAssociatedSale.getSalesAssociated().remove(salesAssociatedSale);
                    oldCustomerOfSalesAssociatedSale = em.merge(oldCustomerOfSalesAssociatedSale);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Customer customer) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Customer persistentCustomer = em.find(Customer.class, customer.getUserId());
            List<Sale> salesAssociatedOld = persistentCustomer.getSalesAssociated();
            List<Sale> salesAssociatedNew = customer.getSalesAssociated();
            List<Sale> attachedSalesAssociatedNew = new ArrayList<Sale>();
            for (Sale salesAssociatedNewSaleToAttach : salesAssociatedNew) {
                salesAssociatedNewSaleToAttach = em.getReference(salesAssociatedNewSaleToAttach.getClass(), salesAssociatedNewSaleToAttach.getSaleId());
                attachedSalesAssociatedNew.add(salesAssociatedNewSaleToAttach);
            }
            salesAssociatedNew = attachedSalesAssociatedNew;
            customer.setSalesAssociated(salesAssociatedNew);
            customer = em.merge(customer);
            for (Sale salesAssociatedOldSale : salesAssociatedOld) {
                if (!salesAssociatedNew.contains(salesAssociatedOldSale)) {
                    salesAssociatedOldSale.setCustomer(null);
                    salesAssociatedOldSale = em.merge(salesAssociatedOldSale);
                }
            }
            for (Sale salesAssociatedNewSale : salesAssociatedNew) {
                if (!salesAssociatedOld.contains(salesAssociatedNewSale)) {
                    Customer oldCustomerOfSalesAssociatedNewSale = salesAssociatedNewSale.getCustomer();
                    salesAssociatedNewSale.setCustomer(customer);
                    salesAssociatedNewSale = em.merge(salesAssociatedNewSale);
                    if (oldCustomerOfSalesAssociatedNewSale != null && !oldCustomerOfSalesAssociatedNewSale.equals(customer)) {
                        oldCustomerOfSalesAssociatedNewSale.getSalesAssociated().remove(salesAssociatedNewSale);
                        oldCustomerOfSalesAssociatedNewSale = em.merge(oldCustomerOfSalesAssociatedNewSale);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = customer.getUserId();
                if (findCustomer(id) == null) {
                    throw new NonexistentEntityException("The customer with id " + id + " no longer exists.");
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
            Customer customer;
            try {
                customer = em.getReference(Customer.class, id);
                customer.getUserId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customer with id " + id + " no longer exists.", enfe);
            }
            List<Sale> salesAssociated = customer.getSalesAssociated();
            for (Sale salesAssociatedSale : salesAssociated) {
                salesAssociatedSale.setCustomer(null);
                salesAssociatedSale = em.merge(salesAssociatedSale);
            }
            em.remove(customer);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Customer> findCustomerEntities() {
        return findCustomerEntities(true, -1, -1);
    }

    public List<Customer> findCustomerEntities(int maxResults, int firstResult) {
        return findCustomerEntities(false, maxResults, firstResult);
    }

    private List<Customer> findCustomerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Customer.class));
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

    public Customer findCustomer(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Customer> rt = cq.from(Customer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
