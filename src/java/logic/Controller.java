package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import persistence.PersistenceController;
import persistence.exceptions.NonexistentEntityException;

public class Controller { //a.k.a la clase del admin

    //Controladora persistencia
    PersistenceController persistence = new PersistenceController();

    //AUTENTICAR DATOS
    public boolean auth(String username, String password) {
        List<Employee> employeeList = persistence.getEmployees();
        
        if (employeeList != null) {
            for (Employee employee : employeeList) {
                if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    //OBTENER EMPLEADOS
    public List<Employee> getEmployees() {
        return persistence.getEmployees();
    }

    //OBTENER EMPLEADO POR ID
    public Employee getEmployeeById(int id) {
        return persistence.getEmployeeById(id);
    }

    //ALTA EMPLEADO
    public void createEmployee(String name, String surname, String address, String dni, Date birthDate, String nationality, String phone, String email, String job, Double salary, String username, String password) {
        Employee employee = new Employee(job, salary, username, password, 0, name, surname, address, dni, nationality, phone, email, birthDate);
        persistence.createEmployee(employee);
    }

    //ELIMINAR EMPLEADO
    public void deleteEmployee(int id) throws NonexistentEntityException {
        persistence.deleteEmployee(id);
    }

    //MODIFICAR EMPLEADO
    public void updateEmployee(Employee employee) {
        persistence.updateEmployee(employee);
    }

    //OBTENER CLIENTES
    public List<Customer> getCustomers() {
        return persistence.getCustomers();
    }

    //OBTENER CLIENTE POR ID
    public Customer getCustomerById(int id) {
        return persistence.getCustomerById(id);
    }

    //ALTA CLIENTE
    public void createCustomer(String name, String surname, String address, String dni, Date birthDate, String nationality, String phone, String email) {
        Customer customer = new Customer(0, name, surname, address, dni, nationality, phone, email, birthDate);
        persistence.createCustomer(customer);
    }

    //ELIMINAR CLIENTE
    public void deleteCustomer(int id) throws NonexistentEntityException {
        persistence.deleteCustomer(id);
    }

    //MODIFICAR CLIENTE
    public void updateCustomer(Customer customer) {
        persistence.updateCustomer(customer);
    }

    //OBTENER VENTAS
    public List<Sale> getSales() {
        return persistence.getSales();
    }

    //OBTENER VENTA POR ID
    public Sale getSaleById(int id) {
        return persistence.getSaleById(id);
    }

    //ALTA VENTA CON SERVICIO ??NICO
    public void createSaleWithService(Date saleDate, String paymentMethod, Employee employee, Customer customer, TouristService service) {
        Sale saleWithService = new Sale(0, saleDate, paymentMethod, employee, customer, service);
        persistence.createSale(saleWithService);
    }

    //ALTA VENTA CON PAQUETE
    public void createSaleWithPackage(Date saleDate, String paymentMethod, Employee employee, Customer customer, TouristPackage touristPackage) {
        Sale saleWithPackage = new Sale(0, saleDate, paymentMethod, employee, customer, touristPackage);
        persistence.createSale(saleWithPackage);
    }

    //ELIMINAR VENTA
    public void deleteSale(int id) throws NonexistentEntityException {
        persistence.deleteSale(id);
    }

    //MODIFICAR VENTA
    public void updateSale(Sale sale) {
        persistence.updateSale(sale);
    }

    //OBTENER SERVICIOS TUR??STICOS
    public List<TouristService> getTouristServices() {
        return persistence.getTouristServices();
    }

    //OBTENER SERVICIO TUR????TICO POR ID
    public TouristService getTouristServiceById(int id) {
        return persistence.getTouristServiceById(id);
    }

    //ALTA SERVICIO TUR??STICO
    public void createTouristService(String name, String description, String destiny, Date serviceDate, double price) {
        TouristService touristService = new TouristService(0, name, description, destiny, serviceDate, price);
        persistence.createTouristService(touristService);
    }

    //ELIMINAR SERVICIO TUR??STICO
    public void deleteTouristService(int id) throws NonexistentEntityException {
        persistence.deleteTouristService(id);
    }

    //MODIFICAR SERVICIO TUR??STICO
    public void updateTouristService(TouristService touristService) {
        persistence.updateTouristService(touristService);
    }

    //OBTENER PAQUETES TUR??STICOS
    public List<TouristPackage> getTouristPackages() {
        return persistence.getTouristPackages();
    }

    //OBTENER PAQUETE TUR????TICO POR ID
    public TouristPackage getTouristPackageById(int id) {
        return persistence.getTouristPackageById(id);
    }

    //ALTA PAQUETE TUR??STICO
    public void createTouristPackage(List<TouristService> servicesList) {
        TouristPackage touristPackage = new TouristPackage(0, servicesList);
        persistence.createTouristPackage(touristPackage);
    }

    //ELIMINAR PAQUETE TUR??STICO
    public void deleteTouristPackage(int id) throws NonexistentEntityException {
        persistence.deleteTouristPackage(id);
    }

    //MODIFICAR PAQUETE TUR??STICO
    public void updateTouristPackage(TouristPackage touristPackage) {
        persistence.updateTouristPackage(touristPackage);
    }

    //Existe un paquete con la lista de servicios dados? devolvemelo, sino crea uno y retornalo
    public TouristPackage getOrCreatePackage(List<TouristService> services) {
        List<TouristPackage> packages = persistence.getTouristPackages();

        ////Obtengo array de ids de la lista servicio
        int arrayA[] = new int[services.size()];
        for (int i = 0; i < services.size(); i++) {
            arrayA[i] = services.get(i).getServiceId();
        }
        /////

        for (TouristPackage tp : packages) {
            int arrayB[] = new int[tp.getServicesList().size()];
            for (int i = 0; i < tp.getServicesList().size(); i++) {
                
                arrayB[i] = tp.getServicesList().get(i).getServiceId();
            }
            
            if (Arrays.equals(arrayA, arrayB)) {
                return tp;
            }
        }
        
        TouristPackage newTouristPackage = new TouristPackage(0, services);
        persistence.createTouristPackage(newTouristPackage);
        return newTouristPackage;
    }

    //Existe un paquete con la lista de servicios dados? devolvemelo, sino actualiza el existente
    public void getOrUpdatePackage(List<TouristService> services, int packageToUpdateId) {
        List<TouristPackage> packages = persistence.getTouristPackages();

        ////Obtengo array de ids de la lista servicio
        int arrayA[] = new int[services.size()];
        for (int i = 0; i < services.size(); i++) {
            arrayA[i] = services.get(i).getServiceId();
        }
        /////

        for (TouristPackage tp : packages) {
            int arrayB[] = new int[tp.getServicesList().size()];
            for (int i = 0; i < tp.getServicesList().size(); i++) {
                
                arrayB[i] = tp.getServicesList().get(i).getServiceId();
            }
            
            if (Arrays.equals(arrayA, arrayB)) {
                return;
            }
        }
        TouristPackage updated = persistence.getTouristPackageById(packageToUpdateId);
        updated.setServicesList(services);
        persistence.updateTouristPackage(updated);
    }
}
