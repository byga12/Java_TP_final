package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Controller;
import persistence.exceptions.NonexistentEntityException;

@WebServlet(name = "SvDeleteEmployee", urlPatterns = {"/SvDeleteEmployee"})
public class SvDeleteEmployee extends HttpServlet {

    //Instancio la clase Controller de la lógica
    Controller control = new Controller();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            control.deleteEmployee(Integer.parseInt(request.getParameter("userId")));
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SvDeleteEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Refresco y redirijo
        request.getSession().setAttribute("employeesList", control.getEmployees());
        response.sendRedirect("pages/employeePage/employee.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
