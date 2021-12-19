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

@WebServlet(name = "SvDeleteTouristService", urlPatterns = {"/SvDeleteTouristService"})
public class SvDeleteTouristService extends HttpServlet {

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
            control.deleteTouristService(Integer.parseInt(request.getParameter("serviceId")));
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SvDeleteTouristService.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("pages/touristServicePage/touristService.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
