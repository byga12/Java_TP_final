package logic;

import java.util.Date;
import persistence.PersistenceController;

public class Controller { //a.k.a la clase del admin
    
    //Controladora persistencia
    PersistenceController persistence = new PersistenceController();
    
    
    //ALTA EMPLEADO
    public void createEmployee(String name, String surname, String address, Date birthDate, String nationality, String phone, String username, String password, String job, Double salary) {
        Employee employee = new Employee(job, salary, 0, name, surname, address, birthDate, nationality, phone, username, password);
        
        
        persistence.createEmployee(employee);
    }
}
