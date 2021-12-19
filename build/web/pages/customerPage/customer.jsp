<%@page import="java.util.List"%>
<%@page import="logic.Customer"%>
<%@page import="logic.Controller"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Administración de clientes</title>

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
                    <h2 class="content_title">Clientes</h2>
                    <a href="customerAdd.jsp"><i class="fas fa-plus content_addButton"></i></a>
                </header>

                <div class="table">
                    <table>
                        <tr>
                            <th>Acciones</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Email</th>
                            <th>D.N.I</th>
                            <th>Nacionalidad</th>
                            <th>Fecha de nacimiento</th>
                            <th>Teléfono</th>
                            <th>Dirección</th>
                            <th>Cantidad de compras</th>
                        </tr>
                        <%
                            List<Customer> customersList = (List) mySession.getAttribute("customersList");
                            for (Customer customer : customersList) {
                        %>
                        <tr>
                            <td class="action_cell">

                                <form action="../../SvDeleteCustomer" method="POST">
                                    <input type="hidden" name="userId" value="<%=customer.getUserId()%>">
                                    <button type="submit" class="action_button"><i class="fas fa-trash-alt"></i></button>
                                </form>
                                /
                                <form action="../../SvEditCustomer" method="POST">
                                    <input type="hidden" name="userId" value="<%=customer.getUserId()%>">
                                    <button type="submit" class="action_button"><i class="fas fa-edit"></i></button>
                                </form>

                            </td>          
                            <td><%=customer.getName()%></td>
                            <td><%=customer.getSurname()%></td>
                            <td><%=customer.getEmail()%></td>
                            <td><%=customer.getDni()%></td>
                            <td><%=customer.getNationality()%></td>
                            <td><%=customer.getBirthDate()%></td>
                            <td><%=customer.getPhone()%></td>
                            <td><%=customer.getAddress()%></td>
                            <td><%=customer.getPurchasesQuantity()%></td>
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
