package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

@WebServlet(name = "SvEmployee", urlPatterns = {"/SvEmployee"})
public class SvEmployee extends HttpServlet {

    //Instancio la clase Controller de la lógica
    Controller control = new Controller();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession mySession = request.getSession();
        List<Employee> employeesList = control.getEmployees();
        mySession.setAttribute("employeesList", employeesList);
        response.sendRedirect("pages/employeePage/employee.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String address = request.getParameter("address");
            String dni = request.getParameter("dni");
            //Para manejar la fecha, utilizo el parser
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-mm");
            Date birthDate = formatter.parse(request.getParameter("birthDate"));
            String nationality = request.getParameter("nationality");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String job = request.getParameter("job");
            Double salary = Double.parseDouble(request.getParameter("salary"));

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            control.createEmployee(name, surname, address, dni, birthDate, nationality, phone, email, job, salary, username, password);
            //redirecciono a otra página
            response.sendRedirect("pages/homePage/home.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(SvEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
