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

@WebServlet(name = "SvDeleteSale", urlPatterns = {"/SvDeleteSale"})
public class SvDeleteSale extends HttpServlet {

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
            control.deleteSale(Integer.parseInt(request.getParameter("saleId")));
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(SvDeleteSale.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("pages/salePage/sale.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
