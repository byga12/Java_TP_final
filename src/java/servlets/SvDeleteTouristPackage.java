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

@WebServlet(name = "SvDeleteTouristPackage", urlPatterns = {"/SvDeleteTouristPackage"})
public class SvDeleteTouristPackage extends HttpServlet {

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
            control.deleteTouristPackage(Integer.parseInt(request.getParameter("packageId")));
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SvDeleteTouristPackage.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("pages/touristPackagePage/touristPackage.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
