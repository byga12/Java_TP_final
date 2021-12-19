<%@page import="java.text.DateFormat"%> <%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%> <%@page import="logic.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Modificar datos de empleado</title>
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
    Employee toUpdateEmployee = (Employee) mySession.getAttribute("toUpdateEmployee"); 
    //Obtengo la fecha de nacimiento del empleado y la formateo 
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
    String strDate = dateFormat.format(toUpdateEmployee.getBirthDate()); %>
    <div class="container">
      <navigation-component></navigation-component>

      <div class="content">
        <header class="header">
          <h2 class="content_title">Formulario de edición de empleado</h2>
        </header>

        <form action="../../SvEditEmployee" method="GET" class="form">
          <label for="name">Nombre</label
          ><input
            type="text"
            id="name"
            name="name"
            value="<%=toUpdateEmployee.getName()%>"
          />

          <label for="surname">Apellido</label
          ><input
            type="text"
            id="surname"
            name="surname"
            value="<%=toUpdateEmployee.getSurname()%>"
          />

          <label for="address">DirecciÃ³n</label
          ><input
            type="text"
            id="address"
            name="address"
            value="<%=toUpdateEmployee.getAddress()%>"
          />

          <label for="dni">D.N.I</label
          ><input
            type="text"
            id="dni"
            name="dni"
            value="<%=toUpdateEmployee.getDni()%>"
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
            value="<%=toUpdateEmployee.getNationality()%>"
          />

          <label for="phone">TelÃ©fono</label
          ><input
            type="text"
            id="phone"
            name="phone"
            value="<%=toUpdateEmployee.getPhone()%>"
          />

          <label for="email">Email</label
          ><input
            type="text"
            id="email"
            name="email"
            value="<%=toUpdateEmployee.getEmail()%>"
          />

          <label for="job">Puesto</label
          ><input
            type="text"
            id="job"
            name="job"
            value="<%=toUpdateEmployee.getJob()%>"
          />

          <label for="salary">Salario</label
          ><input
            type="text"
            id="salary"
            name="salary"
            value="<%=toUpdateEmployee.getSalary()%>"
          />

          <label for="username">Nombre de usuario</label
          ><input
            type="text"
            id="username"
            name="username"
            value="<%=toUpdateEmployee.getUsername()%>"
          />

          <label for="password">ContraseÃ±a</label
          ><input
            type="password"
            id="password"
            name="password"
            value="<%=toUpdateEmployee.getPassword()%>"
          />

          <input
            type="hidden"
            name="userId"
            value="<%=toUpdateEmployee.getUserId()%>"
          />

          <button type="submit" class="submit_button">Actualizar datos</button>
        </form>
      </div>
    </div>
  </body>
</html>
