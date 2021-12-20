<%@page import="java.text.DateFormat"%> 
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%> 
<%@page import="logic.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Modificar datos de cliente</title>
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
    Customer toUpdateCustomer = (Customer) mySession.getAttribute("toUpdateCustomer"); 
    //Obtengo la fecha de nacimiento del cliente y la formateo 
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
    String strDate = dateFormat.format(toUpdateCustomer.getBirthDate()); %>
    <div class="container">
      <navigation-component></navigation-component>

      <div class="content">
        <header class="header">
          <h2 class="content_title">Formulario de edición de cliente</h2>
        </header>

        <form action="../../SvEditCustomer" method="GET" class="form">
          <label for="name">Nombre</label
          ><input
            type="text"
            id="name"
            name="name"
            value="<%=toUpdateCustomer.getName()%>"
          />

          <label for="surname">Apellido</label
          ><input
            type="text"
            id="surname"
            name="surname"
            value="<%=toUpdateCustomer.getSurname()%>"
          />

          <label for="address">Dirección</label
          ><input
            type="text"
            id="address"
            name="address"
            value="<%=toUpdateCustomer.getAddress()%>"
          />

          <label for="dni">D.N.I</label
          ><input
            type="text"
            id="dni"
            name="dni"
            value="<%=toUpdateCustomer.getDni()%>"
          />

          <label for="birthDate">Fecha de nacimiento</label
          ><input
            type="date"
            id="birthDate"
            name="birthDate"
            value="<%=strDate%>"
          />

          <label for="nationality">Nacionalidad</label
          ><input
            type="text"
            id="nationality"
            name="nationality"
            value="<%=toUpdateCustomer.getNationality()%>"
          />

          <label for="phone">Teléfono</label
          ><input
            type="text"
            id="phone"
            name="phone"
            value="<%=toUpdateCustomer.getPhone()%>"
          />

          <label for="email">Email</label
          ><input
            type="text"
            id="email"
            name="email"
            value="<%=toUpdateCustomer.getEmail()%>"
          />

          <input
            type="hidden"
            name="userId"
            value="<%=toUpdateCustomer.getUserId()%>"
          />

          <button type="submit" class="submit_button">Actualizar datos</button>
        </form>
      </div>
    </div>
  </body>
</html>
