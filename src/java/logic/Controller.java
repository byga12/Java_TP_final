package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    public void updateEmployee(int id, String name, String surname, String address, String dni, Date birthDate, String nationality, String phone, String email, String job, Double salary, String username, String password) {
        Employee updatedEmployee = new Employee(job, salary, username, password, id, name, surname, address, dni, nationality, phone, email, birthDate);
        persistence.updateEmployee(updatedEmployee);
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

    //OBTENER VENTAS
    public List<Sale> getSales() {
        return persistence.getSales();
    }

    //OBTENER VENTA POR ID
    //ALTA VENTA CON SERVICIO ÚNICO
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

    //OBTENER SERVICIOS TURÍSTICOS
    public List<TouristService> getTouristServices() {
        return persistence.getTouristServices();
    }

    //OBTENER SERVICIO TURÍßTICO POR ID
    public TouristService getTouristServiceById(int id) {
        return persistence.getTouristServiceById(id);
    }

    //ALTA SERVICIO TURÍSTICO
    public void createTouristService(String name, String description, String destiny, Date serviceDate, double price) {
        TouristService touristService = new TouristService(0, name, description, destiny, serviceDate, price);
        persistence.createTouristService(touristService);
    }

    //ELIMINAR SERVICIO TURÍSTICO
    public void deleteTouristService(int id) throws NonexistentEntityException {
        persistence.deleteTouristService(id);
    }
    //MODIFICAR SERVICIO TURÍSTICO

    //OBTENER PAQUETES TURÍSTICOS
    public List<TouristPackage> getTouristPackages() {
        return persistence.getTouristPackages();
    }

    //OBTENER PAQUETE TURÍßTICO POR ID
    //ALTA PAQUETE TURÍSTICO
    public void createTouristPackage(List<TouristService> servicesList) {
        TouristPackage touristPackage = new TouristPackage(0, servicesList);
        persistence.createTouristPackage(touristPackage);
    }

    //ELIMINAR PAQUETE TURÍSTICO
    public void deleteTouristPackage(int id) throws NonexistentEntityException {
        persistence.deleteTouristPackage(id);
    }
    //MODIFICAR PAQUETE TURÍSTICO

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
}
