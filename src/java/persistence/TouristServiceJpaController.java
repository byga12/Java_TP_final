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
public class TouristServiceJpaController implements Serializable {

    public TouristServiceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public TouristServiceJpaController() {
        emf = Persistence.createEntityManagerFactory("Shimabuku_Gabriel_tpo_finalPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TouristService touristService) {
        if (touristService.getSalesWithService() == null) {
            touristService.setSalesWithService(new ArrayList<Sale>());
        }
        if (touristService.getPackagesWithService() == null) {
            touristService.setPackagesWithService(new ArrayList<TouristPackage>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Sale> attachedSalesWithService = new ArrayList<Sale>();
            for (Sale salesWithServiceSaleToAttach : touristService.getSalesWithService()) {
                salesWithServiceSaleToAttach = em.getReference(salesWithServiceSaleToAttach.getClass(), salesWithServiceSaleToAttach.getSaleId());
                attachedSalesWithService.add(salesWithServiceSaleToAttach);
            }
            touristService.setSalesWithService(attachedSalesWithService);
            List<TouristPackage> attachedPackagesWithService = new ArrayList<TouristPackage>();
            for (TouristPackage packagesWithServiceTouristPackageToAttach : touristService.getPackagesWithService()) {
                packagesWithServiceTouristPackageToAttach = em.getReference(packagesWithServiceTouristPackageToAttach.getClass(), packagesWithServiceTouristPackageToAttach.getPackageId());
                attachedPackagesWithService.add(packagesWithServiceTouristPackageToAttach);
            }
            touristService.setPackagesWithService(attachedPackagesWithService);
            em.persist(touristService);
            for (Sale salesWithServiceSale : touristService.getSalesWithService()) {
                TouristService oldTourist_serviceOfSalesWithServiceSale = salesWithServiceSale.getTourist_service();
                salesWithServiceSale.setTourist_service(touristService);
                salesWithServiceSale = em.merge(salesWithServiceSale);
                if (oldTourist_serviceOfSalesWithServiceSale != null) {
                    oldTourist_serviceOfSalesWithServiceSale.getSalesWithService().remove(salesWithServiceSale);
                    oldTourist_serviceOfSalesWithServiceSale = em.merge(oldTourist_serviceOfSalesWithServiceSale);
                }
            }
            for (TouristPackage packagesWithServiceTouristPackage : touristService.getPackagesWithService()) {
                packagesWithServiceTouristPackage.getServicesList().add(touristService);
                packagesWithServiceTouristPackage = em.merge(packagesWithServiceTouristPackage);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TouristService touristService) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TouristService persistentTouristService = em.find(TouristService.class, touristService.getServiceId());
            List<Sale> salesWithServiceOld = persistentTouristService.getSalesWithService();
            List<Sale> salesWithServiceNew = touristService.getSalesWithService();
            List<TouristPackage> packagesWithServiceOld = persistentTouristService.getPackagesWithService();
            List<TouristPackage> packagesWithServiceNew = touristService.getPackagesWithService();
            List<Sale> attachedSalesWithServiceNew = new ArrayList<Sale>();
            for (Sale salesWithServiceNewSaleToAttach : salesWithServiceNew) {
                salesWithServiceNewSaleToAttach = em.getReference(salesWithServiceNewSaleToAttach.getClass(), salesWithServiceNewSaleToAttach.getSaleId());
                attachedSalesWithServiceNew.add(salesWithServiceNewSaleToAttach);
            }
            salesWithServiceNew = attachedSalesWithServiceNew;
            touristService.setSalesWithService(salesWithServiceNew);
            List<TouristPackage> attachedPackagesWithServiceNew = new ArrayList<TouristPackage>();
            for (TouristPackage packagesWithServiceNewTouristPackageToAttach : packagesWithServiceNew) {
                packagesWithServiceNewTouristPackageToAttach = em.getReference(packagesWithServiceNewTouristPackageToAttach.getClass(), packagesWithServiceNewTouristPackageToAttach.getPackageId());
                attachedPackagesWithServiceNew.add(packagesWithServiceNewTouristPackageToAttach);
            }
            packagesWithServiceNew = attachedPackagesWithServiceNew;
            touristService.setPackagesWithService(packagesWithServiceNew);
            touristService = em.merge(touristService);
            for (Sale salesWithServiceOldSale : salesWithServiceOld) {
                if (!salesWithServiceNew.contains(salesWithServiceOldSale)) {
                    salesWithServiceOldSale.setTourist_service(null);
                    salesWithServiceOldSale = em.merge(salesWithServiceOldSale);
                }
            }
            for (Sale salesWithServiceNewSale : salesWithServiceNew) {
                if (!salesWithServiceOld.contains(salesWithServiceNewSale)) {
                    TouristService oldTourist_serviceOfSalesWithServiceNewSale = salesWithServiceNewSale.getTourist_service();
                    salesWithServiceNewSale.setTourist_service(touristService);
                    salesWithServiceNewSale = em.merge(salesWithServiceNewSale);
                    if (oldTourist_serviceOfSalesWithServiceNewSale != null && !oldTourist_serviceOfSalesWithServiceNewSale.equals(touristService)) {
                        oldTourist_serviceOfSalesWithServiceNewSale.getSalesWithService().remove(salesWithServiceNewSale);
                        oldTourist_serviceOfSalesWithServiceNewSale = em.merge(oldTourist_serviceOfSalesWithServiceNewSale);
                    }
                }
            }
            for (TouristPackage packagesWithServiceOldTouristPackage : packagesWithServiceOld) {
                if (!packagesWithServiceNew.contains(packagesWithServiceOldTouristPackage)) {
                    packagesWithServiceOldTouristPackage.getServicesList().remove(touristService);
                    packagesWithServiceOldTouristPackage = em.merge(packagesWithServiceOldTouristPackage);
                }
            }
            for (TouristPackage packagesWithServiceNewTouristPackage : packagesWithServiceNew) {
                if (!packagesWithServiceOld.contains(packagesWithServiceNewTouristPackage)) {
                    packagesWithServiceNewTouristPackage.getServicesList().add(touristService);
                    packagesWithServiceNewTouristPackage = em.merge(packagesWithServiceNewTouristPackage);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = touristService.getServiceId();
                if (findTouristService(id) == null) {
                    throw new NonexistentEntityException("The touristService with id " + id + " no longer exists.");
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
            TouristService touristService;
            try {
                touristService = em.getReference(TouristService.class, id);
                touristService.getServiceId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The touristService with id " + id + " no longer exists.", enfe);
            }
            List<Sale> salesWithService = touristService.getSalesWithService();
            for (Sale salesWithServiceSale : salesWithService) {
                salesWithServiceSale.setTourist_service(null);
                salesWithServiceSale = em.merge(salesWithServiceSale);
            }
            List<TouristPackage> packagesWithService = touristService.getPackagesWithService();
            for (TouristPackage packagesWithServiceTouristPackage : packagesWithService) {
                packagesWithServiceTouristPackage.getServicesList().remove(touristService);
                packagesWithServiceTouristPackage = em.merge(packagesWithServiceTouristPackage);
            }
            em.remove(touristService);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TouristService> findTouristServiceEntities() {
        return findTouristServiceEntities(true, -1, -1);
    }

    public List<TouristService> findTouristServiceEntities(int maxResults, int firstResult) {
        return findTouristServiceEntities(false, maxResults, firstResult);
    }

    private List<TouristService> findTouristServiceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TouristService.class));
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

    public TouristService findTouristService(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TouristService.class, id);
        } finally {
            em.close();
        }
    }

    public int getTouristServiceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TouristService> rt = cq.from(TouristService.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
