<%@page import="java.util.List"%>
<%@page import="logic.Controller"%>
<%@page import="java.text.DateFormat"%> 
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%> 
<%@page import="logic.Sale"%>
<%@page import="logic.Customer"%>
<%@page import="logic.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Modificar datos de venta</title>
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
            Sale toUpdateSale = (Sale) mySession.getAttribute("toUpdateSale");
            //Obtengo la fecha de la venta y la formateo
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(toUpdateSale.getSaleDate());
            //(no cumple modelo de capas) llamo a la controladora de la lógica
            Controller control = new Controller();
            List<Employee> employeesList = control.getEmployees();
            List<Customer> customersList = control.getCustomers();
        %>
        <div class="container">
            <navigation-component></navigation-component>

            <div class="content">
                <header class="header">
                    <h2 class="content_title">Formulario de edición de venta</h2>
                </header>

                <form action="../../SvEditSale" method="GET" class="form">
                    <label for="date">Fecha de venta</label
                    ><input
                        type="date"
                        id="date"
                        name="date"
                        value="<%=strDate%>"
                        />

                    <label for="paymentMethod">Forma de pago</label
                    ><input
                        type="text"
                        id="paymentMethod"
                        name="paymentMethod"
                        value="<%=toUpdateSale.getPaymentMethod()%>"
                        />

                    <label for="price">Precio</label
                    ><input
                        type="text"
                        id="price"
                        name="price"
                        value="<%=toUpdateSale.getPrice()%>"
                        />

                    <label for="employeeId">Empleado a cargo</label>
                    <select name="employeeId" id="employeeId">
                        <%
                            for (Employee employee : employeesList) {
                        %>
                        <option value=<%=employee.getUserId()%>
                        <span><%=employee.getUserId()%></span>
                        <span>- </span>
                        <span><%=employee.getName()%></span>
                        <span> </span> 
                        <span><%=employee.getSurname()%></span></option>
                        <%}%>
                    </select>

                    <label for="customerId">Comprador (cliente):</label>
                    <select name="customerId" id="customerId">
                        <%
                            for (Customer customer : customersList) {
                        %>
                        <option value=<%=customer.getUserId()%>
                        <span><%=customer.getUserId()%></span>
                        <span>- </span>
                        <span><%=customer.getName()%></span>
                        <span> </span> 
                        <span><%=customer.getSurname()%></span></option>
                        <%}%>
                    </select>

                    <input
                        type="hidden"
                        name="saleId"
                        value="<%=toUpdateSale.getSaleId()%>"
                        />

                    <button type="submit" class="submit_button">Actualizar datos</button>
                </form>
            </div>
        </div>
    </body>
</html>
