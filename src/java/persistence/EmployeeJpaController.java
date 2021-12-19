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
import logic.Employee;
import persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author Micaela
 */
public class EmployeeJpaController implements Serializable {

    public EmployeeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EmployeeJpaController() {
        emf = Persistence.createEntityManagerFactory("Shimabuku_Gabriel_tpo_finalPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Employee employee) {
        if (employee.getSalesDone() == null) {
            employee.setSalesDone(new ArrayList<Sale>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Sale> attachedSalesDone = new ArrayList<Sale>();
            for (Sale salesDoneSaleToAttach : employee.getSalesDone()) {
                salesDoneSaleToAttach = em.getReference(salesDoneSaleToAttach.getClass(), salesDoneSaleToAttach.getSaleId());
                attachedSalesDone.add(salesDoneSaleToAttach);
            }
            employee.setSalesDone(attachedSalesDone);
            em.persist(employee);
            for (Sale salesDoneSale : employee.getSalesDone()) {
                Employee oldEmployeeOfSalesDoneSale = salesDoneSale.getEmployee();
                salesDoneSale.setEmployee(employee);
                salesDoneSale = em.merge(salesDoneSale);
                if (oldEmployeeOfSalesDoneSale != null) {
                    oldEmployeeOfSalesDoneSale.getSalesDone().remove(salesDoneSale);
                    oldEmployeeOfSalesDoneSale = em.merge(oldEmployeeOfSalesDoneSale);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Employee employee) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee persistentEmployee = em.find(Employee.class, employee.getUserId());
            List<Sale> salesDoneOld = persistentEmployee.getSalesDone();
            List<Sale> salesDoneNew = employee.getSalesDone();
            List<Sale> attachedSalesDoneNew = new ArrayList<Sale>();
            for (Sale salesDoneNewSaleToAttach : salesDoneNew) {
                salesDoneNewSaleToAttach = em.getReference(salesDoneNewSaleToAttach.getClass(), salesDoneNewSaleToAttach.getSaleId());
                attachedSalesDoneNew.add(salesDoneNewSaleToAttach);
            }
            salesDoneNew = attachedSalesDoneNew;
            employee.setSalesDone(salesDoneNew);
            employee = em.merge(employee);
            for (Sale salesDoneOldSale : salesDoneOld) {
                if (!salesDoneNew.contains(salesDoneOldSale)) {
                    salesDoneOldSale.setEmployee(null);
                    salesDoneOldSale = em.merge(salesDoneOldSale);
                }
            }
            for (Sale salesDoneNewSale : salesDoneNew) {
                if (!salesDoneOld.contains(salesDoneNewSale)) {
                    Employee oldEmployeeOfSalesDoneNewSale = salesDoneNewSale.getEmployee();
                    salesDoneNewSale.setEmployee(employee);
                    salesDoneNewSale = em.merge(salesDoneNewSale);
                    if (oldEmployeeOfSalesDoneNewSale != null && !oldEmployeeOfSalesDoneNewSale.equals(employee)) {
                        oldEmployeeOfSalesDoneNewSale.getSalesDone().remove(salesDoneNewSale);
                        oldEmployeeOfSalesDoneNewSale = em.merge(oldEmployeeOfSalesDoneNewSale);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = employee.getUserId();
                if (findEmployee(id) == null) {
                    throw new NonexistentEntityException("The employee with id " + id + " no longer exists.");
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
            Employee employee;
            try {
                employee = em.getReference(Employee.class, id);
                employee.getUserId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employee with id " + id + " no longer exists.", enfe);
            }
            List<Sale> salesDone = employee.getSalesDone();
            for (Sale salesDoneSale : salesDone) {
                salesDoneSale.setEmployee(null);
                salesDoneSale = em.merge(salesDoneSale);
            }
            em.remove(employee);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Employee> findEmployeeEntities() {
        return findEmployeeEntities(true, -1, -1);
    }

    public List<Employee> findEmployeeEntities(int maxResults, int firstResult) {
        return findEmployeeEntities(false, maxResults, firstResult);
    }

    private List<Employee> findEmployeeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Employee.class));
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

    public Employee findEmployee(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployeeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Employee> rt = cq.from(Employee.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
