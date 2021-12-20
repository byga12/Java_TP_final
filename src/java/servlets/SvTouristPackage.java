package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Controller;
import logic.TouristPackage;
import logic.TouristService;

@WebServlet(name = "SvTouristPackage", urlPatterns = {"/SvTouristPackage"})
public class SvTouristPackage extends HttpServlet {

    //Instancio la clase Controller de la lógica
    Controller control = new Controller();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession mySession = request.getSession();
        List<TouristPackage> touristPackagesList = control.getTouristPackages();
        mySession.setAttribute("touristPackagesList", touristPackagesList);
        response.sendRedirect("pages/touristPackagePage/touristPackage.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Al llegar la solicitud doPost, obtengo el id de las casillas chequedas
        String arrayIds[] = request.getParameterValues("touristServiceId");
        //Una vez que tengo el id, primero me fijo que el paquete tenga más de un servicio, caso contrario retorno
        if (arrayIds.length == 1) {
            return;
        }
        //Creo una nueva lista de servicios
        List<TouristService> servicesList = new ArrayList<>();
        //Recorro ese array de IDs, convirtiendo cada uno a número, obtengo el servicio y lo añado a mi lista de servicios.
        for (String arrayId : arrayIds) {
            int id = Integer.parseInt(arrayId);
            servicesList.add(control.getTouristServiceById(id));
        }
        //Con el siguiente método me fijo si ya hay un paquete de servicios con los mismos paquetes que se seleccionaron y de ya haber uno, no creo ningún paquete.
        //Caso contrario, creo un paquete nuevo.
        control.getOrCreatePackage(servicesList);
        //Refresco y redirijo
        request.getSession().setAttribute("touristPackagesList", control.getTouristPackages());
        response.sendRedirect("pages/touristPackagePage/touristPackage.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
