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
import logic.Employee;
import logic.Sale;

@WebServlet(name = "SvEditSale", urlPatterns = {"/SvEditSale"})
public class SvEditSale extends HttpServlet {

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
            int saleId = Integer.parseInt(request.getParameter("saleId"));
            String paymentMethod = request.getParameter("paymentMethod");
            Double price = Double.parseDouble(request.getParameter("price"));
            //Para manejar la fecha, utilizo el parser
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(request.getParameter("date"));
            Employee employee = control.getEmployeeById(Integer.parseInt(request.getParameter("employeeId")));
            Customer customer = control.getCustomerById(Integer.parseInt(request.getParameter("customerId")));

            Sale sale = control.getSaleById(saleId);
            sale.setSaleDate(date);
            sale.setPaymentMethod(paymentMethod);
            sale.setPrice(price);
            sale.setEmployee(employee);
            sale.setCustomer(customer);

            //Modifico la venta llamando a la controladora
            control.updateSale(sale);
            //Refresco y redirijo
            request.getSession().setAttribute("salesList", control.getSales());
            response.sendRedirect("pages/salePage/sale.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(SvEditSale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int saleId = Integer.parseInt(request.getParameter("saleId"));
        Sale toUpdateSale = control.getSaleById(saleId);

        HttpSession mySession = request.getSession();
        mySession.setAttribute("toUpdateSale", toUpdateSale);

        response.sendRedirect("pages/salePage/saleEdit.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
