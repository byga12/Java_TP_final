<%@page import="java.util.List"%>
<%@page import="logic.Controller"%>
<%@page import="java.text.DateFormat"%> 
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%> 
<%@page import="logic.TouristPackage"%>
<%@page import="logic.TouristService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Modificar datos de paquete turístico</title>
        <script
            src="https://kit.fontawesome.com/d33b7f5a1f.js"
            crossorigin="anonymous"
        ></script>
        <link rel="stylesheet" href="../../globals.css" />
        <link href="../../formStyles.css" rel="stylesheet" type="text/css" />
        <script
            src="../../components/navigation.js"
            type="text/javascript"
            defer
        ></script>
    </head>
    <body>
        <% HttpSession mySession = request.getSession();
            String username = (String) mySession.getAttribute("username");
            if (username == null) {
                response.sendRedirect("index.jsp");
            }
            TouristPackage toUpdateTouristPackage = (TouristPackage) mySession.getAttribute("toUpdateTouristPackage");
            //(no cumple modelo de capas) llamo a la controladora de la lógica
            Controller control = new Controller();
            List<TouristService> touristServicesList = control.getTouristServices();
        %>
        <div class="container">
            <navigation-component></navigation-component>

            <div class="content">
                <header class="header">
                    <h2 class="content_title">Formulario de edición de paquete turístico</h2>
                </header>

                <form action="../../SvEditTouristPackage" method="GET" class="form">


                    <h2 class="form_title">Servicios incluidos</h2>
                    <div>
                        <%
                            for (TouristService touristService : touristServicesList) {
                        %>
                        <input
                            type="checkbox"
                            id=<%=touristService.getServiceId()%>
                            name="touristServiceId"
                            value=<%=touristService.getServiceId()%>
                            <%=(toUpdateTouristPackage.hasTouristService(touristService) ? "checked" : "")%>
                            />
                        <label class="form_checkbox_label" for=<%=touristService.getServiceId()%>><%=touristService.getServiceName()%></label><br />
                        <%}%>
                    </div>

                    <input
                        type="hidden"
                        name="packageId"
                        value="<%=toUpdateTouristPackage.getPackageId()%>"
                        />

                    <button type="submit" class="submit_button">Actualizar datos</button>
                </form>
            </div>
        </div>
    </body>
</html>
