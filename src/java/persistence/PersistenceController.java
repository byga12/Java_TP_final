
package persistence;

import logic.Employee;


public class PersistenceController {
    
    //JPA Controllers
    EmployeeJpaController employeeJPA = new EmployeeJpaController();
    CustomerJpaController customerJPA = new CustomerJpaController();
    
    
    
    public void createEmployee(Employee employee){
        employeeJPA.create(employee);
    }
    
}
