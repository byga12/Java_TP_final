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
import logic.Customer;

@WebServlet(name = "SvEditCustomer", urlPatterns = {"/SvEditCustomer"})
public class SvEditCustomer extends HttpServlet {

    //Instancio la clase Controller de la lógica
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
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            Date birthDate = formatter.parse(request.getParameter("birthDate"));
            String nationality = request.getParameter("nationality");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");

            Customer customer = control.getCustomerById(userId);
            customer.setName(name);
            customer.setSurname(surname);
            customer.setAddress(address);
            customer.setDni(dni);
            customer.setBirthDate(birthDate);
            customer.setNationality(nationality);
            customer.setPhone(phone);
            customer.setEmail(email);
            //Modifico el cliente llamando a la controladora
            control.updateCustomer(customer);
            //Refresco y redirijo
            request.getSession().setAttribute("customersList", control.getCustomers());
            response.sendRedirect("pages/customerPage/customer.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(SvEditCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        Customer toUpdateCustomer = control.getCustomerById(userId);

        HttpSession mySession = request.getSession();
        mySession.setAttribute("toUpdateCustomer", toUpdateCustomer);

        response.sendRedirect("pages/customerPage/customerEdit.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
