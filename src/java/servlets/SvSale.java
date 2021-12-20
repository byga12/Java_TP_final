package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import logic.Customer;
import logic.Employee;
import logic.Sale;
import logic.TouristPackage;
import logic.TouristService;

@WebServlet(name = "SvSale", urlPatterns = {"/SvSale"})
public class SvSale extends HttpServlet {

    Controller control = new Controller();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession mySession = request.getSession();
        List<Sale> salesList = control.getSales();
        mySession.setAttribute("salesList", salesList);
        response.sendRedirect("pages/salePage/sale.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Para manejar la fecha, utilizo el parser
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date saleDate = formatter.parse(request.getParameter("saleDate"));
            //
            String paymentMethod = request.getParameter("paymentMethod");
            //Para traer el empleado a cargo y el cliente comprador hago lo siguiente: llamo al método getEmployeeById de la controladora y le pasó el id
            Employee employee = control.getEmployeeById(Integer.parseInt(request.getParameter("employeeId")));
            Customer customer = control.getCustomerById(Integer.parseInt(request.getParameter("customerId")));

            //En esta parte se decide cual constructor usar, el de servicio o el de paquete
            //Al llegar la solicitud doPost, obtengo el id de las casillas chequedas
            String arrayIds[] = request.getParameterValues("touristServiceId");
            if (arrayIds.length == 0) {
                return;
            } else if (arrayIds.length == 1) {
                //CONSTRUCTOR SERVICIO ÚNICO
                TouristService service = control.getTouristServiceById(Integer.parseInt(arrayIds[0]));
                control.createSaleWithService(saleDate, paymentMethod, employee, customer, service);
            } else if (arrayIds.length > 1) {
                //CONSTRUCTOR PAQUETE
                //primero me fijo si ya existía
                List<TouristService> servicesChosen = new ArrayList<>();
                for (String arrayId : arrayIds) {
                    int id = Integer.parseInt(arrayId);
                    servicesChosen.add(control.getTouristServiceById(id));
                }
                TouristPackage touristPackage = control.getOrCreatePackage(servicesChosen);
                control.createSaleWithPackage(saleDate, paymentMethod, employee, customer, touristPackage);
            }

            //Refresco y redirijo
            request.getSession().setAttribute("salesList", control.getSales());
            response.sendRedirect("pages/salePage/sale.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(SvSale.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
