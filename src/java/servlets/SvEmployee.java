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
import logic.Controller;


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
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
 
        try {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String address = request.getParameter("address");
            
            //Para manejar la fecha, utilizo el parser
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-dd-mm");
            Date birthDate = formatter.parse(request.getParameter("birthDate"));
            String nationality = request.getParameter("nationality");
            String phone = request.getParameter("phone");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String job = request.getParameter("job");
            Double salary = Double.parseDouble(request.getParameter("salary"));
            
            control.createEmployee(name, surname, address, birthDate,nationality, phone, username, password, job, salary);
            //redirecciono a otra página
            response.sendRedirect("index.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(SvEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
