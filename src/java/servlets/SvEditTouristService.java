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
import logic.TouristService;

@WebServlet(name = "SvEditTouristService", urlPatterns = {"/SvEditTouristService"})
public class SvEditTouristService extends HttpServlet {

    //Instancio la clase Controller de la lógica
    Controller control = new Controller();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Obtengo el serviceId
            int serviceId = Integer.parseInt(request.getParameter("serviceId"));

            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String destiny = request.getParameter("destiny");
            //Para manejar la fecha, utilizo el parser
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(request.getParameter("date"));
            Double price = Double.parseDouble(request.getParameter("price"));

            TouristService touristService = control.getTouristServiceById(serviceId);
            touristService.setServiceName(name);
            touristService.setServiceDescription(description);
            touristService.setServiceDestiny(destiny);
            touristService.setServiceDate(date);
            touristService.setServicePrice(price);

            //Modifico el servicio llamando a la controladora
            control.updateTouristService(touristService);
            //Refresco y redirijo
            request.getSession().setAttribute("touristServicesList", control.getTouristServices());
            response.sendRedirect("pages/touristServicePage/touristService.jsp");
        } catch (ParseException ex) {
            Logger.getLogger(SvEditTouristService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        TouristService toUpdateTouristService = control.getTouristServiceById(serviceId);

        HttpSession mySession = request.getSession();
        mySession.setAttribute("toUpdateTouristService", toUpdateTouristService);

        response.sendRedirect("pages/touristServicePage/touristServiceEdit.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
