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
import logic.TouristService;

@WebServlet(name = "SvTouristService", urlPatterns = {"/SvTouristService"})
public class SvTouristService extends HttpServlet {

    //Instancio la clase Controller de la lógica
    Controller control = new Controller();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession mySession = request.getSession();
        List<TouristService> touristServicesList = control.getTouristServices();
        mySession.setAttribute("touristServicesList", touristServicesList);
        response.sendRedirect("pages/touristServicePage/touristService.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String destiny = request.getParameter("destiny");
            //Para manejar la fecha, utilizo el parser
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date serviceDate = formatter.parse(request.getParameter("serviceDate"));
            Double price = Double.parseDouble(request.getParameter("price"));

            control.createTouristService(name, description, destiny, serviceDate, price);
            //Refresco y redirijo
            request.getSession().setAttribute("touristServicesList", control.getTouristServices());
            response.sendRedirect("pages/touristServicePage/touristService.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(SvCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
