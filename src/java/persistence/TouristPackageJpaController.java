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
import logic.TouristPackage;
import logic.TouristService;
import persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author Micaela
 */
public class TouristPackageJpaController implements Serializable {

    public TouristPackageJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public TouristPackageJpaController() {
        emf = Persistence.createEntityManagerFactory("Shimabuku_Gabriel_tpo_finalPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TouristPackage touristPackage) {
        if (touristPackage.getSalesWithPackage() == null) {
            touristPackage.setSalesWithPackage(new ArrayList<Sale>());
        }
        if (touristPackage.getServicesList() == null) {
            touristPackage.setServicesList(new ArrayList<TouristService>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Sale> attachedSalesWithPackage = new ArrayList<Sale>();
            for (Sale salesWithPackageSaleToAttach : touristPackage.getSalesWithPackage()) {
                salesWithPackageSaleToAttach = em.getReference(salesWithPackageSaleToAttach.getClass(), salesWithPackageSaleToAttach.getSaleId());
                attachedSalesWithPackage.add(salesWithPackageSaleToAttach);
            }
            touristPackage.setSalesWithPackage(attachedSalesWithPackage);
            List<TouristService> attachedServicesList = new ArrayList<TouristService>();
            for (TouristService servicesListTouristServiceToAttach : touristPackage.getServicesList()) {
                servicesListTouristServiceToAttach = em.getReference(servicesListTouristServiceToAttach.getClass(), servicesListTouristServiceToAttach.getServiceId());
                attachedServicesList.add(servicesListTouristServiceToAttach);
            }
            touristPackage.setServicesList(attachedServicesList);
            em.persist(touristPackage);
            for (Sale salesWithPackageSale : touristPackage.getSalesWithPackage()) {
                TouristPackage oldTourist_packageOfSalesWithPackageSale = salesWithPackageSale.getTourist_package();
                salesWithPackageSale.setTourist_package(touristPackage);
                salesWithPackageSale = em.merge(salesWithPackageSale);
                if (oldTourist_packageOfSalesWithPackageSale != null) {
                    oldTourist_packageOfSalesWithPackageSale.getSalesWithPackage().remove(salesWithPackageSale);
                    oldTourist_packageOfSalesWithPackageSale = em.merge(oldTourist_packageOfSalesWithPackageSale);
                }
            }
            for (TouristService servicesListTouristService : touristPackage.getServicesList()) {
                servicesListTouristService.getPackagesWithService().add(touristPackage);
                servicesListTouristService = em.merge(servicesListTouristService);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TouristPackage touristPackage) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TouristPackage persistentTouristPackage = em.find(TouristPackage.class, touristPackage.getPackageId());
            List<Sale> salesWithPackageOld = persistentTouristPackage.getSalesWithPackage();
            List<Sale> salesWithPackageNew = touristPackage.getSalesWithPackage();
            List<TouristService> servicesListOld = persistentTouristPackage.getServicesList();
            List<TouristService> servicesListNew = touristPackage.getServicesList();
            List<Sale> attachedSalesWithPackageNew = new ArrayList<Sale>();
            for (Sale salesWithPackageNewSaleToAttach : salesWithPackageNew) {
                salesWithPackageNewSaleToAttach = em.getReference(salesWithPackageNewSaleToAttach.getClass(), salesWithPackageNewSaleToAttach.getSaleId());
                attachedSalesWithPackageNew.add(salesWithPackageNewSaleToAttach);
            }
            salesWithPackageNew = attachedSalesWithPackageNew;
            touristPackage.setSalesWithPackage(salesWithPackageNew);
            List<TouristService> attachedServicesListNew = new ArrayList<TouristService>();
            for (TouristService servicesListNewTouristServiceToAttach : servicesListNew) {
                servicesListNewTouristServiceToAttach = em.getReference(servicesListNewTouristServiceToAttach.getClass(), servicesListNewTouristServiceToAttach.getServiceId());
                attachedServicesListNew.add(servicesListNewTouristServiceToAttach);
            }
            servicesListNew = attachedServicesListNew;
            touristPackage.setServicesList(servicesListNew);
            touristPackage = em.merge(touristPackage);
            for (Sale salesWithPackageOldSale : salesWithPackageOld) {
                if (!salesWithPackageNew.contains(salesWithPackageOldSale)) {
                    salesWithPackageOldSale.setTourist_package(null);
                    salesWithPackageOldSale = em.merge(salesWithPackageOldSale);
                }
            }
            for (Sale salesWithPackageNewSale : salesWithPackageNew) {
                if (!salesWithPackageOld.contains(salesWithPackageNewSale)) {
                    TouristPackage oldTourist_packageOfSalesWithPackageNewSale = salesWithPackageNewSale.getTourist_package();
                    salesWithPackageNewSale.setTourist_package(touristPackage);
                    salesWithPackageNewSale = em.merge(salesWithPackageNewSale);
                    if (oldTourist_packageOfSalesWithPackageNewSale != null && !oldTourist_packageOfSalesWithPackageNewSale.equals(touristPackage)) {
                        oldTourist_packageOfSalesWithPackageNewSale.getSalesWithPackage().remove(salesWithPackageNewSale);
                        oldTourist_packageOfSalesWithPackageNewSale = em.merge(oldTourist_packageOfSalesWithPackageNewSale);
                    }
                }
            }
            for (TouristService servicesListOldTouristService : servicesListOld) {
                if (!servicesListNew.contains(servicesListOldTouristService)) {
                    servicesListOldTouristService.getPackagesWithService().remove(touristPackage);
                    servicesListOldTouristService = em.merge(servicesListOldTouristService);
                }
            }
            for (TouristService servicesListNewTouristService : servicesListNew) {
                if (!servicesListOld.contains(servicesListNewTouristService)) {
                    servicesListNewTouristService.getPackagesWithService().add(touristPackage);
                    servicesListNewTouristService = em.merge(servicesListNewTouristService);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = touristPackage.getPackageId();
                if (findTouristPackage(id) == null) {
                    throw new NonexistentEntityException("The touristPackage with id " + id + " no longer exists.");
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
            TouristPackage touristPackage;
            try {
                touristPackage = em.getReference(TouristPackage.class, id);
                touristPackage.getPackageId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The touristPackage with id " + id + " no longer exists.", enfe);
            }
            List<Sale> salesWithPackage = touristPackage.getSalesWithPackage();
            for (Sale salesWithPackageSale : salesWithPackage) {
                salesWithPackageSale.setTourist_package(null);
                salesWithPackageSale = em.merge(salesWithPackageSale);
            }
            List<TouristService> servicesList = touristPackage.getServicesList();
            for (TouristService servicesListTouristService : servicesList) {
                servicesListTouristService.getPackagesWithService().remove(touristPackage);
                servicesListTouristService = em.merge(servicesListTouristService);
            }
            em.remove(touristPackage);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TouristPackage> findTouristPackageEntities() {
        return findTouristPackageEntities(true, -1, -1);
    }

    public List<TouristPackage> findTouristPackageEntities(int maxResults, int firstResult) {
        return findTouristPackageEntities(false, maxResults, firstResult);
    }

    private List<TouristPackage> findTouristPackageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TouristPackage.class));
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

    public TouristPackage findTouristPackage(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TouristPackage.class, id);
        } finally {
            em.close();
        }
    }

    public int getTouristPackageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TouristPackage> rt = cq.from(TouristPackage.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
