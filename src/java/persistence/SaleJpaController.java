/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logic.TouristService;
import logic.TouristPackage;
import logic.Employee;
import logic.Customer;
import logic.Sale;
import persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author Micaela
 */
public class SaleJpaController implements Serializable {

    public SaleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public SaleJpaController() {
        emf = Persistence.createEntityManagerFactory("Shimabuku_Gabriel_tpo_finalPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sale sale) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TouristService tourist_service = sale.getTourist_service();
            if (tourist_service != null) {
                tourist_service = em.getReference(tourist_service.getClass(), tourist_service.getServiceId());
                sale.setTourist_service(tourist_service);
            }
            TouristPackage tourist_package = sale.getTourist_package();
            if (tourist_package != null) {
                tourist_package = em.getReference(tourist_package.getClass(), tourist_package.getPackageId());
                sale.setTourist_package(tourist_package);
            }
            Employee employee = sale.getEmployee();
            if (employee != null) {
                employee = em.getReference(employee.getClass(), employee.getUserId());
                sale.setEmployee(employee);
            }
            Customer customer = sale.getCustomer();
            if (customer != null) {
                customer = em.getReference(customer.getClass(), customer.getUserId());
                sale.setCustomer(customer);
            }
            em.persist(sale);
            if (tourist_service != null) {
                tourist_service.getSalesWithService().add(sale);
                tourist_service = em.merge(tourist_service);
            }
            if (tourist_package != null) {
                tourist_package.getSalesWithPackage().add(sale);
                tourist_package = em.merge(tourist_package);
            }
            if (employee != null) {
                employee.getSalesDone().add(sale);
                employee = em.merge(employee);
            }
            if (customer != null) {
                customer.getSalesAssociated().add(sale);
                customer = em.merge(customer);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sale sale) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sale persistentSale = em.find(Sale.class, sale.getSaleId());
            TouristService tourist_serviceOld = persistentSale.getTourist_service();
            TouristService tourist_serviceNew = sale.getTourist_service();
            TouristPackage tourist_packageOld = persistentSale.getTourist_package();
            TouristPackage tourist_packageNew = sale.getTourist_package();
            Employee employeeOld = persistentSale.getEmployee();
            Employee employeeNew = sale.getEmployee();
            Customer customerOld = persistentSale.getCustomer();
            Customer customerNew = sale.getCustomer();
            if (tourist_serviceNew != null) {
                tourist_serviceNew = em.getReference(tourist_serviceNew.getClass(), tourist_serviceNew.getServiceId());
                sale.setTourist_service(tourist_serviceNew);
            }
            if (tourist_packageNew != null) {
                tourist_packageNew = em.getReference(tourist_packageNew.getClass(), tourist_packageNew.getPackageId());
                sale.setTourist_package(tourist_packageNew);
            }
            if (employeeNew != null) {
                employeeNew = em.getReference(employeeNew.getClass(), employeeNew.getUserId());
                sale.setEmployee(employeeNew);
            }
            if (customerNew != null) {
                customerNew = em.getReference(customerNew.getClass(), customerNew.getUserId());
                sale.setCustomer(customerNew);
            }
            sale = em.merge(sale);
            if (tourist_serviceOld != null && !tourist_serviceOld.equals(tourist_serviceNew)) {
                tourist_serviceOld.getSalesWithService().remove(sale);
                tourist_serviceOld = em.merge(tourist_serviceOld);
            }
            if (tourist_serviceNew != null && !tourist_serviceNew.equals(tourist_serviceOld)) {
                tourist_serviceNew.getSalesWithService().add(sale);
                tourist_serviceNew = em.merge(tourist_serviceNew);
            }
            if (tourist_packageOld != null && !tourist_packageOld.equals(tourist_packageNew)) {
                tourist_packageOld.getSalesWithPackage().remove(sale);
                tourist_packageOld = em.merge(tourist_packageOld);
            }
            if (tourist_packageNew != null && !tourist_packageNew.equals(tourist_packageOld)) {
                tourist_packageNew.getSalesWithPackage().add(sale);
                tourist_packageNew = em.merge(tourist_packageNew);
            }
            if (employeeOld != null && !employeeOld.equals(employeeNew)) {
                employeeOld.getSalesDone().remove(sale);
                employeeOld = em.merge(employeeOld);
            }
            if (employeeNew != null && !employeeNew.equals(employeeOld)) {
                employeeNew.getSalesDone().add(sale);
                employeeNew = em.merge(employeeNew);
            }
            if (customerOld != null && !customerOld.equals(customerNew)) {
                customerOld.getSalesAssociated().remove(sale);
                customerOld = em.merge(customerOld);
            }
            if (customerNew != null && !customerNew.equals(customerOld)) {
                customerNew.getSalesAssociated().add(sale);
                customerNew = em.merge(customerNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = sale.getSaleId();
                if (findSale(id) == null) {
                    throw new NonexistentEntityException("The sale with id " + id + " no longer exists.");
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
            Sale sale;
            try {
                sale = em.getReference(Sale.class, id);
                sale.getSaleId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sale with id " + id + " no longer exists.", enfe);
            }
            TouristService tourist_service = sale.getTourist_service();
            if (tourist_service != null) {
                tourist_service.getSalesWithService().remove(sale);
                tourist_service = em.merge(tourist_service);
            }
            TouristPackage tourist_package = sale.getTourist_package();
            if (tourist_package != null) {
                tourist_package.getSalesWithPackage().remove(sale);
                tourist_package = em.merge(tourist_package);
            }
            Employee employee = sale.getEmployee();
            if (employee != null) {
                employee.getSalesDone().remove(sale);
                employee = em.merge(employee);
            }
            Customer customer = sale.getCustomer();
            if (customer != null) {
                customer.getSalesAssociated().remove(sale);
                customer = em.merge(customer);
            }
            em.remove(sale);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sale> findSaleEntities() {
        return findSaleEntities(true, -1, -1);
    }

    public List<Sale> findSaleEntities(int maxResults, int firstResult) {
        return findSaleEntities(false, maxResults, firstResult);
    }

    private List<Sale> findSaleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sale.class));
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

    public Sale findSale(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sale.class, id);
        } finally {
            em.close();
        }
    }

    public int getSaleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sale> rt = cq.from(Sale.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
