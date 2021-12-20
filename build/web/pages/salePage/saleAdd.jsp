<%@page import="logic.Employee"%>
<%@page import="logic.Customer"%>
<%@page import="logic.TouristService"%>
<%@page import="java.util.List"%>
<%@page import="logic.Controller"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Creación de venta</title>
        <script
            src="https://kit.fontawesome.com/d33b7f5a1f.js"
            crossorigin="anonymous"
        ></script>
        <link rel="stylesheet" href="../../globals.css" />
        <link rel="stylesheet" href="../../formStyles.css" />
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
            //(no cumple modelo de capas) llamo a la controladora de la lógica
            Controller control = new Controller();
            List<TouristService> touristServicesList = control.getTouristServices();
            List<Employee> employeesList = control.getEmployees();
            List<Customer> customersList = control.getCustomers();
        %>
        <div class="container">
            <navigation-component></navigation-component>

            <div class="content">
                <header class="header">
                    <h2 class="content_title">
                        Formulario de creación de venta
                    </h2>
                </header>

                <form action="../../SvSale" method="POST" class="form">
                  <label for="saleDate">Fecha</label
                   ><input type="date" id="saleDate" name="saleDate" required />

                  <label for="paymentMethod">Forma de pago</label
                  ><input type="text" id="paymentMethod" name="paymentMethod" required />
                  
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

                  <h2 class="form_title">Servicios</h2>
                    <div>
                        <%
                            for (TouristService touristService : touristServicesList) {
                        %>
                        <input
                            type="checkbox"
                            id=<%=touristService.getServiceId()%>
                            name="touristServiceId"
                            value=<%=touristService.getServiceId()%>
                            />
                        <label class="form_checkbox_label" for=<%=touristService.getServiceId()%>><%=touristService.getServiceName()%></label><br />
                        <%}%>
                    </div>
                    <button type="submit" class="submit_button">Crear venta</button>
                </form>
            </div>
        </div>
    </body>
</html>
