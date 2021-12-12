package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Controller;
import logic.Employee;

@WebServlet(name = "SvEditEmployee", urlPatterns = {"/SvEditEmployee"})
public class SvEditEmployee extends HttpServlet {
    Controller control = new Controller();
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            //Obtengo el userId
            int userId = Integer.parseInt(request.getParameter("userId"));
            
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String address = request.getParameter("address");
            String dni = request.getParameter("dni");
            //Para manejar la fecha, utilizo el parser
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-dd-mm");
            Date birthDate = formatter.parse(request.getParameter("birthDate"));
            String nationality = request.getParameter("nationality");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String job = request.getParameter("job");
            Double salary = Double.parseDouble(request.getParameter("salary"));
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            

            
            //Modifico el empleado llamando a la controladora
            control.updateEmployee(userId, name, surname, address, dni, birthDate, nationality, phone, email, job, salary, username, password);
            
            System.out.println("AAAAAAAAAAAAAA");
            response.sendRedirect("pages/employeePage/employee.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(SvEditEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        int userId = Integer.parseInt(request.getParameter("userId"));
        Employee toUpdateEmployee = control.getEmployeeById(userId); 
        
        HttpSession mySession = request.getSession();
        mySession.setAttribute("toUpdateEmployee", toUpdateEmployee);
        
        response.sendRedirect("pages/employeePage/employeeEditForm.jsp");
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
