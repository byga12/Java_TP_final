package persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Customer;
import logic.Employee;
import logic.Sale;
import logic.TouristPackage;
import logic.TouristService;
import persistence.exceptions.NonexistentEntityException;

public class PersistenceController {

    //JPA Controllers
    EmployeeJpaController employeeJPA = new EmployeeJpaController();
    CustomerJpaController customerJPA = new CustomerJpaController();
    SaleJpaController saleJPA = new SaleJpaController();
    TouristPackageJpaController touristPackageJPA = new TouristPackageJpaController();
    TouristServiceJpaController touristServiceJPA = new TouristServiceJpaController();

    //Employee methods
    public List<Employee> getEmployees() {
        return employeeJPA.findEmployeeEntities();
    }

    public Employee getEmployeeById(int id) {
        return employeeJPA.findEmployee(id);
    }

    public void createEmployee(Employee employee) {
        employeeJPA.create(employee);
    }

    public void deleteEmployee(int id) throws NonexistentEntityException {
        employeeJPA.destroy(id);
    }

    public void updateEmployee(Employee updatedEmployee) {
        try {
            employeeJPA.edit(updatedEmployee);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Customer methods
    public List<Customer> getCustomers() {
        return customerJPA.findCustomerEntities();
    }

    public Customer getCustomerById(int id) {
        return customerJPA.findCustomer(id);
    }

    public void createCustomer(Customer customer) {
        customerJPA.create(customer);
    }

    public void deleteCustomer(int id) throws NonexistentEntityException {
        customerJPA.destroy(id);
    }

    public void updateCustomer(Customer updatedCustomer) {
        try {
            customerJPA.edit(updatedCustomer);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Sale methods
    public List<Sale> getSales() {
        return saleJPA.findSaleEntities();
    }

    public Sale getSaleById(int id) {
        return saleJPA.findSale(id);
    }

    public void createSale(Sale sale) {
        saleJPA.create(sale);
    }

    public void deleteSale(int id) throws NonexistentEntityException {
        saleJPA.destroy(id);
    }

    public void updateSale(Sale updatedSale) {
        try {
            saleJPA.edit(updatedSale);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //TouristService methods
    public List<TouristService> getTouristServices() {
        return touristServiceJPA.findTouristServiceEntities();
    }

    public TouristService getTouristServiceById(int id) {
        return touristServiceJPA.findTouristService(id);
    }

    public void createTouristService(TouristService touristService) {
        touristServiceJPA.create(touristService);
    }

    public void deleteTouristService(int id) throws NonexistentEntityException {
        touristServiceJPA.destroy(id);
    }

    public void updateTouristService(TouristService updatedTouristService) {
        try {
            touristServiceJPA.edit(updatedTouristService);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //TouristPackage methods
    public List<TouristPackage> getTouristPackages() {
        return touristPackageJPA.findTouristPackageEntities();
    }

    public TouristPackage getTouristPackageById(int id) {
        return touristPackageJPA.findTouristPackage(id);
    }

    public void createTouristPackage(TouristPackage touristPackage) {
        touristPackageJPA.create(touristPackage);
    }

    public void deleteTouristPackage(int id) throws NonexistentEntityException {
        touristPackageJPA.destroy(id);
    }

    public void updateTouristPackage(TouristPackage updatedTouristPackage) {
        try {
            touristPackageJPA.edit(updatedTouristPackage);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
