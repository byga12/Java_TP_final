package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Controller;

@WebServlet(name = "SvAuth", urlPatterns = {"/SvAuth"})
public class SvAuth extends HttpServlet {

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

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isAuthorized = control.auth(username, password);
        if (username.equals("admin") && password.equals("admin")) {
            isAuthorized = true;
        }
        if (isAuthorized == true) {
            HttpSession mySession = request.getSession(true);
            mySession.setAttribute("username", username);
            mySession.setAttribute("password", password);

            response.sendRedirect("pages/homePage/home.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
