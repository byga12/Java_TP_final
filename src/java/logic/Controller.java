package logic;

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
    //ALTA CLIENTE
    //ELIMINAR CLIENTE
    //MODIFICAR CLIENTE

    //OBTENER VENTAS
    public List<Sale> getSales() {
        return persistence.getSales();
    }
    //OBTENER VENTA POR ID
    //ALTA VENTA
    //ELIMINAR VENTA
    //MODIFICAR VENTA

    //OBTENER PAQUETES TURÍSTICOS
    public List<TouristPackage> getTouristPackages() {
        return persistence.getTouristPackages();
    }
    //OBTENER PAQUETE TURÍßTICO POR ID
    //ALTA PAQUETE TURÍSTICO
    //ELIMINAR PAQUETE TURÍSTICO
    //MODIFICAR PAQUETE TURÍSTICO

    //OBTENER SERVICIOS TURÍSTICOS
    public List<TouristService> getTouristServices() {
        return persistence.getTouristServices();
    }
    //OBTENER SERVICIO TURÍßTICO POR ID
    //ALTA SERVICIO TURÍSTICO
    //ELIMINAR SERVICIO TURÍSTICO
    //MODIFICAR SERVICIO TURÍSTICO

    //Existe un paquete con la lista de servicios dados? devolvemelo, sino devolve null
//    public TouristPackage existingPackage(List<TouristService> services) {
//        TouristPackage packages = persistence.getTouristPackages();
//        for (TouristPackage touristPackage : packages) {
//            if (touristPackage.getServicesList().containsAll(services)) {
//                return touristPackage;
//            }
//        }
//        return null;
//    }
}
