
package persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Employee;
import persistence.exceptions.NonexistentEntityException;


public class PersistenceController {
    
    //JPA Controllers
    EmployeeJpaController employeeJPA = new EmployeeJpaController();
    CustomerJpaController customerJPA = new CustomerJpaController();
    
    public List<Employee> getEmployees(){
        return employeeJPA.findEmployeeEntities();
    }
    
    public Employee getEmployeeById(int id){
        return employeeJPA.findEmployee(id);
    }
    
    public void createEmployee(Employee employee){
        employeeJPA.create(employee);
    }
    
    public void deleteEmployee(int id) throws NonexistentEntityException{
        employeeJPA.destroy(id);
    }
    
    public void updateEmployee(Employee updatedEmployee){
        try {
            employeeJPA.edit(updatedEmployee);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
