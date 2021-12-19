<%@page import="java.util.List"%>
<%@page import="logic.TouristService"%>
<%@page import="logic.Controller"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Administración de servicios turísticos</title>

        <script
            src="https://kit.fontawesome.com/d33b7f5a1f.js"
            crossorigin="anonymous"
        ></script>
        <link rel="stylesheet" href="../../globals.css" />
        <link href="../../tableGetStyles.css" rel="stylesheet" type="text/css" />
        <script
            src="../../components/navigation.js"
            type="text/javascript"
            defer
        ></script>
    </head>
    <body>
        <%
            HttpSession mySession = request.getSession();
            String username = (String) mySession.getAttribute("username");
            if (username == null) {
                response.sendRedirect("index.jsp");
            }
        %>
        <div class="container">
            <navigation-component></navigation-component>

            <div class="content">
                <header class="header">
                    <h2 class="content_title">Servicios turísticos</h2>
                    <a href="touristServiceAdd.jsp"><i class="fas fa-plus content_addButton"></i></a>
                </header>

                <div class="table">
                    <table>
                        <tr>
                            <th>Acciones</th>
                            <th>ID de servicio</th>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Destino</th>
                            <th>Fecha</th>
                            <th>Precio</th>
                        </tr>
                        <%
                            List<TouristService> touristServicesList = (List) mySession.getAttribute("touristServicesList");
                            for (TouristService touristService : touristServicesList) {
                        %>
                        <tr>
                            <td class="action_cell">

                                <form action="../../SvDeleteTouristService" method="POST">
                                    <input type="hidden" name="userId" value="<%=touristService.getServiceId()%>">
                                    <button type="submit" class="action_button"><i class="fas fa-trash-alt"></i></button>
                                </form>
                                /
                                <form action="../../SvEditTouristService" method="POST">
                                    <input type="hidden" name="userId" value="<%=touristService.getServiceId()%>">
                                    <button type="submit" class="action_button"><i class="fas fa-edit"></i></button>
                                </form>

                            </td>          
                            <td><%=touristService.getServiceId()%></td>
                            <td><%=touristService.getServiceName()%></td>
                            <td><%=touristService.getServiceDescription()%></td>
                            <td><%=touristService.getServiceDestiny()%></td>
                            <td><%=touristService.getServiceDate()%></td>
                            <td><%=touristService.getServicePrice()%></td>
                        </tr>
                        <%}%>


                        <tr>
                            <td></td>
                        </tr>
                    </table>
                </div>
            </div>

        </div>

    </body>
</html>
