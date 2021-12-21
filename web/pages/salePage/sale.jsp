<%@page import="java.util.List"%>
<%@page import="logic.Sale"%>
<%@page import="logic.Employee"%>
<%@page import="logic.Customer"%>
<%@page import="logic.Controller"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Administración de Ventas</title>

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
                    <h2 class="content_title">Ventas</h2>
                    <a href="saleAdd.jsp"><i class="fas fa-plus content_addButton"></i></a>
                </header>

                <div class="table">
                    <table>
                        <tr>
                            <th>Acciones</th>
                            <th>ID de venta</th>
                            <th>Método de pago</th>
                            <th>Fecha</th>
                            <th>Precio</th>
                            <th>Empleado a cargo</th>
                            <th>Cliente (comprador)</th>
                        </tr>
                        <%
                            List<Sale> salesList = (List) mySession.getAttribute("salesList");
                            for (Sale sale : salesList) {
                        %>
                        <tr>
                            <td class="action_cell">

                                <form action="../../SvDeleteSale" method="POST">
                                    <input type="hidden" name="saleId" value="<%=sale.getSaleId()%>">
                                    <button type="submit" class="action_button"><i class="fas fa-trash-alt"></i></button>
                                </form>
                                /
                                <form action="../../SvEditSale" method="POST">
                                    <input type="hidden" name="saleId" value="<%=sale.getSaleId()%>">
                                    <button type="submit" class="action_button"><i class="fas fa-edit"></i></button>
                                </form>

                            </td>
                            <td><%=sale.getSaleId()%></td>          
                            <td><%=sale.getPaymentMethod()%></td>
                            <td><%=sale.getSaleDate()%></td>
                            <td><%=sale.getPrice()%></td>
                            <td><%=(sale.getEmployee() == null ? "(borrado)" : sale.getEmployee().getName())%> 
                                <%=(sale.getEmployee() == null ? " " : sale.getEmployee().getName())%></td>
                            <td>
                                <%=(sale.getCustomer() == null ? "(borrado)" : sale.getCustomer().getName())%> 
                                <%=(sale.getCustomer() == null ? " " : sale.getCustomer().getName())%>
                            </td>
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
